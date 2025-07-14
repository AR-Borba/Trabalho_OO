package com.trabalho_oo.Validadores;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.trabalho_oo.entities.Aluno;
import com.trabalho_oo.entities.Turma;
import com.trabalho_oo.entities.Disciplinas.Disciplina;
import com.trabalho_oo.entities.Disciplinas.DisciplinaObrigatoria;

public class ValidadorCreditosMinimosTest {

    private Aluno aluno;
    private Map<String, Double> historico = new HashMap<>();
    private List<Turma> gradeFutura;

    private DisciplinaObrigatoria calculo1;
    private DisciplinaObrigatoria algoritmos;
    private DisciplinaObrigatoria TCC;
    private ArrayList<ValidadorPreRequisito> vazia = new ArrayList<>();
    
    @BeforeEach
    void setUp() {
        aluno = new Aluno("Aluno de Teste", "202501001", 70, historico, gradeFutura);
        calculo1 = new DisciplinaObrigatoria("Cálculo 1", "MAT001" ,4, vazia, null);
        algoritmos = new DisciplinaObrigatoria("Algoritmos", "DCC001" ,4, vazia, null);
        TCC = new DisciplinaObrigatoria("Trabalho de conclusão de curso", "DCC204",  4, vazia, null);
        
    }

    @Test
    void Retorna_True_Para_Creditos_Validos() {
        aluno.adicionarAoHistorico("Cálculo 1", 70.0);
        aluno.adicionarAoHistorico("Algoritmos", 70.0);
        ValidadorCreditosMinimos validador = new ValidadorCreditosMinimos(8);

        boolean resultado = validador.validar(aluno, TCC);
        
        assertTrue(resultado);
    }

    @Test
    void Retorna_False_Para_Creditos_Insuficientes() {
        aluno.adicionarAoHistorico("Cálculo 1", 70.0);
        aluno.adicionarAoHistorico("Algoritmos", 70.0);
        ValidadorCreditosMinimos validador = new ValidadorCreditosMinimos(20);

        boolean resultado = validador.validar(aluno, TCC);

        assertFalse(resultado);
    }
}