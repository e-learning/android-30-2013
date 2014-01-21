package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        MyThread t1 = new MyThread("vasya");
        MyThread t2 = new MyThread("petya");
        t1.start();
        t2.start();
    }
}
