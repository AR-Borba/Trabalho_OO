package com.trabalho_oo.Utils;

import com.trabalho_oo.entities.Disciplinas.Disciplina;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogoDisciplinas {

    private static final Map<String, Disciplina> catalogo = new HashMap<>();

    public static void carregar(List<Disciplina> disciplinas) {
        if (disciplinas == null) return;
        
        for (Disciplina d : disciplinas) {
            catalogo.put(d.getCodigo(), d);
        }
    }

    public static Disciplina getDisciplina(String codigo) {
        return catalogo.get(codigo);
    }
}