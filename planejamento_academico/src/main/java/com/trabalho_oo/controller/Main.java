package com.trabalho_oo.controller;

import com.trabalho_oo.Models.RelatorioMatricula;
import com.trabalho_oo.Persistence.AlunoPersistence;
import com.trabalho_oo.Persistence.Persistence;
import com.trabalho_oo.entities.Aluno;
import java.util.List;

/**
 * Classe principal para executar a simulação de matrícula acadêmica.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("====== INICIANDO SISTEMA DE SIMULAÇÃO DE MATRÍCULA ======");

        System.out.println("Carregando dados dos alunos de alunos.json...");
        Persistence<Aluno> alunoPersistence = new AlunoPersistence();
        List<Aluno> todosOsAlunos = alunoPersistence.findAll();
        System.out.println(todosOsAlunos.size() + " alunos carregados com sucesso. \n");

        ServicoMatricula servicoMatricula = new ServicoMatricula();

        for (Aluno aluno : todosOsAlunos) {
            System.out.println("-------------------------------------------------------");
            System.out.println("Processando matrícula para: " + aluno.getNomeAluno() + " (" + aluno.getMatricula() + ")");
            System.out.println("-------------------------------------------------------");
            
            RelatorioMatricula relatorio = servicoMatricula.realizarMatricula(aluno);
            relatorio.imprimirRelatorio(aluno);
            
            servicoMatricula.avancarPeriodo(aluno, relatorio);

            System.out.println("\n");
        }

        
        alunoPersistence.save(todosOsAlunos); 

        System.out.println("====== SIMULAÇÃO FINALIZADA ======");
    }
}