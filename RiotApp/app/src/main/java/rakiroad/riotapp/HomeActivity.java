package rakiroad.riotapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.view.View;

public class HomeActivity extends AppCompatActivity {
    String selected, spinner_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Spinner spinner_region = (Spinner) findViewById(R.id.selectRegion);
        ArrayAdapter<String> adapter_region = new ArrayAdapter<String>(HomeActivity.this, android.R.layout.simple_expandable_list_item_1
            , getResources().getStringArray(R.array.Regions));
        adapter_region.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_region.setAdapter(adapter_region);

        ButtonDefinition();
    }

    public void ButtonDefinition() {
        findViewById(R.id.btnSearch).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            switch (view.getId()){
                case R.id.btnSearch:
                    Intent intent = new Intent(HomeActivity.this, MMR_ResultsActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };




}
