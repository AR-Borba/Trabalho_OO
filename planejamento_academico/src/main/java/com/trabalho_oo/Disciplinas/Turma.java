package com.trabalho_oo.Disciplinas;

import java.time.LocalTime;
import java.util.Set;

import com.trabalho_oo.Models.DiaDaSemana;
import com.trabalho_oo.Models.HorarioAula;

public class Turma {
    char id;
    int capacidadeMaxima;
    int alunosMatriculados;
    Set<HorarioAula> horarioTurma;

    public Turma() {

    }

    public Turma(char id, int capacidadeMaxima, int alunosMatriculados, DiaDaSemana diaSemana, LocalTime horarioInicio, LocalTime horarioFim){
        this.id = id;
        this.capacidadeMaxima = capacidadeMaxima;
        this.alunosMatriculados = alunosMatriculados;
        HorarioAula horarioNovo = new HorarioAula(diaSemana, horarioInicio, horarioFim);
        
        horarioTurma.add(horarioNovo);
    }
}
