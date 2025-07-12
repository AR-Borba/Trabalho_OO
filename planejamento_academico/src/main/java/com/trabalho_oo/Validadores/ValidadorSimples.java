package com.trabalho_oo.Validadores;

import com.trabalho_oo.entities.Aluno;
import com.trabalho_oo.entities.Disciplinas.Disciplina;

public class ValidadorSimples implements ValidadorPreRequisito {
    private static final double NOTA_MINIMA = 60.0;

    public ValidadorSimples() {
    
    }

    @Override
    public boolean validar(Aluno aluno, Disciplina disciplina) {   
        for(Disciplina cursada : aluno.getHistorico().keySet()){
            if( (aluno.getHistorico().get(cursada) >= NOTA_MINIMA) && (cursada == disciplina.getPreRequisitos() ))
                return true;
        }
        return false;
    }
}
