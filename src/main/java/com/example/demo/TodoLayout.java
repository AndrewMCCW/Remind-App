package com.example.demo;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringComponent
public class TodoLayout extends VerticalLayout {

    @Autowired
    ClientRepo repo;

    @PostConstruct
    void init()
    {
        update();
    }

    public void update() {
        setClients(repo.findAll());
    }

    private void setClients(List<Client> clients) {
        removeAllComponents();

        clients.forEach(client -> addComponent(new ClientItemLayout(client)));
    }

    public void add (Client client)
    {
        repo.save(client);
        update();
    }
}
