package com.trabalho_oo.Validadores;

import com.trabalho_oo.Aluno;
import com.trabalho_oo.Disciplinas.*;

public interface ValidadorPreRequisito {
    boolean validar(Aluno aluno, Disciplina disciplina);
}
