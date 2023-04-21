package com.vinicius.gameofthrones.Models;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Getter@Setter
@NoArgsConstructor @AllArgsConstructor
public class BornModel {
    private String timeline;
    private String local;



}
