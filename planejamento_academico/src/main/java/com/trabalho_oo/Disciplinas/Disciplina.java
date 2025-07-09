package com.trabalho_oo.Disciplinas;

import java.util.ArrayList;
import java.util.List;

import com.trabalho_oo.Models.CodigoDisciplina;

public abstract class Disciplina {
    private String nomeDisciplina;
    private CodigoDisciplina codigo;              // criar um tipo código?
    private int cargaHorariaSemanal;
    List<CodigoDisciplina> preRequisitos = new ArrayList<>(); 
    
    List<CodigoDisciplina> coRequisitos = new ArrayList<>();
    
    public Disciplina(){
        
    }
    
    public Disciplina(String nomeDisciplina, CodigoDisciplina codigo, int cargaHorariaSemanal, ArrayList<CodigoDisciplina> preRequisitos, ArrayList<CodigoDisciplina> coRequisitos){
        this.nomeDisciplina = nomeDisciplina;
        this.codigo = codigo;
        this.cargaHorariaSemanal = cargaHorariaSemanal;
        this.preRequisitos = preRequisitos;
        this.coRequisitos = coRequisitos;
    }
    
    private void setNome(String nome){
        nomeDisciplina = nome;
    }
    
    public List<CodigoDisciplina> getPreRequisitos() {
        return preRequisitos;
    }
}
