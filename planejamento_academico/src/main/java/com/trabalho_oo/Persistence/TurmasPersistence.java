package com.trabalho_oo.Persistence;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trabalho_oo.entities.Turma;

public class TurmasPersistence implements Persistence<Turma> {

    private static final String PATH = DIRECTORY + File.separator + "turmas.json";

    @Override
    public void save(List<Turma> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();

        Arquivo.salva(PATH, json);

    }

    @Override
    public List<Turma> findAll() {
        Gson gson = new Gson();
        String json = Arquivo.le(PATH);

        List<Turma> itens = new ArrayList<>();

        if(!json.trim().equals("")) {
            Type tipoLista = new TypeToken<List<Turma>>() {
            }.getType();
            itens = gson.fromJson(json, tipoLista);

                if(itens == null)
                    itens = new ArrayList<>();
        }

        return itens;
    }

}
