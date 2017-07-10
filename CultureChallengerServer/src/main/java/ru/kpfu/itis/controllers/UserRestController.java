package ru.kpfu.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.service.UserService;

import java.util.List;
import java.util.Set;

/**
 * Created by Anatoly on 10.07.2017.
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;


    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public Set<User> getFriends(@RequestParam(value = "login") String login){
        return userRepository.findUserByLogin(login).getFriends();
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    @PreAuthorize("isAnonymous()")
    public Integer addUser(@RequestBody User user) {
        if(userService.registerUser(user)) return 1;
        else return 0;                  //логин уже существует
    }
    @RequestMapping(value = "/aut", method = RequestMethod.POST)
    @PreAuthorize("isAnonymous()")
    public Integer autUser(@RequestBody User user){
        User userFromDatabase = (User) userService.loadUserByUsername(user.getLogin());
        if(userFromDatabase.getLogin().equals(user.getLogin()) && userFromDatabase.getPassword().equals(user.getPassword())){
            return 1;
        }
        else return 0;
    }
}
