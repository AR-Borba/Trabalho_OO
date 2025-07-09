package com.trabalho_oo.Validadores;

import com.trabalho_oo.Aluno;
import com.trabalho_oo.Disciplinas.Disciplina;

public class ValidadorCreditosMinimos implements ValidadorPreRequisito{
     private int creditosMinimos;

    public ValidadorCreditosMinimos(int creditosMinimos) {
        this.creditosMinimos = creditosMinimos;
    }

    @Override
    public boolean validar(Aluno aluno, Disciplina disciplina) {
        return true;
    }
}
