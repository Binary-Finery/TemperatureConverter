package com.spencerstudios.temperatureconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextWatcher, View.OnFocusChangeListener {

    EditText editTextCelsius;
    EditText editTextFahrenheit;

    boolean celsiusHasFocus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextCelsius = findViewById(R.id.et_c);
        editTextFahrenheit = findViewById(R.id.et_f);

        editTextCelsius.addTextChangedListener(this);
        editTextFahrenheit.addTextChangedListener(this);

        editTextCelsius.setOnFocusChangeListener(this);
        editTextFahrenheit.setOnFocusChangeListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (celsiusHasFocus) {
            String string = editTextCelsius.getText().toString();
            if (!string.isEmpty())
                editTextFahrenheit.setText(String.format(Locale.getDefault(), "%.2f", (Double.valueOf(string) * 1.8) + 32.0));
            else editTextFahrenheit.setText("");
        } else {
            String string = editTextFahrenheit.getText().toString();
            if (!string.isEmpty())
                editTextCelsius.setText(String.format(Locale.getDefault(), "%.2f", (Double.valueOf(string) - 32.0) / 1.8));
            else editTextCelsius.setText("");

        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (editTextCelsius.hasFocus()) {
            celsiusHasFocus = true;
            editTextCelsius.addTextChangedListener(MainActivity.this);
            editTextFahrenheit.removeTextChangedListener(MainActivity.this);
        }

        if (editTextFahrenheit.hasFocus()) {
            celsiusHasFocus = false;
            editTextFahrenheit.addTextChangedListener(MainActivity.this);
            editTextCelsius.removeTextChangedListener(MainActivity.this);
        }
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
