package com.trabalho_oo;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.trabalho_oo.Disciplinas.Disciplina;
import com.trabalho_oo.Disciplinas.Turma;

public class Aluno {
    private String nomeAluno;
    private String matricula;
    private int cargaHorariaMaxima = 20;
    private Map<Disciplina, Double> historico = new HashMap<>();
    private List<Turma> gradeFutura;

    public String getNomeAluno() {
        return nomeAluno;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getCargaHorariaMaxima() {
        return cargaHorariaMaxima;
    }

    public Map<Disciplina, Double> getHistorico() {
        return historico;
    }

    public List<Turma> getGradeFutura() {
        return gradeFutura;
    }
}
