package org.example.entity_graph.repository;

import org.example.entity_graph.entity.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
//    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "emailAddresses")
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "client_entity-graph")
    List<Client> findByFullNameContaining(String name);
}
