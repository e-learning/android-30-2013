package ru.spb.school30.ldvsoft.calc;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import static ru.spb.school30.ldvsoft.calc.R.id.*;

public class MainActivity extends Activity
{
	private float mx, my;

	private ScrollView myvScroll;
	private HorizontalScrollView myhScroll;

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

	private class StringButtonListener implements View.OnClickListener
	{
		String toAdd;

		public StringButtonListener(String s)
		{
			toAdd = s;
		}

		@Override
		public void onClick(View view)
		{
			final TextView field = (TextView) findViewById(editText);
			field.setText(field.getText().toString() + toAdd);
		}
	}

	private void buildDisplay()
	{
		LinearLayout display = (LinearLayout) findViewById(R.id.display);
		TextView tv = new TextView(this);
		tv.setText("Meow?");
		display.addView(tv);
		tv.setGravity(Gravity.CENTER_VERTICAL);
		tv.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
		tv.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;

		myhScroll.fullScroll(View.FOCUS_LEFT);
		//myhScroll.scrollTo(0, 0);
		myvScroll.fullScroll(View.FOCUS_UP);
		//myvScroll.scrollTo(0, 0);

		Toast.makeText(this, Integer.toString(findViewById(R.id.display).getHeight()), Toast.LENGTH_SHORT).show();
	}

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		myvScroll = (ScrollView) findViewById(R.id.vScroll);
		myhScroll = (HorizontalScrollView) findViewById(R.id.hScroll);

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


		findViewById(buttonCalc).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				buildDisplay();
			}
		});
		findViewById(buttonRCL).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
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
		findViewById(buttonIntegral).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				int displayBaseHeight = findViewById(displayBase).getHeight();
				findViewById(vScroll).setMinimumHeight(displayBaseHeight);
				findViewById(displayBase1).setMinimumHeight(displayBaseHeight);
				findViewById(hScroll).setMinimumHeight(displayBaseHeight);
				findViewById(display).setMinimumHeight(displayBaseHeight);
				findViewById(hScroll).setMinimumHeight(displayBaseHeight);
				findViewById(displayBase1).setMinimumHeight(displayBaseHeight);
				findViewById(vScroll).setMinimumHeight(displayBaseHeight);

				findViewById(displayBase).invalidate();
				String message =
						"Edit:" + Integer.toString(findViewById(editText).getHeight()) +
								"\nDisplayBase: " + Integer.toString(displayBaseHeight) +
								"\nDisplayBase1: " + Integer.toString(findViewById(displayBase1).getHeight()) +
								"\nDisplay: " + Integer.toString(findViewById(display).getHeight());
				Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
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

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float curX, curY;

		switch (event.getAction()) {

			case MotionEvent.ACTION_DOWN:
				mx = event.getX();
				my = event.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				curX = event.getX();
				curY = event.getY();
				myvScroll.scrollBy((int) (mx - curX), (int) (my - curY));
				myhScroll.scrollBy((int) (mx - curX), (int) (my - curY));
				mx = curX;
				my = curY;
				break;
			case MotionEvent.ACTION_UP:
				curX = event.getX();
				curY = event.getY();
				myvScroll.scrollBy((int) (mx - curX), (int) (my - curY));
				myhScroll.scrollBy((int) (mx - curX), (int) (my - curY));
				break;
		}

		return true;
	}

	@Override
	protected void onStart()
	{
		super.onStart();
		findViewById(buttonIntegral).callOnClick();
	}
}
