package com.example.estudo.repository;

import com.example.estudo.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

        Paciente findByCpf(String cpf);
}
