package com.trabalho_oo.Persistence;

import com.trabalho_oo.Utils.GsonFactory;
import com.trabalho_oo.entities.Disciplinas.Disciplina;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DisciplinaPersistence implements Persistence<Disciplina> {

    private static final String PATH = DIRECTORY + File.separator + "disciplinas.json";
    private final Gson gson = GsonFactory.getCustomGson();

    @Override
    public void save(List<Disciplina> itens) {
        System.out.println("DEBUG: Tentando salvar o ficheiro em: " + new File(PATH).getAbsolutePath());
        
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();

        Arquivo.salva(PATH, json);

    }

    @Override
    public List<Disciplina> findAll() {
        System.out.println("DEBUG: Tentando ler o ficheiro de: " + new File(PATH).getAbsolutePath());
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
