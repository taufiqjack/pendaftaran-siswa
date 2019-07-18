package com.cahyonoz.exercise_2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Profile extends AppCompatActivity {

    TextView textNIM, texttNama, texttJK, texttEmail;
    int idMahasiswa;

    DBController cnt = new DBController(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textNIM = findViewById(R.id.txNIMM);
        texttNama = findViewById(R.id.txNamaa);
        texttJK = findViewById(R.id.txJenisKelamin);
        texttEmail = findViewById(R.id.txEmaill);

        Intent intent = getIntent();
        String Id = intent.getExtras().getString("a");
        idMahasiswa = Integer.parseInt(Id);

        ArrayList<HashMap<String, String>> data = cnt.showMahasiswabyId(idMahasiswa);

        textNIM.setText(data.get(0).get("nim"));
        texttNama.setText(data.get(0).get("jk"));
        texttJK.setText(data.get(0).get("email"));
        texttEmail.setText(data.get(0).get("nama"));



    }
}
