package com.trabalho_oo.Validadores;

import com.trabalho_oo.Utils.CatalogoDisciplinas;
import com.trabalho_oo.entities.Aluno;
import com.trabalho_oo.entities.Disciplinas.Disciplina;

public class ValidadorCreditosMinimos implements ValidadorPreRequisito {
    private static final double NOTA_MINIMA = 60.0;
    private int creditosMinimos;

    public ValidadorCreditosMinimos(int creditosMinimos) {
        this.creditosMinimos = creditosMinimos;
    }

    @Override
    public boolean validar(Aluno aluno, Disciplina disciplina) {
        int creditosAcumulados = 0;
        for (String codigoCursada : aluno.getHistorico().keySet()) {
            if (aluno.getHistorico().get(codigoCursada) >= NOTA_MINIMA) {
                Disciplina disciplinaCursada = CatalogoDisciplinas.getDisciplina(codigoCursada);
                if (disciplinaCursada != null) {
                    creditosAcumulados += disciplinaCursada.getCargaHorariaSemanal();
                }
            }
        }
        return creditosAcumulados >= this.creditosMinimos;
    }
}