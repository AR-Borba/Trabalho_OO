package com.trabalho_oo.Persistence;

import java.util.List;

import com.trabalho_oo.entities.Turma;

public interface TurmasPersistence {
    
     String DIRECTORY = "data";
    public void save(List<Turma> turmas);
    public List<Turma> findAll();
}
