package com.store.user.services;

import com.store.user.models.dtos.address.AddressDTO;
import com.store.user.models.dtos.cep.CepDTO;

import java.util.List;

public interface CepService {

    List<CepDTO> getCep(List<AddressDTO> cep);
}
