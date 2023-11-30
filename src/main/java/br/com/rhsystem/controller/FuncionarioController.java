package br.com.rhsystem.controller;

import br.com.rhsystem.dto.FuncionarioDTO;
import br.com.rhsystem.exception.FuncionarioNaoEncotradoException;
import br.com.rhsystem.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public FuncionarioDTO incluirFuncionario(@RequestBody FuncionarioDTO funcionario) {
        return funcionarioService.incluirFuncionario(funcionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> alterarFuncionario(@PathVariable Long id, @RequestBody FuncionarioDTO funcionarioAlterado) {
        try {
            FuncionarioDTO funcionarioDTO = funcionarioService.alterarFuncionario(id, funcionarioAlterado);
            return ResponseEntity.ok(funcionarioDTO);
        } catch (FuncionarioNaoEncotradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public void excluirFuncionario(@PathVariable Long id) {
        funcionarioService.excluirFuncionario(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> buscarFuncionario(@PathVariable Long id) {
        try {
            FuncionarioDTO funcionarioDTO = funcionarioService.buscarFuncionario(id);
            return ResponseEntity.ok(funcionarioDTO);
        } catch (FuncionarioNaoEncotradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public Page<FuncionarioDTO> buscarTodosFuncionarios(@PageableDefault(size = 5) Pageable pageable) {
        return funcionarioService.buscarTodosFuncionarios(pageable);

    }


}
