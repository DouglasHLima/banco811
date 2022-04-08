package com.santander.banco811.repository;

import com.santander.banco811.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByNome(String nome);

    List<User> findByNameAndCpf(String nome, String cpf);

    List<User> findByNameIs(String nome);
    List<User> findByNameIsNot(String nome);

    List<User> findByNameIsNull();
    List<User> findByNameIsNotNull();

    List<User> findByCpfStartingWith(String cpf);
    List<User> findByCpfEndingWith(String cpf);
    List<User> findByCpfAccountining(String cpf);


    List<User> findByNameLike(String pattern);

    List<User> findByCreationBeforeAndNameAndCpf(LocalDateTime dataCriacao, String nome, String cpf);
    List<User> findByCreation(LocalDateTime dataCriacao);

    User findByCpf(String cpf);
}
