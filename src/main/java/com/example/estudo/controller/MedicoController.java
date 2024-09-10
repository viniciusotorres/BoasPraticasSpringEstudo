package com.example.estudo.controller;

import com.example.estudo.dto.MedicoDTO;
import com.example.estudo.dto.RespostaListaMedicos;
import com.example.estudo.dto.RespostaPadrao;
import com.example.estudo.dto.RespostaUpdateMedico;
import com.example.estudo.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Controlador para gerenciar médicos.
 */
@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    /**
     * Método para cadastrar um novo médico.
     * @param medicoDTO Dados do médico a ser cadastrado.
     * @param uriComponentsBuilder Utilizado para construir a URI do novo recurso.
     * @return Resposta padrão com a URI do recurso criado e o status HTTP.
     */
    @PostMapping("/cadastrar")
    public ResponseEntity<RespostaPadrao> cadastrarMedico(@RequestBody MedicoDTO medicoDTO, UriComponentsBuilder uriComponentsBuilder) {
        RespostaPadrao resposta = medicoService.cadastrarMedico(medicoDTO);
        var uri = uriComponentsBuilder.path("/medico/{id}").buildAndExpand(medicoDTO.crm()).toUri(); // Exemplo, ajuste conforme necessidade
        return ResponseEntity.status(resposta.status()).location(uri).body(resposta);
    }

    /**
     * Método para listar todos os médicos.
     * @return Lista de médicos com status HTTP.
     */
    @GetMapping("/listar")
    public ResponseEntity<RespostaListaMedicos> listarMedicos() {
        RespostaListaMedicos resposta = medicoService.listarMedicos();
        return ResponseEntity.status(resposta.status()).body(resposta);
    }

    /**
     * Método para buscar um médico por ID.
     * @param id Identificador do médico.
     * @return Dados do médico ou mensagem de não encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> obterMedicoPorId(@PathVariable("id") Long id) {
        MedicoDTO medicoDTO = medicoService.obterMedicoPorId(id);
        if (medicoDTO.nome() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(medicoDTO);
    }

    /**
     * Método para ativar um médico.
     * @param id Identificador do médico.
     * @return Resposta padrão com o status HTTP.
     */
    @PutMapping("/ativar/{id}")
    public ResponseEntity<RespostaPadrao> ativarMedico(@PathVariable("id") Long id) {
        RespostaPadrao resposta = medicoService.ativarMedico(id);
        return ResponseEntity.status(resposta.status()).body(resposta);
    }

    /**
     * Método para desativar um médico.
     * @param id Identificador do médico.
     * @return Resposta padrão com o status HTTP.
     */
    @PutMapping("/desativar/{id}")
    public ResponseEntity<RespostaPadrao> desativarMedico(@PathVariable("id") Long id) {
        RespostaPadrao resposta = medicoService.desativarMedico(id);
        return ResponseEntity.status(resposta.status()).body(resposta);
    }

    /**
     * Método para atualizar os dados de um médico.
     * @param id Identificador do médico a ser atualizado.
     * @param medicoDTO Dados atualizados do médico.
     * @return Resposta com os dados atualizados e o status HTTP.
     */
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<RespostaUpdateMedico> atualizarMedico(@PathVariable("id") Long id, @RequestBody MedicoDTO medicoDTO) {
        RespostaUpdateMedico resposta = medicoService.atualizarMedico(id, medicoDTO);
        return ResponseEntity.status(resposta.status()).body(resposta);
    }
}
