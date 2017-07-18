package com.example.bond.staywoke;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.HashMap;
public class Trivia extends AppCompatActivity {
    Riddle riddle;
    HashMap questions,answers,options;
    RadioGroup radioGroup;
    RadioButton a,b,c,d,selected;
    TextView toBeAsked, alarmNotes;
    String[] possbileOptions;
    Button submit;
    int choice,selectedId ;
    Intent returnIntent;
    int tries;
    String asked,q;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);toBeAsked = (TextView) findViewById(R.id.question);

        returnIntent = new Intent();
        bundle = getIntent().getExtras();

        a = (RadioButton) findViewById(R.id.a);
        b = (RadioButton) findViewById(R.id.b);
        c = (RadioButton) findViewById(R.id.c);
        d = (RadioButton) findViewById(R.id.d);
        tries = 2;
        selectedId=5;
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        submit = (Button) findViewById(R.id.submit);
        alarmNotes = (TextView) findViewById(R.id.mathAlarmNotes);
        alarmNotes.setText("ALARM NOTES: " + bundle.getString("note"));
        riddle = new Riddle();
        questions = riddle.questions;
        answers = riddle.answers;
        options = riddle.options;
        asked = "";
        setup();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedId = radioGroup.getCheckedRadioButtonId();
                selected = (RadioButton) findViewById(selectedId);
                if (selected == null)
                    Toast.makeText(Trivia.this, "Please answer the question", Toast.LENGTH_SHORT).show();
                else {
                    if (tries ==0) {
                        asked = q;
                        setup();
                    }
                    if(selected.getText().equals(answers.get(Integer.toString(choice)))){
                        RingtonePlayingService.mediaSong.stop();
                        setResult(Activity.RESULT_OK, returnIntent);
                        Trivia.this.finish();
                        tries = 2;
                    }

                    else {

                        tries = tries-1;
                        alert(false);
                        //Toast.makeText(MainActivity.this, "False " + (tries)+" try left" , Toast.LENGTH_SHORT).show();
                        if( tries ==0) {
                            asked = q;

                            setup();
                            // selected.setChecked(false);
                            tries = 2;
                        }

                    }
                }
            }
        });



    }
    private void setup(){
        choice = (int) (7*Math.random());

        possbileOptions = (String[]) options.get(Integer.toString(choice));

        q = (String) questions.get(Integer.toString(choice));
        while (asked.equals(q)){
            choice = (int) (7*Math.random());
            q = (String) questions.get(Integer.toString(choice));
        }
        toBeAsked.setText(q);
        a.setText(possbileOptions[0]);
        b.setText(possbileOptions[1]);
        c.setText(possbileOptions[2]);
        d.setText(possbileOptions[3]);
    }
    private void alert(boolean result){
        String message;
        AlertDialog alertDialog = new AlertDialog.Builder(Trivia.this).create();
        alertDialog.setTitle("Result");
        if(tries==0) message = "Wrong Answer! Please try the next question.";
        else message = result? "Correct!": "Wrong Answer! You have "+ tries + " try left." ;
        alertDialog.setMessage(message);
        alertDialog.show();
    }
}
