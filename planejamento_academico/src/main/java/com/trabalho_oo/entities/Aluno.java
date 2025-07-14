package com.trabalho_oo.entities;

import java.util.Map;

//import com.trabalho_oo.entities.Disciplinas.Disciplina;

import java.util.HashMap;
import java.util.List;

public class Aluno {
    private String nomeAluno;
    private String matricula;
    private int cargaHorariaMaxima = 20;
    private Map<String, Double> historico = new HashMap<>();
    private List<Turma> gradeFutura;

    public Aluno() {

    }

    public Aluno(String nomeAluno, String matricula, int cargaHorariaMaxima, Map<String, Double> historico,
    List<Turma> gradeFutura) {
        this.nomeAluno = nomeAluno;
        this.matricula = matricula;
        this.cargaHorariaMaxima = cargaHorariaMaxima;
        this.historico = historico;
        this.gradeFutura = gradeFutura;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }
    
    public String getMatricula() {
        return matricula;
    }

    public int getCargaHorariaMaxima() {
        return cargaHorariaMaxima;
    }

    public Map<String, Double> getHistorico() {
        return historico;
    }

    public List<Turma> getGradeFutura() {
        return gradeFutura;
    }

    public boolean isAprovado(String d) {
        if(historico.get(d) == null)
            return false;
        if(historico.get(d) >= 60.0)
            return true;
        return false;
    }

    public void adicionarAoHistorico(String d, double nota) {
        if(historico.get(d) == null || historico.get(d) < nota)
            historico.put(d, nota);
    }

    public void adicionarAoPlanejamento(Turma t) {
        gradeFutura.add(t);
    }

    public void setPlanejamento(List<Turma> gradeFutura) {
        this.gradeFutura = gradeFutura;
    }
}
