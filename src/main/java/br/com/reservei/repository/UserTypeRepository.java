package br.com.reservei.repository;

import br.com.reservei.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
}
