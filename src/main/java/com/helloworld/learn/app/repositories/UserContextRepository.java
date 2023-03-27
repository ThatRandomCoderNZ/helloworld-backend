package com.helloworld.learn.app.repositories;

import com.helloworld.learn.app.models.openai.UserContext;
import com.helloworld.learn.app.models.user.DAOUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContextRepository extends CrudRepository<UserContext, Long> {
    UserContext findByUserId(Long userId);

    void deleteByUserId(Long userId);
}
