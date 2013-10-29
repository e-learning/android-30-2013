package LV5.Calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Calculator extends Activity {


    private class NumButtonListener implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            final Button sender = (Button) view;
            final TextView field = (TextView) findViewById(R.id.textView);
            field.setText(field.getText().toString() + sender.getText().toString());
        }
    }
    private NumButtonListener numButtonListener = new NumButtonListener();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final Button num1 = (Button)findViewById(R.id.button2);

        final TextView TV_res =(TextView)findViewById(R.id.textView);



        findViewById(R.id.button).setOnClickListener(numButtonListener);
        findViewById(R.id.button3).setOnClickListener(numButtonListener);
        findViewById(R.id.button5).setOnClickListener(numButtonListener);
        findViewById(R.id.button1).setOnClickListener(numButtonListener);
        findViewById(R.id.button4).setOnClickListener(numButtonListener);
        findViewById(R.id.button8).setOnClickListener(numButtonListener);
        findViewById(R.id.button2).setOnClickListener(numButtonListener);
        findViewById(R.id.button6).setOnClickListener(numButtonListener);
        findViewById(R.id.button7).setOnClickListener(numButtonListener);


    }
}
