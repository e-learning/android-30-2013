package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: SEMI14
 * Date: 10.12.13
 * Time: 18:12
 * To change this template use File | Settings | File Templates.
 */
public class MyThread extends Thread {

    String Name;
    MyThread( String name )
    {
        Name = name;
    }
    @Override
    public void run() {
      while(true)
      {
          System.out.println(Name);
      }
    }


}
