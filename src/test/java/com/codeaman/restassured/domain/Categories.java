package com.codeaman.restassured.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Categories{

    private int id;

    private String name;

    private Categories() {
    }

    public Categories(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
