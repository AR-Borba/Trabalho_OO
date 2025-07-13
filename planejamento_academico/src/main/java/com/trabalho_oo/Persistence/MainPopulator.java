package com.trabalho_oo.Persistence;

import com.trabalho_oo.entities.Aluno;
import com.trabalho_oo.entities.Turma;
import com.trabalho_oo.entities.Disciplinas.Disciplina;

public class MainPopulator {
    
    public static void main(String[] args) {
        Persistence<Disciplina> disciplinaPersistence = new DisciplinaPersistence();
        Persistence<Turma> turmaPersistence = new TurmaPersistence();
        Persistence<Aluno> alunoPersistence = new AlunoPersistence();

        
    }
}
