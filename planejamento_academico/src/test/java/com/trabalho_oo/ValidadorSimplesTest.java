package com.trabalho_oo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.trabalho_oo.Disciplinas.DisciplinaObrigatoria;
import com.trabalho_oo.Models.CodigoDisciplina;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class ValidadorSimplesTest {

    private Aluno aluno;
    private DisciplinaObrigatoria calculo1;
    private DisciplinaObrigatoria calculo2;
    private ArrayList<CodigoDisciplina> vazia;
    private CodigoDisciplina calc1; 

    @BeforeEach
    void setUp() {
        aluno = new Aluno("Aluno de Teste", "202501001");
        calculo1 = new DisciplinaObrigatoria("Cálculo 1", calc1 ,4, vazia, vazia);
        calculo2 = new DisciplinaObrigatoria("Cálculo 2", "MAT002",  4, vazia, vazia);
    }

    @Test
    void deveRetornarTrueSePreRequisitoFoiAprovado() {
        [cite_start]// Arrange: Adiciona o pré-requisito como cursado e aprovado ao aluno [cite: 66, 98]
        aluno.adicionarDisciplinaCursada(new DisciplinaCursada(calculo1, 70.0));
        ValidadorSimples validador = new ValidadorSimples(calculo1);

        // Act: Executa a validação
        boolean resultado = validador.validar(aluno, calculo2);

        // Assert: Verifica se o resultado é verdadeiro
        assertTrue(resultado);
    }

    @Test
    void deveRetornarFalseSePreRequisitoFoiReprovado() {
        [cite_start]// Arrange: Adiciona o pré-requisito com nota de reprovação [cite: 66, 98, 166]
        aluno.adicionarDisciplinaCursada(new DisciplinaCursada(calculo1, 59.9));
        ValidadorSimples validador = new ValidadorSimples(calculo1);

        // Act
        boolean resultado = validador.validar(aluno, calculo2);

        // Assert
        assertFalse(resultado);
    }

    @Test
    void deveRetornarFalseSePreRequisitoNaoFoiCursado() {
        // Arrange: O aluno não tem disciplinas cursadas
        ValidadorSimples validador = new ValidadorSimples(calculo1);

        // Act
        boolean resultado = validador.validar(aluno, calculo2);

        // Assert
        assertFalse(resultado);
    }
}