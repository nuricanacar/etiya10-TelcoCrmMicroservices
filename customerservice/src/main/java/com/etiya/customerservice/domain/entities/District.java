package com.etiya.customerservice.domain.entities;


import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "districts")
@ToString(exclude = "city")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name",nullable = false,length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;

    @OneToMany(mappedBy = "district",fetch = FetchType.LAZY)
    private List<Address> addresses;

    public District(int id, String name, City city, List<Address> addresses) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.addresses = addresses;
    }

    public District() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}

