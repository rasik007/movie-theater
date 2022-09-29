package com.jpmc.theater.model;

import lombok.Data;
import lombok.ToString;

import java.util.Objects;

@Data
@ToString
public class Customer {

    private String name;
    private String id;

    /**
     * @param name customer name
     * @param id customer id
     */
    public Customer(String name, String id) {
        this.id = id; // NOTE - id is not used anywhere at the moment
        this.name = name;
    }


}