package edu.usta.domain.entities;

import edu.usta.domain.enums.Role;
import edu.usta.infraestructure.config.UUIDGenerator;

public class Person {
    private String id;
    private String fullName;
    private String document;
    private Role role;


    public Person() {
        this.id = UUIDGenerator.execute();
        this.fullName = "Leonardo";
        this.document = null;
        this.role = Role.ADMIN;
    }


    public Person(String fullName, String document, Role role) {
        this.id = UUIDGenerator.execute();
        this.fullName = fullName;
        this.document = document;
        this.role = role;
    }


    public Person(String id, String fullName, String document, Role role) {
        this.id = id;
        this.fullName = fullName;
        this.document = document;
        this.role = role;
    }


    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getDocument() {
        return document;
    }


    public void setDocument(String document) {
        this.document = document;
    }


    public Role getRole() {
        return role;
    }


    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "Person [id=" + id + ", fullName=" + fullName + ", document=" + document + ", role=" + role + "]";
    }

    
}
