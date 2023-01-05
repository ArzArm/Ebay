package com.ebay.service;

import com.ebay.api.client.auth.oauth2.model.AccessToken;
import com.ebay.config.Authorization;
import com.ebay.dto.OfferDTO;
import com.ebay.entity.offer.Offer;
import com.ebay.model.ItemModel;
import com.ebay.model.OfferModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class EbayService {
    @Autowired
    Authorization authorization;


    private HttpHeaders getHttpHeaders() throws IOException {
        AccessToken accessToken = authorization.getAccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentLanguage(Locale.US);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(accessToken.getToken());
        return headers;
    }

    public List<ItemModel> getAllItems(Integer limit) throws URISyntaxException, IOException {
        String url = "https://api.sandbox.ebay.com/sell/inventory/v1/inventory_item?limit=" + limit + "&offset=0";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHttpHeaders();
        String jsonResponse = restTemplate.exchange(RequestEntity.get(new URI(url)).headers(headers).build(), String.class).getBody();
        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        JsonArray jsonArray = jsonObject.getAsJsonArray("inventoryItems");
        String jsonString = jsonArray.toString();
        Gson gson = new Gson();
        Type typeList = new TypeToken<ArrayList<ItemModel>>() {
        }.getType();
        return gson.fromJson(jsonString, typeList);
    }


    public void createUpdateItem(ItemModel itemModel, String SKU) throws IOException {
        String url = "https://api.sandbox.ebay.com/sell/inventory/v1/inventory_item/" + SKU;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHttpHeaders();
        HttpEntity<ItemModel> entity = new HttpEntity<>(itemModel, headers);
        restTemplate.put(url, entity);
    }


    public void deleteItem(String SKU) throws IOException {
        String url = "https://api.sandbox.ebay.com/sell/inventory/v1/inventory_item/" + SKU;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHttpHeaders();
        HttpEntity<ItemModel> entity = new HttpEntity<>(headers);
        restTemplate.delete(url, entity);
    }


    public void createOffer(Offer offer) throws IOException {
        String url = "https://api.sandbox.ebay.com/sell/inventory/v1/offer";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHttpHeaders();
        HttpEntity<Offer> entity = new HttpEntity<>(offer, headers);
      restTemplate.postForEntity(url, entity, Offer.class).getBody();
    }


    public List<OfferDTO> getAllOffers(String SKU) throws IOException, URISyntaxException {
        String url = "https://api.sandbox.ebay.com/sell/inventory/v1/offer?sku=" + SKU;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHttpHeaders();
        String jsonResponse = restTemplate.exchange(RequestEntity.get(new URI(url)).headers(headers).build(), String.class).getBody();
        JsonObject jsonObject = new Gson().fromJson(jsonResponse, JsonObject.class);
        JsonArray jsonArray = jsonObject.getAsJsonArray("offers");
        String jsonString = jsonArray.toString();
        Gson gson = new Gson();
        Type typeList = new TypeToken<ArrayList<OfferDTO>>() {
        }.getType();
        return gson.fromJson(jsonString, typeList);

    }

    public void updateOffer(OfferModel offerModel, String offerId) throws IOException {
        String url = "https://api.sandbox.ebay.com/sell/inventory/v1/offer/" + offerId;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHttpHeaders();
        HttpEntity<OfferModel> entity = new HttpEntity<>(offerModel, headers);
        restTemplate.put(url, entity);
    }

    public void deleteOffer(String offerId) throws IOException {
        String url = "https://api.sandbox.ebay.com/sell/inventory/v1/offer/" + offerId;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHttpHeaders();
        HttpEntity<ItemModel> entity = new HttpEntity<>(headers);
        restTemplate.delete(url, entity);
    }

}
