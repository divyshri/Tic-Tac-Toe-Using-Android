package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Yellow = 0 and Red = 1
    boolean isActive = true;
    int active_player = 0;
    int[] game_state = {2,2,2,2,2,2,2,2,2};
    int [][] winning_position = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view){

        ImageView counter = (ImageView) view;
        int tapped_counter = (Integer.parseInt(counter.getTag().toString()));
        if (game_state[tapped_counter] == 2 && isActive) {
            counter.setTranslationY(-1000f);
            if (active_player == 0) {
                counter.setImageResource(R.drawable.yellow);
                active_player = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                active_player = 0;
            }

            counter.animate().translationYBy(1000f).setDuration(500);
            game_state[tapped_counter] = active_player;
            for (int[] wp : winning_position) {

                if ((game_state[wp[0]] == game_state[wp[1]]) &&
                        (game_state[wp[1]] == game_state[wp[2]]) &&
                        (game_state[wp[0]] != 2)) {
                    isActive = false;
                    String str = "Red";
                    if (game_state[wp[0]] == 1) {
                        str = "Yellow";
                    }
                    TextView msg = (TextView) findViewById(R.id.winner_mesage);
                    msg.setText(str + " has Won!");
                    msg.setAlpha(1f);
                    Button btn = (Button) findViewById(R.id.play_again_button);
                    btn.setAlpha(1f);
                }
                else{
                    boolean isDraw = true;
                    for(int state : game_state){
                        if(state == 2) isDraw = false;
                    }
                    if(isDraw){
                        TextView msg = (TextView) findViewById(R.id.winner_mesage);
                        msg.setText("It's A Draw");
                        msg.setAlpha(1f);
                        Button btn = (Button) findViewById(R.id.play_again_button);
                        btn.setAlpha(1f);
                    }
                }

            }
        }
    }

    public void playAgain(View view){
        isActive = true;
        TextView msg = (TextView) findViewById(R.id.winner_mesage);
        msg.setAlpha(0f);
        Button btn = (Button)findViewById(R.id.play_again_button);
        btn.setAlpha(0f);
        active_player = 0;
        //game_state = {2,2,2,2,2,2,2,2,2};
        for (int i = 0;i<game_state.length;i++){
            game_state[i] = 2;
        }
        GridLayout grid = (GridLayout) findViewById(R.id.grid_layout_1);
        for(int i=0;i<grid.getChildCount();i++){

            ((ImageView)grid.getChildAt(i)).setImageResource(0);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
