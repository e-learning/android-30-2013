package ru.spb.school30.ldvsoft.calc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		final Button btn = (Button) findViewById(R.id.buttonCalculate);
		btn.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				Toast.makeText(MainActivity.this, "Hello! Is it me you're looking for?", Toast.LENGTH_LONG).show();
			}
		});
    }
}
