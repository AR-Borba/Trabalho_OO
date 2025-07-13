package com.trabalho_oo.entities.Disciplinas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.trabalho_oo.Validadores.ValidadorPreRequisito;
import com.trabalho_oo.entities.Aluno;

public abstract class Disciplina {
    private String nomeDisciplina;
    private String codigo;
    private int cargaHorariaSemanal;
    List<ValidadorPreRequisito> preRequisitos = new ArrayList<>(); 
    List<Disciplina> coRequisitos = new ArrayList<>();
    
    public Disciplina(){
        
    }
        
    public Disciplina(String nomeDisciplina, String codigo, int cargaHorariaSemanal, ArrayList<ValidadorPreRequisito> preRequisitos, ArrayList<Disciplina> coRequisitos) {
        this.nomeDisciplina = nomeDisciplina;
        this.codigo = codigo;
        this.cargaHorariaSemanal = cargaHorariaSemanal;
        this.preRequisitos = preRequisitos;
        this.coRequisitos = coRequisitos;
    }
    
    public String getNomeDisciplina() {
        return nomeDisciplina;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public int getCargaHorariaSemanal() {
        return cargaHorariaSemanal;
    }
    
    public List<ValidadorPreRequisito> getPreRequisitos() {
        return preRequisitos;
    }
    
    public List<Disciplina> getCoRequisitos() {
        return coRequisitos;
    }
    
    public boolean podeSerCursadoPor(Aluno aluno) {
        // lógica de validação de requisitos
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Disciplina that = (Disciplina) obj;

        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
