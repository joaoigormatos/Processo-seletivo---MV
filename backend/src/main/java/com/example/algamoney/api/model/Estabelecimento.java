package com.example.algamoney.api.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "estabelecimento")
@Data
public class Estabelecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;


    private String nome;
    @NonNull
    private String endereco;
    @NonNull
    private String telefone;

    public Estabelecimento() {

    }
}
