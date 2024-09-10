package com.example.estudo.controller;

import com.example.estudo.dto.PacienteDTO;
import com.example.estudo.dto.RespostaListaPacientes;
import com.example.estudo.dto.RespostaPadrao;
import com.example.estudo.dto.RespostaUpdatePaciente;
import com.example.estudo.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * PacienteController
 *  - Classe controladora para o recurso Paciente
 *  - Define os endpoints para o recurso Paciente
 *  - Define os métodos para manipulação do recurso Paciente
 *  - Define os métodos para manipulação de dados do recurso Paciente
 */
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    /**
     * Método para listar todos os pacientes
     * @return String
     */
    @GetMapping("/listar")
    public ResponseEntity<RespostaListaPacientes> listarPacientes() {
        RespostaListaPacientes resposta = pacienteService.listarPacientes();
        return ResponseEntity.status(resposta.status()).body(resposta);
    }

    /**
     * Método para cadastrar um novo paciente
     * @param pacienteDTO
     * @param uriComponentsBuilder
     * @return
     */
    @PostMapping("/cadastrar")
    public ResponseEntity<RespostaPadrao> cadastrarPaciente(@RequestBody PacienteDTO pacienteDTO, UriComponentsBuilder uriComponentsBuilder) {
        RespostaPadrao resposta = pacienteService.cadastrarPaciente(pacienteDTO);

        var uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(pacienteDTO.cpf()).toUri();
        return ResponseEntity.status(resposta.status()).location(uri).body(resposta);
    }

    /**
     * Método para buscar um paciente por ID
     * @param id
     * @return
     */

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> obterPacientePorId(@PathVariable("id") Long id) {
        PacienteDTO pacienteDTO = pacienteService.obterPacientePorId(id);
        if(pacienteDTO.nome() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pacienteDTO);
    }

    /**
     * Método para atualizar um paciente
     * @param id
     * @param pacienteDTO
     * @return
     */
    @PutMapping("/atualizando/{id}")
    public ResponseEntity<RespostaUpdatePaciente> atualizarPaciente(@PathVariable("id") Long id, @RequestBody PacienteDTO pacienteDTO) {
        RespostaUpdatePaciente resposta = pacienteService.atualizarPaciente(id, pacienteDTO);
        return ResponseEntity.status(resposta.status()).body(resposta);
    }
}
