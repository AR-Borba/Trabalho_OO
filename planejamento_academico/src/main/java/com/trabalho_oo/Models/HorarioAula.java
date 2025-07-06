package com.trabalho_oo.Models;

import java.time.LocalTime;

public class HorarioAula {
    DiaDaSemana diaDaAula;
    LocalTime horaInicioAula;
    LocalTime horaFimAula;

    public HorarioAula(DiaDaSemana diaDaAula, LocalTime horaInicioAula, LocalTime horaFimAula) {
        this.diaDaAula = diaDaAula;
        this.horaInicioAula = horaInicioAula;
        this.horaFimAula = horaFimAula;
    }
}
