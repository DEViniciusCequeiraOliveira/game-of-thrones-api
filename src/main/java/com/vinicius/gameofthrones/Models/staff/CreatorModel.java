package com.vinicius.gameofthrones.Models.staff;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
public class CreatorModel extends Staff {
    public CreatorModel(String name) {
        super(name);
    }
}
