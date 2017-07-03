package com.example.android.lesmillsquizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
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
        CheckBox q3a = (CheckBox) findViewById(R.id.aAnswerQ3checkBox);
        CheckBox q3b = (CheckBox) findViewById(R.id.bAnswerQ3checkBox);
        CheckBox q3c = (CheckBox) findViewById(R.id.cAnswerQ3checkBox);
        CheckBox q3d = (CheckBox) findViewById(R.id.dAnswerQ3checkBox);
        CheckBox[] question3List = {q3a, q3b, q3c, q3d};
        RadioGroup q4 = (RadioGroup) findViewById(R.id.question4Group);
        RadioGroup q5 = (RadioGroup) findViewById(R.id.question5Group);
        RadioGroup q6 = (RadioGroup) findViewById(R.id.question6Group);
        RadioGroup q7 = (RadioGroup) findViewById(R.id.question7Group);
        RadioGroup q8 = (RadioGroup) findViewById(R.id.question8Group);
        RadioGroup q9 = (RadioGroup) findViewById(R.id.question9Group);
        RadioGroup[] radioGroupList = {q1, q2, q4, q5, q6, q7, q8, q9};
        EditText question10EditText = (EditText) findViewById(R.id.question10EditText);
        String question10Answer = question10EditText.getText().toString();

        if (userName.isEmpty()) {
            displayToast(getString(R.string.missingName));
        } else if (missedAnswerNumber(radioGroupList, question3List, question10Answer) != 0) {
            displayToast(String.format(getString(R.string.missedAnswer), userName, missedAnswerNumber(radioGroupList, question3List, question10Answer)));
        } else {
            totalAnswersCorrect = finalCorrectAnswers(answersCorrectRadioGroup(radioGroupList), answerCorrectCheckBox(question3List), answerCorrectEditText(question10Answer));

            switch (totalAnswersCorrect) {
                case 0:
                    displayToast((String.format(getString(R.string.correctAnswers0), userName, totalAnswersCorrect)));
                    break;

                case 1:
                    displayToast((String.format(getString(R.string.correctAnswers1), userName, totalAnswersCorrect)));
                    break;

                case 2:
                    displayToast((String.format(getString(R.string.correctAnswers2), userName, totalAnswersCorrect)));
                    break;

                case 3:
                    displayToast((String.format(getString(R.string.correctAnswers3), userName, totalAnswersCorrect)));
                    break;

                case 4:
                    displayToast((String.format(getString(R.string.correctAnswers4), userName, totalAnswersCorrect)));
                    break;

                case 5:
                    displayToast((String.format(getString(R.string.correctAnswers5), userName, totalAnswersCorrect)));
                    break;

                case 6:
                    displayToast((String.format(getString(R.string.correctAnswers6), userName, totalAnswersCorrect)));
                    break;

                case 7:
                    displayToast((String.format(getString(R.string.correctAnswers7), userName, totalAnswersCorrect)));
                    break;

                case 8:
                    displayToast((String.format(getString(R.string.correctAnswers8), userName, totalAnswersCorrect)));
                    break;

                case 9:
                    displayToast((String.format(getString(R.string.correctAnswers9), userName, totalAnswersCorrect)));
                    break;

                case 10:
                    displayToast((String.format(getString(R.string.correctAnswers10), userName, totalAnswersCorrect)));
                    break;
            }
        }
    }

    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private boolean answerSelected(RadioGroup radioGroup) {
        int id = radioGroup.getCheckedRadioButtonId();
        if (id != -1) {
            return true;
        }
        return false;
    }

    private int missedAnswerNumber(RadioGroup[] radioGroup, CheckBox[] checkBoxes, String text) {
        int n = 0;
        boolean x = false;
        for (int i = 0; i < radioGroup.length; i++) {
            if (!answerSelected(radioGroup[i]) && x == false) {
                x = true;
                if (i < 2) {
                    n = i + 1;
                } else {
                    n = i + 2;
                }
            }
        }
        int count = 0;
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                count++;
            }
        }
        if (count == 0) {
            n = 3;
        }

        if (text.isEmpty()) {
            n = 10;
        }
        return n;
    }

    private boolean isAnswerCorrectRadioGroup(RadioGroup radioGroup) {
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

    private int answersCorrectRadioGroup(RadioGroup[] radioGroups) {
        int n = 0;
        for (RadioGroup radioGroup : radioGroups) {
            if (isAnswerCorrectRadioGroup(radioGroup))
                n++;
        }
        return n;
    }

    private int answerCorrectCheckBox(CheckBox[] checkBoxes) {
        int i = 0;
        int n = 0;
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                i += Integer.parseInt(checkBox.getTag().toString());
            }
        }
        if (i == 2) {
            n = 1;
        }
        return n;
    }

    private int answerCorrectEditText(String text) {
        int n = 0;
        text = text.trim();
        if (text.equalsIgnoreCase(getString(R.string.correctAnswerQ10))) {
            n = 1;
        }
        return n;
    }

    private int finalCorrectAnswers(int a, int b, int c) {
        int n = 0;
        n = a + b + c;
        return n;
    }
}