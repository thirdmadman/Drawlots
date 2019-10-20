package com.thirdmadman.drawlots.drawlots;

import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainScreeen extends AppCompatActivity {
Button startGameButton;
Button showResultButton;
MultiAutoCompleteTextView variantsText;
TextView resultText;
TextView resultTextTitle;
TextView addTitleText;
ConstraintLayout inputFrameLayout;
ConstraintLayout showFrameLayout;
ImageView addTitleImg, stopGame;
EditText editTextTitle;
boolean titleState = false, inGame = false;
int numberOfClikcs = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screeen);
        variantsText = findViewById(R.id.variantsText);
        startGameButton = findViewById(R.id.startGameButton);
        resultText = findViewById(R.id.resultText);
        inputFrameLayout = findViewById(R.id.inputFrameLayout);
        showFrameLayout = findViewById(R.id.showFrameLayout);
        showResultButton = findViewById(R.id.showResultButton);
        addTitleImg = findViewById(R.id.addTitleImg);
        editTextTitle = findViewById(R.id.editTextTitle);
        addTitleText = findViewById(R.id.addTitleText);
        resultTextTitle = findViewById(R.id.resultTextTitle);
        stopGame = findViewById(R.id.stopGame);
        addTitleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleState == false) {
                    titleState = true;
                    addTitleImg.setImageResource(android.R.drawable.ic_delete);
                    editTextTitle.setVisibility(View.VISIBLE);
                    addTitleText.setText(R.string.removeTitle);
                } else {
                    titleState = false;
                    addTitleImg.setImageResource(android.R.drawable.ic_menu_add);
                    editTextTitle.setVisibility(View.INVISIBLE);
                    addTitleText.setText(R.string.addTitleText);
                }
            }
        });
        showResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (variantsText.getText().toString().length() > 1) {
                    numberOfClikcs++;
                    if (numberOfClikcs > 2) {
                        stopGame.setVisibility(View.VISIBLE);
                    }
                    final Random random = new Random();
                    String variants = variantsText.getText().toString();
                    int linesNumber = variants.split("\n").length;
                    String[] variantsArray = variants.split("\n");
                    int randNum = random.nextInt(linesNumber);
                    String g = variantsArray[randNum];
                    resultText.setText(g.toUpperCase());
                    String out ="";
                    for (int i = 0; i< linesNumber;i++){
                        if (i!=randNum) {
                            out += variantsArray[i] + "\n";
                        }
                    }
                    variantsText.setText(out);
                }
                else {
                    resultText.setText("");
                    inputFrameLayout.setVisibility(View.VISIBLE);
                    showFrameLayout.setVisibility(View.INVISIBLE);
                    stopGame.setVisibility(View.INVISIBLE);
                }
            }
        });
        stopGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inGame==true) {
                    inGame=false;
                    resultText.setText("");
                    inputFrameLayout.setVisibility(View.VISIBLE);
                    showFrameLayout.setVisibility(View.INVISIBLE);
                    stopGame.setVisibility(View.INVISIBLE);
                }
            }
        });
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (variantsText.getText().toString().length() > 2) {
                    inputFrameLayout.setVisibility(View.INVISIBLE);
                    showFrameLayout.setVisibility(View.VISIBLE);
                    inGame = true;
                    numberOfClikcs= 0;
                    if (titleState == true){
                        if (editTextTitle.getText()!= null){
                            if (editTextTitle.getText().toString().length() > 1){
                                resultTextTitle.setText(editTextTitle.getText().toString());
                            }
                        }
                    }
                }
            }
        });
    }
}
