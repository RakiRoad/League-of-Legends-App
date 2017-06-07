package rakiroad.riotapp;

/**
 * Created by Rocky on 5/31/2017.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MMR_ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_page);
        Toast.makeText(this, "Why do they fear me?", Toast.LENGTH_LONG).show();
    }
}
