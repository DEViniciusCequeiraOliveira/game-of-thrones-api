package com.vinicius.gameofthrones.Models.staff;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
public class ProducersModel extends Staff {
    public ProducersModel(String name) {
        super(name);
    }
}
