package br.com.rhsystem.controller;

import br.com.rhsystem.dto.FuncionarioDTO;
import br.com.rhsystem.entity.FuncionarioEntity;
import br.com.rhsystem.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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

    @PostMapping("/alterar")
    public void alterarFuncionario(@RequestBody FuncionarioDTO funcionarioAlterado) {
        funcionarioService.alterarFuncionario(funcionarioAlterado);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluirFuncionario(@PathVariable Long id) {
        funcionarioService.excluirFuncionario(id);
    }

    @GetMapping("/{id}")
    public Optional<FuncionarioDTO> buscarFuncionario(@PathVariable Long id) {
        return funcionarioService.buscarFuncionario(id);
    }

    @GetMapping("/buscar-todos")
    public List<FuncionarioDTO> buscarTodosFuncionarios() {
        return funcionarioService.buscarTodosFuncionarios();
    }


}
