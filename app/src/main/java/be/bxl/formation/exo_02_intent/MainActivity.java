package be.bxl.formation.exo_02_intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    // region Exo 02
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
    //endregion

    //region Exo 03
    private static final int REQUEST_CODE_EXO_3 = 42 ;

    private void runActivityQuestion() {
        // Créer un intent pour l'activité
        Intent intent = new Intent(getApplicationContext(), Exo03Activity.class);

        // Démarre l'activté de l'exo 03 en attendant sa réponse
        //  -> Ne pas faire de fini de cette activité
        startActivityForResult(intent, REQUEST_CODE_EXO_3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // requestCode -> Code envoyer via le "startActivityForResult"
        // resultCode  -> Code envoyer par l'autre activité à l'aide du "setResult"
        // data        -> Objet "Intent" qui sert de stockage de donnée.
        //                Celui-ci a été envoyer par l'autre activité via le "setResult"

        switch (requestCode) {

            case REQUEST_CODE_EXO_3:
                if(resultCode == RESULT_OK && data != null && data.hasExtra(Exo03Activity.EXTRA_RESULT_COLOR)) {
                    String color = data.getStringExtra(Exo03Activity.EXTRA_RESULT_COLOR);
                    displayChoiceColor(color);
                }
                else {
                    displayNoChoice();
                }
            // Etc...
        }
    }

    private void displayChoiceColor(String color) {
        String msg = String.format(getString(R.string.msg_choice_color), color);

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void displayNoChoice() {
        Toast.makeText(this, R.string.msg_choice_none, Toast.LENGTH_SHORT).show();
    }
    //endregion

    //region Exo 04
    private static final int REQUEST_PERMISSION_CALLPHONE = 1337 ;

    private void runActivityCallPhone() {
        String phoneNumberData = editPhoneNumber.getText().toString();

        // Création d'une ressouce URI qui contient le numero de téléphone
        Uri phoneNumber = Uri.parse("tel:" + phoneNumberData);

        // Création d'un intent "CALL" avec en data l'uri précédement créer.
        Intent intentPhone = new Intent(Intent.ACTION_CALL);
        intentPhone.setData(phoneNumber);

        // Verification "runtime" de la permission "CALL_PHONE"
        //  -> Dans l'objectif de faire une demande de permission, si ce n'est pas le cas
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
            // Demande la permission à l'utilisateur
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CALL_PHONE }, REQUEST_PERMISSION_CALLPHONE);
        }
        else if(intentPhone.resolveActivity(getPackageManager()) != null) {
            // Démarrage de l'application téléphone
            startActivity(intentPhone);
        }
    }

    // Méthode lancer quand l'utilisateur a accepter/refuser une permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // requestCode  -> Code envoyé lors de l'appel de la méthode "ActivityCompat.requestPermissions"
        // permissions  -> La liste des permissions qui ont été demandé
        // grantResults -> La liste des choix de l'utilisateur par rapport à chaque permission (Accepté / resfusé)

        switch (requestCode) {

            case REQUEST_PERMISSION_CALLPHONE:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Relance la méthode, mais avec la permission est accepter mtn =)
                    runActivityCallPhone();
                }
                else {
                    Toast.makeText(this, "Dommage, le bouton ne fonctionnera pas !", Toast.LENGTH_LONG).show();
                }
                break;
            // Etc...
        }
    }

    //endregion

}