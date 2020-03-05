package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {
    private TextView tvHome;
    private TextView tvAway;
    private TextView homeScore;
    private TextView awayScore;
    private TextView scorerHome;
    private TextView scorerAway;
    private ImageView ivHome;
    private ImageView ivAway;
    private int scoreAway;
    private int scoreHome;
    public static final String WINNER_KEY="arema";
    private String homeScorer;
    private String awayScorer;

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
        scorerHome = findViewById(R.id.tvScorerHome);
        scorerAway = findViewById(R.id.tvScorerAway);
        ivHome = findViewById(R.id.home_logo);
        ivAway = findViewById(R.id.away_logo);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String home = extras.getString(MainActivity.HOMETEAM_KEY);
            String away = extras.getString(MainActivity.AWAYTEAM_KEY);
            Bitmap bpHome = (Bitmap) extras.get("Bitmap1");
            ivHome.setImageBitmap(bpHome);
            Bitmap bpAway = (Bitmap) extras.get("Bitmap2");
            ivAway.setImageBitmap(bpAway);
            tvHome.setText(home);
            tvAway.setText(away);
        }

    }

    //2.Tombol add score menambahkan memindah activity ke scorerActivity dimana pada scorer activity di isikan nama pencetak gol
    public void AddHomeScoreHandler(View view) {
        Intent i = new Intent(this, ScorerActivity.class);
        startActivityForResult(i,1);
    }


    public void AddAwayScoreHandler(View view) {
        Intent i = new Intent(this, ScorerActivity.class);
        startActivityForResult(i,2);
    }

    //3.Dari activity scorer akan mengirim kembali ke activity matchactivity otomatis nama pencetak gol dan skor bertambah +1
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                if (requestCode == 1) {
                    // TODO Extract the data returned from the child Activity.
                    String returnHome = data.getStringExtra(ScorerActivity.SCORER_KEY);
                    homeScorer += returnHome;
                    scorerHome.setText(""+homeScorer);
                    scoreHome ++;
                    homeScore.setText(""+scoreHome);
                }
                else if (requestCode == 2){
                    String returnAway = data.getStringExtra(ScorerActivity.SCORER_KEY);
                    awayScorer += returnAway;
                    scorerAway.setText("\n"+awayScorer);
                    scoreAway++;
                    awayScore.setText("\n"+scoreAway);
                }
    }


    //4.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang beserta nama pencetak gol ke ResultActivity, jika seri di kirim text "Draw",
    public void PrintResultHandler(View view) {
        Intent intent = new Intent(MatchActivity.this, ResultActivity.class);
        if (scoreAway>scoreHome)
            intent.putExtra(WINNER_KEY,tvAway.getText().toString());
        else if(scoreHome>scoreAway)
            intent.putExtra(WINNER_KEY,tvHome.getText().toString());
        else
            intent.putExtra(WINNER_KEY,"Seri");
        startActivity(intent);
    }


}
