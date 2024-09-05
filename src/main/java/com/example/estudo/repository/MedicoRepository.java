package com.example.estudo.repository;

import com.example.estudo.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Medico findById(long id);
    Medico findByNome(String nome);
    Medico findByCrm(String crm);

}
