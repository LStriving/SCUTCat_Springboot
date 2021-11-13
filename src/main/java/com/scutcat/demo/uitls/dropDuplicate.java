package com.scutcat.demo.uitls;

import java.util.List;
import java.util.TreeSet;

public class dropDuplicate {
    public static List<String> drop(List<String> res){
        /*
         remove duplicate element in list and maintain the order
         */
        TreeSet<String> set = new TreeSet<>(res);
        //clear all element in list
        res.clear();
        //add all object in hashset to res
        res.addAll(set);
        return res;
    }
}
