package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: SEMI12
 * Date: 10.12.13
 * Time: 18:12
 * To change this template use File | Settings | File Templates.
 */
public class Person extends Thread {
    private String name;

    Person( String name ) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true)
            System.out.println(name);
    }
}
