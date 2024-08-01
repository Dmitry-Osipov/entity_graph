package org.example.entity_graph.service;

import com.github.javafaker.Faker;
import org.example.entity_graph.entity.Client;
import org.example.entity_graph.entity.EmailAddress;
import org.example.entity_graph.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ClientService {
    private final ClientRepository repository;

    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<Client> findByNameContaining(String userName) {
        return repository.findByFullNameContaining(userName);
    }

    public void generateDB() {
        List<Client> clients = create2000Clients();
        repository.saveAll(clients);
    }

    private static List<Client> create2000Clients() {
        List<Client> clients = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < 2000; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String sufixTel = String.valueOf(i);
            String telephone = "+375290000000";

            List<EmailAddress> emailAddresses = Arrays.asList(
                    new EmailAddress((firstName + lastName).toLowerCase() + "1" + i + "@Gmail.com"),
                    new EmailAddress((firstName + lastName).toLowerCase() + "2" + i + "@Gmail.com")
            );
            telephone = telephone.substring(0, telephone.length() - sufixTel.length()) + sufixTel;
            Client client = new Client(firstName + lastName, telephone, emailAddresses);

            for (EmailAddress emailAddress : emailAddresses) {
                emailAddress.setClient(client);
            }
            clients.add(client);
        }

        return clients;
    }
}
