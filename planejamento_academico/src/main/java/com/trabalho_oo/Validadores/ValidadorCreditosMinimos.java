package com.trabalho_oo.Validadores;

import com.trabalho_oo.entities.Aluno;
import com.trabalho_oo.entities.Disciplinas.Disciplina;

public class ValidadorCreditosMinimos implements ValidadorPreRequisito{
    private static final double NOTA_MINIMA = 60.0;
    private int creditosMinimos;

    public ValidadorCreditosMinimos(int creditosMinimos) {
        this.creditosMinimos = creditosMinimos;
    }

    @Override
    public boolean validar(Aluno aluno, Disciplina disciplina) {
        int creditosAcumulados = 0;
        for (String cursada : aluno.getHistorico().keySet()) {
            if (aluno.getHistorico().get(cursada) >= NOTA_MINIMA) {
                creditosAcumulados += disciplina.getCargaHorariaSemanal();
            }
        }
        return creditosAcumulados >= this.creditosMinimos;
    }
}
