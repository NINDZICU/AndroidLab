package ru.kpfu.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.entities.MyTasks;
import ru.kpfu.itis.entities.Tasks;
import ru.kpfu.itis.repository.MyTasksRepository;
import ru.kpfu.itis.repository.TasksRepository;

import java.util.List;


/**
 * Created by Anatoly on 08.07.2017.
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestController {
    @Autowired
    TasksRepository tasksRepository;
    @Autowired
    MyTasksRepository myTasksRepository;

    @PreAuthorize("isAnonymous()")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<MyTasks> getMyTasks(){
        return myTasksRepository.findAll();
//        return new MyTasks("sadasd","kekek", "12.04.2017", "1", "Tolya" );
    }


}
