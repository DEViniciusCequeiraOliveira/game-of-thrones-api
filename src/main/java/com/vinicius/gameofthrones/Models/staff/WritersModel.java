package com.vinicius.gameofthrones.Models.staff;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
public class WritersModel extends Staff {
    public WritersModel(String name) {
        super(name);
    }
}
