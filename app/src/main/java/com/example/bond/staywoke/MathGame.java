package com.example.bond.staywoke;


import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MathGame extends AppCompatActivity {
    RadioButton a,b,c,d,selected;
    public int choice,selectedId ,resourceId;
    RadioGroup radioGroup;
    int tries;
    Question[] questions;
    ImageView toBeAsked;
    Question chosen;
    Button submit;
    String answer;
    Question asked;
    int askId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);questions = new Question[10];
        questions[0] = new Question("one","9",new String[]{"3","6","10","9"},"Solve");
        questions[1] = new Question("two","2,3",new String[]{"5,6","2,4","2,3","1,3"},"Solve for x");
        questions[2] = new Question("three","-2",new String[]{"-3","-4","-1","-2"},"Solve for x");
        questions[3] = new Question("four","21",new String[]{"22","21","19","20"},"Solve");
        questions[4] = new Question("five","18",new String[]{"17","18","19","15"},"Solve");
        questions[5] = new Question("six","-6,-3",new String[]{"-3,-4","4,3","-6,-3","1,2"},"Solve for x");
        questions[6] = new Question("seven","-2",new String[]{"-3","-2","2","3"},"Solve for x");
        questions[7] = new Question("eight","12",new String[]{"12","11","14","13"},"Solve");
        questions[8] = new Question("nine","7",new String[]{"6","8","9","7"},"Solve");
        questions[9] = new Question("nine","41",new String[]{"43","41","51","54"},"Solve");
        toBeAsked = (ImageView) findViewById(R.id.toBeAsked);
        a = (RadioButton) findViewById(R.id.a);
        b = (RadioButton) findViewById(R.id.b);
        c = (RadioButton) findViewById(R.id.c);
        d = (RadioButton) findViewById(R.id.d);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        submit = (Button) findViewById(R.id.submit);
        tries=2;
        asked = new Question("","",new String[]{""},"");
        setup(); askId=0;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedId = radioGroup.getCheckedRadioButtonId();
                selected = (RadioButton) findViewById(selectedId);
                if (selected == null)
                    Toast.makeText(MathGame.this, "Please answer the question", Toast.LENGTH_SHORT).show();
                else {
                    if (tries ==0) {
                        askId = resourceId;
                        alert(false);
                        setup();
                    }
                    if(selected.getText().equals(answer)){
                        alert(true);
                        // Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                        tries = 2;
                    }

                    else {

                        tries = tries-1;
                        alert(false);
                        // Toast.makeText(MainActivity.this, "False " + (tries)+" try left" , Toast.LENGTH_SHORT).show();
                        if( tries ==0) {
                            //alert(false);
                            asked = chosen;
                            askId = resourceId;
                            tries = 2;
                            setup();
                            // selected.setChecked(false);

                        }

                    }
                }
            }
        });
    }
    private void setup(){
        choice = (int) (10* java.lang.Math.random());
        chosen = questions[choice];
        resourceId = this.getResources().getIdentifier(chosen.getImage(),"drawable", this.getPackageName());

        while (asked.getImage().equals(chosen.getImage())){
            //Toast.makeText(this, asked.getImage() + " " + chosen.getImage(), Toast.LENGTH_SHORT).show();
            choice = (int) (10* java.lang.Math.random());
            chosen = questions[choice];
            resourceId = this.getResources().getIdentifier(chosen.getImage(),"drawable", this.getPackageName());
        }

        toBeAsked.setImageResource(resourceId);
        a.setText(chosen.getOptions()[0]);
        b.setText(chosen.getOptions()[1]);
        c.setText(chosen.getOptions()[2]);
        d.setText(chosen.getOptions()[3]);
        this.answer = chosen.getAnswer();
    }
    private void alert(boolean result){
        String message;
        AlertDialog alertDialog = new AlertDialog.Builder(MathGame.this).create();
        alertDialog.setTitle("Result");
        if(tries==0) message = "Wrong Answer! Please try the next question.";
        else message = result? "Correct!": "Wrong Answer! You have "+ tries + " try left." ;
        alertDialog.setMessage(message);
        alertDialog.show();
    }


}
