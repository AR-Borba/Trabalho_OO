package com.trabalho_oo.Validadores;

import com.trabalho_oo.entities.Aluno;
import com.trabalho_oo.entities.Turma;
import com.trabalho_oo.entities.Disciplinas.Disciplina;

public class ValidadorCorequisito implements ValidadorPreRequisito {
    private Disciplina coRequisito;

    public ValidadorCorequisito(Disciplina coRequisito) {
        this.coRequisito = coRequisito;
    }

    @Override
    public boolean validar(Aluno aluno, Disciplina disciplina) {
        for( Turma turmaCoRequisto : aluno.getGradeFutura()) {
            if (turmaCoRequisto.getDisciplina() == coRequisito)
                return true;
        }
        return false;
    }
}
