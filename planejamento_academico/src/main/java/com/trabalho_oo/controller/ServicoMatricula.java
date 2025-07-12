package com.trabalho_oo.controller;

import java.util.ArrayList;
import java.util.List;

import com.trabalho_oo.Validadores.ValidadorPreRequisito;
import com.trabalho_oo.entities.Disciplinas.DisciplinaEletiva;
import com.trabalho_oo.entities.Disciplinas.DisciplinaObrigatoria;

public class ServicoMatricula {
    
    public static void main(String[] args) {
        List<ValidadorPreRequisito> preRequisitos = new ArrayList<>();
        List<Disciplina> coRequisitos = new ArrayList<>();
        Disciplina eh = new DisciplinaObrigatoria("OO", "DCC202", 2, pre preRequisitos, new ArrayList<Disciplina>());
    }
}
