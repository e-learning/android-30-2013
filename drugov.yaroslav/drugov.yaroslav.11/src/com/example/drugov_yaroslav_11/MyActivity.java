package com.example.drugov_yaroslav_11;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyActivity extends Activity {

    static final private int MAIN_ACTIVITY = 0;
    static final private int NUM_OF_TESTS = 5;
    private int[] table;
    private TextView textView;
    private int index;

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MAIN_ACTIVITY)
            if (resultCode == RESULT_OK)
            {
                Update(data.getExtras().getInt("index"), data.getExtras().getInt("res"));
                index = data.getExtras().getInt("index") + data.getExtras().getInt("next");
                if (index >= 0 && index < NUM_OF_TESTS && data.getExtras().getInt("next") != 0)
                {
                    Intent intent = new Intent(getApplicationContext(), Test.class);
                    intent.putExtra("index", index);
                    startActivityForResult(intent, MAIN_ACTIVITY);
                }
            }
    }

    private void Update( int index, int value ) {
        if (index < 0)
            for (int i = 0; i < NUM_OF_TESTS; i++)
                table[i] = value;
        else
            table[index] = value;

        int res = 0;
        for (int i = 0; i < NUM_OF_TESTS; i++)
            res += table[i];

        textView.setText("Результат: " + res);
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        index = 0;
        table = new int[NUM_OF_TESTS];
        for (int i = 0; i < NUM_OF_TESTS; i++)
            table[i] = 0;

        textView = (TextView)findViewById(R.id.textView);

        ((Button)findViewById(R.id.button0)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Update(-1, 0);
                Intent intent = new Intent(getApplicationContext(), Test.class);
                intent.putExtra("index", 0);
                startActivityForResult(intent, MAIN_ACTIVITY);
            }
        });

        ((Button)findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Test.class);
                intent.putExtra("index", index);
                startActivityForResult(intent, MAIN_ACTIVITY);
            }
        });
    }
}
