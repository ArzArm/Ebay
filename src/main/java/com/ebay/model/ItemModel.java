package com.ebay.model;

import com.ebay.entity.item.Availability;
import com.ebay.entity.item.Product;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ItemModel {
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
