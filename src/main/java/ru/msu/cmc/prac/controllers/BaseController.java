package ru.msu.cmc.prac.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {
    @RequestMapping(value = { "/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/allPeople" )
    public String allPerson() {
        return "people";
    }

    @RequestMapping(value = "/generateTree")
    public String generateTree() {
        return "generateTree";
    }
}
