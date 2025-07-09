package com.trabalho_oo;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.trabalho_oo.Models.CodigoDisciplina;

public class Aluno {
    private String nomeAluno;
    private String matricula;
    private Map<CodigoDisciplina, Double> historico = new HashMap<>();
    private int cargaHorariaMaxima = 20;
    private List<CodigoDisciplina> gradeFutura;
    
    public Map<CodigoDisciplina, Double> getHistorico() {
        return historico;
    }
    
}
