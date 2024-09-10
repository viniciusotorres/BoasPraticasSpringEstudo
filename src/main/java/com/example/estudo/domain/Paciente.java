package com.example.estudo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Paciente
 *  - Classe de domínio para o recurso Paciente
 *  - Define os atributos do recurso Paciente
 *  - Define os métodos getter e setter para os atributos do recurso Paciente
 *  - Define os métodos construtores para o recurso Paciente
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pacientes")
public class Paciente {

    /**
     * Identificador do paciente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String endereco;
    private String dataNascimento;
    private String sexo;
    private String tipoSanguineo;
    private String peso;
    private String altura;
    private String alergias;
    private String observacoes;
    private String dataCadastro;
    private String dataAtualizacao;
    private String dataExclusao;
    private String status;
    private Boolean ativo;
}
