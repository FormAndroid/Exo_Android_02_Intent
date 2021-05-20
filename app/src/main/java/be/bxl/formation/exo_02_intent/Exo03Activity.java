package be.bxl.formation.exo_02_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Exo03Activity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_RESULT_COLOR = "color";

    private Button btnRed, btnGreen, btnOrange, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo03);

        // Liaision avec le layout
        btnRed = findViewById(R.id.btn_exo_03_red);
        btnGreen = findViewById(R.id.btn_exo_03_green);
        btnOrange = findViewById(R.id.btn_exo_03_orange);
        btnCancel = findViewById(R.id.btn_exo_03_cancel);

        btnRed.setOnClickListener(this);
        btnGreen.setOnClickListener(this);
        btnOrange.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exo_03_red:
                setResultColor("Rouge");
                break;
            case R.id.btn_exo_03_green:
                setResultColor("Vert");
                break;
            case R.id.btn_exo_03_orange:
                setResultColor("Orange");
                break;
            case R.id.btn_exo_03_cancel:
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }

    private void setResultColor(String color) {
        // -> Cette intent sert de "stockage" de donnée pour accompagner le resultat
        Intent intentData = new Intent();
        intentData.putExtra(EXTRA_RESULT_COLOR, color);

        setResult(RESULT_OK, intentData);
    }
}