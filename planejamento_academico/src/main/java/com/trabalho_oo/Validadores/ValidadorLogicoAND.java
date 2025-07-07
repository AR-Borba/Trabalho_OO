package com.trabalho_oo.Validadores;

import java.util.Arrays;
import java.util.List;

import com.trabalho_oo.Aluno;

public class ValidadorLogicoAND implements ValidadorPreRequisito {
    private List<ValidadorPreRequisito> validadores;

    public ValidadorLogicoAND(ValidadorPreRequisito... validadores) {
        this.validadores = Arrays.asList(validadores);
    }

    @Override
    public boolean validar(Aluno aluno, Disciplina disciplina) {
        return true;
    }
}
