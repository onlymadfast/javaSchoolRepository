package com.MmsApplication.config.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "addressClient")
public class ClientAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Address_id")
    private int ID;

    @Column(name = "Country")
    private String Country;

    @Column(name = "City")
    private String City;

    @Column(name = "Zip")
    private int Zip;

    @Column(name = "Street")
    private String Street;

    @Column(name = "House")
    private int House;

    @Column(name = "Apartment")
    private int Apartment;

    @Column(name = "client_id", insertable = false, updatable = false)
    private int client_id;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

}
