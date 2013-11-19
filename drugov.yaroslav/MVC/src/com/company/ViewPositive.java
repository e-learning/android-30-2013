package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: SEMI12
 * Date: 08.10.13
 * Time: 19:59
 * To change this template use File | Settings | File Templates.
 */
public class ViewPositive extends DefaultView {
    public void ChangePositive() {
        System.out.println("Number of positive = " + model.GetPositive());
    }
}
