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
    private LocalTime horarioInicio;

    @BeforeEach
    void setUp() {
        aluno             = new Aluno("Aluno de Teste", "202501001", 70, historico, gradeFutura);
        algIIsala         = new DisciplinaObrigatoria("Algoritmos II - Sala", "DCC200" ,4, vazia, null);
        algIIpratica      = new DisciplinaObrigatoria("Algoritmos II - Pratica", "DCC201" ,4, vazia, null);        
        algIIsalaTurma    = new Turma('A', 30, 0, diaSemana, horarioInicio, horarioInicio, "Sala 101", algIIsala);
        algIIpraticaTurma = new Turma('A', 30, 0, diaSemana, horarioInicio, horarioInicio, "Sala 101", algIIsala);
        gradeFutura       = new ArrayList<>();
        gradeFutura.add(algIIsalaTurma);
        gradeFutura.add(algIIpraticaTurma);
    }

    @Test
    void Retorna_True_Para_CoRequisito_Matriculado() {
        aluno.adicionarAoPlanejamento(null);
        ValidadorCorequisito validador = new ValidadorCorequisito(algIIsala);

        boolean resultado = validador.validar(aluno, algIIpratica);
        
    //     assertTrue(resultado);
    // }

    @Test
    void Retorna_False_Para_CoRequisito_NAO_Matriculado() {
        aluno.adicionarAoPlanejamento(null);
        ValidadorCorequisito validador = new ValidadorCorequisito(algIIsala);

        boolean resultado = validador.validar(aluno, algIIpratica);
        
        assertFalse(resultado);
    }
}
