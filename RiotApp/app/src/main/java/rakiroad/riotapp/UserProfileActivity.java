package rakiroad.riotapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

import rakiroad.riotapp.ThatTastedPurple.lulu.Region;
import rakiroad.riotapp.ThatTastedPurple.requests.SummonerRequest;
import rakiroad.riotapp.ThatTastedPurple.summoner.Summoner;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Summoner me = null;

        try {
            me = SummonerRequest.getSummoner(32641520, Region.NA);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("i love memes");
        }

        ImageView imgLeagueIcon = (ImageView)findViewById(R.id.imgLeagueIcon);
        TextView textViewSummoner = (TextView)findViewById(R.id.textViewSummoner);
        //loadImageFromURL("http://ddragon.leagueoflegends.com/cdn/7.10.1/img/profileicon/1631.png", imgLeagueIcon);
        new DownloadImageTask(imgLeagueIcon).execute("http://ddragon.leagueoflegends.com/cdn/7.14.1/img/profileicon/" + me.getProfileIconId() + ".png");     //this will be the user's current league icon when we import wrapper
        textViewSummoner.setText("Welcome " + me.getName() + "!");
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}


