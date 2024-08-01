package org.example.entity_graph.controller;

import org.example.entity_graph.entity.Client;
import org.example.entity_graph.repository.ClientRepository;
import org.example.entity_graph.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/client")
public class ClientController {
    private final ClientService service;
    private final ClientRepository repository;

    @Autowired
    public ClientController(ClientService service, ClientRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Client> findByNameContaining(@RequestParam String name) {
        return service.findByNameContaining(name);
    }

    @GetMapping("/fillDB")
    @ResponseStatus(HttpStatus.CREATED)
    public String fillDataBase() {
        service.generateDB();
        return "Amount clients: " + repository.count();
    }
}
