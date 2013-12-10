package com.example.drugov_yaroslav_10;

import android.app.Activity;
import android.text.method.KeyListener;
import android.view.View;
import android.widget.Button;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 18.11.13
 * Time: 22:26
 * To change this template use File | Settings | File Templates.
 */
public class MyButton implements View.OnClickListener {
    private Button button;
    private InText redactor;

    MyButton( Activity activity, int id, InText inText ) {
        button = (Button)activity.findViewById(id);
        button.setOnClickListener(this);
        redactor = inText;
    }

    public void onClick( View view ) {
        redactor.Add(button.getText().toString());
    }
}
