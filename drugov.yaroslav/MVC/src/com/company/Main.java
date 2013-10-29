package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Model model = new Model();
        Random random = new Random();
        for (int i = 0; i < 10; i++)
        {
            int a = (random.nextInt() % 3) - 1;
            Observer observer;
            if (a > 0)
                observer = new ViewPositive();
            else if (a < 0)
                observer = new ViewNegative();
            else
                observer = new ViewZero();
            observer.AddModel(model);
            model.AddObserver(observer);
        }
	    Controller controller = new Controller(model);
        while (controller.Update()) {}
    }
}
