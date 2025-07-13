package com.trabalho_oo.Utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalTime;

/**
 * Adaptador personalizado para ensinar o Gson a serializar e desserializar
 * a classe java.time.LocalTime, evitando problemas de reflexão com os módulos do Java.
 */
public class LocalTimeAdapter extends TypeAdapter<LocalTime> {

    @Override
    public void write(JsonWriter out, LocalTime value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            // Converte o LocalTime para uma string no formato padrão (ex: "19:00" ou "19:00:30")
            out.value(value.toString());
        }
    }

    @Override
    public LocalTime read(JsonReader in) throws IOException {
        if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        // Lê a string do JSON e a converte de volta para um objeto LocalTime
        return LocalTime.parse(in.nextString());
    }
}
