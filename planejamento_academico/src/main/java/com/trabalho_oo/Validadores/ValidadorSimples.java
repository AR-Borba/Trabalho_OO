package com.trabalho_oo.Validadores;

import com.trabalho_oo.Aluno;

public class ValidadorSimples implements ValidadorPreRequisito {
    private Disciplina preRequisito;
    private static final double NOTA_MINIMA = 60.0;

    public ValidadorSimples(Disciplina preRequisito) {
        this.preRequisito = preRequisito;
    }

    @Override
    public boolean validar(Aluno aluno, Disciplina disciplina) {
        return true;
    }
}
