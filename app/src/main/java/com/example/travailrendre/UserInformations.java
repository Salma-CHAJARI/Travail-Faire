package com.example.travailrendre;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserInformations extends AppCompatActivity {
    private TextView nomUtil,emailUtil,adressUtil,villeUtil,teleUtil,dispoUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_informations);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent ins=getIntent();
        nomUtil=findViewById(R.id.nomUtil);
        emailUtil=findViewById(R.id.emailUtil);
        adressUtil=findViewById(R.id.adressUtil);
        villeUtil=findViewById(R.id.villeUtil);
        teleUtil=findViewById(R.id.teleUtil);
        dispoUtil=findViewById(R.id.dispoUtil);
        nomUtil.setText(ins.getStringExtra("nom"));
        emailUtil.setText(ins.getStringExtra("email"));
        teleUtil.setText(ins.getStringExtra("telephone"));
        adressUtil.setText(ins.getStringExtra("adress"));
        villeUtil.setText(ins.getStringExtra("ville"));
        dispoUtil.setText(ins.getStringExtra("dispo"));

    }
}