package com.example.gridgame;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    boolean xTurn = true;
    boolean gameWon = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button resetButton = findViewById(R.id.reset_button);
        Button[][] boardButtons;


        {
            boardButtons = new Button[][]{
                    {findViewById(R.id.button00), findViewById(R.id.button01), findViewById(R.id.button02)},
                    {findViewById(R.id.button10), findViewById(R.id.button11), findViewById(R.id.button12)},
                    {findViewById(R.id.button20), findViewById(R.id.button21), findViewById(R.id.button22)}
            };
        }

        //initialize board onclick listeners
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                int finalI = i;
                int finalJ = j;
                boardButtons[i][j].setOnClickListener(view -> boardButtonClick(boardButtons[finalI][finalJ], boardButtons));
            }
        }

        //Blank out board initially
    resetButtons(boardButtons);

        //initialize reset button on click listener
        resetButton.setOnClickListener(view -> resetButtons(boardButtons));
    }



    void resetButtons(Button[][] buttons){
        TextView topTextView = findViewById(R.id.topTextView);
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                Button button = buttons[i][j];
                button.setText("");
            }
        }
        topTextView.setText("TicTacToe X's Turn");
        gameWon = false;
        xTurn = true;
    }

    void boardButtonClick(Button buttonClicked, Button[][] boardButtons){
        TextView topTextView = findViewById(R.id.topTextView);
        if(gameWon){
            resetButtons(boardButtons);
            gameWon = false;
            return;}
        if(buttonClicked.getText() == "X" || buttonClicked.getText() == "O")
            return;
        int x = -1;
        int y = -1;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(boardButtons[i][j] == buttonClicked){
                    x = i;
                    y = j;
                    break;
                }
            }
            if(x > -1)
                break;
        }
        if(xTurn) {
            xTurn = false;
            topTextView.setText("O's Turn");
            buttonClicked.setText("X");
            if (winCheck(boardButtons, "X")) {
                Toast.makeText(this, "X Won!", Toast.LENGTH_LONG).show();
                topTextView.setText("X Won!");
                gameWon = true;
            }
        } else {
            xTurn = true;
            topTextView.setText("X's Turn");
            buttonClicked.setText("O");
            if (winCheck(boardButtons, "O")) {
                Toast.makeText(this, "O Won!", Toast.LENGTH_LONG).show();
                topTextView.setText("O Won!");
                gameWon = true;
            }
        }
    }

    boolean winCheck(Button[][] boardButtons, String player){
        if(boardButtons[0][0].getText() == player && boardButtons[0][1].getText() == player && boardButtons[0][2].getText() == player)
            return true;
        if(boardButtons[1][0].getText() == player && boardButtons[1][1].getText() == player && boardButtons[1][2].getText() == player)
            return true;
        if(boardButtons[2][0].getText() == player && boardButtons[2][1].getText() == player && boardButtons[2][2].getText() == player)
            return true;
        if(boardButtons[0][0].getText() == player && boardButtons[1][0].getText() == player && boardButtons[2][0].getText() == player)
            return true;
        if(boardButtons[0][1].getText() == player && boardButtons[1][1].getText() == player && boardButtons[2][1].getText() == player)
            return true;
        if(boardButtons[0][2].getText() == player && boardButtons[1][2].getText() == player && boardButtons[2][2].getText() == player)
            return true;
        if(boardButtons[0][0].getText() == player && boardButtons[1][1].getText() == player && boardButtons[2][2].getText() == player)
            return true;
        if(boardButtons[2][0].getText() == player && boardButtons[1][1].getText() == player && boardButtons[0][2].getText() == player)
            return true;

        return false;
    }
}