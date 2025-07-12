package com.trabalho_oo.entities;

import java.util.Map;

import com.trabalho_oo.entities.Disciplinas.Disciplina;

import java.util.HashMap;
import java.util.List;

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
