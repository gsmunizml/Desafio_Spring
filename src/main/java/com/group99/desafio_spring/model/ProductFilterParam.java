package com.group99.desafio_spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductFilterParam {
    private String category;
    private Integer order;
    private String prestige;
    private Boolean freeShipping;

    public boolean hasFilterParam() {
        return this.category != null || this.freeShipping != null || this.prestige != null;
    }

    public boolean hasOrderParam() {
        return this.order != null;
    }
}
