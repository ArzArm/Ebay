package com.ebay.service;

import com.ebay.dto.OfferDTO;
import com.ebay.entity.offer.Offer;
import com.ebay.exception.MissingAccessTokenException;
import com.ebay.model.ItemModel;
import com.ebay.model.OfferModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbayService {

    private final RestTemplate restTemplate;

    @Value("${ebay.url}")
    private String url;

    public EbayService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ItemModel> getAllItems(Integer limit) throws URISyntaxException, MissingAccessTokenException {
        String urlItem = url + "inventory_item?limit=" + limit + "&offset=0";
        String jsonResponse = restTemplate.exchange(RequestEntity.get(new URI(urlItem)).build(), String.class).getBody();
        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        JsonArray jsonArray = jsonObject.getAsJsonArray("inventoryItems");
        String jsonString = jsonArray.toString();
        Gson gson = new Gson();
        Type typeList = new TypeToken<ArrayList<ItemModel>>() {
        }.getType();
        return gson.fromJson(jsonString, typeList);
    }


    public void createUpdateItem(ItemModel itemModel, String SKU) throws MissingAccessTokenException {
        String itemUrl = url + "inventory_item/" + SKU;
        HttpEntity<ItemModel> entity = new HttpEntity<>(itemModel);
        restTemplate.put(itemUrl, entity);
    }


    public void deleteItem(String SKU) throws MissingAccessTokenException {
        String itemUrl = url + "inventory_item/" + SKU;
        restTemplate.delete(itemUrl);
    }


    public void createOffer(Offer offer) throws MissingAccessTokenException {
        String itemUrl = url + "offer";
        HttpEntity<Offer> entity = new HttpEntity<>(offer);
        restTemplate.postForEntity(itemUrl, entity, Offer.class).getBody();
    }


    public List<OfferDTO> getAllOffers(String SKU) throws URISyntaxException, MissingAccessTokenException {
        String itemUrl = url + "offer?sku=" + SKU;
        String jsonResponse = restTemplate.exchange(RequestEntity.get(new URI(itemUrl)).build(), String.class).getBody();
        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        JsonArray jsonArray = jsonObject.getAsJsonArray("offers");
        String jsonString = jsonArray.toString();
        Gson gson = new Gson();
        Type typeList = new TypeToken<ArrayList<OfferDTO>>() {
        }.getType();
        return gson.fromJson(jsonString, typeList);

    }

    public void updateOffer(OfferModel offerModel, String offerId) throws MissingAccessTokenException {
        String itemUrl = url + "offer/" + offerId;
        HttpEntity<OfferModel> entity = new HttpEntity<>(offerModel);
        restTemplate.put(itemUrl, entity);
    }

    public void deleteOffer(String offerId) throws MissingAccessTokenException {
        String itemUrl = url + "offer/" + offerId;
        restTemplate.delete(itemUrl);
    }

}
