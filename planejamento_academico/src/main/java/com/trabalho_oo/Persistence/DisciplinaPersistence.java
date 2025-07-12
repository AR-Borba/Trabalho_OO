package com.trabalho_oo.Persistence;

import java.util.List;

import com.trabalho_oo.entities.Disciplinas.Disciplina;

public interface DisciplinaPersistence {
    
    String DIRECTORY = "data";
    public void save(List<Disciplina> disciplinas);
    public List<Disciplina> findAll();
}
