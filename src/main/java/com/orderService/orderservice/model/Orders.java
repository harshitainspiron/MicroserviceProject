package com.orderService.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders implements Serializable {

    private static final Long serialVersionId =  32312314114L;

    private int id;
    private String product;
    private int quantity;

}
