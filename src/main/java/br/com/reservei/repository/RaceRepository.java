package br.com.reservei.repository;

import br.com.reservei.entity.Race;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepository extends JpaRepository<Race, Integer> {
}
