package com.ebay.entity.item;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Item {
    @NotNull
    @Length(max = 5)
    String sku;
    @NotNull
    String locale;
    Product product;
    String condition;
    @NotNull
    Availability availability;



}
