package com.trabalho_oo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.trabalho_oo.entities.*;
import com.trabalho_oo.entities.Disciplinas.*;
import com.trabalho_oo.Models.CodigoDisciplina;
import com.trabalho_oo.Validadores.ValidadorPreRequisito;
import com.trabalho_oo.Validadores.ValidadorSimples;

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
    // private ArrayList<Disciplina> vazia;
    private ArrayList<ValidadorPreRequisito> vazia1 = new ArrayList<>();
    // private CodigoDisciplina calc1; 

    @BeforeEach
    void setUp() {
        aluno = new Aluno("Aluno de Teste", "202501001", 70, historico, gradeFutura);
        calculo1 = new DisciplinaObrigatoria("Cálculo 1", null ,4, vazia1, null);
        calculo2 = new DisciplinaObrigatoria("Cálculo 2", null,  4, vazia1, null);
        
    }

    @Test
    void deveRetornarTrueSePreRequisitoFoiAprovado() {
        //[cite_start]// Arrange: Adiciona o pré-requisito como cursado e aprovado ao aluno [cite: 66, 98]
        aluno.adicionarAoHistorico(calculo1, 70.0);
        ValidadorSimples validador = new ValidadorSimples();

        // Act: Executa a validação
        boolean resultado = validador.validar(aluno, calculo2);

        // Assert: Verifica se o resultado é verdadeiro
        assertTrue(resultado, "O resultado deve ser true pois o aluno tem nota para passar");
    }

    @Test
    void deveRetornarFalseSePreRequisitoFoiReprovado() {
        //[cite_start]// Arrange: Adiciona o pré-requisito com nota de reprovação [cite: 66, 98, 166]
        aluno.adicionarAoHistorico(calculo1, 30);
        ValidadorSimples validador = new ValidadorSimples();

        // Act
        boolean resultado = validador.validar(aluno, calculo2);

        // Assert
        assertFalse(resultado);
    }

    @Test
    void deveRetornarFalseSePreRequisitoNaoFoiCursado() {
        // Arrange: O aluno não tem disciplinas cursadas
        ValidadorSimples validador = new ValidadorSimples();

        // Act
        boolean resultado = validador.validar(aluno, calculo2);

        // Assert
        assertFalse(resultado);
    }
}