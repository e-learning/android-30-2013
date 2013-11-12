package com.asamarin.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends Activity {
    private TextView textView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        textView = (TextView) findViewById(R.id.textView);

        ((Button)findViewById(R.id.buttonCalculate)).setOnClickListener(new DisplayMessageClickListener("Message!"));

        ((Button)findViewById(R.id.button1)).setOnClickListener(new AddNumberClickListener("1"));
        ((Button)findViewById(R.id.button2)).setOnClickListener(new AddNumberClickListener("2"));
        ((Button)findViewById(R.id.button3)).setOnClickListener(new AddNumberClickListener("3"));
        ((Button)findViewById(R.id.button4)).setOnClickListener(new AddNumberClickListener("4"));
        ((Button)findViewById(R.id.button5)).setOnClickListener(new AddNumberClickListener("5"));
        ((Button)findViewById(R.id.button6)).setOnClickListener(new AddNumberClickListener("6"));
        ((Button)findViewById(R.id.button7)).setOnClickListener(new AddNumberClickListener("7"));
        ((Button)findViewById(R.id.button8)).setOnClickListener(new AddNumberClickListener("8"));
        ((Button)findViewById(R.id.button9)).setOnClickListener(new AddNumberClickListener("9"));
        ((Button)findViewById(R.id.button0)).setOnClickListener(new AddNumberClickListener("0"));

        ((Button)findViewById(R.id.resetButton)).setOnClickListener(new ClearNumberClickListener());
        ((Button)findViewById(R.id.buttonPoint)).setOnClickListener(new AddCommaClickListener());
    }

    private class AddCommaClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (!textView.getText().toString().contains("."))
                textView.setText(textView.getText() + ".");
        }
    }

    private class ClearNumberClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            textView.setText("0");
        }
    }

    private class AddNumberClickListener implements View.OnClickListener {
        private String num;

        private AddNumberClickListener(String num) {
            this.num = num;
        }

        @Override
        public void onClick(View view) {
            if ("0".equals(textView.getText()))
                textView.setText("");

            if (textView.getText().length() < 9)
              textView.setText(textView.getText() + num);
        }
    }

    private class DisplayMessageClickListener implements View.OnClickListener {
        private String message;

        private DisplayMessageClickListener(String message) {
            this.message = message;
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(CalculatorActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
