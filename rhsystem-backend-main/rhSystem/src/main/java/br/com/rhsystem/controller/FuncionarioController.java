package br.com.rhsystem.controller;

import br.com.rhsystem.dto.FuncionarioDTO;
import br.com.rhsystem.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Lazy
    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public void incluirFuncionario(@RequestBody FuncionarioDTO funcionario) {
        funcionarioService.incluirFuncionario(funcionario);
    }

    @PutMapping("/{id}")
    public void alterarFuncionario(@PathVariable Long id, @RequestBody FuncionarioDTO funcionarioAlterado) {
        funcionarioService.alterarFuncionario(id, funcionarioAlterado);
    }

    @DeleteMapping("/{id}")
    public void excluirFuncionario(@PathVariable Long id) {
        funcionarioService.excluirFuncionario(id);
    }

    @GetMapping("/{id}")
    public FuncionarioDTO buscarFuncionario(@PathVariable Long id) {
        return funcionarioService.buscarFuncionario(id);
    }

    @GetMapping
    public List<FuncionarioDTO> buscarTodosFuncionarios() {
        return funcionarioService.buscarTodosFuncionarios();
    }


}
