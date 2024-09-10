package com.example.estudo.services;

import com.example.estudo.domain.Paciente;
import com.example.estudo.dto.PacienteDTO;
import com.example.estudo.dto.RespostaListaPacientes;
import com.example.estudo.dto.RespostaPadrao;
import com.example.estudo.dto.RespostaUpdatePaciente;
import com.example.estudo.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PacienteService
 * - Classe de serviço para o recurso Paciente
 * - Define os métodos para manipulação de dados do recurso Paciente
 */
@Service
public class PacienteService {


    @Autowired
    private PacienteRepository pacienteRepository;

    /**
     * Método para cadastrar um novo paciente
     *
     * @param pacienteDTO Dados do paciente a ser cadastrado
     * @return RespostaPadrao
     */
    public RespostaPadrao cadastrarPaciente(PacienteDTO pacienteDTO) {
        try {
            if (pacienteRepository.findByCpf(pacienteDTO.cpf()) != null) {
                return new RespostaPadrao("CPF já cadastrado", 400, "Bad Request");
            }

            if (pacienteDTO.nome() == null || pacienteDTO.cpf() == null || pacienteDTO.dataNascimento() == null) {
                return new RespostaPadrao("Dados não podem ser nulos", 400, "Bad Request");
            }

            Paciente pacienteEntity = new Paciente();
            pacienteEntity.setNome(pacienteDTO.nome());
            pacienteEntity.setCpf(pacienteDTO.cpf());
            pacienteEntity.setDataNascimento(pacienteDTO.dataNascimento());
            pacienteEntity.setEmail(pacienteDTO.email());
            pacienteEntity.setAtivo(pacienteDTO.ativo());

            pacienteRepository.save(pacienteEntity);

            return new RespostaPadrao("Paciente cadastrado com sucesso", 201, "Created");
        } catch (Exception e) {
            return new RespostaPadrao("Erro ao cadastrar paciente", 500, "Internal Server Error");
        }
    }

    /**
     * Método para listar todos os pacientes
     *
     * @return RespostaListaPacientes com a lista de pacientes e status HTTP
     */
    public RespostaListaPacientes listarPacientes() {
        try {
            List<Paciente> pacientes = pacienteRepository.findAll();
            if (pacientes.isEmpty()) {
                return new RespostaListaPacientes(pacientes, "Nenhum paciente cadastrado", 204, "No Content");
            }
            return new RespostaListaPacientes(pacientes, "Pacientes listados com sucesso", 200, "OK");
        } catch (Exception e) {
            return new RespostaListaPacientes(null, "Erro ao listar pacientes", 500, "Internal Server Error");

        }
    }

    /**
     * Método para obter um paciente por ID
     *
     * @param id Identificador do paciente
     * @return PacienteDTO com os dados do paciente ou mensagem de não encontrado
     */
    public PacienteDTO obterPacientePorId(Long id) {
        try {
            Paciente paciente = pacienteRepository.findById(id).orElse(null);
            if (paciente == null) {
                return new PacienteDTO(null, null, null, null, null, false);
            }
            return new PacienteDTO(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getEmail(), paciente.getDataNascimento(), paciente.getAtivo());
        } catch (Exception e) {
            return new PacienteDTO(null, null, null, null, null, false);
        }
    }

    /**
     * Método para atualizar um paciente
     *
     * @param id          Identificador do paciente a ser atualizado
     * @param pacienteDTO Dados atualizados do paciente
     * @return RespostaPadrao com a mensagem e status HTTP
     */
    public RespostaUpdatePaciente atualizarPaciente(Long id, PacienteDTO pacienteDTO) {
        try {
            Paciente paciente = pacienteRepository.findById(id).orElse(null);
            if (paciente == null) {
                return new RespostaUpdatePaciente(null,"Paciente não encontrado", 404, "Not Found");
            }

            if (pacienteDTO.nome() == null || pacienteDTO.cpf() == null || pacienteDTO.dataNascimento() == null) {
                return new RespostaUpdatePaciente(null, "Dados não podem ser nulos", 400, "Bad Request");
            }

            paciente.setNome(pacienteDTO.nome());
            paciente.setCpf(pacienteDTO.cpf());
            paciente.setDataNascimento(pacienteDTO.dataNascimento());
            paciente.setEmail(pacienteDTO.email());
            paciente.setAtivo(pacienteDTO.ativo());

            pacienteRepository.save(paciente);

            return new RespostaUpdatePaciente(pacienteDTO, "Paciente atualizado com sucesso", 200, "OK");
        } catch (Exception e) {
            return new RespostaUpdatePaciente(null, "Erro ao atualizar paciente", 500, "Internal Server Error");
        }
    }


}
