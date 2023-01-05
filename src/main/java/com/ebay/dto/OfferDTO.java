package com.ebay.dto;

import com.ebay.entity.offer.PricingSummary;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OfferDTO {

    String offerId;
    String listingDescription;
    Long quantityLimitPerBuyer;
    PricingSummary pricingSummary;

}
