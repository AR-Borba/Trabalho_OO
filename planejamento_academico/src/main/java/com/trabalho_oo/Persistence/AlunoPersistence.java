package com.trabalho_oo.Persistence;

import java.util.List;

import com.trabalho_oo.entities.Aluno;

public interface AlunoPersistence {
     String DIRECTORY = "data";
    public void save(List<Aluno> alunos);
    public List<Aluno> findAll();
}
