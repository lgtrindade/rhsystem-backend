package br.com.rhsystem.exception;

public class FuncionarioNaoEncotradoException extends RuntimeException {

    public FuncionarioNaoEncotradoException(String message){
        super(message);
    }
}
