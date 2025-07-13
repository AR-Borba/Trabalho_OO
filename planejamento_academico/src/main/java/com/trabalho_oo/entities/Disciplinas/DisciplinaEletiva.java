package com.trabalho_oo.entities.Disciplinas;

import java.util.ArrayList;

import com.trabalho_oo.Validadores.ValidadorPreRequisito;

public class DisciplinaEletiva extends Disciplina{
    public DisciplinaEletiva(String nomeDisciplina, String codigo, int cargaHorariaSemanal, ArrayList<ValidadorPreRequisito> preRequisitos, ArrayList<Disciplina> coRequisitos, String type) {
        super(nomeDisciplina, codigo, cargaHorariaSemanal, preRequisitos, coRequisitos);
    }

    @Override
    public int getPrecedencia() {
        return 2;
    }
}
