package br.uniesp.si.iespflix.mappers;

import br.uniesp.si.iespflix.dtos.response.PlanoResponseDTO;
import br.uniesp.si.iespflix.models.Plano;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlanoMapper {
    PlanoResponseDTO toResponseDTO(Plano plano);
}