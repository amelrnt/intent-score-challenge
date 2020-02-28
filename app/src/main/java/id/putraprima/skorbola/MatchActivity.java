package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {
    private TextView tvHome;
    private TextView tvAway;
    private TextView homeScore;
    private TextView awayScore;
    private int scoreAway;
    private int scoreHome;
    public static final String WINNER_KEY="arema";
    public static final String RESULTCODE_KEY = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        tvHome = findViewById(R.id.txt_home);
        tvAway = findViewById(R.id.txt_away);
        homeScore = findViewById(R.id.score_home);
        awayScore = findViewById(R.id.score_away);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String home = extras.getString(MainActivity.HOMETEAM_KEY);
            String away = extras.getString(MainActivity.AWAYTEAM_KEY);

            tvHome.setText(home);
            tvAway.setText(away);
        }



    }

    //2.Tombol add score menambahkan memindah activity ke scorerActivity dimana pada scorer activity di isikan nama pencetak gol
    public void AddHomeScoreHandler(View view) {
        Intent i = new Intent(this, ScorerActivity.class);
        i.putExtra(RESULTCODE_KEY,1);
        startActivity(i);
    }


    public void AddAwayScoreHandler(View view) {
        Intent i = new Intent(this, ScorerActivity.class);
        i.putExtra(RESULTCODE_KEY,2);
        startActivity(i);
    }

    //3.Dari activity scorer akan mengirim kembali ke activity matchactivity otomatis nama pencetak gol dan skor bertambah +1
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                if (resultCode == 1) {
                    // TODO Extract the data returned from the child Activity.
                    String returnValue = data.getStringExtra("some_key"); //nama scorer
                    scoreHome ++;
                }
                else if (resultCode == 2){
                    String returnValue = data.getStringExtra("some_key");
                    scoreHome++;
                }
    }

    //4.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang beserta nama pencetak gol ke ResultActivity, jika seri di kirim text "Draw",


}
