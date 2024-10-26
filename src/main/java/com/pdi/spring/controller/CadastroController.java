package com.pdi.spring.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.OK;

import com.pdi.spring.entidade.Cadastro;
import com.pdi.spring.entidade.Endereco;
import com.pdi.spring.entidade.Pessoa;
import com.pdi.spring.servico.CadastroService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "v1/cadastro")
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;

    @ApiResponse(responseCode = "302", description = "Consultar um cadastro via CPF ")
    @GetMapping(value = "/consultar/{cpf}")
    @ResponseStatus(code = FOUND)
    public Optional<Cadastro> consultarCadastro(@PathVariable @NotEmpty String cpf){
        return cadastroService.consultar(cpf);
    }

    @ApiResponse(responseCode = "201", description = "Cria um novo cadastro com pessoa e endereço")
    @PostMapping(value = "/cadastrar")
    @ResponseStatus(code = CREATED)
    public ResponseEntity<Cadastro> criarCadastro(@NotEmpty @RequestBody Pessoa pessoa,
        @NotEmpty @RequestBody Endereco endereco){
        return cadastroService.cadastrar(pessoa, endereco);
    }

    @ApiResponse(responseCode = "202", description = "Atualizar cadastro via ID do cadastro")
    @PatchMapping(value = "/atualizar/{id}")
    @ResponseStatus(code = ACCEPTED)
    public ResponseEntity<String> atualizarCadastro(@PathVariable @Valid long id, @RequestBody Pessoa pessoa,
        @RequestBody Endereco endereco){
        return cadastroService.atualizar(id, pessoa, endereco);
    }

    @ApiResponse(responseCode = "200", description = "Deletetar cadastro via ID")
    @DeleteMapping(value = "/deletar/{id}")
    @ResponseStatus(value = OK)
    public ResponseEntity<String> deletarCadastro(@PathVariable @Valid long id){
        return cadastroService.deletar(id);
    }
}
