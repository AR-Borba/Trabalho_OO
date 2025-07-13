package com.trabalho_oo.controller;

import java.util.ArrayList;
import java.util.Comparator;
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

        for (Turma turma : turmasDesejadas) {
            try {
                // Validações
                validarVagas(turma);
                if (!turma.getDisciplina().podeSerCursadoPor(aluno)) {
                    throw new PreRequisitoNaoCumpridoException();
                }
                validarCoRequisitos(aluno, turma.getDisciplina().getCoRequisitos());
                validarCargaHoraria(aluno, turma.getDisciplina(), cargaHorariaAtual);
                verificarConflito(turma, turmasConfirmadas);
                
                // Se tudo deu certo, confirma a matrícula
                turmasConfirmadas.add(turma);
                cargaHorariaAtual += turma.getDisciplina().getCargaHorariaSemanal();
                
            } catch (MatriculaException e) {
                disciplinasRejeitadas.put(turma.getDisciplina().getNomeDisciplina(), e.getMessage());
            }
        }

        aluno.setPlanejamento(turmasConfirmadas);
        // Chama o novo método para imprimir o relatório formatado
        imprimirRelatorio(aluno, turmasConfirmadas, disciplinasRejeitadas);
    }

     private void imprimirRelatorio(Aluno aluno, List<Turma> confirmadas, Map<String, String> rejeitadas) {
        System.out.println("\n=======================================================");
        System.out.println("### Relatório de Matrícula: " + aluno.getNomeAluno() + " ###");
        System.out.println("=======================================================");

        System.out.println("\n--- DISCIPLINAS MATRICULADAS ---");
        if (confirmadas.isEmpty()) {
            System.out.println("Nenhuma disciplina matriculada.");
        } else {
            for (Turma turma : confirmadas) {
                // Formato detalhado para as disciplinas aceitas
                System.out.println("  - " + turma.getDisciplina().getNomeDisciplina() + " (Turma " + turma.getId() + ")");
            }
        }
        
        System.out.println("\n--- DISCIPLINAS REJEITADAS ---");
        if (rejeitadas.isEmpty()) {
            System.out.println("Nenhuma disciplina rejeitada.");
        } else {
            // Mantém o formato detalhado para as disciplinas rejeitadas
            rejeitadas.forEach((disciplina, motivo) -> 
                System.out.println("  - " + disciplina + " | Motivo: " + motivo));
        }
        System.out.println("=======================================================\n");
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
