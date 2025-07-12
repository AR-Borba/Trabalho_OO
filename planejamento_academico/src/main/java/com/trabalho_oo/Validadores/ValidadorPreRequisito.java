package com.trabalho_oo.Validadores;

import com.trabalho_oo.entities.Aluno;
import com.trabalho_oo.entities.Disciplinas.*;

public interface ValidadorPreRequisito {
    boolean validar(Aluno aluno, Disciplina disciplina);
}
