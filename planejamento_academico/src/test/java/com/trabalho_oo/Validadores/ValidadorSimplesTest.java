package com.trabalho_oo.Validadores;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.trabalho_oo.entities.*;
import com.trabalho_oo.entities.Disciplinas.*;
// import com.trabalho_oo.Validadores.ValidadorPreRequisito;
// import com.trabalho_oo.Validadores.ValidadorSimples;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidadorSimplesTest {

    private Aluno aluno;
    private Map<Disciplina, Double> historico = new HashMap<>();
    private List<Turma> gradeFutura;

    private DisciplinaObrigatoria calculo1;
    private DisciplinaObrigatoria calculo2;
    private ArrayList<ValidadorPreRequisito> vazia = new ArrayList<>();
    
    @BeforeEach
    void setUp() {
        aluno = new Aluno("Aluno de Teste", "202501001", 70, historico, gradeFutura);
        calculo1 = new DisciplinaObrigatoria("Cálculo 1", "MAT001" ,4, vazia, null);
        calculo2 = new DisciplinaObrigatoria("Cálculo 2", "MAT002",  4, vazia, null);  
    }

    @Test
    void deveRetornarTrueSePreRequisitoFoiAprovado() {
        aluno.adicionarAoHistorico(calculo1, 70.0);
        ValidadorSimples validador = new ValidadorSimples(calculo1);

        boolean resultado = validador.validar(aluno, calculo2);
        
        assertTrue(resultado);
    }

    @Test
    void deveRetornarFalseSePreRequisitoFoiReprovado() {
        aluno.adicionarAoHistorico(calculo1, 30);
        ValidadorSimples validador = new ValidadorSimples(calculo1);

        boolean resultado = validador.validar(aluno, calculo2);

        assertFalse(resultado);
    }

    @Test
    void deveRetornarFalseSePreRequisitoNaoFoiCursado() {
        ValidadorSimples validador = new ValidadorSimples();

        boolean resultado = validador.validar(aluno, calculo2);

        assertFalse(resultado);
    }
}