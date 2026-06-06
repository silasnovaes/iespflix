package br.uniesp.si.iespflix.services;

import br.uniesp.si.iespflix.clients.BrasilApiClient;
import br.uniesp.si.iespflix.clients.ViaCepClient;
import br.uniesp.si.iespflix.dtos.response.BrasilApiResponseDTO;
import br.uniesp.si.iespflix.dtos.response.ViaCepResponseDTO;
import br.uniesp.si.iespflix.models.Endereco;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IntegracaoEnderecoService {

    private final ViaCepClient viaCepClient;
    private final BrasilApiClient brasilApiClient;

    public Endereco buscarEPreencherEndereco(String cep, String ddd) {
        Endereco endereco = new Endereco();

        if (cep != null && !cep.isBlank()) {
            try {
                log.info("Iniciando busca de endereço via Feign para o CEP: {}", cep);
                ViaCepResponseDTO response = viaCepClient.buscarPorCep(cep);

                if (response.erro() == null || !response.erro()) {
                    endereco.setCep(response.cep());
                    endereco.setLogradouro(response.logradouro());
                    endereco.setBairro(response.bairro());
                    endereco.setCidade(response.localidade());
                    endereco.setEstado(response.uf());
                } else {
                    log.warn("ViaCEP retornou flag de erro para o CEP: {}", cep);
                }
            } catch (Exception e) {
                log.error("Falha na integração com ViaCEP: {}", e.getMessage());
            }
        } else if (ddd != null && !ddd.isBlank()) {
            try {
                log.info("Iniciando busca de região via Feign para o DDD: {}", ddd);
                BrasilApiResponseDTO response = brasilApiClient.buscarPorDdd(ddd);

                if (response != null && response.state() != null) {
                    endereco.setEstado(response.state());
                    endereco.setDdd(ddd);
                    if (response.cities() != null && !response.cities().isEmpty()) {
                        endereco.setCidade(response.cities().get(0));
                    }
                }
            } catch (Exception e) {
                // A BrasilAPI não retorna flag "erro", ela devolve 404/400 que cai direto aqui.
                log.error("Falha na integração com BrasilAPI para o DDD {}: {}", ddd, e.getMessage());
            }
        }

        return endereco;
    }
}