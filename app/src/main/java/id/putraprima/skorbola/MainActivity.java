package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    private EditText homeTeam;
    private EditText awayTeam;
    private Button btnNext;
    public static final String HOMETEAM_KEY = "arema";
    public static final String AWAYTEAM_KEY = "persebaya";
    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1;
    private ImageView HomeLogo;
    private ImageView AwayLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeTeam = findViewById(R.id.home_team);
        awayTeam = findViewById(R.id.away_team);
        HomeLogo = findViewById(R.id.home_logo);
        AwayLogo = findViewById(R.id.away_logo);
        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        HomeLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALLERY_REQUEST_CODE);
            }
        });
        //4. Ganti Logo Away Team
        AwayLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALLERY_REQUEST_CODE);
            }
        });

    }

    //5. Next Button Pindah Ke MatchActivity
    public void MatchActivityHandler(View view) {
        final String home = homeTeam.getText().toString();
        final String away = awayTeam.getText().toString();
        if ((home).equals("") || away.equals("")) {
            Toast.makeText(MainActivity.this, "Isi Team Bertanding!", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(getApplicationContext(), MatchActivity.class);
            intent.putExtra(HOMETEAM_KEY, home);
            intent.putExtra(AWAYTEAM_KEY, away);
            HomeLogo.setDrawingCacheEnabled(true);
            Bitmap bp = HomeLogo.getDrawingCache();
            intent.putExtra("Bitmap", bp);

            startActivity(intent);
        }
    }
}
