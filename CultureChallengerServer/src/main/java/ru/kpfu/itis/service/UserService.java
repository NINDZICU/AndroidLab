package ru.kpfu.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.repository.AuthorityRepository;
import ru.kpfu.itis.repository.UserRepository;


/**
 * Created by Anatoly on 22.05.2017.
 */
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityJPARepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findUserByLogin(login);
        return user;
    }
    public boolean registerUser(User user) {
        if(userRepository.findUserByLogin(user.getLogin()) != null){
            return false;
//            throw new DuplicateKeyException("Duplicate key - username field.");
        }
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.addAuthority(authorityJPARepository.findByAuthority("ROLE_USER"));
            userRepository.save(user);
            return true;
        }
    }

}
