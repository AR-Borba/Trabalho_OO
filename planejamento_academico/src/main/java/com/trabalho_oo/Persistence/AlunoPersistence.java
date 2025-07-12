package com.trabalho_oo.Persistence;

import com.trabalho_oo.entities.Aluno;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class AlunoPersistence implements Persistence<Aluno> {

    private static final String PATH = DIRECTORY + File.separator + "alunos.json";

    @Override
    public void save(List<Aluno> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();

        Arquivo.salva(PATH, json);

    }

    @Override
    public List<Aluno> findAll() {
        Gson gson = new Gson();
        String json = Arquivo.le(PATH);

        List<Aluno> itens = new ArrayList<>();

        if(!json.trim().equals("")) {
            Type tipoLista = new TypeToken<List<Aluno>>() {
            }.getType();
            itens = gson.fromJson(json, tipoLista);

                if(itens == null)
                    itens = new ArrayList<>();
        }

        return itens;
    }
    
}
