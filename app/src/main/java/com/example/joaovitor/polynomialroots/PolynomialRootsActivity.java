package com.example.joaovitor.polynomialroots;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by joaovitor on 01/09/17.
 */

public class PolynomialRootsActivity extends AppCompatActivity {

    private EditText firstCoeficient;
    private EditText secondCoeficient;
    private EditText thirdCoeficient;
    private Button calculateButton;
    private TextView resultText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roots_polynomial);
        firstCoeficient = (EditText) findViewById(R.id.first_coeficient);
        secondCoeficient = (EditText) findViewById(R.id.second_coeficient);
        thirdCoeficient = (EditText) findViewById(R.id.third_coeficient);
        calculateButton = (Button) findViewById(R.id.result_button);
        resultText = (TextView) findViewById(R.id.result_text);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resolveRoots();
            }
        });

        thirdCoeficient.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    resolveRoots();
                    return true;
                }
                return false;
            }
        });
    }

    private void resolveRoots() {
        try {
            Double a = Double.valueOf(firstCoeficient.getText().toString());
            Double b = Double.valueOf(secondCoeficient.getText().toString());
            Double c = Double.valueOf(thirdCoeficient.getText().toString());
            if (a != 0) {
                Double delta = delta(a, b, c);
                Double x1 = (-1 * b - Math.sqrt(delta)) / (2 * a);
                Double x2 = (-1 * b + Math.sqrt(delta)) / (2 * a);
                resultText.setTextColor(ContextCompat.getColor(PolynomialRootsActivity.this, R.color.colorValid));
                resultText.setText(String.format(Locale.getDefault(), getString(R.string.result_roots), x1, x2));
            } else {
                resultText.setTextColor(ContextCompat.getColor(PolynomialRootsActivity.this, R.color.colorInvalid));
                resultText.setText((R.string.invalid_coeficient));
            }
        } catch (Exception e) {
            resultText.setTextColor(ContextCompat.getColor(PolynomialRootsActivity.this, R.color.colorInvalid));
            resultText.setText((R.string.invalid_result));
        }
    }

    private Double delta(Double a, Double b, Double c) {
        Double result = b * b - 4 * a * c;
        if (result < 0) throw new NumberFormatException();
        return result;
    }
}
