package com.trabalho_oo.Persistence;

import java.io.File;
import java.util.List;

public interface Persistence<T> {

    String DIRECTORY = "Trabalho_OO" + File.separator + "planejamento_academico" + File.separator + "data";
    void save(List<T> itens);
    List<T> findAll();
}
