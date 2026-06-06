package br.uniesp.si.iespflix.services;

import br.uniesp.si.iespflix.dtos.request.UsuarioRequestDTO;
import br.uniesp.si.iespflix.dtos.response.UsuarioResponseDTO;
import br.uniesp.si.iespflix.exceptions.BusinessRuleException;
import br.uniesp.si.iespflix.exceptions.ResourceNotFoundException;
import br.uniesp.si.iespflix.mappers.UsuarioMapper;
import br.uniesp.si.iespflix.models.Endereco;
import br.uniesp.si.iespflix.models.Usuario;
import br.uniesp.si.iespflix.repositories.EnderecoRepository;
import br.uniesp.si.iespflix.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final UsuarioMapper usuarioMapper;
    private final IntegracaoEnderecoService integracaoEnderecoService;

    @Transactional
    public UsuarioResponseDTO criar(UsuarioRequestDTO requestDTO) {
        log.info("Validando regras de negócio para criação do usuário: {}", requestDTO.email());

        if (usuarioRepository.findByEmail(requestDTO.email()).isPresent()) {
            throw new BusinessRuleException("Conflito: Já existe um usuário cadastrado com este e-mail.");
        }

        if (requestDTO.cpfCnpj() != null && usuarioRepository.findByCpfCnpj(requestDTO.cpfCnpj()).isPresent()) {
            throw new BusinessRuleException("Conflito: Já existe um usuário cadastrado com este CPF/CNPJ.");
        }

        Usuario usuario = usuarioMapper.toEntity(requestDTO);

        // Simulação estática de Bcrypt para manter o escopo enxuto (sem starter-security)
        usuario.setSenhaHash("$2a$10$simulatedBcryptHash" + requestDTO.senha().hashCode());

        usuario = usuarioRepository.save(usuario);

        Endereco endereco = integracaoEnderecoService.buscarEPreencherEndereco(requestDTO.cep(), requestDTO.ddd());
        if (endereco.getEstado() != null) {
            endereco.setUsuario(usuario);
            enderecoRepository.save(endereco);
            log.info("Endereço do usuário {} persistido com sucesso no banco de dados.", usuario.getId());
        }

        return usuarioMapper.toResponseDTO(usuario);
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarPorId(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID fornecido."));
        return usuarioMapper.toResponseDTO(usuario);
    }

    @Transactional(readOnly = true)
    public Page<UsuarioResponseDTO> listarTodos(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(usuarioMapper::toResponseDTO);
    }

    @Transactional
    public UsuarioResponseDTO atualizar(UUID id, UsuarioRequestDTO requestDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado para atualização."));

        usuario.setNomeCompleto(requestDTO.nomeCompleto());
        usuario.setDataNascimento(requestDTO.dataNascimento());
        usuario.setPerfil(requestDTO.perfil());

        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toResponseDTO(usuario);
    }
}