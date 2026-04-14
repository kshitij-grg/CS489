package edu.miu.cs.cs489.lab6.adsappointment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GraphiQLController {

    @GetMapping("/graphiql")
    public String graphiql() {
        return "redirect:/graphiql/index.html";
    }
}
