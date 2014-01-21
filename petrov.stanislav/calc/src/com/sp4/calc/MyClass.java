package com.sp4.calc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: SEMI14
 * Date: 19.11.13
 * Time: 17:44
 * To change this template use File | Settings | File Templates.
 */
public class MyClass extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String his;
        setContentView(R.layout.mylayaut);

        Intent i = getIntent();

        his = i.getStringExtra("s2");
        TextView t = (TextView) findViewById(R.id.editText1);
        t.setText(his);
    }
}
