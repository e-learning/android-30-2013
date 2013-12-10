package com.example.drugov_yaroslav_10;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyActivity extends Activity {
     InText inText;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        inText = new InText(this, R.id.in_text, new OutText(this, R.id.out_text));

        ((Button)findViewById(R.id.button_help)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), HelpActivity.class);
                startActivity(intent);
            }
        });

        new MyButton(this, R.id.button_0, inText);
        new MyButton(this, R.id.button_1, inText);
        new MyButton(this, R.id.button_2, inText);
        new MyButton(this, R.id.button_3, inText);
        new MyButton(this, R.id.button_4, inText);
        new MyButton(this, R.id.button_5, inText);
        new MyButton(this, R.id.button_6, inText);
        new MyButton(this, R.id.button_7, inText);
        new MyButton(this, R.id.button_8, inText);
        new MyButton(this, R.id.button_9, inText);

        new MyButton(this, R.id.button_add, inText);
        new MyButton(this, R.id.button_sub, inText);
        new MyButton(this, R.id.button_mul, inText);
        new MyButton(this, R.id.button_div, inText);

        new MyButton(this, R.id.button_left_br, inText);
        new MyButton(this, R.id.button_right_br, inText);
        new MyButton(this, R.id.button_comma, inText);
        new MyButton(this, R.id.button_dot, inText);

        new MyButton(this, R.id.button_clear, inText);
        new MyButton(this, R.id.button_delete, inText);

        new MyButton(this, R.id.button_sin, inText);
        new MyButton(this, R.id.button_cos, inText);
        new MyButton(this, R.id.button_tg, inText);
        new MyButton(this, R.id.button_ctg, inText);

        //new MyButton(this, R.id.button_pi, inText);
        //new MyButton(this, R.id.button_e, inText);
    }
}
