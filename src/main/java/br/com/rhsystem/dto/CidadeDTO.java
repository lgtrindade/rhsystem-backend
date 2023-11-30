package br.com.rhsystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CidadeDTO {
    private Long id;
    private String nome;
    private EstadoDTO estado;
}
