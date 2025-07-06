package com.trabalho_oo;

import java.util.Arrays;
import java.util.List;

public class ValidadorLogicoOR implements ValidadorPreRequisito {
    private List<ValidadorPreRequisito> validadores;

    public ValidadorLogicoOR(ValidadorPreRequisito... validadores) {
        this.validadores = Arrays.asList(validadores);
    }

    @Override
    public boolean validar(Aluno aluno, Disciplina disciplina) {
        return false;
    }
}
