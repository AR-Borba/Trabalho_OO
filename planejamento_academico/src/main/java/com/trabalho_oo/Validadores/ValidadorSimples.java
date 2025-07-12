package com.trabalho_oo.Validadores;

import com.trabalho_oo.Models.CodigoDisciplina;
import com.trabalho_oo.entities.Aluno;
import com.trabalho_oo.entities.Disciplinas.Disciplina;

public class ValidadorSimples implements ValidadorPreRequisito {
    private static final double NOTA_MINIMA = 60.0;

    public ValidadorSimples() {
    
    }

    @Override
    public boolean validar(Aluno aluno, Disciplina disciplina) {
        boolean teste = true;
        for(CodigoDisciplina codigoPreRequisito : disciplina.getPreRequisitos()){
            teste = false;
            for(CodigoDisciplina codigoAluno: aluno.getHistorico().keySet() ){
                if(codigoPreRequisito == codigoAluno && aluno.getHistorico().get(codigoAluno) >= NOTA_MINIMA)
                    teste = true;
            }
            if(!teste)
                return teste;  
        }
        return true;    
    }
}
