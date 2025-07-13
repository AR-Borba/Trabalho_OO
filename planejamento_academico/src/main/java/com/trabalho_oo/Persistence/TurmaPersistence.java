package com.trabalho_oo.Persistence;

import com.trabalho_oo.Utils.GsonFactory;
import com.trabalho_oo.entities.Turma;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TurmaPersistence implements Persistence<Turma> {

    private static final String PATH = DIRECTORY + File.separator + "turmas.json";
    private final Gson gson = GsonFactory.getCustomGson();

    @Override
    public void save(List<Turma> itens) {
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();

        Arquivo.salva(PATH, json);

    }

    @Override
    public List<Turma> findAll() {
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
