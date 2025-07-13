package com.trabalho_oo.controller;

import java.util.ArrayList;
import java.util.List;

import com.trabalho_oo.Validadores.ValidadorPreRequisito;
import com.trabalho_oo.entities.Aluno;
import com.trabalho_oo.entities.Turma;
import com.trabalho_oo.entities.Disciplinas.Disciplina;
import com.trabalho_oo.entities.Disciplinas.DisciplinaEletiva;
import com.trabalho_oo.entities.Disciplinas.DisciplinaObrigatoria;
import com.trabalho_oo.exceptions.CoRequisitoNaoAtendidoException;
import com.trabalho_oo.exceptions.ConflitoDeHorarioException;
import com.trabalho_oo.exceptions.PreRequisitoNaoCumpridoException;
import com.trabalho_oo.exceptions.TurmaCheiaException;

public class ServicoMatricula {
    
    public void matriculaAluno(Aluno aluno) throws TurmaCheiaException {
        for(Turma turmaDesejada : aluno.getGradeFutura()) {
            if(turmaDesejada.isCheia())
                throw new TurmaCheiaException();
            if(turmaDesejada.getDisciplina().getPreRequisitos())
    }

    private void validarPreRequisitos(Aluno aluno) throws PreRequisitoNaoCumpridoException{
        for(Turma turma : aluno.getGradeFutura()) {
            if(!turma.getDisciplina().podeSerCursadoPor(aluno)) {
                throw new PreRequisitoNaoCumpridoException();
            }
        }
    }

    private void validarCoRequisitos(Aluno aluno, List<Disciplina> coRequisitos) throws CoRequisitoNaoAtendidoException {
        if(coRequisitos == null || coRequisitos.isEmpty())
            return;

        for(Disciplina coRequisito : coRequisitos) {
            if(aluno.getGradeFutura().get)
    }

    private void verificarConflito(Turma turma, List<Turma> turmasMatriculadas) throws ConflitoDeHorarioException {
        for (Turma turmaMatriculada : turmasMatriculadas) {
            if (turmaMatriculada.hasConflict(turma)) {
                throw new ConflitoDeHorarioException();
            }
        }
    }
}
