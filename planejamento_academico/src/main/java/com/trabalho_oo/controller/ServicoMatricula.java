package com.trabalho_oo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.trabalho_oo.Models.RelatorioMatricula;
import com.trabalho_oo.Validadores.*;
import com.trabalho_oo.entities.*;
import com.trabalho_oo.entities.Disciplinas.*;
import com.trabalho_oo.exceptions.*;

public class ServicoMatricula {

    public RelatorioMatricula realizarMatricula(Aluno aluno) {
        List<Turma> turmasDesejadas = new ArrayList<>(aluno.getGradeFutura());
        List<Turma> turmasConfirmadas = new ArrayList<>();
        Map<String, String> disciplinasRejeitadas = new HashMap<>();
        int cargaHorariaAtual = 0;

        for (Turma turma : turmasDesejadas) {
            try {
                // Validações
                validarVagas(turma);
                validarPreRequisitos(aluno);
                validarCoRequisitos(aluno, turma.getDisciplina().getCoRequisitos());
                validarCargaHoraria(aluno, turma.getDisciplina(), cargaHorariaAtual);
                verificarConflito(turma, turmasConfirmadas);
                
                turmasConfirmadas.add(turma);
                cargaHorariaAtual += turma.getDisciplina().getCargaHorariaSemanal();
                
            } catch (MatriculaException e) {
                disciplinasRejeitadas.put(turma.getDisciplina().getNomeDisciplina(), e.getMessage());
            }
        }

        return new RelatorioMatricula(turmasConfirmadas, disciplinasRejeitadas);
    }

    public void avancarPeriodo(Aluno aluno, RelatorioMatricula relatorio) {
        final double NOTA_APROVACAO_PADRAO = 70.0;

        List<Turma> turmasCursadas = relatorio.getTurmasConfirmadas();
        if(turmasCursadas != null && !turmasCursadas.isEmpty()) {
            for(Turma turma : turmasCursadas) {
                String disciplina = turma.getDisciplina().getCodigo();
                aluno.adicionarAoHistorico(disciplina, NOTA_APROVACAO_PADRAO);
            }
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
