package com.trabalho_oo.exceptions;

public class TurmaCheiaException extends GerenciamentoVagasException {
    public TurmaCheiaException() {
        super("Turma sem vagas!");
    }
}
