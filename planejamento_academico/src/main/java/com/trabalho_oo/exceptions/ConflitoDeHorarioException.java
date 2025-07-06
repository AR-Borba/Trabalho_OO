package com.trabalho_oo.exceptions;

public class ConflitoDeHorarioException extends ValidacaoMatriculaException {
    public ConflitoDeHorarioException() {
        super("Conflito de horário!");
    }
}
