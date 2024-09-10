package com.example.estudo.repository;

import com.example.estudo.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface para persistência de dados de médicos.
 * @param id
 * @param nome
 * @param crm
 * @param especialidade
 * @param ativo
 * @return Medico
 */
@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Medico findById(long id);
    Medico findByNome(String nome);
    Medico findByCrm(String crm);

}
