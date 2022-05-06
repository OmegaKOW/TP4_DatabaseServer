package com.example.tp3.controllers;

import com.example.tp3.models.library.Document;
import com.example.tp3.models.library.Livre;
import com.example.tp3.models.users.Client;
import com.example.tp3.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
public class DocumentController {
    Logger logger = LoggerFactory.getLogger(ClientController.class);

    private final LibraryService libraryService;

    public DocumentController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/documents")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Document> getAllDocuments() {
        logger.info("getAllDocuments");
        return libraryService.getDocuments();
    }

    @GetMapping("/document")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Document> getADocument() {
        logger.info("get a doc");
        return libraryService.getDocuments();
    }

    @PostMapping("/document")
    @CrossOrigin(origins = "http://localhost:3000")
    public Document documentSubmit(@RequestBody Livre newDocument) {
        logger.info("post - createClient " + newDocument);
        return libraryService.saveLivre(newDocument);
    }

    @DeleteMapping("/document/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public void documentDelete(@PathVariable("id") String id) {
        logger.info("post - createClient " + id);
        Document d = libraryService.findDocumentWithId(getIdFromString(id)).get();
        libraryService.removeDocument(d);
    }

    @GetMapping("/livres")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Livre> getAllLivres() {
        logger.info("getAllDocuments");
        return libraryService.getLivres();
    }

    @GetMapping("/livre")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Livre> getALivre() {
        logger.info("get a book");
        return libraryService.getLivres();
    }

    @PostMapping("/livre")
    @CrossOrigin(origins = "http://localhost:3000")
    public Document livreSubmit(@RequestBody Livre newDocument) {
        logger.info("post - createClient " + newDocument);
        return libraryService.saveLivre(newDocument);
    }

    @DeleteMapping("/livre/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public void livreDelete(@PathVariable("id") String id) {
        logger.info("post - createClient " + id);
        Document d = libraryService.findDocumentWithId(getIdFromString(id)).get();
        libraryService.removeDocument(d);
    }

    private long getIdFromString(String id) {
        try {
            return Long.parseLong(id);
        } catch(NumberFormatException e) {}
        return 0;
    }
}
