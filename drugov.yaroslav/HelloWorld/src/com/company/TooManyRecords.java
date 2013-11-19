package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: SEMI12
 * Date: 01.10.13
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */
public class TooManyRecords extends Exception {
    public int max;

    public TooManyRecords( int maxRecords ) {
        max = maxRecords;
    }
}
