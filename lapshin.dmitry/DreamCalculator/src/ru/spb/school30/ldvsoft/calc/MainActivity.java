package ru.spb.school30.ldvsoft.calc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static ru.spb.school30.ldvsoft.calc.R.id.*;

public class MainActivity extends Activity
{
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
					//expression = Double.toString(Calculator.Calc(expression));
					MathExpression expr = MathExpressionBuilder.buildExpression(expression);
					expression = expr.calculate().toString();
				}
				catch (Exception E)
				{
					expression = "ERROR: " + E.toString();
				}
				((TextView) findViewById(textView)).setText(expression);
			}
		});


		findViewById(buttonRCL).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				try
				{
					(new MathExpressionBuilderTest()).testBuildExpression();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		findViewById(buttonAC).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				((TextView) findViewById(editText)).setText("");
			}
		});

		findViewById(button1).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				findViewById(tableButtonsTop).setVisibility(View.GONE);
			}
		});
		findViewById(button2).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				findViewById(tableButtonsTop).setVisibility(View.VISIBLE);
			}
		});
	}
}
