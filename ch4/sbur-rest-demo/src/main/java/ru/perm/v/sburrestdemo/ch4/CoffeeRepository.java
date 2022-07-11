package ru.perm.v.sburrestdemo.ch4;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepository extends CrudRepository<Coffee, String> {

}
