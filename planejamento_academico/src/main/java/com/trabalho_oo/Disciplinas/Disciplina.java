package com.trabalho_oo.Disciplinas;

import java.util.ArrayList;
import java.util.List;

import com.trabalho_oo.Models.CodigoDisciplina;
import com.trabalho_oo.Validadores.ValidadorPreRequisito;

public abstract class Disciplina {
    private String nomeDisciplina;
    private CodigoDisciplina codigo;
    private int cargaHorariaSemanal;
    List<ValidadorPreRequisito> preRequisitos = new ArrayList<>(); 
    List<ValidadorPreRequisito> coRequisitos = new ArrayList<>();
    
    public Disciplina(){
        
    }
    
    
    public Disciplina(String nomeDisciplina, CodigoDisciplina codigo, int cargaHorariaSemanal, ArrayList<ValidadorPreRequisito> preRequisitos, ArrayList<ValidadorPreRequisito> coRequisitos){
        this.nomeDisciplina = nomeDisciplina;
        this.codigo = codigo;
        this.cargaHorariaSemanal = cargaHorariaSemanal;
        this.preRequisitos = preRequisitos;
        this.coRequisitos = coRequisitos;
    }
    
    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public CodigoDisciplina getCodigo() {
        return codigo;
    }

    public int getCargaHorariaSemanal() {
        return cargaHorariaSemanal;
    }
    
    public List<ValidadorPreRequisito> getPreRequisitos() {
        return preRequisitos;
    }

    public List<ValidadorPreRequisito> getCoRequisitos() {
        return coRequisitos;
    }
}
