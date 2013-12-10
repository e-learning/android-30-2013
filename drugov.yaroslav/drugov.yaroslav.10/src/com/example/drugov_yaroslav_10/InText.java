package com.example.drugov_yaroslav_10;

import android.app.Activity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Yaroslav
 * Date: 18.11.13
 * Time: 21:38
 * To change this template use File | Settings | File Templates.
 */
public class InText {
    private EditText editText;
    private String text;
    private ArrayList story;
    private OutText out;

    public InText( Activity activity, int id, OutText outText ) {
        editText = (EditText)activity.findViewById(id);
        story = new ArrayList();
        out = outText;
        //text = new String("");
        //Update();
        //editText.setInputType(InputType.TYPE_NULL);
    }

    public void Add( String string ) {
        int delta = 0;
        if (string.equals("C"))
             text = "";
        else
        {
            if (editText.length() > 0)
            {
                text = editText.getText().toString();
                delta = editText.getSelectionStart();
            }
            else
            {
                text = "";
                delta = 0;
            }
            if (string.equals("←"))
            {
                text = (delta - 1 > 0 ? text.substring(0, delta - 1) : "") + (text.length() > 0 ? text.substring(delta, text.length()) : "");
                if (delta > 0)
                    delta--;
            }
            else
            {
                text = (delta > 0 ? text.substring(0, delta) : "") + string + (text.length() - delta > 0 ? text.substring(delta, text.length()) : "");
                delta += string.length();
            }

        }
        editText.setText(text);
        editText.setSelection(delta);
        if (out.Update(text))
            story.add(text);
    }
}
