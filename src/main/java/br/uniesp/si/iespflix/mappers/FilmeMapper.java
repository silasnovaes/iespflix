package br.uniesp.si.iespflix.mappers;

import br.uniesp.si.iespflix.dtos.request.FilmeRequestDTO;
import br.uniesp.si.iespflix.dtos.response.FilmeResponseDTO;
import br.uniesp.si.iespflix.models.Filme;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FilmeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(target = "atualizadoEm", ignore = true)
    Filme toEntity(FilmeRequestDTO requestDTO);

    FilmeResponseDTO toResponseDTO(Filme filme);
}