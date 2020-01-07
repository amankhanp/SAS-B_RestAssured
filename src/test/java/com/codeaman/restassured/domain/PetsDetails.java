package com.codeaman.restassured.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PetsDetails {

    private int id;

    private Categories category;

    private String name;

    private List<String> photoUrls;

    private List<Tags> tags;

    private String status;

    private PetsDetails() {
    }

    public PetsDetails(int id, Categories category, String name, String status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.status = status;
    }
}
