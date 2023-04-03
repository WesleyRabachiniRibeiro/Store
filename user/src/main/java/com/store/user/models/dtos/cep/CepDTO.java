package com.store.user.models.dtos.cep;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CepDTO {

    private String cep;

    @JsonProperty("logradouro")
    private String place;

    @JsonProperty("complemento")
    private String complement;

    @JsonProperty("bairro")
    private String neighborhood;

    @JsonProperty("localidade")
    private String locale;

    private String uf;

    private String ibge;

    private String gia;

    private String ddd;

    private String siafi;
}
