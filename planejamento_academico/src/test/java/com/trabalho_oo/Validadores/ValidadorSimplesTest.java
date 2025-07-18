package com.trabalho_oo.Validadores;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.trabalho_oo.entities.*;
import com.trabalho_oo.entities.Disciplinas.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidadorSimplesTest {

    private Aluno aluno;
    private Map<String, Double> historico = new HashMap<>();
    private List<Turma> gradeFutura;

    private DisciplinaObrigatoria calculo1;
    private DisciplinaObrigatoria calculo2;
    private ArrayList<ValidadorPreRequisito> vazia = new ArrayList<>();
    
    @BeforeEach
    void setUp() {
        aluno = new Aluno("Aluno de Teste", "202501001", 70, historico, gradeFutura);
        calculo1 = new DisciplinaObrigatoria("Cálculo 1", "MAT154" ,4, vazia, null);
        calculo2 = new DisciplinaObrigatoria("Cálculo 2", "MAT156",  4, vazia, null);  
    }

    @Test //caso padrao
    void Retorna_True_Para_PreRequisito_Aprovado() {
        aluno.adicionarAoHistorico("MAT154", 70.0);
        ValidadorSimples validador = new ValidadorSimples(calculo1);

        boolean resultado = validador.validar(aluno, calculo2);
        
        assertTrue(resultado);
    }

    @Test // reprovado na disciplina anterior
    void Retorna_False_Para_PreRequisito_Reprovado() {
        aluno.adicionarAoHistorico("MAT154", 30);
        ValidadorSimples validador = new ValidadorSimples(calculo1);

        boolean resultado = validador.validar(aluno, calculo2);

        assertFalse(resultado);
    }

    @Test // nao cursou a disciplina anterior
    void Retorna_False_Para_PreRequisito_NaoCursado() {
        ValidadorSimples validador = new ValidadorSimples();

        boolean resultado = validador.validar(aluno, calculo2);

        assertFalse(resultado);
    }
}