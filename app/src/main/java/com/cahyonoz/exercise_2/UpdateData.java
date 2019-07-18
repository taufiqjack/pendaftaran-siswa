package com.cahyonoz.exercise_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateData extends AppCompatActivity {

    EditText editNAma, editNotel, editEmail, editAlamat;
    Button btnSimpan;
    int id;
    DBController controller = new DBController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        editNAma = findViewById(R.id.ENama);
        editNotel = findViewById(R.id.ENotel);
        editEmail = findViewById(R.id.EEmail);
        editAlamat = findViewById(R.id.EAlamat);
        btnSimpan = findViewById(R.id.BSimpan);

        Intent intent = getIntent();
        String kontakId = intent.getExtras().getString("a");
        id = Integer.parseInt(kontakId);
        ArrayList<HashMap<String, String>> dataCari = controller.showMahasiswabyId(id);
        editNAma.setText(dataCari.get(0).get("nim"));
        editNotel.setText(dataCari.get(0).get("jk"));
        editEmail.setText(dataCari.get(0).get("email"));
        editAlamat.setText(dataCari.get(0).get("nama"));

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditData(v);
            }
        });
    }
    public void EditData(View view) {
        HashMap<String, String> queryValues = new HashMap<String, String>();
        queryValues.put("id", String.valueOf(id));
        queryValues.put("nim", editNAma.getText().toString());
        queryValues.put("jk", editNotel.getText().toString());
        queryValues.put("email", editEmail.getText().toString());
        queryValues.put("nama", editAlamat.getText().toString());

        if (editNAma.getText().toString().equals("") ||
                editNotel.getText().toString().equals("") ||
                editEmail.getText().toString().equals("") ||
                editAlamat.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Data Harus Lengkap", Toast.LENGTH_LONG).show();
        } else {
            controller.EditTeman(queryValues);
            this.callHomeActivity(view);
            this.refresTbs();
        }

    }
    public void  callHomeActivity(View view){
        Intent objIntent = new Intent(getApplicationContext(),
                MainActivity.class);
        startActivity(objIntent);
    }
    public void refresTbs() {
        editNAma.setText("");
        editNotel.setText("");
        editEmail.setText("");
        editAlamat.setText("");


    }
}