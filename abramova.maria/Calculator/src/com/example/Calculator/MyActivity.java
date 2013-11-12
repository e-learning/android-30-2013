package com.example.Calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyActivity extends Activity
{
  public static String his = "";
  private class ButtonListener implements View.OnClickListener
  {
    @Override
    public void onClick(View view)
    {
      final Button sender = (Button) view;
      final TextView field = (TextView) findViewById(R.id.textView);
      field.setText(field.getText().toString() + sender.getText().toString());
    }
  }
  private class ClearListener implements View.OnClickListener
  {
    @Override
    public void onClick(View view)
    {
      final Button sender = (Button) view;
      final TextView field = (TextView) findViewById(R.id.textView);
      if (sender.getText().toString().charAt(0) == 'C')
        field.setText("");
      else if (sender.getText().toString().charAt(0) == 'H')
        field.setText(his);
    }
  }
  private class EqualListener implements View.OnClickListener
  {
    @Override
    public void onClick(View view)
    {
      final Button sender = (Button) view;
      final TextView field = (TextView) findViewById(R.id.textView);
      Double ans;
      String str;
      his += (str = field.getText().toString());
      his += "=";
      his += (ans = Parser.Calculator(str));
      his += "\n";
      field.setText(Double.toString(ans));

    }
}

  private ButtonListener buttonListener = new ButtonListener();
  private ClearListener clearListener = new ClearListener();
  private EqualListener equalListener = new EqualListener();
  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);



    findViewById(R.id.id0).setOnClickListener(buttonListener);
    findViewById(R.id.id1).setOnClickListener(buttonListener);
    findViewById(R.id.id2).setOnClickListener(buttonListener);
    findViewById(R.id.id3).setOnClickListener(buttonListener);
    findViewById(R.id.id4).setOnClickListener(buttonListener);
    findViewById(R.id.id5).setOnClickListener(buttonListener);
    findViewById(R.id.id6).setOnClickListener(buttonListener);
    findViewById(R.id.id7).setOnClickListener(buttonListener);
    findViewById(R.id.id8).setOnClickListener(buttonListener);
    findViewById(R.id.id9).setOnClickListener(buttonListener);
    findViewById(R.id.add).setOnClickListener(buttonListener);
    findViewById(R.id.clear).setOnClickListener(clearListener);
    findViewById(R.id.cosinus).setOnClickListener(buttonListener);
    findViewById(R.id.cotangents).setOnClickListener(buttonListener);
    findViewById(R.id.divide).setOnClickListener(buttonListener);
    findViewById(R.id.equal).setOnClickListener(equalListener);
    findViewById(R.id.multiply).setOnClickListener(buttonListener);
    findViewById(R.id.factorial).setOnClickListener(buttonListener);
    findViewById(R.id.point).setOnClickListener(buttonListener);
    findViewById(R.id.power).setOnClickListener(buttonListener);
    findViewById(R.id.sqrt).setOnClickListener(buttonListener);
    findViewById(R.id.subtract).setOnClickListener(buttonListener);
    findViewById(R.id.tangents).setOnClickListener(buttonListener);
    findViewById(R.id.sinus).setOnClickListener(buttonListener);
    findViewById(R.id.openbracket).setOnClickListener(buttonListener);
    findViewById(R.id.closebracket).setOnClickListener(buttonListener);
    findViewById(R.id.history).setOnClickListener(clearListener);



    }

}
