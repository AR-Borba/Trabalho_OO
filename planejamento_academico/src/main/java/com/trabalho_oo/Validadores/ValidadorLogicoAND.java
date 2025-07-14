package com.trabalho_oo.Validadores;

import java.util.ArrayList;
import java.util.List;

import com.trabalho_oo.entities.Aluno;
import com.trabalho_oo.entities.Disciplinas.Disciplina;

public class ValidadorLogicoAND implements ValidadorPreRequisito {
    private List<String> preRequisitos;

    public ValidadorLogicoAND(List<String> preRequisitos) {
        this.preRequisitos = preRequisitos;
    }

    public ValidadorLogicoAND() {
        this.preRequisitos = new ArrayList<>();
    }

    @Override
    public boolean validar(Aluno aluno, Disciplina disciplina) {
        boolean flag = false;
        for (String cursada : aluno.getHistorico().keySet()){
            for (String preRequisito : this.preRequisitos) {
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