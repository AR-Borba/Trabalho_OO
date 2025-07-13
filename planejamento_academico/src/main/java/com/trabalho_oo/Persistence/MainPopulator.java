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

/**
 * Classe final para popular os arquivos JSON com os dados iniciais do sistema.
 * Executar este main() uma única vez para gerar 'disciplinas.json' e 'turmas.json'.
 */
public class MainPopulator {

    public static void main(String[] args) {
        // Passo 1: Cria todas as disciplinas em memória e salva no ficheiro.
        Map<String, Disciplina> mapaDeDisciplinas = criaDisciplinas();

        // Passo 2: Se a criação foi bem-sucedida, passa o mapa de disciplinas diretamente
        // para o método que cria as turmas, evitando erros de leitura de ficheiro.
        if (mapaDeDisciplinas != null && !mapaDeDisciplinas.isEmpty()) {
            criaTurmas(mapaDeDisciplinas);
        } else {
            System.out.println("ERRO: Não foi possível criar as disciplinas. A criação de turmas foi abortada.");
        }
    }

    /**
     * Cria e salva as turmas para as disciplinas existentes.
     * Gera no máximo 2 turmas para cada disciplina de DCC.
     * @param mapaDeDisciplinas O mapa de disciplinas já criado em memória.
     */
    private static void criaTurmas(Map<String, Disciplina> mapaDeDisciplinas) {
       System.out.println("Iniciando a criação das turmas...");
        List<Turma> listaDeTurmas = new ArrayList<>();

        // Itera sobre todas as disciplinas de DCC que foram criadas
        for (Disciplina disciplina : mapaDeDisciplinas.values()) {
            if (disciplina.getCodigo().startsWith("DCC")) {
                
                // --- Turma A (criada para todas as disciplinas de DCC) ---
                // Horários e salas de exemplo. Ajuste conforme necessário.
                if (disciplina.getCodigo().equals("DCC199") || disciplina.getCodigo().equals("DCC200")) {
                    // Exemplo: Disciplinas de Algoritmos com horário noturno
                    listaDeTurmas.add(new Turma('A', 60, 0, DiaDaSemana.SEGUNDA, LocalTime.of(19, 0), LocalTime.of(20, 40), "Sala 301", disciplina));
                } else if (disciplina.getCodigo().contains("5")) { // Práticas
                     listaDeTurmas.add(new Turma('A', 30, 0, DiaDaSemana.QUARTA, LocalTime.of(19, 0), LocalTime.of(20, 40), "Laboratório 1", disciplina));
                }
                else {
                    // Padrão para outras disciplinas
                    listaDeTurmas.add(new Turma('A', 50, 0, DiaDaSemana.TERÇA, LocalTime.of(21, 0), LocalTime.of(22, 40), "Sala 205", disciplina));
                }

                // --- Turma B (criada para disciplinas selecionadas, até o 3º período) ---
                // Adicionando uma segunda turma para matérias com mais alunos
                String codigo = disciplina.getCodigo();
                if (codigo.equals("DCC199") || codigo.equals("DCC200") || codigo.equals("DCC013") || codigo.equals("DCC025")) {
                    listaDeTurmas.add(new Turma('B', 50, 0, DiaDaSemana.QUINTA, LocalTime.of(19, 0), LocalTime.of(20, 40), "Sala 305", disciplina));
                }
            }
        }

        System.out.println("Salvando " + listaDeTurmas.size() + " turmas em turmas.json...");
        Persistence<Turma> turmasPersistence = new TurmaPersistence();
        turmasPersistence.save(listaDeTurmas);
        System.out.println("Processo concluído! Arquivo turmas.json foi criado/atualizado.");
    }

    /**
     * Cria TODAS as disciplinas da grade, configura os seus pré-requisitos e salva num ficheiro.
     * @return Um mapa com todas as disciplinas criadas.
     */
    private static Map<String, Disciplina> criaDisciplinas() {
        System.out.println("Iniciando a criação de todas as disciplinas...");
        Map<String, Disciplina> mapaDeDisciplinas = new HashMap<>();

        // --- Pré-Carga de Disciplinas de outros departamentos ---
        mapaDeDisciplinas.put("MAT154", new DisciplinaObrigatoria("CÁLCULO I", "MAT154", 60, new ArrayList<>(), new ArrayList<>()));
        mapaDeDisciplinas.put("MAT155", new DisciplinaObrigatoria("GEOMETRIA ANALÍTICA E SISTEMA LINEARES I", "MAT155", 60, new ArrayList<>(), new ArrayList<>()));
        mapaDeDisciplinas.put("MAT156", new DisciplinaObrigatoria("CÁLCULO II", "MAT156", 60, new ArrayList<>(), new ArrayList<>()));
        mapaDeDisciplinas.put("MAT157", new DisciplinaObrigatoria("CÁLCULO III", "MAT157", 60, new ArrayList<>(), new ArrayList<>()));
        mapaDeDisciplinas.put("MAT025", new DisciplinaObrigatoria("EQUAÇÕES DIFERENCIAIS I", "MAT025", 60, new ArrayList<>(), new ArrayList<>()));
        mapaDeDisciplinas.put("MAT158", new DisciplinaObrigatoria("ÁLGEBRA LINEAR", "MAT158", 60, new ArrayList<>(), new ArrayList<>()));
        mapaDeDisciplinas.put("EST028", new DisciplinaObrigatoria("INTRODUÇÃO A ESTATÍSTICA", "EST028", 60, new ArrayList<>(), new ArrayList<>()));

        // --- Período 1 ---
        mapaDeDisciplinas.put("DCC199", new DisciplinaObrigatoria("ALGORITMO I", "DCC199", 90, new ArrayList<>(), new ArrayList<>()));
        mapaDeDisciplinas.put("DCC5199", new DisciplinaObrigatoria("ALGORITMO I - PRÁTICA", "DCC5199", 0, new ArrayList<>(), new ArrayList<>()));

        // --- Período 2 ---
        ArrayList<ValidadorPreRequisito> preReqDCC200 = new ArrayList<>();
        preReqDCC200.add(new ValidadorSimples(mapaDeDisciplinas.get("DCC199")));
        mapaDeDisciplinas.put("DCC200", new DisciplinaObrigatoria("ALGORITMO II", "DCC200", 90, preReqDCC200, new ArrayList<>()));
        mapaDeDisciplinas.put("DCC5200", new DisciplinaObrigatoria("ALGORITMO II - PRÁTICA", "DCC5200", 0, new ArrayList<>(), new ArrayList<>()));

        // --- Período 3 ---
        ArrayList<ValidadorPreRequisito> preReqDCC013 = new ArrayList<>();
        preReqDCC013.add(new ValidadorSimples(mapaDeDisciplinas.get("DCC200")));
        mapaDeDisciplinas.put("DCC013", new DisciplinaObrigatoria("ESTRUTURA DE DADOS I", "DCC013", 60, preReqDCC013, new ArrayList<>()));

        ArrayList<ValidadorPreRequisito> preReqDCC025 = new ArrayList<>();
        preReqDCC025.add(new ValidadorSimples(mapaDeDisciplinas.get("DCC200")));
        mapaDeDisciplinas.put("DCC025", new DisciplinaObrigatoria("ORIENTAÇÃO A OBJETO I", "DCC025", 60, preReqDCC025, new ArrayList<>()));

        mapaDeDisciplinas.put("DCC122", new DisciplinaObrigatoria("CIRCUITOS DIGITAIS", "DCC122", 60, new ArrayList<>(), new ArrayList<>()));
        mapaDeDisciplinas.put("DCC160", new DisciplinaObrigatoria("LÓGICA E FUNDAMENTOS PARA A COMPUTAÇÃO", "DCC160", 60, new ArrayList<>(), new ArrayList<>()));
        mapaDeDisciplinas.put("DCC202", new DisciplinaObrigatoria("DESENVOLVIMENTO WEB", "DCC202", 30, new ArrayList<>(), new ArrayList<>()));

        // --- Período 4 ---
        ArrayList<ValidadorPreRequisito> preReqDCC008 = new ArrayList<>();
        preReqDCC008.add(new ValidadorLogicoAND(new ValidadorSimples(mapaDeDisciplinas.get("DCC199")), new ValidadorSimples(mapaDeDisciplinas.get("MAT155"))));
        mapaDeDisciplinas.put("DCC008", new DisciplinaObrigatoria("CÁLCULO NUMÉRICO", "DCC008", 60, preReqDCC008, new ArrayList<>()));

        ArrayList<ValidadorPreRequisito> preReqDCC012 = new ArrayList<>();
        preReqDCC012.add(new ValidadorSimples(mapaDeDisciplinas.get("DCC013")));
        mapaDeDisciplinas.put("DCC012", new DisciplinaObrigatoria("ESTRUTURA DE DADOS II", "DCC012", 60, preReqDCC012, new ArrayList<>()));
        
        ArrayList<ValidadorPreRequisito> preReqDCC070 = new ArrayList<>();
        preReqDCC070.add(new ValidadorSimples(mapaDeDisciplinas.get("DCC122")));
        mapaDeDisciplinas.put("DCC070", new DisciplinaObrigatoria("ORGANIZAÇÃO DE COMPUTADORES I", "DCC070", 60, preReqDCC070, new ArrayList<>()));

        ArrayList<ValidadorPreRequisito> preReqDCC117 = new ArrayList<>();
        preReqDCC117.add(new ValidadorSimples(mapaDeDisciplinas.get("DCC025")));
        mapaDeDisciplinas.put("DCC117", new DisciplinaObrigatoria("MODELAGEM DE SISTEMA I", "DCC117", 60, preReqDCC117, new ArrayList<>()));

        // --- Período 5 ---
        ArrayList<ValidadorPreRequisito> preReqDCC055 = new ArrayList<>();
        preReqDCC055.add(new ValidadorSimples(mapaDeDisciplinas.get("DCC013")));
        mapaDeDisciplinas.put("DCC055", new DisciplinaObrigatoria("TEORIA DOS GRAFOS", "DCC055", 60, preReqDCC055, new ArrayList<>()));

        ArrayList<ValidadorPreRequisito> preReqDCC060 = new ArrayList<>();
        preReqDCC060.add(new ValidadorLogicoAND(new ValidadorSimples(mapaDeDisciplinas.get("DCC012")), new ValidadorSimples(mapaDeDisciplinas.get("DCC117"))));
        mapaDeDisciplinas.put("DCC060", new DisciplinaObrigatoria("BANCO DE DADOS I", "DCC060", 60, preReqDCC060, new ArrayList<>()));

        ArrayList<ValidadorPreRequisito> preReqDCC061 = new ArrayList<>();
        preReqDCC061.add(new ValidadorSimples(mapaDeDisciplinas.get("DCC117")));
        mapaDeDisciplinas.put("DCC061", new DisciplinaObrigatoria("ENGENHARIA DE SOFTWARE", "DCC061", 60, preReqDCC061, new ArrayList<>()));

        ArrayList<ValidadorPreRequisito> preReqDCC062 = new ArrayList<>();
        preReqDCC062.add(new ValidadorSimples(mapaDeDisciplinas.get("DCC070")));
        mapaDeDisciplinas.put("DCC062", new DisciplinaObrigatoria("SISTEMA OPERACIONAIS", "DCC062", 60, preReqDCC062, new ArrayList<>()));

        ArrayList<ValidadorPreRequisito> preReqDCC095 = new ArrayList<>();
        preReqDCC095.add(new ValidadorLogicoAND(new ValidadorSimples(mapaDeDisciplinas.get("DCC199")), new ValidadorSimples(mapaDeDisciplinas.get("MAT156"))));
        mapaDeDisciplinas.put("DCC095", new DisciplinaObrigatoria("COMPUTAÇÃO GRÁFICA", "DCC095", 60, preReqDCC095, new ArrayList<>()));
        
        ArrayList<ValidadorPreRequisito> preReqDCC203 = new ArrayList<>();
        preReqDCC203.add(new ValidadorSimples(mapaDeDisciplinas.get("DCC200")));
        mapaDeDisciplinas.put("DCC203", new DisciplinaObrigatoria("METODOLOGIA CIENTÍFICA", "DCC203", 30, preReqDCC203, new ArrayList<>()));
        
        System.out.println("Salvando disciplinas em disciplinas.json...");
        Persistence<Disciplina> disciplinaPersistence = new DisciplinaPersistence();
        disciplinaPersistence.save(new ArrayList<>(mapaDeDisciplinas.values()));
        System.out.println("Processo concluído! Arquivo disciplinas.json foi criado/atualizado.");

        return mapaDeDisciplinas;
    }
}
