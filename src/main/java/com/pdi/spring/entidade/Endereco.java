package com.pdi.spring.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public record Endereco(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
                       @Column(nullable = false) @NotBlank String rua,
                       @Column(nullable = false) @NotBlank int numero,
                       @Column(nullable = false) @NotBlank String cidade,
                       @Column(nullable = false) @NotBlank String bairro,
                       @Column(nullable = false) @NotBlank String estado,
                       @Column(nullable = false) @NotBlank int cep) {

}
