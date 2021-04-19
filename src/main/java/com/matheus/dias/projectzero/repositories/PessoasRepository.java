package com.matheus.dias.projectzero.repositories;

import com.matheus.dias.projectzero.models.Pessoas;
import com.matheus.dias.projectzero.models.QPessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PessoasRepository extends JpaRepository<Pessoas, Long>, QuerydslPredicateExecutor<Pessoas> {

}
