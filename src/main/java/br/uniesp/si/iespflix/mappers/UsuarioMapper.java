package br.uniesp.si.iespflix.mappers;

import br.uniesp.si.iespflix.dtos.request.UsuarioRequestDTO;
import br.uniesp.si.iespflix.dtos.response.UsuarioResponseDTO;
import br.uniesp.si.iespflix.models.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "senhaHash", ignore = true) // A senha será criptografada e mapeada manualmente na Service
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(target = "atualizadoEm", ignore = true)
    Usuario toEntity(UsuarioRequestDTO requestDTO);

    UsuarioResponseDTO toResponseDTO(Usuario usuario);
}