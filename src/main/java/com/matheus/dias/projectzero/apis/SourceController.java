package com.matheus.dias.projectzero.apis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/source")
public class SourceController {

    @GetMapping
    public String getSource() {
        return "https://github.com/mteoo/projectzero";
    }
}
