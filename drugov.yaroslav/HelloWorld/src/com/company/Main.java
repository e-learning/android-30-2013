package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        IntegerValue IV = new IntegerValue();

        IV.Attach(new Printer(100));
        IV.Attach(new Printer(50));

        /*
        IV.Attach(new Observer(){
            public void HandleEvent( int NewValue ) throws TooManyRecords
            {
                System.out.println("Lol.");
            }
            public void Delete() {}
            });
        */
        Random random = new Random();
        for (int i = 0; i < 50; i++)
            IV.Change(random.nextInt() % 10);
        IV.Attach(new Printer());
        for (int i = 0; i < 100; i++)
            IV.Change(random.nextInt() % 10);

        IV.Delete();
    }
}
