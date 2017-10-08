package com.example.demo;

import com.vaadin.data.Binder;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;


@SpringUI
public class ClientUI extends UI {

    private final Binder<Client> binder = new Binder<>();

    private VerticalLayout root;
    @Autowired
    TodoLayout todoLayout;

    @Override
    protected void init(VaadinRequest request)
    {
        setupLayout();
        addHeader();
        addForm();
        addTodoList();
        addDeleteButton();
    }

    private void setupLayout()
    {
        /*
            The root layout for the entire page is Vertical, centered in the middle.
         */
        root = new VerticalLayout();
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(root);
    }

    private void addHeader()
    {
        /*
            Basically the header in bold using a Valo theme, added to the root layout, as the first component.
         */
        Label header = new Label("Sign-Up");
        header.addStyleName(ValoTheme.LABEL_H1);
        root.addComponent(header);
    }

    private void addForm()
    {
        VerticalLayout formLayout = new VerticalLayout();

        /*
            Each textfield has it's own Horizonal Layout, and is binded to a data field from the Client class.
            These Horizontal layouts are added to a parent Vertical layout which is then added to the root layout.
         */
        HorizontalLayout f = new HorizontalLayout();
        f.setWidth("20%");
        Label fname = new Label("First Name:");
        TextField firstName = new TextField();
        f.addComponentsAndExpand(fname);
        f.addComponent(firstName);
        binder.forField(firstName).bind(Client::getFirstName, Client::setFirstName);

        HorizontalLayout l = new HorizontalLayout();
        l.setWidth("20%");
        Label lname = new Label ("Last Name:");
        TextField lastName = new TextField();
        l.addComponentsAndExpand(lname);
        l.addComponent(lastName);
        binder.forField(lastName).bind(Client::getLastName, Client::setLastName);

        HorizontalLayout e = new HorizontalLayout();
        e.setWidth("20%");
        Label em = new Label ("Email:");
        TextField email = new TextField();
        e.addComponentsAndExpand(em);
        e.addComponent(email);
        binder.forField(email).bind(Client::getEmail, Client::setEmail);

        HorizontalLayout t = new HorizontalLayout();
        t.setWidth("20%");
        Label tel = new Label ("Phone Number #:");
        TextField phone = new TextField();
        t.addComponentsAndExpand(tel);
        t.addComponents(phone);
        binder.forField(phone).bind(Client::getPhone, Client::setPhone);

        Button submit = new Button();
        submit.addStyleName(ValoTheme.BUTTON_PRIMARY);
        root.addComponent(submit);

        submit.addClickListener(click->{
            TodoLayout.add(new Client(fname, lname, em, tel))
        });

        formLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        formLayout.addComponents(f, l, e, t);

        root.addComponent(formLayout);
    }

    private void addTodoList()
    {
        root.addComponent(todoLayout);
    }

    private void addDeleteButton()
    {

    }

}
