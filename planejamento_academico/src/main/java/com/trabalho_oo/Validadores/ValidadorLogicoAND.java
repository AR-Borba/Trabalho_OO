package com.trabalho_oo.Validadores;

import java.util.List;

import com.trabalho_oo.entities.Aluno;
import com.trabalho_oo.entities.Disciplinas.Disciplina;

public class ValidadorLogicoAND implements ValidadorPreRequisito {
    private List<Disciplina> preRequisitos;

    public ValidadorLogicoAND(List<Disciplina> preRequisitos) {
        this.preRequisitos = preRequisitos;
    }

    @Override
    public boolean validar(Aluno aluno, Disciplina disciplina) {
        boolean flag = false;
        for (Disciplina cursada : aluno.getHistorico().keySet()){
            for (Disciplina preRequisito : this.preRequisitos) {
                    if (cursada == preRequisito){ 
                        if(aluno.isAprovado(cursada)) 
                            flag = true;
                        else
                            return false;
                    }
                }
            }
        return flag;
    }
}