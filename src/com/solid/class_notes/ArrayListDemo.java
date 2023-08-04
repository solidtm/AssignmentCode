package com.solid.class_notes;

import java.util.*;
public class ArrayListDemo{
    public static void main(String[] args) {

        //ArrayList
        ArrayList<Integer> myList = new ArrayList();

        //ArrayList methods
        // add()
        myList.add(1);
        myList.add(2);
        myList.add(0, 0);
        System.out.println(myList);
        System.out.println(myList.size());

        myList.remove(1);
        System.out.println(myList);
//        myList.clear();
        int gottenValue = myList.get(1);
        System.out.println(gottenValue);
        myList.set(1, 3);
        System.out.println(myList);


//        int, boolean, double,

        int x = 5;
        Integer y = new Integer(6);

    }
}
