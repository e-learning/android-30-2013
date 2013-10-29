package ru.spb.school30.ldvsoft.calc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static ru.spb.school30.ldvsoft.calc.R.id.*;

public class MainActivity extends Activity
{
    private class DigitButtonListener implements View.OnClickListener
	{
		@Override
		public void onClick(View view)
		{
			final Button sender = (Button) view;
			final TextView field = (TextView) findViewById(editText);
			field.setText(field.getText().toString() + sender.getText().toString());
		}
	}
	private DigitButtonListener digitButtonListener = new DigitButtonListener();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		findViewById(buttonCalculate).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				String expression = ((TextView) findViewById(editText)).getText().toString();
				try
				{
					expression = Double.toString(Calculator.Calc(expression));
				}
				catch (Exception E)
				{
					expression = "ERROR: " + E.toString();
				}
				((TextView) findViewById(editText)).setText(expression);
			}
		});

		findViewById(buttonClear).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				((TextView) findViewById(editText)).setText("");
			}
		});

		findViewById(button0).setOnClickListener(digitButtonListener);
		findViewById(button1).setOnClickListener(digitButtonListener);
		findViewById(button2).setOnClickListener(digitButtonListener);
		findViewById(button3).setOnClickListener(digitButtonListener);
		findViewById(button4).setOnClickListener(digitButtonListener);
		findViewById(button5).setOnClickListener(digitButtonListener);
		findViewById(button6).setOnClickListener(digitButtonListener);
		findViewById(button7).setOnClickListener(digitButtonListener);
		findViewById(button8).setOnClickListener(digitButtonListener);
		findViewById(button9).setOnClickListener(digitButtonListener);
		findViewById(buttonDot).setOnClickListener(digitButtonListener);
		findViewById(buttonAdd     ).setOnClickListener(digitButtonListener);
		findViewById(buttonSubtract).setOnClickListener(digitButtonListener);
		findViewById(buttonMultiply).setOnClickListener(digitButtonListener);
		findViewById(buttonDivide  ).setOnClickListener(digitButtonListener);
	}
}
