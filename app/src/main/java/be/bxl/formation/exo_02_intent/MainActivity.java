package be.bxl.formation.exo_02_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{

    private Button btnExo01, btnExo02, btnExo03, btnExo04;
    private EditText editFirstname, editLastname, editPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Liaison entre le code java et le layout
        btnExo01 = findViewById(R.id.btn_main_exo_01);
        btnExo02 = findViewById(R.id.btn_main_exo_02);
        btnExo03 = findViewById(R.id.btn_main_exo_03);
        btnExo04 = findViewById(R.id.btn_main_exo_04);

        editFirstname = findViewById(R.id.et_main_exo_02_firstname);
        editLastname = findViewById(R.id.et_main_exo_02_lastname);
        editPhoneNumber = findViewById(R.id.et_main_exo_04_number);

        // Pour l'exo 01, on défini le code du "click" directement (via une lamdba)
        btnExo01.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Exo01Activity.class);
            startActivity(intent);
        });

    }

}