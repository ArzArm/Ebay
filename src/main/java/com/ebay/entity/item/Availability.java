package com.ebay.entity.item;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Availability {

    Map<String,Integer> shipToLocationAvailability = new HashMap<>();

}
