package com.trabalho_oo.Validadores;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.trabalho_oo.Models.DiaDaSemana;
import com.trabalho_oo.entities.Aluno;
import com.trabalho_oo.entities.Turma;
import com.trabalho_oo.entities.Disciplinas.Disciplina;
import com.trabalho_oo.entities.Disciplinas.DisciplinaObrigatoria;

public class ValidadorCorequisitoTest {

    private Aluno aluno;
    private Map<Disciplina, Double> historico = new HashMap<>();
    private List<Turma> gradeFutura;

    private DisciplinaObrigatoria algIIsala;
    private DisciplinaObrigatoria algIIpratica;
    private ArrayList<ValidadorPreRequisito> vazia = new ArrayList<>();
    private Turma algIIsalaTurma;
    private Turma algIIpraticaTurma;
    private DiaDaSemana diaSemana = DiaDaSemana.SEGUNDA;
    private LocalDateTime horarioInicio;

    @BeforeEach
    void setUp() {
        aluno = new Aluno("Aluno de Teste", "202501001", 70, historico, gradeFutura);
        algIIsala = new DisciplinaObrigatoria("Algoritmos II - Sala", "DCC200" ,4, vazia, null);
        algIIpratica = new DisciplinaObrigatoria("Algoritmos II - Pratica", "DCC201" ,4, vazia, null);        
        algIIsalaTurma = new Turma('A', 30, 0, diaSemana, "08:00", "10:00", "Sala 101", algIIsala);
        algIIpraticaTurma = new Turma(algIIpratica, "Segunda", "10:00", "12:00", 30);
        gradeFutura = new ArrayList<>();
        gradeFutura.add(algIIsalaTurma);
        gradeFutura.add(algIIpraticaTurma);
    }

    @Test
    void deveRetornarTrueSePreRequisitoFoiAprovado() {
        aluno.adicionarAoPlanejamento(null);
        ValidadorCreditosMinimos validador = new ValidadorCreditosMinimos(8);

        boolean resultado = validador.validar(aluno, TCC);
        
        assertTrue(resultado);
    }

    @Test
    void deveRetornarFalseSePreRequisitoFoiReprovado() {
        aluno.adicionarAoHistorico(calculo1, 70.0);
        aluno.adicionarAoHistorico(algoritmos, 70.0);
        ValidadorCreditosMinimos validador = new ValidadorCreditosMinimos(20);

        boolean resultado = validador.validar(aluno, TCC);

        assertFalse(resultado);
    }
}
