package org.example.web.controller;

import java.util.concurrent.Callable;

public class MyTask implements Callable<String> {
    private String name;
    public MyTask(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(new Long(name));
        return "Termine ok!!";
    }
}
