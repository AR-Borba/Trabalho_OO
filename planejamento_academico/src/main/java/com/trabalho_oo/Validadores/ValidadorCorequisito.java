package com.trabalho_oo.Validadores;

import com.trabalho_oo.entities.Aluno;
import com.trabalho_oo.entities.Disciplinas.Disciplina;

public class ValidadorCorequisito {
    private Disciplina coRequisito;

    public ValidadorCorequisito(Disciplina coRequisito) {
        this.coRequisito = coRequisito;
    }

    @Override
    public boolean validar(Aluno aluno, Disciplina disciplina) {
        return true;
    }
}
