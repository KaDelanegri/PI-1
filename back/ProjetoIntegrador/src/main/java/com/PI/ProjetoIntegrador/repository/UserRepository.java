package com.PI.ProjetoIntegrador.repository;

import com.PI.ProjetoIntegrador.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value ="select username from tb_user where email = :paramEmail",nativeQuery = true)
    public String findByEmail(@Param("paramEmail") String email);

    public Optional<User> findByUsername(String user);
}
