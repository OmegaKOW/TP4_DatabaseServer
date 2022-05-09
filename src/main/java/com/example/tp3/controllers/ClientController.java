package com.example.tp3.controllers;

import com.example.tp3.models.library.Dette;
import com.example.tp3.models.library.Document;
import com.example.tp3.models.library.Emprunt;
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
        return libraryService.getClientsAndFines();
    }

    @PostMapping("/client")
    @CrossOrigin(origins = "http://localhost:3000")
    public Client clientSubmit(@RequestBody Client newClient) {
        logger.info("post - createClient " + newClient);
        return libraryService.saveClient(newClient);
    }

    @DeleteMapping("/client/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public void clientDelete(@PathVariable("id") String str) {
        logger.info("post - deleteClient " + str);
        Client c = libraryService.findClientWithId(getIdFromString(str)).get();
        libraryService.removeClient(c);
    }

    @GetMapping("/emprunts/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Emprunt> getEmprunts(@PathVariable("id") String id) {
        logger.info("getAllClients");
        return libraryService.getEmprunts(getIdFromString(id));
    }

    @GetMapping("/dettes/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Dette> getDettes(@PathVariable("id") String id) {
        logger.info("getAllClients");
        return libraryService.getDettesWithClientId(getIdFromString(id));
    }

    @GetMapping("/payer/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public void payerDettes(@PathVariable("id") String id) {
        logger.info("getAllClients");
        libraryService.payDebts(getIdFromString(id));
    }

    @GetMapping("/emprunter/{clientId}/{bookId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public void emprunterDocument(@PathVariable("clientId") String clientId, @PathVariable("bookId") String bookId) {
        logger.info("Client " + clientId + " emprunte document " + bookId);
        try{
            libraryService.borrowDocument(getIdFromString(clientId), getIdFromString(bookId));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private long getIdFromString(String id) {
        try {
            return Long.parseLong(id);
        } catch(NumberFormatException e) {}
        return 0;
    }
}
