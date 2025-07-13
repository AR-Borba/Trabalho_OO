package com.trabalho_oo.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.trabalho_oo.entities.Disciplinas.*;

public class GsonFactory {

    private static Gson customGsonInstance;

    public static Gson getCustomGson() {
        if (customGsonInstance == null) {
            // Cria a fábrica para a nossa classe abstrata 'Disciplina'
            // O primeiro argumento "type" é o nome do campo no JSON que vai diferenciar as classes
            RuntimeTypeAdapterFactory<Disciplina> adapter = RuntimeTypeAdapterFactory
                    .of(Disciplina.class, "type")
                    .registerSubtype(DisciplinaObrigatoria.class, "OBRIGATORIA")
                    .registerSubtype(DisciplinaEletiva.class, "ELETIVA")
                    .registerSubtype(DisciplinaOptativa.class, "OPTATIVA");

            // Constrói a instância do Gson, registrando nossa fábrica de adaptadores
            customGsonInstance = new GsonBuilder()
                    .registerTypeAdapterFactory(adapter)
                    .setPrettyPrinting() // Opcional: deixa o JSON mais legível
                    .create();
        }
        return customGsonInstance;
    }
}
