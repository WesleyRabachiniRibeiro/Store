package com.store.user.config.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Cep", url = "viacep.com.br/ws/")
public interface CepFeign {

    @GetMapping("/{cep}/json/")
    String searchCep(@PathVariable String cep);
}
