package com.trabalho_oo.entities.Disciplinas;

import java.util.ArrayList;

import com.trabalho_oo.Models.CodigoDisciplina;
import com.trabalho_oo.Validadores.ValidadorPreRequisito;

public class DisciplinaObrigatoria extends Disciplina {
    public DisciplinaObrigatoria(String nomeDisciplina, CodigoDisciplina codigo, int cargaHorariaSemanal, ArrayList<ValidadorPreRequisito> preRequisitos, ArrayList<Disciplina> coRequisitos) {
        super(nomeDisciplina, codigo, cargaHorariaSemanal, preRequisitos, coRequisitos);
    }
}