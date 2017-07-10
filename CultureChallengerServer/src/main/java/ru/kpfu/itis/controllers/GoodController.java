package ru.kpfu.itis.controllers;

/**
 * Created by Anatoly on 08.07.2017.
 */

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping(value = "/asd", produces = MediaType.APPLICATION_JSON_VALUE)
public class GoodController {

    @RequestMapping({""})
    public String showGoods(Map map) {
        return "main";
    }
}
