package com.example.estudo.services;

import com.example.estudo.domain.Medico;
import com.example.estudo.dto.MedicoDTO;
import com.example.estudo.dto.RespostaLista;
import com.example.estudo.dto.RespostaPadrao;
import com.example.estudo.dto.RespostaUpdate;
import com.example.estudo.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço para gerenciar operações relacionadas a médicos.
 */
@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    /**
     * Cadastra um novo médico.
     *
     * @param medicoDTO Dados do médico a ser cadastrado.
     * @return Resposta padrão contendo mensagem e status HTTP.
     */
    public RespostaPadrao cadastrarMedico(MedicoDTO medicoDTO) {
        try {
            if (medicoRepository.findByCrm(medicoDTO.crm()) != null) {
                return new RespostaPadrao("CRM já cadastrado", 400, "Bad Request");
            }

            if (medicoDTO.nome() == null || medicoDTO.crm() == null || medicoDTO.especialidade() == null) {
                return new RespostaPadrao("Dados não podem ser nulos", 400, "Bad Request");
            }

            Medico medicoEntity = new Medico();
            medicoEntity.setNome(medicoDTO.nome());
            medicoEntity.setCrm(medicoDTO.crm());
            medicoEntity.setEspecialidade(medicoDTO.especialidade());
            medicoEntity.setAtivo(medicoDTO.ativo());

            medicoRepository.save(medicoEntity);

            return new RespostaPadrao("Médico cadastrado com sucesso", 201, "Created");
        } catch (Exception e) {
            return new RespostaPadrao("Erro ao cadastrar médico", 500, "Internal Server Error");
        }
    }

    /**
     * Lista todos os médicos cadastrados.
     *
     * @return Resposta contendo a lista de médicos e status HTTP.
     */
    public RespostaLista listarMedicos() {
        try {
            List<Medico> medicos = medicoRepository.findAll();
            if (medicos.isEmpty()) {
                return new RespostaLista(medicos, "Nenhum médico cadastrado", 204, "No Content");
            }
            return new RespostaLista(medicos, "Médicos listados com sucesso", 200, "Success");
        } catch (Exception e) {
            return new RespostaLista(null, "Erro ao listar médicos", 500, "Internal Server Error");
        }
    }

    /**
     * Obtém os detalhes de um médico pelo ID.
     *
     * @param id Identificador do médico.
     * @return Dados do médico ou um objeto vazio se não encontrado.
     */
    public MedicoDTO obterMedicoPorId(Long id) {
        try {
            Medico medico = medicoRepository.findById(id).orElse(null);
            if (medico == null) {
                return new MedicoDTO(null, null, null, null);
            }
            return new MedicoDTO(medico.getNome(), medico.getCrm(), medico.getEspecialidade(), medico.getAtivo());
        } catch (Exception e) {
            return new MedicoDTO(null, null, null, null);
        }
    }

    /**
     * Ativa um médico.
     *
     * @param id Identificador do médico.
     * @return Resposta padrão contendo mensagem e status HTTP.
     */
    public RespostaPadrao ativarMedico(Long id) {
        try {
            Medico medico = medicoRepository.findById(id).orElse(null);
            if (medico == null) {
                return new RespostaPadrao("Médico não encontrado", 404, "Not Found");
            }
            medico.setAtivo(true);
            medicoRepository.save(medico);
            return new RespostaPadrao("Médico ativado com sucesso", 200, "Success");
        } catch (Exception e) {
            return new RespostaPadrao("Erro ao ativar médico", 500, "Internal Server Error");
        }
    }

    /**
     * Desativa um médico.
     *
     * @param id Identificador do médico.
     * @return Resposta padrão contendo mensagem e status HTTP.
     */
    public RespostaPadrao desativarMedico(Long id) {
        try {
            Medico medico = medicoRepository.findById(id).orElse(null);
            if (medico == null) {
                return new RespostaPadrao("Médico não encontrado", 404, "Not Found");
            }
            medico.setAtivo(false);
            medicoRepository.save(medico);
            return new RespostaPadrao("Médico desativado com sucesso", 200, "Success");
        } catch (Exception e) {
            return new RespostaPadrao("Erro ao desativar médico", 500, "Internal Server Error");
        }
    }

    /**
     * Atualiza os dados de um médico existente.
     *
     * @param id Identificador do médico a ser atualizado.
     * @param medicoDTO Dados atualizados do médico.
     * @return Resposta contendo dados atualizados e status HTTP.
     */
    public RespostaUpdate atualizarMedico(Long id, MedicoDTO medicoDTO) {
        try {
            Medico medico = medicoRepository.findById(id).orElse(null);
            if (medico == null) {
                return new RespostaUpdate(medicoDTO, "Médico não encontrado", 404, "Not Found");
            }

            if (medicoDTO.nome() != null) {
                medico.setNome(medicoDTO.nome());
            }

            if (medicoDTO.crm() != null) {
                medico.setCrm(medicoDTO.crm());
            }

            if (medicoDTO.especialidade() != null) {
                medico.setEspecialidade(medicoDTO.especialidade());
            }

            medicoRepository.save(medico);
            return new RespostaUpdate(medicoDTO, "Médico atualizado com sucesso", 200, "Success");
        } catch (Exception e) {
            return new RespostaUpdate(medicoDTO, "Erro ao atualizar médico", 500, "Internal Server Error");
        }
    }
}