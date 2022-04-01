package com.eseo.rest_server.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ville_france")
@Data public class Ville {

    @Id
    @Column(name = "insee_code", nullable = false)
    private String inseeCode;

    @Column(name = "name")
    private String name;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "label")
    private String label;

    @Column(name = "ligne5")
    private String ligne5;

    @Column(name = "latitude")
    private String lat;

    @Column(name = "longitude")
    private String lon;

}