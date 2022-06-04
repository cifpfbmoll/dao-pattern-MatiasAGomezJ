package edu.poniperro.daop.domain;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class DeveloperRepository implements PanacheRepository<Developer> {

    @Transactional
    public Developer createDeveloper(Developer developer) {
        persist(developer);
        return developer;
    }

    public List<Developer> findAllDevs() {
        return findAll().list();
    }

    public Developer findDevById(Long id) {
        return findById(id);
    }

    public Developer findDevByName(String name) {
        return find("name", name).firstResult();
    }




}
