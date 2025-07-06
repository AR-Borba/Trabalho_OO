package com.trabalho_oo.Models;

import com.trabalho_oo.exceptions.CodigoDisciplinaException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodigoDisciplina {
    public String codigoDisciplina;

    private static final Pattern pattern = Pattern.compile("^[A-Z]{3}[0-9]{3}");

    public CodigoDisciplina(String codigoDisciplina) throws CodigoDisciplinaException {
        setCodigoDisciplina(codigoDisciplina);
    }

    public boolean isCodigoValido(String codigoDisciplina) {

        Matcher matcher = pattern.matcher(codigoDisciplina);
        return matcher.matches();
    }

    public String getCodigoDisciplina() {
        return codigoDisciplina;
    }

    public void setCodigoDisciplina(String codigoDisciplina) throws CodigoDisciplinaException {
        if(!isCodigoValido(codigoDisciplina)) 
            throw new CodigoDisciplinaException();

        this.codigoDisciplina = codigoDisciplina;
    }
}
