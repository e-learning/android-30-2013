package com.company;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: SEMI12
 * Date: 08.10.13
 * Time: 20:00
 * To change this template use File | Settings | File Templates.
 */
public class Controller {
    Scanner scanner;
    Model model;

    public Controller() {
        scanner = new Scanner(System.in);
    }

    public Controller( Model newModel ) {
        scanner = new Scanner(System.in);
        SetModel(newModel);
    }

    public void SetModel( Model newModel ) {
        model = newModel;
    }

    public boolean Update() {
        System.out.print("Input number: ");
        model.AddNumber(scanner.nextInt());

        System.out.println("Do you want to continue? 'y' / 'n'");
        if (scanner.next().equals("y"))
            return true;

        return false;
    }
}
