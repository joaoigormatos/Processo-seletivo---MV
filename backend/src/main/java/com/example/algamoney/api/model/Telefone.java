package com.example.algamoney.api.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Telefone {
    private String celular;
    private String residencial;
}
