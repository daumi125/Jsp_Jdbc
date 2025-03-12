package com.test.java;

import java.util.ArrayList;

public class Item {
    private String name;
    private String address;
    
    public Item(String name, String address) {
        super();
        this.name = name;
        this.address = address;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
    public static ArrayList<Item> generate(){
        ArrayList<Item> list = new ArrayList<Item>();
        
        list.add(new Item("독수리", "강남구"));
        list.add(new Item("참새", "강서구"));
        list.add(new Item("비둘기", "강동구"));
        list.add(new Item("꿩", "강북구"));
        list.add(new Item("매", "중구"));
        
        return list;
    }
    
    
}
