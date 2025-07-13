package com.trabalho_oo.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.trabalho_oo.entities.Disciplinas.*;
import com.trabalho_oo.Validadores.*;
import java.time.LocalTime; // Importar a classe LocalTime

public class GsonFactory {

    private static Gson customGsonInstance;

    public static Gson getCustomGson() {
        if (customGsonInstance == null) {
            
            // Fábrica para a hierarquia de Disciplinas
            RuntimeTypeAdapterFactory<Disciplina> disciplinaAdapterFactory = RuntimeTypeAdapterFactory
                    .of(Disciplina.class, "type")
                    .registerSubtype(DisciplinaObrigatoria.class, "OBRIGATORIA")
                    .registerSubtype(DisciplinaEletiva.class, "ELETIVA")
                    .registerSubtype(DisciplinaOptativa.class, "OPTATIVA");

            // Fábrica para a hierarquia de Validadores
            RuntimeTypeAdapterFactory<ValidadorPreRequisito> validadorAdapterFactory = RuntimeTypeAdapterFactory
                    .of(ValidadorPreRequisito.class, "validatorType")
                    .registerSubtype(ValidadorSimples.class, "SIMPLES")
                    .registerSubtype(ValidadorLogicoAND.class, "AND")
                    .registerSubtype(ValidadorLogicoOR.class, "OR")
                    .registerSubtype(ValidadorCreditosMinimos.class, "CREDITOS");

            // Constrói a instância do Gson, registando TUDO o que é necessário
            customGsonInstance = new GsonBuilder()
                    .registerTypeAdapterFactory(disciplinaAdapterFactory)
                    .registerTypeAdapterFactory(validadorAdapterFactory)
                    // ADICIONAR ESTA LINHA: Regista o nosso novo adaptador para LocalTime
                    .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                    .setPrettyPrinting()
                    .create();
        }
        return customGsonInstance;
    }
}
