package com.trabalho_oo.Disciplinas;

import java.time.LocalTime;
import java.util.Set;

import com.trabalho_oo.Models.DiaDaSemana;
import com.trabalho_oo.Models.HorarioAula;

public class Turma {
    private char id;
    private int capacidadeMaxima;
    private int alunosMatriculados;
    private Set<HorarioAula> horarioTurma;
    private Disciplina disciplina;
    
    public Turma() {
        
    }
    
    public Turma(char id, int capacidadeMaxima, int alunosMatriculados, DiaDaSemana diaSemana, LocalTime horarioInicio, LocalTime horarioFim, Disciplina disciplina){
        this.id = id;
        this.capacidadeMaxima = capacidadeMaxima;
        this.alunosMatriculados = alunosMatriculados;
        HorarioAula horarioNovo = new HorarioAula(diaSemana, horarioInicio, horarioFim);
        this.disciplina = disciplina;
        
        horarioTurma.add(horarioNovo);
    }
    
    public char getId() {
        return id;
    }
    
    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }
    
    public int getAlunosMatriculados() {
        return alunosMatriculados;
    }
    
    public Set<HorarioAula> getHorarioTurma() {
        return horarioTurma;
    }
    
    public Disciplina getDisciplina() {
        return disciplina;
    }
}
