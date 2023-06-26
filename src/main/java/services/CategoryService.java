package services;

import entity.Transaction;

import java.util.*;

public class CategoryService {

    public enum Category {
        Energy, General, CarRental, DrugStores, Furniture, TravelAgencies, Groceries, BakeriesAndCafes, Catering, ConvenienceStores,
        PublicTransport, LodgingAndAccommodation, ServiceStations, TaxiAndLimousines, TrainTravel, AirTravel, Clothes, OnlineShopping, Other

    }

    public static Category categorize(Transaction transaction) {
        String targetAccountIban = transaction.getCounterparty();
        HashMap<Category, List<String>> categoryMapping = getCategoriesListHashMap();

        return categoryMapping.entrySet().stream()
                .filter(entry -> entry.getValue().contains(targetAccountIban.toLowerCase(Locale.ROOT)))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(Category.Other);
    }

    private static HashMap<Category, List<String>> getCategoriesListHashMap() {
        HashMap<Category, List<String>> categoryMapping = new HashMap<>(); //Linked HashMap for order
        categoryMapping.put(Category.Clothes, Arrays.asList("Zara", "hm", "Guess", "Mango"));
        categoryMapping.put(Category.Groceries, Arrays.asList("Lidl", "Aldi", "Albert", "Jumbo", "Coop"));
        categoryMapping.put(Category.AirTravel, Arrays.asList("WizzAir", "RyanAir", "KLM", "Turkish Airlines"));
        categoryMapping.put(Category.Energy, Arrays.asList("Eneco","Engie","Greenchoice"));
        categoryMapping.put(Category.Furniture, Arrays.asList("Ikea", "Jysk"));
        categoryMapping.put(Category.ConvenienceStores, Arrays.asList("Spar"));
        categoryMapping.put(Category.Catering, Arrays.asList("HelloFresh"));
        return categoryMapping;
    }

}


