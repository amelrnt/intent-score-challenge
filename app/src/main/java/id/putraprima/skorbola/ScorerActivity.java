package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ScorerActivity extends AppCompatActivity {
    private EditText ScorerName;
    private Button ScorerButton;
    public static final String SCORER_KEY = "Upin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorer);
        ScorerName = findViewById(R.id.editTextScorer);
        ScorerButton = findViewById(R.id.buttonScorer);

        ScorerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String scorer = ScorerName.getText().toString();

                Intent i = new Intent(ScorerActivity.this, MatchActivity.class);
                i.putExtra(SCORER_KEY, scorer);
                setResult(MatchActivity.RESULT_OK,i);
                finish();
            }
        });

    }

}
