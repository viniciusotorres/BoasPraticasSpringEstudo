package com.example.estudo.dto;

/**
 * Resposta para atualização de médico.
 * @param medico
 * @param mensagem
 * @param status
 * @param type
 */
public record RespostaUpdateMedico(MedicoDTO medico, String mensagem, int status, String type) {
}
