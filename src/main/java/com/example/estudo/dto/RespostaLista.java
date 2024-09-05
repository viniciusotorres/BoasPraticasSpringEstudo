package com.example.estudo.dto;

import com.example.estudo.domain.Medico;

import java.util.List;

public record RespostaLista(List<Medico> medicos, String mensagem, int status, String type) {
}
