package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView tvWinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tvWinner = findViewById(R.id.tvWinner);
        Bundle extras = getIntent().getExtras();

        tvWinner.setText("Selamat "+extras.getString(MatchActivity.WINNER_KEY));
    }
}
