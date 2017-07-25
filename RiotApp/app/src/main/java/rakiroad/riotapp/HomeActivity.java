package rakiroad.riotapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class HomeActivity extends AppCompatActivity {
    String selected, spinner_item;
    Spinner spinner_region;
    ArrayAdapter<String> adapter_region;
    String regionSelect = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //setting up the spinner region menu
        spinner_region = (Spinner) findViewById(R.id.selectRegion);
        adapter_region = new ArrayAdapter<String>(HomeActivity.this, android.R.layout.simple_expandable_list_item_1
                , getResources().getStringArray(R.array.Regions));
        adapter_region.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_region.setAdapter(adapter_region);

        spinner_region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                regionSelect = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Calls the button function
        ButtonDefinition();
    }

    //Listens for button press
    public void ButtonDefinition() {
        findViewById(R.id.btnSearch).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            switch (view.getId()){
                case R.id.btnSearch:
                    Intent intent = new Intent(HomeActivity.this, MMR_ResultsActivity.class);

                    //saves the summoner text input into String variable
                    String summonerName2 = ((EditText) findViewById(R.id.summonerName)).getText().toString();
                    Log.e("test", summonerName2);


                    //passes the summoner Name variable to MMR_Results activity
                    intent.putExtra ( "summoner", summonerName2);
                    intent.putExtra( "Region", regionSelect);
                    startActivity(intent);
                    break;
            }
        }
    };

}
