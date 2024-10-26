package com.pdi.spring.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public record Pessoa(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id,
                     @Column(nullable = false) @NotBlank String nome,
                     @Column(nullable = false) @NotBlank String cpf,
                     @Column(nullable = false) @NotBlank LocalDate dataNascimento) {


}
