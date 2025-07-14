package com.trabalho_oo.Validadores;

import java.util.List;

import com.trabalho_oo.entities.Aluno;
import com.trabalho_oo.entities.Disciplinas.Disciplina;

public class ValidadorLogicoAND implements ValidadorPreRequisito {
    private List<String> preRequisitos;

    public ValidadorLogicoAND(List<String> preRequisitos) {
        this.preRequisitos = preRequisitos;
    }

    @Override
    public boolean validar(Aluno aluno, Disciplina disciplina) {
        for (String codigoPreRequisito : this.preRequisitos) {
            if (!aluno.isAprovado(codigoPreRequisito)) {
                return false;
            }
        }
        return true;
    }
}