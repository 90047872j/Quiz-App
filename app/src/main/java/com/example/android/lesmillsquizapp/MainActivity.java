package com.example.android.lesmillsquizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkResults(View view) {

        int totalAnswersCorrect = 0;

        EditText userNameEditText = (EditText) findViewById(R.id.userNameEditText);
        String userName = userNameEditText.getText().toString();

        RadioGroup q1 = (RadioGroup) findViewById(R.id.question1Group);
        RadioGroup q2 = (RadioGroup) findViewById(R.id.question2Group);
        RadioGroup q3 = (RadioGroup) findViewById(R.id.question3Group);
        RadioGroup q4 = (RadioGroup) findViewById(R.id.question4Group);
        RadioGroup q5 = (RadioGroup) findViewById(R.id.question5Group);
        RadioGroup q6 = (RadioGroup) findViewById(R.id.question6Group);
        RadioGroup q7 = (RadioGroup) findViewById(R.id.question7Group);
        RadioGroup q8 = (RadioGroup) findViewById(R.id.question8Group);
        RadioGroup q9 = (RadioGroup) findViewById(R.id.question9Group);
        RadioGroup q10 = (RadioGroup) findViewById(R.id.question10Group);

        RadioGroup[] radioGroupArray = {q1, q2, q3, q4, q5, q6, q7, q8, q9, q10};


        if (userName.isEmpty()) {
            Toast.makeText(this, R.string.missingName, Toast.LENGTH_LONG).show();
        } else if (missedAnswerNumber(radioGroupArray) != 0) {
            Toast.makeText(this, String.format(getString(R.string.missedAnswer), userName, missedAnswerNumber(radioGroupArray)), Toast.LENGTH_LONG).show();
        } else {
            for (RadioGroup radioGroup : radioGroupArray) {
                if (correctAnswer(radioGroup))
                    totalAnswersCorrect++;
            }
            switch (totalAnswersCorrect) {
                case 0:
                    Toast.makeText(this, String.format(getString(R.string.correctAnswers0), userName, totalAnswersCorrect), Toast.LENGTH_LONG).show();
                    break;

                case 1:
                    Toast.makeText(this, String.format(getString(R.string.correctAnswers1), userName, totalAnswersCorrect), Toast.LENGTH_LONG).show();
                    break;

                case 2:
                    Toast.makeText(this, String.format(getString(R.string.correctAnswers2), userName, totalAnswersCorrect), Toast.LENGTH_LONG).show();
                    break;

                case 3:
                    Toast.makeText(this, String.format(getString(R.string.correctAnswers3), userName, totalAnswersCorrect), Toast.LENGTH_LONG).show();
                    break;

                case 4:
                    Toast.makeText(this, String.format(getString(R.string.correctAnswers4), userName, totalAnswersCorrect), Toast.LENGTH_LONG).show();
                    break;

                case 5:
                    Toast.makeText(this, String.format(getString(R.string.correctAnswers5), userName, totalAnswersCorrect), Toast.LENGTH_LONG).show();
                    break;

                case 6:
                    Toast.makeText(this, String.format(getString(R.string.correctAnswers6), userName, totalAnswersCorrect), Toast.LENGTH_LONG).show();
                    break;

                case 7:
                    Toast.makeText(this, String.format(getString(R.string.correctAnswers7), userName, totalAnswersCorrect), Toast.LENGTH_LONG).show();
                    break;

                case 8:
                    Toast.makeText(this, String.format(getString(R.string.correctAnswers8), userName, totalAnswersCorrect), Toast.LENGTH_LONG).show();
                    break;

                case 9:
                    Toast.makeText(this, String.format(getString(R.string.correctAnswers9), userName, totalAnswersCorrect), Toast.LENGTH_LONG).show();
                    break;

                case 10:
                    Toast.makeText(this, String.format(getString(R.string.correctAnswers10), userName, totalAnswersCorrect), Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    private boolean answerSelected(RadioGroup radioGroup) {
        int id = radioGroup.getCheckedRadioButtonId();
        if (id != -1) {
            return true;
        }
        return false;
    }

    private int missedAnswerNumber(RadioGroup[] radioGroup) {
        int n = 0;
        boolean x = false;
        for (int i = 0; i < radioGroup.length; i++) {
            if (!answerSelected(radioGroup[i]) && x == false) {
                n = i + 1;
                x = true;
            }
        }
        return n;
    }

    private boolean correctAnswer(RadioGroup radioGroup) {
        int correctAnswerPosition = Integer.parseInt(radioGroup.getTag().toString());
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
            if (radioButton.isChecked()) {
                if (i == correctAnswerPosition)
                return true;
            }
        }
        return false;
    }
}