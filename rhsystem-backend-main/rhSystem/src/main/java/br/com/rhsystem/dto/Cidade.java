package br.com.rhsystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cidade {
    private Long id;
    private String nome;
    private Estado estado;
}
