package com.trabalho_oo.entities;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import com.trabalho_oo.Models.DiaDaSemana;
import com.trabalho_oo.Models.HorarioAula;
import com.trabalho_oo.entities.Disciplinas.Disciplina;
import com.trabalho_oo.exceptions.TurmaCheiaException;

public class Turma {
    private char id;
    private int capacidadeMaxima;
    private int alunosMatriculados;
    private Set<HorarioAula> horarioTurma = new HashSet<>();
    private String salaAula;
    private Disciplina disciplina;
    
    public Turma() {
        
    }
    
    public Turma(char id, int capacidadeMaxima, int alunosMatriculados, DiaDaSemana diaSemana, LocalTime horarioInicio, LocalTime horarioFim, String salaAula, Disciplina disciplina){
        this.id = id;
        this.capacidadeMaxima = capacidadeMaxima;
        this.alunosMatriculados = alunosMatriculados;
        HorarioAula horarioNovo = new HorarioAula(diaSemana, horarioInicio, horarioFim);
        this.salaAula = salaAula;
        this.disciplina = disciplina;
        
        horarioTurma.add(horarioNovo);
    }

    public void matricularAluno() throws TurmaCheiaException {
        if(!isCheia()) {
            alunosMatriculados++;
        } else {
            throw new TurmaCheiaException();
        }
    }

    public boolean hasConflict(Turma outraTurma) {
        for (HorarioAula horario : this.horarioTurma) {
            for (HorarioAula outroHorario : outraTurma.getHorarioTurma()) {
                if (horario.getDiaDaSemana() == outroHorario.getDiaDaSemana() &&
                    horario.getHorarioInicio().isBefore(outroHorario.getHorarioFim()) &&
                    horario.getHorarioFim().isAfter(outroHorario.getHorarioInicio())) {
                    return true;
                }
            }
        }
        return false;
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
    
    public String getSalaAula() {
        return salaAula;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }
    
    public boolean isCheia() {
        return (capacidadeMaxima - alunosMatriculados) == 0;
    }
}
