package br.uniesp.si.iespflix.clients;

import br.uniesp.si.iespflix.dtos.response.BrasilApiResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "brasilApiClient", url = "${iespflix.integracoes.brasilapi.url}")
public interface BrasilApiClient {

    @GetMapping("/ddd/v1/{ddd}")
    BrasilApiResponseDTO buscarPorDdd(@PathVariable("ddd") String ddd);
}