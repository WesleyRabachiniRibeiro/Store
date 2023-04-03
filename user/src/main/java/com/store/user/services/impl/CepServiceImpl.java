package com.store.user.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.user.config.clients.CepFeign;
import com.store.user.models.dtos.address.AddressDTO;
import com.store.user.models.dtos.cep.CepDTO;
import com.store.user.services.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CepServiceImpl implements CepService {

    @Autowired
    private CepFeign cepFeign;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<CepDTO> getCep(List<AddressDTO> addresses) {
        List<CepDTO> cepList = new ArrayList<>();
        addresses.forEach(address -> {
            CepDTO cepDTO;
            try {
                cepDTO = objectMapper.readValue(cepFeign.searchCep(address.getCep()), CepDTO.class);
                cepList.add(cepDTO);
                if(cepDTO.getPlace() == null) {
                    throw new RuntimeException("Invalid CEP");
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        return cepList;
    }

}
