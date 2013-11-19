package com.company;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: YD4
 * Date: 24.09.13
 * Time: 18:19
 * To change this template use File | Settings | File Templates.
 */
public class IntegerValue implements Object {

    private int value;
    private ArrayList printers;

    public IntegerValue() {
        printers = new ArrayList();
    }
    public void Change( int NewValue ) {
        value = NewValue;
        Update();
    }

    public void Attach( Observer observer ) {
        printers.add(observer);
    }

    private void Update() {
        for (int i = 0; i < printers.size(); i++)
        {
            try {
                Observer p = (Observer)printers.get(i);
                p.HandleEvent(value);
            }
            catch (TooManyRecords e) {
                System.out.println("Too many records. Max = " + e.max);
            }
            finally {
                System.out.println("End of value changing in printer #" + (i + 1));
                System.out.println("");
            }
        }
    }

    public void Delete() {
        for (int i = 0; i < printers.size(); i++)
        {
            Observer p = (Observer)printers.get(i);
            p.Delete();
        }
        printers.clear();
    }
}
