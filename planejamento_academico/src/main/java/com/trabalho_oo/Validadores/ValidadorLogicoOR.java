package com.trabalho_oo.Validadores;

import java.util.Arrays;
import java.util.List;

import com.trabalho_oo.Aluno;

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
