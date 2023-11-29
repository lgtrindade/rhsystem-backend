package br.com.rhsystem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Endereco {
    private String logradouro;
    private Long numero;
    private String complemento;
    private String bairro;
    private String cep;
    private Cidade cidade;
}
