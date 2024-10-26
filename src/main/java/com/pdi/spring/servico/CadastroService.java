package com.pdi.spring.servico;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import com.pdi.spring.entidade.Cadastro;
import com.pdi.spring.entidade.Endereco;
import com.pdi.spring.entidade.Pessoa;
import com.pdi.spring.repositorio.CadastroRepositorio;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastroService {

    CadastroRepositorio repositorio;

    public Optional<Cadastro> consultar(String cpf){
        return repositorio.findCadastroByPessoa_Cpf(cpf);
    }

    public ResponseEntity<Cadastro> cadastrar(Pessoa pessoa, Endereco endereco){
        Cadastro novoCadastro = Cadastro.criarNovoCadastro(pessoa, endereco);
        repositorio.save(novoCadastro);
        return ResponseEntity.status(CREATED)
            .body(novoCadastro);
    }

    public ResponseEntity<String> atualizar(long id, Pessoa pessoa, Endereco endereco){
        Optional<Cadastro> atual = repositorio.findCadastroById(id);
        if(atual.isPresent()) {
            Cadastro cadastroAtual = atual.get();
            Cadastro cadastroAtualizado = Cadastro.atualizarCadastro(cadastroAtual, pessoa, endereco);
            repositorio.save(cadastroAtualizado);
            return ResponseEntity.status(OK)
                .body("Cadastro atualizado.");
        }
        return ResponseEntity.status(NOT_FOUND)
            .body("Cadastro não encontrado. Não foi possível atualizar.");
    }

    public ResponseEntity<String> deletar(long id){
        Optional<Cadastro> atual = repositorio.findCadastroById(id);
        if(atual.isPresent()) {
            repositorio.delete(atual.get());
            return ResponseEntity.status(OK)
                .body("Cadastro deletado.");
        }

        return ResponseEntity.status(NOT_FOUND)
            .body("Cadastro não encontrado. Não foi possível deletar.");
    }
}
