package com.example.travailrendre;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText nom, email, telephone, adress;
    private Spinner ville, dispo;
    private Button inscris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialisation des champs
        nom = findViewById(R.id.nom);
        email = findViewById(R.id.email);
        telephone = findViewById(R.id.telephone);
        adress = findViewById(R.id.adress);
        ville = findViewById(R.id.ville);
        dispo = findViewById(R.id.dispo);
        inscris = findViewById(R.id.inscriss);

        inscris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vérification des champs
                if (validateFields()) {
                    Intent in = new Intent(MainActivity.this, UserInformations.class);
                    in.putExtra("nom", nom.getText().toString());
                    in.putExtra("email", email.getText().toString());
                    in.putExtra("telephone", telephone.getText().toString());
                    in.putExtra("adress", adress.getText().toString());
                    in.putExtra("ville", ville.getSelectedItem().toString());
                    in.putExtra("dispo", dispo.getSelectedItem().toString());
                    startActivity(in);
                }
            }
        });
    }

    // Méthode de validation des champs
    private boolean validateFields() {
        // Vérifier si le nom est vide
        if (TextUtils.isEmpty(nom.getText().toString())) {
            nom.setError("Veuillez entrer votre nom");
            nom.requestFocus();
            return false;
        }

        // Vérifier si l'email est valide
        if (TextUtils.isEmpty(email.getText().toString())) {
            email.setError("Veuillez entrer votre email");
            email.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError("Veuillez entrer un email valide");
            email.requestFocus();
            return false;
        }

        // Vérifier si le numéro de téléphone est valide
        if (TextUtils.isEmpty(telephone.getText().toString())) {
            telephone.setError("Veuillez entrer votre numéro de téléphone");
            telephone.requestFocus();
            return false;
        } else if (telephone.getText().toString().length() != 10) {
            telephone.setError("Le numéro de téléphone doit contenir 10 chiffres");
            telephone.requestFocus();
            return false;
        }

        // Vérifier si l'adresse est vide
        if (TextUtils.isEmpty(adress.getText().toString())) {
            adress.setError("Veuillez entrer votre adresse");
            adress.requestFocus();
            return false;
        }

        // Vérifier si une ville est sélectionnée
        if (ville.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Veuillez sélectionner une ville", Toast.LENGTH_SHORT).show();
            ville.requestFocus();
            return false;
        }

        // Vérifier si une disponibilité est sélectionnée
        if (dispo.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Veuillez sélectionner votre disponibilité", Toast.LENGTH_SHORT).show();
            dispo.requestFocus();
            return false;
        }

        // Si toutes les vérifications sont correctes
        return true;
    }
}
