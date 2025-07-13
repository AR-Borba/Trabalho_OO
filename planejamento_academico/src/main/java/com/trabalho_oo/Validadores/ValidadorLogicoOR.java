package com.trabalho_oo.Validadores;

import java.util.List;

import com.trabalho_oo.entities.Aluno;
import com.trabalho_oo.entities.Disciplinas.Disciplina;

public class ValidadorLogicoOR implements ValidadorPreRequisito {
    private List<Disciplina> preRequisitos;

    public ValidadorLogicoOR(List<Disciplina> preRequisitos) {
        this.preRequisitos = preRequisitos;
    }

    @Override
    public boolean validar(Aluno aluno, Disciplina disciplina) {
    for (Disciplina preRequisito : this.preRequisitos) {
            if (aluno.isAprovado(preRequisito)) {
                return true;
            }
        }
        return false;
    }
}
