package br.edu.iftm.edumetrics.repository;

import java.util.List;

public interface DisciplinaRepository {
    List<String> findAllNomes();
}
