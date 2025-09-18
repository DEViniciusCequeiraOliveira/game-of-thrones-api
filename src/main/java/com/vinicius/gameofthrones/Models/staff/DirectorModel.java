package com.vinicius.gameofthrones.Models.staff;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class DirectorModel extends Staff {
    public DirectorModel(String name) {
        super(name);
    }
}
