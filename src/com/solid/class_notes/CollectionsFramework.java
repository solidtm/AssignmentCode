package com.solid.class_notes;

import java.util.*;

public class CollectionsFramework {
    /*
    * 1. Lists
    * 2. Set
    * 3. Map
    *
    * */

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Richard");
        names.add("Alice");
        names.add("John");

        String firstName = names.get(0);
        System.out.println(names);
        System.out.println(firstName);

        Set<String> countries = new HashSet<>();
        countries.add("Nigeria");
        countries.add("Morocco");
        countries.add("Tanzania");
        countries.add("Niger Republic");

        System.out.println(countries);

    }
}
