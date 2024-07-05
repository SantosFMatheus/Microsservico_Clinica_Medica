package br.edu.imepac.repositories;

import br.edu.imepac.models.AgendaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository<AgendaModel, Long> {
}