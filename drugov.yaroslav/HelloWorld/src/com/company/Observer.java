package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: YD4
 * Date: 24.09.13
 * Time: 18:12
 * To change this template use File | Settings | File Templates.
 */
public interface Observer {
    public void HandleEvent( int NewValue ) throws TooManyRecords ;
    public void Delete();
}
