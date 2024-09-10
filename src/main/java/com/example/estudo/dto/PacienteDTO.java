package com.example.estudo.dto;

public record PacienteDTO(Long id, String nome, String cpf, String email, String dataNascimento, boolean ativo) {
    public PacienteDTO {
        if (nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (cpf.isBlank()) {
            throw new IllegalArgumentException("CPF não pode ser vazio");
        }
        if (email.isBlank()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }
    }
}
