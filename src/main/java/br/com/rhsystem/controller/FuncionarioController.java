package br.com.rhsystem.controller;

import br.com.rhsystem.dto.FuncionarioDTO;
import br.com.rhsystem.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<FuncionarioDTO> buscarTodosFuncionarios(@PageableDefault(size = 5) Pageable pageable) {
        List<FuncionarioDTO> funcionarioDTO = funcionarioService.buscarTodosFuncionarios(pageable);
        return new PageImpl<>(funcionarioDTO, pageable, funcionarioDTO.size());
    }


}
