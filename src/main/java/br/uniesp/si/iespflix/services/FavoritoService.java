package br.uniesp.si.iespflix.services;

import br.uniesp.si.iespflix.exceptions.ResourceNotFoundException;
import br.uniesp.si.iespflix.models.Favorito;
import br.uniesp.si.iespflix.models.FavoritoId;
import br.uniesp.si.iespflix.models.Filme;
import br.uniesp.si.iespflix.models.Usuario;
import br.uniesp.si.iespflix.repositories.FavoritoRepository;
import br.uniesp.si.iespflix.repositories.FilmeRepository;
import br.uniesp.si.iespflix.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;
    private final UsuarioRepository usuarioRepository;
    private final FilmeRepository filmeRepository;

    @Transactional
    public void adicionar(UUID usuarioId, UUID filmeId) {
        log.info("Processando adição de favorito. Usuario: {}, Filme: {}", usuarioId, filmeId);

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));

        Filme filme = filmeRepository.findById(filmeId)
                .orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado no catálogo."));

        FavoritoId id = new FavoritoId(usuarioId, filmeId);

        if (!favoritoRepository.existsById(id)) {
            Favorito favorito = Favorito.builder()
                    .id(id)
                    .usuario(usuario)
                    .filme(filme)
                    .build();
            favoritoRepository.save(favorito);
        } else {
            log.warn("O filme {} já está na lista de favoritos do usuário {}", filmeId, usuarioId);
        }
    }

    @Transactional
    public void remover(UUID usuarioId, UUID filmeId) {
        FavoritoId id = new FavoritoId(usuarioId, filmeId);
        if (favoritoRepository.existsById(id)) {
            favoritoRepository.deleteById(id);
            log.info("Favorito removido com sucesso.");
        }
    }
}