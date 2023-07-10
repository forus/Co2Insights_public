package nl.rabobank.co2insights.services;

import nl.rabobank.co2insights.entity.Transaction;

import java.util.*;

public class CategoryService {

    public enum Category {
        Energy, General, CarRental, DrugStores, Furniture, TravelAgencies, Groceries, BakeriesAndCafes, Catering, ConvenienceStores,
        PublicTransport, LodgingAndAccommodation, ServiceStations, TaxiAndLimousines, TrainTravel, AirTravel, Clothes, OnlineShopping, Other

    }

    public static Category categorize(Transaction transaction) {
        String targetAccountIban = transaction.getCounterParty();
        HashMap<Category, List<String>> categoryMapping = getCategoriesListHashMap();

        return categoryMapping.entrySet().stream()
                .filter(entry -> entry.getValue().contains(targetAccountIban.toLowerCase(Locale.ROOT)))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(Category.Other);
    }

    private static HashMap<Category, List<String>> getCategoriesListHashMap() {
        HashMap<Category, List<String>> categoryMapping = new HashMap<>(); //Linked HashMap for order
        categoryMapping.put(Category.Clothes, Arrays.asList("zara", "hm", "guess", "mango"));
        categoryMapping.put(Category.Groceries, Arrays.asList("lidl", "aldi", "albert", "jumbo", "coop"));
        categoryMapping.put(Category.AirTravel, Arrays.asList("wizzair", "ryanair", "klm", "turkish airlines"));
        categoryMapping.put(Category.Energy, Arrays.asList("eneco","engie","greenchoice"));
        categoryMapping.put(Category.Furniture, Arrays.asList("ikea", "jysk"));
        categoryMapping.put(Category.ConvenienceStores, Arrays.asList("spar"));
        categoryMapping.put(Category.Catering, Arrays.asList("hellofresh"));
        return categoryMapping;
    }

}


