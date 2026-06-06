package br.uniesp.si.iespflix.services;

import br.uniesp.si.iespflix.dtos.request.FilmeRequestDTO;
import br.uniesp.si.iespflix.dtos.response.FilmeResponseDTO;
import br.uniesp.si.iespflix.exceptions.ResourceNotFoundException;
import br.uniesp.si.iespflix.mappers.FilmeMapper;
import br.uniesp.si.iespflix.models.Filme;
import br.uniesp.si.iespflix.repositories.FilmeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmeService {

    private final FilmeRepository filmeRepository;
    private final FilmeMapper filmeMapper;

    @Transactional(readOnly = true)
    public List<FilmeResponseDTO> listarTodos() {
        return filmeRepository.findAllOrderByTituloAsc().stream()
                .map(filmeMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FilmeResponseDTO buscarPorId(UUID id) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conteúdo não encontrado com o ID fornecido."));
        return filmeMapper.toResponseDTO(filme);
    }

    @Transactional
    public FilmeResponseDTO criar(FilmeRequestDTO requestDTO) {
        log.info("Processando criação de novo conteúdo: {}", requestDTO.titulo());
        Filme filme = filmeMapper.toEntity(requestDTO);
        filme = filmeRepository.save(filme);
        return filmeMapper.toResponseDTO(filme);
    }

    @Transactional
    public FilmeResponseDTO atualizar(UUID id, FilmeRequestDTO requestDTO) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conteúdo não encontrado para atualização."));

        filme.setTitulo(requestDTO.titulo());
        filme.setTipo(requestDTO.tipo());
        filme.setAno(requestDTO.ano());
        filme.setDuracaoMinutos(requestDTO.duracaoMinutos());
        filme.setRelevancia(requestDTO.relevancia());
        filme.setSinopse(requestDTO.sinopse());
        filme.setGenero(requestDTO.genero());

        filme = filmeRepository.save(filme);
        return filmeMapper.toResponseDTO(filme);
    }

    @Transactional
    public void excluir(UUID id) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conteúdo não encontrado para exclusão."));
        filmeRepository.delete(filme);
        log.info("Conteúdo {} excluído com sucesso do catálogo.", id);
    }
}