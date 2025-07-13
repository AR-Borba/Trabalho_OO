package com.trabalho_oo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.trabalho_oo.Validadores.*;
import com.trabalho_oo.entities.*;
import com.trabalho_oo.entities.Disciplinas.*;
import com.trabalho_oo.exceptions.*;

public class ServicoMatricula {

    public void realizarMatricula(Aluno aluno) {
        List<Turma> turmasDesejadas = new ArrayList<>(aluno.getGradeFutura());
        List<Turma> turmasConfirmadas = new ArrayList<>();
        Map<String, String> disciplinasRejeitadas = new HashMap<>();
        int cargaHorariaAtual = 0;
        
        for(Turma turma : turmasDesejadas) {
            try {
                validarPreRequisitos(aluno);
                validarCoRequisitos(aluno, turma.getDisciplina().getCoRequisitos());
                verificarConflito(turma, turmasConfirmadas);
                validarCargaHoraria(aluno, turma.getDisciplina(), cargaHorariaAtual);
                validarVagas(turma);
                
                cargaHorariaAtual += turma.getDisciplina().getCargaHorariaSemanal();
                turmasConfirmadas.add(turma);
            } catch (PreRequisitoNaoCumpridoException | CoRequisitoNaoAtendidoException | ConflitoDeHorarioException | CargaHorariaExcedidaException | TurmaCheiaException e) {
                disciplinasRejeitadas.put(turma.getDisciplina().getNomeDisciplina(), e.getMessage());
            }
        }

        System.out.println("IMPRIME RELATÓRIO DE MATRÍCULA");
        System.out.println("Disciplinas Matriculadas:");
        for (Turma turma : turmasConfirmadas) 
            System.out.println("Matriculado em: " + turma.getDisciplina().getNomeDisciplina());
        System.out.println("Disciplinas Rejeitadas:");
        for (Map.Entry<String, String> entry : disciplinasRejeitadas.entrySet()) {
            System.out.println("Disciplina: " + entry.getKey() + " - Motivo: " + entry.getValue());
        }
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
            ValidadorCorequisito validador = new ValidadorCorequisito(coRequisito);
            if(!validador.validar(aluno, null))
                throw new CoRequisitoNaoAtendidoException();
        }
    }

    private void verificarConflito(Turma turma, List<Turma> turmasMatriculadas) throws ConflitoDeHorarioException {
        for (Turma turmaMatriculada : turmasMatriculadas) {
            if (turmaMatriculada.hasConflict(turma)) {
                throw new ConflitoDeHorarioException();
            }
        }
    }

    private void validarCargaHoraria(Aluno aluno, Disciplina novaDisciplina, int cargaHorariaAtual) throws CargaHorariaExcedidaException {
        if (cargaHorariaAtual + novaDisciplina.getCargaHorariaSemanal() > aluno.getCargaHorariaMaxima()) {
            throw new CargaHorariaExcedidaException();
        }
    }

    private void validarVagas(Turma turma) throws TurmaCheiaException {
        if (turma.isCheia()) {
            throw new TurmaCheiaException();
        }
    }
}
