package com.company;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: SEMI12
 * Date: 08.10.13
 * Time: 19:58
 * To change this template use File | Settings | File Templates.
 */
public class Model {
    private int neg, pos, zero;
    private ArrayList observers;

    public Model() {
        neg = pos = zero = 0;
        observers = new ArrayList();
    }

    public void AddObserver( Observer observer ) {
        observers.add(observer);
    }

    public void AddNumber( int number ) {
        int sign;
        if (number > 0)
        {
            sign = 1;
            pos++;
        }
        else if (number < 0)
        {
            sign = -1;
            neg++;
        }
        else
        {
            sign = 0;
            zero++;
        }
        for (int i = 0; i < observers.size(); i++)
        {
            Observer observer = (Observer)observers.get(i);

            if (sign > 0)
                observer.ChangePositive();
            else if (sign < 0)
                observer.ChangeNegative();
            else
                observer.ChangeZero();
        }
    }

    int GetPositive() {
        return pos;
    }

    int GetNegative() {
        return neg;
    }

    int GetZero() {
        return zero;
    }
}
