package com.itb.mif3an.pizzaria.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {

    @GetMapping
    public ResponseEntity<Object> index() {
        return ResponseEntity.ok().body("Controller::Cliente");
    }

}
