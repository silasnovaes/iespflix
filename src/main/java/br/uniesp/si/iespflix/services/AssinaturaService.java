package br.uniesp.si.iespflix.services;

import br.uniesp.si.iespflix.dtos.request.AssinaturaRequestDTO;
import br.uniesp.si.iespflix.exceptions.BusinessRuleException;
import br.uniesp.si.iespflix.exceptions.ResourceNotFoundException;
import br.uniesp.si.iespflix.models.Assinatura;
import br.uniesp.si.iespflix.models.Plano;
import br.uniesp.si.iespflix.models.Usuario;
import br.uniesp.si.iespflix.repositories.AssinaturaRepository;
import br.uniesp.si.iespflix.repositories.PlanoRepository;
import br.uniesp.si.iespflix.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssinaturaService {

    private final AssinaturaRepository assinaturaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PlanoRepository planoRepository;

    @Transactional
    public Assinatura criar(AssinaturaRequestDTO requestDTO) {
        Usuario usuario = usuarioRepository.findById(requestDTO.usuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado. O ID fornecido é inválido."));

        Plano plano = planoRepository.findByCodigo(requestDTO.codigoPlano())
                .orElseThrow(() -> new ResourceNotFoundException("Código de plano inválido ou não suportado."));

        Assinatura assinatura = Assinatura.builder()
                .usuario(usuario)
                .plano(plano)
                .status("ATIVA")
                .iniciadaEm(LocalDateTime.now())
                .build();

        return assinaturaRepository.save(assinatura);
    }

    @Transactional
    public void cancelar(UUID id) {
        Assinatura assinatura = assinaturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assinatura não localizada na base de dados."));

        if ("CANCELADA".equals(assinatura.getStatus())) {
            throw new BusinessRuleException("A assinatura informada já encontra-se cancelada.");
        }

        assinatura.setStatus("CANCELADA");
        assinatura.setCanceladaEm(LocalDateTime.now());
        assinaturaRepository.save(assinatura);
    }
}