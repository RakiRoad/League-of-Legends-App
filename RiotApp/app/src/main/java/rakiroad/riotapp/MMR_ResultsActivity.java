package rakiroad.riotapp;

/**
 * Created by Rocky on 5/31/2017.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MMR_ResultsActivity extends AppCompatActivity {
    /*
    Sets the divisionID of the user to get passed to DisplayRankActiviity
    set divionID for the following ranks
    unrank = 0
    bronze = 1
    silver = 2
    gold = 3
    platinum = 4
    diamond = 5
    master = 6
    challenger = 7
     */

    //default value is unrank
    int divisionID =  0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_page);

        //retrieves the summoner Name typed by the user
        Intent i = getIntent();
        String summonerName = i.getStringExtra("summoner");
        Log.e("test 2", summonerName);

        mmrCalculate();
    }

    public void mmrCalculate(){
        /*
            This is MMR values will be calculated
        */

        //testing Display Result
        Intent DisplayResult = new Intent(MMR_ResultsActivity.this, DisplayResult.class);
        DisplayResult.putExtra("Division", divisionID);
        startActivity(DisplayResult);

    }
}
