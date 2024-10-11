package com.project.Mesa.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.Mesa.Model.Users;



@Repository
@Transactional
public interface UserRepository extends CrudRepository<Users, Long>{

}
