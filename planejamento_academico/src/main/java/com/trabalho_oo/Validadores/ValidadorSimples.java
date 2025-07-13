package com.trabalho_oo.Validadores;

import com.trabalho_oo.entities.Aluno;
import com.trabalho_oo.entities.Disciplinas.Disciplina;

public class ValidadorSimples implements ValidadorPreRequisito {
    private static final double NOTA_MINIMA = 60.0;
    private Disciplina preRequisito;

    public ValidadorSimples() {
    
    }

    public ValidadorSimples(Disciplina preRequisito) {
        this.preRequisito = preRequisito;
    }

    @Override
    public boolean validar(Aluno aluno, Disciplina disciplina) {
        Double nota = aluno.getHistorico().get(preRequisito);
        return nota != null && nota >= NOTA_MINIMA;
    }
}
