package org.example.web.controller;

import org.junit.Test;

public class TestTing {

    @Test
    public void test1(){
        String value = "100-200";
        String[] split = value.split("-");
        System.out.println("hola");
        System.out.println(split.length);
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }
    
    }



}
