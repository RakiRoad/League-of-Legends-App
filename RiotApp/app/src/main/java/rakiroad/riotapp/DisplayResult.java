package rakiroad.riotapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by Rocky on 7/19/2017.
 */

public class DisplayResult  extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_page);
        ImageView rankView;
        rankView = (ImageView) findViewById(R.id.imageView);

        //Retrieves the Division Value
        Intent i = getIntent();
        int Division = i.getIntExtra("Division", 0);

        //switch case that decides which image gets displayed based on division
        switch (Division) {
            case 0:
                rankView.setImageResource(R.drawable._blank2);
                break;
            case 1:
                rankView.setImageResource(R.drawable._bronze);
                break;
            case 2:
                rankView.setImageResource(R.drawable._silver);
                break;
            case 3:
                rankView.setImageResource(R.drawable._gold);
                break;
            case 4:
                rankView.setImageResource(R.drawable._platinum);
                break;
            case 5:
                rankView.setImageResource(R.drawable._diamond);
                break;
            case 6:
                rankView.setImageResource(R.drawable._master);
                break;
            case 7:
                rankView.setImageResource(R.drawable._challenger);
                break;
        }


    }
}
