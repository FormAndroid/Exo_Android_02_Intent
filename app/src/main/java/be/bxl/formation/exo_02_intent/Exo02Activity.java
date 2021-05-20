package be.bxl.formation.exo_02_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Exo02Activity extends AppCompatActivity {

    public static final String EXTRA_FIRSTNAME = "firstname";
    public static final String EXTRA_LASTNAME = "lastname";

    private TextView textWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo02);

        // Liaison avec le layout
        textWelcome = findViewById(R.id.tv_exo_02_welcome_message);

        // Recuperation des infos necessaires
        if(getIntent().hasExtra(EXTRA_FIRSTNAME) && getIntent().hasExtra(EXTRA_LASTNAME) ) {

            Bundle bundle = getIntent().getExtras();

            String firstname = bundle.getString(EXTRA_FIRSTNAME);
            String lastname = bundle.getString(EXTRA_LASTNAME);

            // Affichage du message
            String message = String.format(getString(R.string.msg_exo_02), firstname, lastname);
            textWelcome.setText(message);
        }
        else {
            throw new RuntimeException("Le données \"firstname\" et \"lastname\" sont requises");
        }
    }
}