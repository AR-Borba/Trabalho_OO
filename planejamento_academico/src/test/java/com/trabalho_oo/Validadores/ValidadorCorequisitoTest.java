package com.trabalho_oo.Validadores;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.trabalho_oo.Models.DiaDaSemana;
import com.trabalho_oo.entities.Aluno;
import com.trabalho_oo.entities.Turma;
import com.trabalho_oo.entities.Disciplinas.DisciplinaObrigatoria;

public class ValidadorCorequisitoTest {

    private Aluno aluno;
    private Map<String, Double> historico = new HashMap<>();
    private List<Turma> gradeFutura;

    private DisciplinaObrigatoria algIIsala;
    private DisciplinaObrigatoria algIIpratica;
    private ArrayList<ValidadorPreRequisito> vazia = new ArrayList<>();
    private Turma algIIsalaTurma;
    private Turma algIIpraticaTurma;
    private Turma turmaVazia;
    private DiaDaSemana diaSemana = DiaDaSemana.SEGUNDA;
    private LocalTime horarioInicio;

    @BeforeEach
    void setUp() {
        algIIsala         = new DisciplinaObrigatoria("Algoritmos II - Sala", "DCC200" ,4, vazia, null);
        algIIpratica      = new DisciplinaObrigatoria("Algoritmos II - Pratica", "DCC201" ,4, vazia, null);        
        algIIsalaTurma    = new Turma('A', 30, 0, diaSemana, horarioInicio, horarioInicio, "Sala 101", algIIsala);
        algIIpraticaTurma = new Turma('A', 30, 0, diaSemana, horarioInicio, horarioInicio, "Sala 102", algIIpratica);
        gradeFutura       = List.of(algIIpraticaTurma, algIIsalaTurma);
        aluno             = new Aluno("Aluno de Teste", "202501001", 70, historico, gradeFutura);
    }

    @Test
    void Retorna_True_Para_CoRequisito_Matriculado() {
        aluno.adicionarAoPlanejamento(algIIpraticaTurma);
        ValidadorCorequisito validador = new ValidadorCorequisito(algIIsala);

        boolean resultado = validador.validar(aluno, algIIpratica);
        
        assertTrue(resultado);
    }

    @Test
    void Retorna_False_Para_CoRequisito_NAO_Matriculado() {
        aluno.adicionarAoPlanejamento(turmaVazia);
        ValidadorCorequisito validador = new ValidadorCorequisito(algIIsala);

        boolean resultado = validador.validar(aluno, algIIpratica);
        
        assertFalse(resultado);
    }
}
