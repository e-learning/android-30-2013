package com.example.drugov_yaroslav_11;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 09.12.13
 * Time: 17:57
 * To change this template use File | Settings | File Templates.
 */
public class Test extends Activity {
    public class TestText {
        final int[] strings = {
                // Test 1
                R.string.q_1,
                R.string.c_1,
                R.string.ic1_1,
                R.string.ic2_1,
                R.string.ic3_1,
                // Test 2
                R.string.q_2,
                R.string.c_2,
                R.string.ic1_2,
                R.string.ic2_2,
                R.string.ic3_2,
                // Test 3
                R.string.q_3,
                R.string.c_3,
                R.string.ic1_3,
                R.string.ic2_3,
                R.string.ic3_3,
                // Test 4
                R.string.q_4,
                R.string.c_4,
                R.string.ic1_4,
                R.string.ic2_4,
                R.string.ic3_4,
                // Test 5
                R.string.q_5,
                R.string.c_5,
                R.string.ic1_5,
                R.string.ic2_5,
                R.string.ic3_5,
        };

        public String question;
        public String correct;
        public String[] incorrect;

        public TestText ( Activity activity, int index ) {
            if (index >= strings.length / 5)
                return;

            incorrect = new String[3];
            question   = activity.getString(strings[index * 5 + 0]);
            correct    = activity.getString(strings[index * 5 + 1]);
            incorrect[0] = activity.getString(strings[index * 5 + 2]);
            incorrect[1] = activity.getString(strings[index * 5 + 3]);
            incorrect[2] = activity.getString(strings[index * 5 + 4]);
        }

        public TestText ( String question, String correct, String incorrect1, String incorrect2, String incorrect3 ) {
            this.question   = question;
            this.correct    = correct;
            this.incorrect[0] = incorrect1;
            this.incorrect[1] = incorrect2;
            this.incorrect[2] = incorrect3;
        }
    }

    private TestText text;
    private RadioButton[] buttons;
    private int index;
    private int cor;
    private int res;

    public void onRadioClick( View v ) {
        Intent intent = new Intent();

        intent.putExtra("index", index);
        if (v.getId() == buttons[cor].getId())
            res = 1;
        else
            res = 0;
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        //Intent intent = new Intent(getApplicationContext(), MyActivity.class);
        Intent intent = new Intent();
        intent.putExtra("next", 0);

        ((Button)findViewById(R.id.button0)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getApplicationContext(), MyActivity.class);
                Intent intent = new Intent();
                intent.putExtra("index", index);
                intent.putExtra("res", res);
                intent.putExtra("next", -1);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        ((Button)findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getApplicationContext(), MyActivity.class);
                Intent intent = new Intent();
                intent.putExtra("index", index);
                intent.putExtra("res", res);
                intent.putExtra("next", 1);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        ((Button)findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getApplicationContext(), MyActivity.class);
                Intent intent = new Intent();
                intent.putExtra("index", index);
                intent.putExtra("res", res);
                intent.putExtra("next", 0);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        index = getIntent().getExtras().getInt("index");
        text = new TestText(this, index);

        Random random = new Random();

        buttons = new RadioButton[4];
        buttons[0] = (RadioButton)findViewById(R.id.radioButton0);
        buttons[1] = (RadioButton)findViewById(R.id.radioButton1);
        buttons[2] = (RadioButton)findViewById(R.id.radioButton2);
        buttons[3] = (RadioButton)findViewById(R.id.radioButton3);

        ArrayList list = new ArrayList();
        for (int i = 0; i < 4; i++)
            list.add(i);
        int a, b;
        for (int i = 0; i < 4; i++)
        {
            if ((a = (Integer)list.get(b = random.nextInt(4 - i))) == 0)
            {
                buttons[i].setText(text.correct);
                cor = i;
            }
            else
                buttons[i].setText(text.incorrect[a - 1]);
            list.remove(b);
        }
        list.clear();
        ((TextView)findViewById(R.id.textView)).setText(text.question);
    }
}
