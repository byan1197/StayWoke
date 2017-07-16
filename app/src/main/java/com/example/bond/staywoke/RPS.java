package com.example.bond.staywoke;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class RPS extends AppCompatActivity {
    Button b_scissors, b_rock, b_paper;
    ImageView iv_ComputerChoice, iv_HumanChoice;
    //TextView tv_score;
    TextView human_score;
    TextView cpu_score;
    int humanScore = 0, computerScore = 0;
    Intent returnIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rps);
        returnIntent = new Intent();
        b_paper = (Button) findViewById(R.id.b_paper);
        b_rock = (Button) findViewById(R.id.b_rock);
        b_scissors = (Button) findViewById(R.id.b_scissors);
        human_score = (TextView) findViewById(R.id.humanPoints);
        cpu_score = (TextView) findViewById(R.id.cpuPoints);

        iv_ComputerChoice = (ImageView) findViewById(R.id.iv_ComputerChoice);
        iv_HumanChoice = (ImageView) findViewById(R.id.iv_HumanChoice);
        //tv_score = (TextView) findViewById(R.id.tv_score);
        b_paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_HumanChoice.setImageResource(R.drawable.paper);
                String message = playTurn("paper");
                //Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
                //tv_score.setText("Score: Human " + Integer.toString(humanScore) + " Computer: " + Integer.toString(computerScore));
                human_score.setText(Integer.toString(humanScore));
                cpu_score.setText(Integer.toString(computerScore));
            }
        });
        b_rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_HumanChoice.setImageResource(R.drawable.rock);
                String message = playTurn("rock");
                //Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
                //tv_score.setText("Score: Human " + Integer.toString(humanScore) + " Computer: " + Integer.toString(computerScore));
                human_score.setText(Integer.toString(humanScore));
                cpu_score.setText(Integer.toString(computerScore));
            }

        });
        b_scissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_HumanChoice.setImageResource(R.drawable.scissors);
                String message = playTurn("scissors");
               // Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
                //tv_score.setText("Score: Human " + Integer.toString(humanScore) + " Computer: " + Integer.toString(computerScore));
                human_score.setText(Integer.toString(humanScore));
                cpu_score.setText(Integer.toString(computerScore));
            }
        });
    }
    private String playTurn(String playerChoice) {
        String computerChoice = "";
        Random r = new Random();
        int computerChoiceNumber = r.nextInt(3)+1;


        if (computerChoiceNumber ==1) computerChoice="rock";
        else if (computerChoiceNumber ==2) computerChoice = "paper";
        else computerChoice = "scissors";

        if (computerChoice.equals("rock")) iv_ComputerChoice.setImageResource(R.drawable.rock);
        else if (computerChoice.equals("paper")) iv_ComputerChoice.setImageResource(R.drawable.paper);
        else if (computerChoice.equals("scissors")) iv_ComputerChoice.setImageResource(R.drawable.scissors);

        if(computerChoice.equals(playerChoice)) {
            draw();
            return ("Draw");
        }
        else if(computerChoice.equals("rock") && playerChoice.equals("scissors")) {
            computerScore++;
            alert(false);
            return ("You Lose");
        }
        else if(computerChoice.equals("paper") && playerChoice.equals("rock")) {
            computerScore++;
            alert(false);
            return ("You Lose");
        }
        else if(computerChoice.equals("scissors") && playerChoice.equals("paper")){
            computerScore++;
            alert(false);
            return ("You Lose");
        }

        else {
            humanScore++;
            if (humanScore >= 3){
                setResult(Activity.RESULT_OK, returnIntent);
                RPS.this.finish();
            }
            alert(true);
            return "You win";
        }

    }
    private void alert(boolean result){
        AlertDialog alertDialog = new AlertDialog.Builder(RPS.this).create();
        alertDialog.setTitle("Result");
        String message = result? "You win this round!": "You lose this round!";
        alertDialog.setMessage(message);
        alertDialog.show();
    }
    private void draw(){
        AlertDialog alertDialog = new AlertDialog.Builder(RPS.this).create();
        alertDialog.setTitle("Result");

        alertDialog.setMessage("Its a draw!");
        alertDialog.show();
    }

}
