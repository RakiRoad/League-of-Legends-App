package rakiroad.riotapp;

/**
 * Created by Rocky on 5/31/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import rakiroad.riotapp.ThatTastedPurple.requests.GameRequest;
import rakiroad.riotapp.ThatTastedPurple.requests.LeagueRequest;
import rakiroad.riotapp.ThatTastedPurple.requests.MatchRequest;
import rakiroad.riotapp.ThatTastedPurple.requests.SummonerRequest;
import rakiroad.riotapp.ThatTastedPurple.summoner.Summoner;

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
        String regionSelect = i.getStringExtra("Region");
        Log.e("test 2", summonerName);
        Log.e("test 3", regionSelect);
        String Region = "";

        if (regionSelect.contains("North America")){
            Region = "na1";
        }
        else if (regionSelect.contains("Europe West")){
            Region = "euw1";
        }
        else if (regionSelect.contains("Europe East")){
            Region = "eun1";
        }
        else if (regionSelect.contains("Korea")){
            Region = "kr";
        }
        else if (regionSelect.contains("Japan")){
            Region = "jp1";
        }
        else if (regionSelect.contains("Latin South America")){
            Region = "la1";
        }

        try {
            mmrCalculate(summonerName, Region);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mmrCalculate(String Name, String region) throws Exception {
        /*
            This is MMR values will be calculated
        */
        ImageView rankView;
        rankView = (ImageView) findViewById(R.id.imageView);
        TextView message = (TextView)findViewById(R.id.textView);

        Summoner summonerUser = SummonerRequest.getSummoner(Name, region);

        //tests if able to pull summoner Id from user (works)
        long summonerId1 = summonerUser.getId();
        String test = Long.toString(summonerId1);
        Log.e("summsID: ", test);
        int summonerId = (int) summonerId1;

        long summonerACCID1 = summonerUser.getAccountId();
        int summonerACCID = (int) summonerACCID1;

        Log.e("test ", "-1");

        //tests if able to pull summoner rank from user (works)
        summonerUser.setLeague(LeagueRequest.getPosition(summonerId, region));
        String summonerRank = summonerUser.getLeague().toString();
        Log.e("Position", summonerRank);

        Log.e("test ", "0");

        double userWeight = 0;
        double userWeight2 = 0;

        if (summonerRank.contains("BRONZE")) {
            divisionID = 1;
            userWeight = 0;
        }
        else if (summonerRank.contains("SILVER")){
            divisionID = 2;
            userWeight = 1000;
        }
        else if (summonerRank.contains("GOLD")){
            divisionID = 3;
            userWeight = 2000;
        }
        else if (summonerRank.contains("PLATINUM")){
            divisionID = 4;
            userWeight = 3000;
        }
        else if (summonerRank.contains("DIAMOND")){
            divisionID = 5;
            userWeight = 4000;
        }
        else if (summonerRank.contains("MASTER")){
            divisionID = 6;
            userWeight = 5000;
        }
        else if (summonerRank.contains("CHALLENGER")){
            divisionID = 7;
            userWeight = 6000;
        }
        else{
            divisionID = 0;
        }

        if (summonerRank.contains("V")) {
            userWeight2 = 0;
        }
        else if (summonerRank.contains("IV")){
            userWeight2 = 200;
        }
        else if (summonerRank.contains("III")){
            userWeight2 = 400;
        }
        else if (summonerRank.contains("II")){
            userWeight2 = 600;
        }
        else if (summonerRank.contains("I")){
            userWeight2 = 800;
        }

        double userRankWeight = userWeight + userWeight2;

        //tests if able to pull match id (works)
        String recentMatch[] = {"",""};
        for( int i = 0; i<2; i++){
           summonerUser.setMatch(MatchRequest.getMatchList(summonerACCID, region, i));
            String matchList = summonerUser.getMatch().toString();
            long matchLong = Long.parseLong(matchList);
            recentMatch[i] = matchList;
            //TimeUnit.SECONDS.sleep(1);
        }
        Log.e("test ", "1");

        //tests if able to pull player ids for 1 game (works)
        //String players1[] = {"","","","","","","","","",""};
        int players [] = {0,0,0,0,0,0,0,0,0,0};

        for (int j = 0; j<10; j++){
            summonerUser.setGame(GameRequest.getGameInfo(recentMatch[0], region, j));
            System.out.println(summonerUser+ " " + summonerUser.getGame());
            String playerIDs = summonerUser.getGame().toString();
            int playerIDsINT = Integer.parseInt(playerIDs);
            //players1[j] = playerIDs;
            players [j] = playerIDsINT;

        }

        //tests if able to retrieve rank data of a single user (works)
        String playerRanks [] = {"","","","","","","","","",""};
        for (int k = 0; k<10; k++){

            Summoner summonerPlayer = SummonerRequest.getSummoner(players[k],region);
            summonerPlayer.setLeague(LeagueRequest.getPosition(players[k], region));
            String summonerPRank = summonerPlayer.getLeague().toString();
            playerRanks[k] = summonerPRank;
        }

        Log.e("test ", "2");

        //test if weights could be assigned to each player (works)
        int rankWeightsONE [] = {0,0,0,0,0,0,0,0,0,0};
        int rankWeightsTWO [] = {0,0,0,0,0,0,0,0,0,0};

        for (int i = 0; i<10; i++) {
            if (playerRanks[i].contains("BRONZE")) {
                rankWeightsONE[i] = 0;
            }
            else if (playerRanks[i].contains("SILVER")){
                rankWeightsONE[i] = 1000;
            }
            else if (playerRanks[i].contains("GOLD")){
                rankWeightsONE[i] = 2000;
            }
            else if (playerRanks[i].contains("PLATINUM")){
                rankWeightsONE[i] = 3000;
            }
            else if (playerRanks[i].contains("DIAMOND")){
                rankWeightsONE[i] = 4000;
            }
            else if (playerRanks[i].contains("MASTER")){
                rankWeightsONE[i] = 5000;
            }
            else if (playerRanks[i].contains("CHALLENGER")){
                rankWeightsONE[i] = 6000;
            }
            else{
                rankWeightsONE[i] = 0;
            }
        }

        for (int i = 0; i<10; i++) {
            if (playerRanks[i].contains("V")) {
                rankWeightsTWO[i] = 0;
            }
            else if (playerRanks[i].contains("IV")){
                rankWeightsTWO[i] = 200;
            }
            else if (playerRanks[i].contains("III")){
                rankWeightsTWO[i] = 400;
            }
            else if (playerRanks[i].contains("II")){
                rankWeightsTWO[i] = 600;
            }
            else if (playerRanks[i].contains("I")){
                rankWeightsTWO[i] = 800;
            }
        }


        //determines weights (works)
        int sumONE = 0;
        int sumTWO = 0;

        for(int i=0; i < rankWeightsONE.length ; i++) {
            sumONE = sumONE + rankWeightsONE[i];
            sumTWO = sumTWO + rankWeightsTWO[i];
        }

        //calculate average value
        double averageONE = sumONE / rankWeightsONE.length;
        double averageTWO = sumTWO / rankWeightsTWO.length;

        double sumFINAL = (averageONE+averageTWO);

        double actualMMR = (sumFINAL + userRankWeight) / 2;
        String actualMMRtext = Double.toString(actualMMR);
        String userRankWeighttext = Double.toString(userRankWeight);

        String messageDisplay = "";
        Log.e("test ", "3");

        if (sumFINAL > (userRankWeight + 200)){
            messageDisplay = "You will skip a division! Your current MMR is " + actualMMRtext + ". While the average is " + userRankWeighttext;
        }

        else if (sumFINAL < (userRankWeight - 200)){
            messageDisplay = "You are in elo hell! Your current MMR is " + actualMMRtext + ". While the average is " + userRankWeighttext;
        }

        else{
            messageDisplay = "You belong in this division!";
        }


        //sends to new activity and sends id to display rank emblem (works)
        switch (divisionID) {
            case 0:
                rankView.setImageResource(R.drawable._blank2);
                message.setText(messageDisplay);
                break;
            case 1:
                rankView.setImageResource(R.drawable._bronze);
                message.setText(messageDisplay);
                break;
            case 2:
                rankView.setImageResource(R.drawable._silver);
                message.setText(messageDisplay);
                break;
            case 3:
                rankView.setImageResource(R.drawable._gold);
                message.setText(messageDisplay);
                break;
            case 4:
                rankView.setImageResource(R.drawable._platinum);
                message.setText(messageDisplay);
                break;
            case 5:
                rankView.setImageResource(R.drawable._diamond);
                message.setText(messageDisplay);
                break;
            case 6:
                rankView.setImageResource(R.drawable._master);
                message.setText(messageDisplay);
                break;
            case 7:
                rankView.setImageResource(R.drawable._challenger);
                message.setText(messageDisplay);
                break;
        }

    }
}
