package com.gbdmf.demo.dubbo3.api;


import java.util.concurrent.TimeUnit;

public class GreetingsServiceImpl implements GreetingsService {
    @Override
    public String sayHi(String name) {

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "hi, " + name;
    }
}