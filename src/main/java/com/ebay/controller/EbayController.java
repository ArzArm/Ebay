package com.ebay.controller;


import com.ebay.dto.OfferDTO;
import com.ebay.entity.offer.Offer;
import com.ebay.exception.MissingAccessTokenException;
import com.ebay.service.EbayService;
import com.ebay.model.ItemModel;
import com.ebay.model.OfferModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("api/v1")
public class EbayController {


    @Autowired
    EbayService ebayService;


    @GetMapping("/items/{limit}")
    public List<ItemModel> getAllItems(@PathVariable Integer limit) throws URISyntaxException, MissingAccessTokenException {
      return ebayService.getAllItems(limit);
    }

    @PutMapping("/create-update-item/{SKU}")
    public void createUpdateItem(@Valid @RequestBody ItemModel itemModel, @PathVariable String SKU) throws MissingAccessTokenException {
       ebayService.createUpdateItem(itemModel,SKU);
    }

    @DeleteMapping("/delete-item/{SKU}")
    public void deleteItem(@PathVariable String SKU) throws MissingAccessTokenException {
        ebayService.deleteItem(SKU);
    }


    @PostMapping("/create-offer")
    public void createOffer(@Valid @RequestBody Offer offer) throws MissingAccessTokenException {
        ebayService.createOffer(offer);
    }


    @GetMapping("/offers/{SKU}")
    public List<OfferDTO> getAllOffers(@PathVariable String SKU) throws MissingAccessTokenException, URISyntaxException {
        return ebayService.getAllOffers(SKU);
    }

    @PutMapping("/update-offer/{offerId}")
    public void updateOffer(@Valid @RequestBody OfferModel offerModel, @PathVariable String offerId) throws MissingAccessTokenException {
        ebayService.updateOffer(offerModel, offerId);
    }

    @DeleteMapping("/delete-offer/{offerId}")
    public void deleteOffer(@PathVariable String offerId) throws MissingAccessTokenException {
        ebayService.deleteOffer(offerId);
    }



}

