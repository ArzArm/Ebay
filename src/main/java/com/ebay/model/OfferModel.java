package com.ebay.model;

import com.ebay.entity.offer.PricingSummary;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter

public class OfferModel {

    @Length(max = 30)
    String listingDescription;
    Long quantityLimitPerBuyer;
    @NotNull
    PricingSummary pricingSummary;
}
