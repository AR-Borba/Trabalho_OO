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
//import com.trabalho_oo.entities.Disciplinas.Disciplina;
import com.trabalho_oo.entities.Disciplinas.DisciplinaObrigatoria;

public class ValidadorLogicoANDTest {
    private Aluno aluno;
    private Map<String, Double> historico = new HashMap<>();
    private List<Turma> gradeFutura;

    private DisciplinaObrigatoria calcNum;
    // private DisciplinaObrigatoria calcII;
    // private DisciplinaObrigatoria algoritmos;
    private ArrayList<ValidadorPreRequisito> vazia = new ArrayList<>();
    private List<String> preRequisitos;

    @BeforeEach
    void setUp() {
        aluno = new Aluno("Aluno de Teste", "202501001", 70, historico, gradeFutura);
        calcNum = new DisciplinaObrigatoria("Cálculo numérico", "DCC008" ,4, vazia, null);
        // calcII = new DisciplinaObrigatoria("Calculo II", "MAT002",  4, vazia, null);
        // algoritmos = new DisciplinaObrigatoria("Algoritmos I", "DCC001",  4, vazia, null);
        preRequisitos = List.of("Calculo II", "Algoritmos I");
    }
    
    @Test
    void Retorna_True_Para_PreRequisito_Aprovado1() {
        aluno.adicionarAoHistorico("Calculo II", 70.0);
        aluno.adicionarAoHistorico("Algoritmos I", 70.0);

        ValidadorSimples validadorCalcII = new ValidadorSimples(calcII);
        ValidadorSimples validadorAlgoritmos = new ValidadorSimples(algoritmos);

        ValidadorLogicoAND validador = new ValidadorLogicoAND(preRequisitos);

        boolean resultado = validador.validar(aluno, calcNum);

        assertTrue(resultado);
    }

    @Test
    void Retorna_FalsePara_PreRequisito_Reprovado2() {
        aluno.adicionarAoHistorico("Calculo II", 70.0);
        aluno.adicionarAoHistorico("Algoritmos I", 46.0);
       
        ValidadorSimples validadorCalcII = new ValidadorSimples(calcII);
        ValidadorSimples validadorAlgoritmos = new ValidadorSimples(algoritmos);

        ValidadorLogicoAND validador = new ValidadorLogicoAND(preRequisitos);

        boolean resultado = validador.validar(aluno, calcNum);

        assertFalse(resultado);
    }
}