package com.project.Mesa.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.Mesa.Model.Filial;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface FilialRepository extends CrudRepository<Filial, String>{

}
