package com.ebay.entity.offer;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter

public class Offer {

    @NotNull
    @Length(max = 5)
    String sku;
    @NotNull
    String marketplaceId;
    @NotNull
    String format;
    @Length(max = 200)
    String listingDescription;
    @NotNull
    Long availableQuantity;

    @NotNull
    PricingSummary pricingSummary;
    @NotNull
    Long quantityLimitPerBuyer;



}
