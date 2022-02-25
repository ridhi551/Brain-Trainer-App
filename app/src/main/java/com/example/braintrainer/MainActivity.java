package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
// import android.widget.GridLayout;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView countDown, scoreUpdate, showExpression, express;
    GridLayout answerOptions;
    Button go, b1, b2, b3, b4 , playAgainButton;
    Random random = new Random();
    ArrayList<Integer> answer = new ArrayList<>();

    int locationOfCorrectAnswer;
    int score;
    int noOfQuestions;
    boolean activeGame = true;

    public void playAgain(View v){


        playAgainButton.setVisibility(View.INVISIBLE);
        express.setText("");
        activeGame=true;
        score =0;
        noOfQuestions=0;
        scoreUpdate.setText(Integer.toString(score) + "/" + Integer.toString(noOfQuestions));

        newQuestion();

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                Log.i("seconds Left::", String.valueOf(l));
                countDown.setText(l / 1000 + "s");

            }

            @Override
            public void onFinish() {

                express.setText("WE ARE DONE");
                activeGame = false ;
                // scoreUpdate.setText("0/0");
                countDown.setText("30s");
                playAgainButton.setVisibility(View.VISIBLE);

            }
        }.start();
    }

    public void newQuestion() {


            int a, b;
            a = random.nextInt(21);
            b = random.nextInt(21);

            showExpression.setText(Integer.toString(a) + "+" + Integer.toString(b));
            locationOfCorrectAnswer = random.nextInt(4);

            answer.clear();

            for (int i = 0; i < 4; i++) {
                if (i == locationOfCorrectAnswer) {
                    answer.add(a + b);

                } else {

                    int wrongAnswer = random.nextInt(41);

                    while (wrongAnswer == (a + b)) {
                        wrongAnswer = random.nextInt(41);
                    }
                    answer.add(wrongAnswer);
                }
            }

            b1.setText(Integer.toString(answer.get(0)));
            b2.setText(Integer.toString(answer.get(1)));
            b3.setText(Integer.toString(answer.get(2)));
            b4.setText(Integer.toString(answer.get(3)));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         b1 = findViewById(R.id.button1);
         b2 = findViewById(R.id.button2);
         b3 = findViewById(R.id.button3);
         b4 = findViewById(R.id.button4);

        countDown = findViewById(R.id.textView);
        scoreUpdate = findViewById(R.id.textView2);
        express = findViewById(R.id.textView3);
        showExpression = findViewById(R.id.textView4);
        answerOptions = findViewById(R.id.gridLayout);
        go = findViewById(R.id.button7);
        playAgainButton = findViewById(R.id.button);

        playAgain(showExpression);
    }


        public void mainClick (View v){

            go.setVisibility(View.INVISIBLE);
            answerOptions.setVisibility(View.VISIBLE);
            scoreUpdate.setVisibility(View.VISIBLE);
            countDown.setVisibility(View.VISIBLE);
            showExpression.setVisibility(View.VISIBLE);


        }

        public void chooseAnswer (View v) {
            Button buttonChosen = (Button) v;


            if (activeGame) {
                if (Integer.toString(locationOfCorrectAnswer).equals(buttonChosen.getTag().toString())) {

                    Log.i("CHECK::", "YOU GOT RIGHT");
                    express.setVisibility(View.VISIBLE);
                    express.setText("CORRECT !");
                    score++;

                } else {
                    Log.i("CHECK::", "YOU GOT WRONG");
                    express.setVisibility(View.VISIBLE);

                    express.setText("WRONG :(");

                }
                noOfQuestions++;
                scoreUpdate.setText(Integer.toString(score) + "/" + Integer.toString(noOfQuestions));
                newQuestion();

            }
        }

    }
