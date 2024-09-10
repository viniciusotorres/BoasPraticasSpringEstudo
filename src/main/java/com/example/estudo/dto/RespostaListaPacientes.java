package com.example.estudo.dto;

import com.example.estudo.domain.Paciente;

import java.util.List;

/**
 * Resposta para listagem de pacientes.
 * @param pacientes
 * @param mensagem
 * @param status
 * @param type
 */

public record RespostaListaPacientes(List<Paciente> pacientes, String mensagem, int status, String type) {
}
