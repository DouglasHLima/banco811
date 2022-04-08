package com.santander.banco811.repository;

import com.santander.banco811.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByNome(String nome);

    List<User> findByNomeAndCpf(String nome, String cpf);

    List<User> findByNomeIs(String nome);
    List<User> findByNomeIsNot(String nome);

    List<User> findByNomeIsNull();
    List<User> findByNomeIsNotNull();

    List<User> findByCpfStartingWith(String cpf);
    List<User> findByCpfEndingWith(String cpf);
    List<User> findByCpfContaining(String cpf);


    List<User> findByNomeLike(String pattern);

    List<User> findByDataCriacaoBeforeAndNomeAndCpf(LocalDateTime dataCriacao, String nome, String cpf);
    List<User> findByDataCriacao(LocalDateTime dataCriacao);

    List<User> findByNomeAndDataCriacaoOrderByNomeAsc(String nome, LocalDateTime dataCriacao);

    User findByCpf(String cpf);
}
