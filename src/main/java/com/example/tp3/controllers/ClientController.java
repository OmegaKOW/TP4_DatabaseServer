package com.example.tp3.controllers;

import com.example.tp3.models.users.Client;
import com.example.tp3.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {


    Logger logger = LoggerFactory.getLogger(ClientController.class);

    private final LibraryService libraryService;


    public ClientController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/clients")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Client> getAllClients() {
        logger.info("getAllClients");
        return libraryService.getClients();
    }

    @GetMapping("/client")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Client> getAClient() {
        logger.info("get a client");
        return libraryService.getClients();
    }

    @PostMapping("/client")
    @CrossOrigin(origins = "http://localhost:3000")
    public Client clientSubmit(@RequestBody Client newClient) {
        logger.info("post - createClient " + newClient);
        return libraryService.saveClient(newClient);
    }
}
