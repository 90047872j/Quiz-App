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
        int missedAnswerNumber = getMissedAnswerNumber(radioGroupList, question3List, question10Answer);
        if (userName.isEmpty()) {
            displayToast(getString(R.string.missingName));
        } else if (missedAnswerNumber != 0) {
            displayToast(String.format(getString(R.string.missedAnswer), userName, missedAnswerNumber));
        } else {
            int totalCorrectAnwers = getCorrectAnswersSum(getCorrectAnswersRadioGroupNumber(radioGroupList), getCorrectAnswerCheckBox(question3List), getCorrectAnswerEditText(question10Answer));
            switch (totalCorrectAnwers) {
                case 0:
                    displayToast((String.format(getString(R.string.correctAnswers0), userName, totalCorrectAnwers)));
                    break;
                case 1:
                    displayToast((String.format(getString(R.string.correctAnswers1), userName, totalCorrectAnwers)));
                    break;
                case 2:
                    displayToast((String.format(getString(R.string.correctAnswers2), userName, totalCorrectAnwers)));
                    break;
                case 3:
                    displayToast((String.format(getString(R.string.correctAnswers3), userName, totalCorrectAnwers)));
                    break;
                case 4:
                    displayToast((String.format(getString(R.string.correctAnswers4), userName, totalCorrectAnwers)));
                    break;
                case 5:
                    displayToast((String.format(getString(R.string.correctAnswers5), userName, totalCorrectAnwers)));
                    break;
                case 6:
                    displayToast((String.format(getString(R.string.correctAnswers6), userName, totalCorrectAnwers)));
                    break;
                case 7:
                    displayToast((String.format(getString(R.string.correctAnswers7), userName, totalCorrectAnwers)));
                    break;
                case 8:
                    displayToast((String.format(getString(R.string.correctAnswers8), userName, totalCorrectAnwers)));
                    break;
                case 9:
                    displayToast((String.format(getString(R.string.correctAnswers9), userName, totalCorrectAnwers)));
                    break;
                case 10:
                    displayToast((String.format(getString(R.string.correctAnswers10), userName, totalCorrectAnwers)));
                    break;
            }
        }
    }

    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private boolean isAnswerSelectedRadioGroup(RadioGroup radioGroup) {
        return radioGroup.getCheckedRadioButtonId() != -1;
    }

    private int getMissedAnswerNumber(RadioGroup[] radioGroup, CheckBox[] checkBoxes, String text) {
        int missedAnswerNumber = 0;
        boolean x = false;
        for (int i = 0; i < radioGroup.length; i++) {
            if (!isAnswerSelectedRadioGroup(radioGroup[i]) && x == false) {
                if (i < 2) {
                    missedAnswerNumber = i + 1;
                } else {
                    missedAnswerNumber = i + 2;
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
            missedAnswerNumber = 3;
        }

        if (text.isEmpty()) {
            missedAnswerNumber = 10;
        }
        return missedAnswerNumber;
    }

    private boolean isAnswerCorrectRadioGroup(RadioGroup radioGroup) {
        int correctAnswerPosition = Integer.parseInt(radioGroup.getTag().toString());
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
            if (i == correctAnswerPosition && radioButton.isChecked()) {
                return true;
            }
        }
        return false;
    }

    private int getCorrectAnswersRadioGroupNumber(RadioGroup[] radioGroups) {
        int correctAnswers = 0;
        for (RadioGroup radioGroup : radioGroups) {
            if (isAnswerCorrectRadioGroup(radioGroup))
                correctAnswers++;
        }
        return correctAnswers;
    }

    private int getCorrectAnswerCheckBox(CheckBox[] checkBoxes) {
        int i = 0;
        int correctAnswer = 0;
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                i += Integer.parseInt(checkBox.getTag().toString());
            }
        }
        if (i == 2) {
            correctAnswer = 1;
        }
        return correctAnswer;
    }

    private int getCorrectAnswerEditText(String text) {
        int correctAnswer = 0;
        text = text.trim();
        if (text.equals(getString(R.string.correctAnswerQ10))) {
            correctAnswer = 1;
        }
        return correctAnswer;
    }

    private int getCorrectAnswersSum(int a, int b, int c) {
        int totalCorrectAnswers = a + b + c;
        return totalCorrectAnswers;
    }
}