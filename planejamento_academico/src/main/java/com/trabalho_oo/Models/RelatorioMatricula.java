package com.trabalho_oo.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.trabalho_oo.entities.Aluno;
import com.trabalho_oo.entities.Turma;

public class RelatorioMatricula {
    List<Turma> turmasConfirmadas = new ArrayList<>();
    Map<String, String> disciplinasRejeitadas = new HashMap<>();
    
    public RelatorioMatricula(List<Turma> turmasConfirmadas, Map<String, String> disciplinasRejeitadas) {
        this.turmasConfirmadas = turmasConfirmadas;
        this.disciplinasRejeitadas = disciplinasRejeitadas;
    }

    public void imprimirRelatorio(Aluno aluno) {
        System.out.println("\n=======================================================");
        System.out.println("### Relatório de Matrícula: " + aluno.getNomeAluno() + " ###");
        System.out.println("=======================================================");

        System.out.println("\n--- DISCIPLINAS MATRICULADAS ---");
        if (turmasConfirmadas.isEmpty()) {
            System.out.println("Nenhuma disciplina matriculada.");
        } else {
            for (Turma turma : turmasConfirmadas) {
                // Formato detalhado para as disciplinas aceitas
                System.out.println("  - " + turma.getDisciplina().getNomeDisciplina() + " (Turma " + turma.getId() + ")");
            }
        }
        
        System.out.println("\n--- DISCIPLINAS REJEITADAS ---");
        if (disciplinasRejeitadas.isEmpty()) {
            System.out.println("Nenhuma disciplina rejeitada.");
        } else {
            // Mantém o formato detalhado para as disciplinas rejeitadas
            disciplinasRejeitadas.forEach((disciplina, motivo) ->
                System.out.println("  - " + disciplina + " | Motivo: " + motivo));
        }
        System.out.println("=======================================================\n");
    }

    public List<Turma> getTurmasConfirmadas() {
        return turmasConfirmadas;
    }
}
