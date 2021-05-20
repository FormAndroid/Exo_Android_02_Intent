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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

        // Pour les autres, on utilise l'implémentation de l'interface "View.OnClickListener"
        // par la classe "MainActivité" (qui est notre instance)
        btnExo02.setOnClickListener(this);
        btnExo03.setOnClickListener(this);
        btnExo04.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Log.d("DEMO", "Clique sur le bouton qui a l'id : " + v.getId());

        switch (v.getId()) {
            case R.id.btn_main_exo_02:
                runActivityWelcome();
                break;
            case R.id.btn_main_exo_03:
                runActivityQuestion();
                break;
            case R.id.btn_main_exo_04:
                runActivityCallPhone();
                break;
            default:
                throw new RuntimeException("Bouton non implementé !");
        }
    }

    private void runActivityWelcome() {
        // Recup les datas
        String firstname = editFirstname.getText().toString();
        String lastname = editLastname.getText().toString();

        // Créer l'intent avec les datas
        Intent intent = new Intent(getApplicationContext(), Exo02Activity.class);
        intent.putExtra(Exo02Activity.EXTRA_FIRSTNAME, firstname);
        intent.putExtra(Exo02Activity.EXTRA_LASTNAME, lastname);

        // Démarre l'activté de l'exo 02
        startActivity(intent);
    }

    private void runActivityQuestion() {

    }

    private void runActivityCallPhone() {

    }
}