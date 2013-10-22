package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: SEMI12
 * Date: 08.10.13
 * Time: 19:59
 * To change this template use File | Settings | File Templates.
 */
public class DefaultView implements Observer {
    protected Model model;

    public void ChangePositive() {}
    public void ChangeNegative() {}
    public void ChangeZero() {}
    public void AddModel( Model newModel ) {
        model = newModel;
    }
}
