package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.entities.Authority;

/**
 * Created by Anatoly on 08.07.2017.
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer>{
    Authority findByAuthority(String role_user);
}
