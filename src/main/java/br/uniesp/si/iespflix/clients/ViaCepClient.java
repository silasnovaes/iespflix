package br.uniesp.si.iespflix.clients;

import br.uniesp.si.iespflix.dtos.response.ViaCepResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient
        (name = "viaCepClient", url = "${viacep.url:https://viacep.com.br/ws}")
public interface ViaCepClient {

    @GetMapping("/{cep}/json/")
    ViaCepResponseDTO buscarPorCep(@PathVariable("cep") String cep);
}