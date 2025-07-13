package com.trabalho_oo.Persistence;

import com.trabalho_oo.entities.Turma;
import com.trabalho_oo.entities.Disciplinas.*;
import com.trabalho_oo.Models.DiaDaSemana;
import com.trabalho_oo.Validadores.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MainPopulator {

    public static void main(String[] args) {
        criaDisciplinas();
        criaTurmas();
    }

    private static void criaTurmas() {
       System.out.println("Iniciando a criação das turmas...");

        // 1. Carregar as disciplinas já existentes
        Persistence<Disciplina> disciplinaPersistence = new DisciplinaPersistence();
        List<Disciplina> todasAsDisciplinas = disciplinaPersistence.findAll();

        if (todasAsDisciplinas.isEmpty()) {
            System.out.println("ERRO: O arquivo disciplinas.json está vazio ou não foi encontrado.");
            System.out.println("Por favor, execute o PopulatorDCC primeiro.");
            return;
        }

        // Mapeia disciplinas pelo código para facilitar a busca
        Map<String, Disciplina> mapaDeDisciplinas = new HashMap<>();
        for (Disciplina d : todasAsDisciplinas) {
            mapaDeDisciplinas.put(d.getCodigo(), d);
        }

        // 2. Criar a lista de turmas
        List<Turma> listaDeTurmas = new ArrayList<>();

        // --- Adicionando turmas para as disciplinas de DCC ---
        // A lógica abaixo é baseada nos planos departamentais.
        // Você pode ajustar capacidades, salas e horários conforme necessário.

        // Exemplo para DCC199 - ALGORITMO I
        Disciplina dcc199 = mapaDeDisciplinas.get("DCC199");
        if (dcc199 != null) {
            listaDeTurmas.add(new Turma('A', 60, 0, DiaDaSemana.SEGUNDA, LocalTime.of(19, 0), LocalTime.of(21, 0), "Sala 302", dcc199));
            listaDeTurmas.add(new Turma('B', 60, 0, DiaDaSemana.TERÇA, LocalTime.of(19, 0), LocalTime.of(21, 0), "Sala 303", dcc199));
        }

        // Exemplo para DCC200 - ALGORITMO II
        Disciplina dcc200 = mapaDeDisciplinas.get("DCC200");
        if (dcc200 != null) {
            listaDeTurmas.add(new Turma('A', 50, 0, DiaDaSemana.QUARTA, LocalTime.of(21, 0), LocalTime.of(23, 0), "Sala 4101", dcc200));
        }

        // Exemplo para DCC013 - ESTRUTURA DE DADOS I
        Disciplina dcc013 = mapaDeDisciplinas.get("DCC013");
        if (dcc013 != null) {
            listaDeTurmas.add(new Turma('A', 45, 0, DiaDaSemana.QUINTA, LocalTime.of(19, 0), LocalTime.of(21, 0), "Lab 1", dcc013));
            listaDeTurmas.add(new Turma('B', 45, 0, DiaDaSemana.SEXTA, LocalTime.of(19, 0), LocalTime.of(21, 0), "Lab 2", dcc013));
        }

        // Exemplo para DCC025 - ORIENTAÇÃO A OBJETO I
        Disciplina dcc025 = mapaDeDisciplinas.get("DCC025");
        if (dcc025 != null) {
            listaDeTurmas.add(new Turma('A', 50, 0, DiaDaSemana.SEGUNDA, LocalTime.of(21, 0), LocalTime.of(23, 0), "Sala 201", dcc025));
        }

        // ... continue adicionando as outras turmas seguindo o mesmo padrão ...
        // Verifique se a disciplina existe no mapa antes de criar a turma.


        // 3. Salvar a lista de turmas no arquivo JSON
        System.out.println("Salvando " + listaDeTurmas.size() + " turmas em turmas.json...");
        Persistence<Turma> turmasPersistence = new TurmaPersistence();
        turmasPersistence.save(listaDeTurmas);

        System.out.println("Processo concluído! Arquivo turmas.json foi criado/atualizado.");
    }

    private static void criaDisciplinas() {
        System.out.println("Iniciando a criação das disciplinas...");

        // Mapa para armazenar e buscar disciplinas já criadas pelo código.
        Map<String, Disciplina> mapaDeDisciplinas = new HashMap<>();

        // --- Pré-Carga de Disciplinas de outros departamentos (essencial para os pré-requisitos) ---
        // Adicionando placeholders. Você pode completar com os dados corretos.
        mapaDeDisciplinas.put("MAT154", new DisciplinaObrigatoria("CÁLCULO I", "MAT154", 60, new ArrayList<>(), new ArrayList<>()));
        mapaDeDisciplinas.put("MAT155", new DisciplinaObrigatoria("GEOMETRIA ANALÍTICA E SISTEMA LINEARES I", "MAT155", 60, new ArrayList<>(), new ArrayList<>()));
        mapaDeDisciplinas.put("MAT156", new DisciplinaObrigatoria("CÁLCULO II", "MAT156", 60, new ArrayList<>(), new ArrayList<>()));
        // ... adicione outras disciplinas não-DCC aqui se necessário

        // --- Período 1 ---
        System.out.println("Criando disciplinas do Período 1...");
        Disciplina dcc199 = new DisciplinaObrigatoria("ALGORITMO I", "DCC199", 90, new ArrayList<>(), new ArrayList<>());
        mapaDeDisciplinas.put("DCC199", dcc199);
        Disciplina dc5199 = new DisciplinaObrigatoria("ALGORITMO I - PRÁTICA", "DC5199", 0, new ArrayList<>(), new ArrayList<>());
        mapaDeDisciplinas.put("DC5199", dc5199);


        // --- Período 2 ---
        System.out.println("Criando disciplinas do Período 2...");
        // Pré-req para DCC200
        ArrayList<ValidadorPreRequisito> preReqDCC200 = new ArrayList<>();
        preReqDCC200.add(new ValidadorSimples(mapaDeDisciplinas.get("DCC199")));
        Disciplina dcc200 = new DisciplinaObrigatoria("ALGORITMO II", "DCC200", 90, preReqDCC200, new ArrayList<>());
        mapaDeDisciplinas.put("DCC200", dcc200);

        Disciplina dcc5200 = new DisciplinaObrigatoria("ALGORITMO II - PRÁTICA", "DCC5200", 0, new ArrayList<>(), new ArrayList<>());
        mapaDeDisciplinas.put("DCC5200", dcc5200);


        // --- Período 3 ---
        System.out.println("Criando disciplinas do Período 3...");
        // Pré-req para DCC013
        ArrayList<ValidadorPreRequisito> preReqDCC013 = new ArrayList<>();
        preReqDCC013.add(new ValidadorSimples(mapaDeDisciplinas.get("DCC200")));
        Disciplina dcc013 = new DisciplinaObrigatoria("ESTRUTURA DE DADOS I", "DCC013", 60, preReqDCC013, new ArrayList<>());
        mapaDeDisciplinas.put("DCC013", dcc013);
        
        // Pré-req para DCC025
        ArrayList<ValidadorPreRequisito> preReqDCC025 = new ArrayList<>();
        preReqDCC025.add(new ValidadorSimples(mapaDeDisciplinas.get("DCC200")));
        Disciplina dcc025 = new DisciplinaObrigatoria("ORIENTAÇÃO A OBJETO I", "DCC025", 60, preReqDCC025, new ArrayList<>());
        mapaDeDisciplinas.put("DCC025", dcc025);

        // Disciplinas sem pré-req de DCC neste período
        mapaDeDisciplinas.put("DCC122", new DisciplinaObrigatoria("CIRCUITOS DIGITAIS", "DCC122", 60, new ArrayList<>(), new ArrayList<>()));
        mapaDeDisciplinas.put("DCC160", new DisciplinaObrigatoria("LÓGICA E FUNDAMENTOS PARA A COMPUTAÇÃO", "DCC160", 60, new ArrayList<>(), new ArrayList<>()));
        mapaDeDisciplinas.put("DCC202", new DisciplinaObrigatoria("DESENVOLVIMENTO WEB", "DCC202", 30, new ArrayList<>(), new ArrayList<>()));


        // --- Período 4 ---
        System.out.println("Criando disciplinas do Período 4...");
        // Pré-req para DCC008
        ArrayList<ValidadorPreRequisito> preReqDCC008 = new ArrayList<>();
        preReqDCC008.add(new ValidadorLogicoAND(
            new ValidadorSimples(mapaDeDisciplinas.get("DCC199")),
            new ValidadorSimples(mapaDeDisciplinas.get("MAT155"))
        ));
        Disciplina dcc008 = new DisciplinaObrigatoria("CÁLCULO NUMÉRICO", "DCC008", 60, preReqDCC008, new ArrayList<>());
        mapaDeDisciplinas.put("DCC008", dcc008);
        
        // ... continue o padrão para as outras disciplinas ...
        // Exemplo: DCC012
        ArrayList<ValidadorPreRequisito> preReqDCC012 = new ArrayList<>();
        preReqDCC012.add(new ValidadorSimples(mapaDeDisciplinas.get("DCC013")));
        Disciplina dcc012 = new DisciplinaObrigatoria("ESTRUTURA DE DADOS II", "DCC012", 60, preReqDCC012, new ArrayList<>());
        mapaDeDisciplinas.put("DCC012", dcc012);

        ArrayList<ValidadorPreRequisito> preReqDCC070 = new ArrayList<>();
        preReqDCC070.add(new ValidadorSimples(mapaDeDisciplinas.get("DCC122")));
        Disciplina dcc070 = new DisciplinaObrigatoria("ORGANIZAÇÃO DE COMPUTADORES I", "DCC070", 60, preReqDCC070, new ArrayList<>());
        mapaDeDisciplinas.put("DCC070", dcc070);

        ArrayList<ValidadorPreRequisito> preReqDCC117 = new ArrayList<>();
        preReqDCC117.add(new ValidadorSimples(mapaDeDisciplinas.get("DCC025")));
        Disciplina dcc117 = new DisciplinaObrigatoria("MODELAGEM DE SISTEMA I", "DCC117", 60, preReqDCC117, new ArrayList<>());
        mapaDeDisciplinas.put("DCC117", dcc117);


        // --- Período 5 ---
        System.out.println("Criando disciplinas do Período 5...");
        // ... continue o mesmo padrão para o Período 5 ...
        
        // --- Salve tudo no arquivo JSON ---
        System.out.println("Salvando disciplinas em disciplinas.json...");
        Persistence<Disciplina> disciplinaPersistence = new DisciplinaPersistence();
        disciplinaPersistence.save(new ArrayList<>(mapaDeDisciplinas.values()));
        System.out.println("Processo concluído! Arquivo disciplinas.json foi criado/atualizado.");
    }
}   