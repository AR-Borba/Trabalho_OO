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

public class ValidadorLogicoORTest {
    private Aluno aluno;
    private Map<Disciplina, Double> historico = new HashMap<>();
    private List<Turma> gradeFutura;

    private DisciplinaObrigatoria organizacaoComputadores;
    private DisciplinaObrigatoria circuitosDigitais;
    private DisciplinaObrigatoria eletronicaDigital;
    private ArrayList<ValidadorPreRequisito> vazia = new ArrayList<>();

    
    @BeforeEach
    void setUp() {
        aluno = new Aluno("Aluno de Teste", "202501001", 70, historico, gradeFutura);
        organizacaoComputadores = new DisciplinaObrigatoria("Organização de Computadors", "DCC070" ,4, vazia, null);
        circuitosDigitais = new DisciplinaObrigatoria("Circuitos digitais", "DCC122",  4, vazia, null);
        eletronicaDigital = new DisciplinaObrigatoria("Eletrônica Digital", "DCC123",  4, vazia, null);
    }
    
    @Test
    void deveRetornarTrueSePreRequisitoFoiAprovado() {
        
        aluno.adicionarAoHistorico(circuitosDigitais, 70.0);
        ValidadorSimples validador = new ValidadorSimples();

        // Act: Executa a validação
        boolean resultado = validador.validar(aluno, organizacaoComputadores);

        // Assert: Verifica se o resultado é verdadeiro
        assertTrue(resultado);
    }

    @Test
    void deveRetornarFalseSePreRequisitoFoiReprovado() {
        //[cite_start]// Arrange: Adiciona o pré-requisito com nota de reprovação [cite: 66, 98, 166]
        aluno.adicionarAoHistorico(eletronicaDigital, 80);
        ValidadorSimples validador = new ValidadorSimples();

        // Act
        boolean resultado = validador.validar(aluno, organizacaoComputadores);

        // Assert
        assertFalse(resultado);
    }
}
