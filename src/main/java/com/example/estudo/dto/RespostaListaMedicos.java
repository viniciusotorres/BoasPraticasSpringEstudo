package com.example.estudo.dto;

import com.example.estudo.domain.Medico;

import java.util.List;

/**
 * Resposta para listagem de médicos.
 * @param medicos
 * @param mensagem
 * @param status
 * @param type
 */
public record RespostaListaMedicos(List<Medico> medicos, String mensagem, int status, String type) {
}
