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

    @Getter @Setter
    public class Categories{

        private int id;

        private String name;
    }

}
