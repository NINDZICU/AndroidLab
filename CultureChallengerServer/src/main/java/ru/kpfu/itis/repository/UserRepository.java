package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.entities.User;

/**
 * Created by Anatoly on 08.07.2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByLogin(String login);
}
