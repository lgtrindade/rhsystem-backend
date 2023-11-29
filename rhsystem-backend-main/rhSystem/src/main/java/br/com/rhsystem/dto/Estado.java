package br.com.rhsystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Estado {
    private Long id;
    private String nome;
}
