package com.trabalho_oo.Persistence;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trabalho_oo.entities.Disciplinas.Disciplina;

public class DisciplinaPersistence implements Persistence<Disciplina> {

    private static final String PATH = DIRECTORY + File.separator + "disciplinas.json";

    @Override
    public void save(List<Disciplina> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();

        Arquivo.salva(PATH, json);

    }

    @Override
    public List<Disciplina> findAll() {
        Gson gson = new Gson();
        String json = Arquivo.le(PATH);

        List<Disciplina> itens = new ArrayList<>();

        if(!json.trim().equals("")) {
            Type tipoLista = new TypeToken<List<Disciplina>>() {
            }.getType();
            itens = gson.fromJson(json, tipoLista);

                if(itens == null)
                    itens = new ArrayList<>();
        }

        return itens;
    }
    
}
