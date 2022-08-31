package com.helloworld.learn.app.repositories;

import com.helloworld.learn.app.models.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends CrudRepository<Test, Long> {
}
