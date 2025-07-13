package com.trabalho_oo.controller;

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

        // Passo 1: Carregar os dados dos alunos a partir do arquivo JSON.
        System.out.println("Carregando dados dos alunos de alunos.json...");
        Persistence<Aluno> alunoPersistence = new AlunoPersistence();
        List<Aluno> todosOsAlunos = alunoPersistence.findAll();
        System.out.println(todosOsAlunos.size() + " alunos carregados com sucesso. \n");

        // Passo 2: Instanciar o serviço de matrícula.
        ServicoMatricula servicoMatricula = new ServicoMatricula();

        // Passo 3: Processar a matrícula para cada aluno carregado.
        for (Aluno aluno : todosOsAlunos) {
            System.out.println("-------------------------------------------------------");
            System.out.println("Processando matrícula para: " + aluno.getNomeAluno() + " (" + aluno.getMatricula() + ")");
            System.out.println("-------------------------------------------------------");
            
            // O método `realizarMatricula` contém toda a lógica de validação
            // e já imprime o relatório ao final de sua execução.
            servicoMatricula.realizarMatricula(aluno);
            
            System.out.println("\n"); // Adiciona um espaço para melhor legibilidade
        }

        System.out.println("====== SIMULAÇÃO FINALIZADA ======");
    }
}