package ru.karachurin.restaurants.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Денис on 15.11.2016.
 */

@Entity
@Table(name = "restaurants")
public class Restaurant extends NamedEntity {
    @Column
    private String address;
    @Column
    private String contacts;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, String address, String contacts) {
        super(id, name);
        this.address = address;
        this.contacts = contacts;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
}
