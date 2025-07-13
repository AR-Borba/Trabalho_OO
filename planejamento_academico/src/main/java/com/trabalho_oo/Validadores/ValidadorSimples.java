package com.trabalho_oo.Validadores;

import com.trabalho_oo.entities.Aluno;
import com.trabalho_oo.entities.Disciplinas.Disciplina;

public class ValidadorSimples implements ValidadorPreRequisito {
    private Disciplina preRequisito;

    public ValidadorSimples() {
    
    }

    public ValidadorSimples(Disciplina preRequisito) {
        this.preRequisito = preRequisito;
    }

    @Override
    public boolean validar(Aluno aluno, Disciplina disciplina) {
        if(this.preRequisito == null) {
            return false;
        }
        return aluno.isAprovado(this.preRequisito);
    }
}
