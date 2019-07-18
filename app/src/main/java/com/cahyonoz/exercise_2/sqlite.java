package com.cahyonoz.exercise_2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class sqlite extends AppCompatActivity {
    EditText editNIM, editNama, editJk, editEmail;
    Button btnSimpan;
    ImageView imBack;
    Intent intent;
    String idSiswa;
    DBController cnt = new DBController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        editNIM = findViewById(R.id.EdNIM);
        editNama = findViewById(R.id.EdNama);
        editJk = findViewById(R.id.EdJK);
        editEmail = findViewById(R.id.EdEmail);
        btnSimpan = findViewById(R.id.BtSimpan);
        imBack = (ImageView) findViewById(R.id.Imgback);

        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
            });

            showData();
        btnSimpan.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                try {
                    if (editNIM.getText().toString().equals("") ||
                            editNama.getText().toString().equals("") ||
                            editJk.getText().toString().equals("") ||
                            editEmail.getText().toString().equals("")) {
                        throw new RuntimeException("Data harus diisi lengkap!");
                    } else {
                        HashMap<String, String> queryValues = new HashMap<String, String>();
                        queryValues.put("nim", editNIM.getText().toString());
                        queryValues.put("nama", editNama.getText().toString());
                        queryValues.put("jk", editJk.getText().toString());
                        queryValues.put("email", editEmail.getText().toString());
                        cnt.saveMahasiswa(queryValues);
                        Toast.makeText(sqlite.this, "Mahasiswa : " + editNama.getText().toString() +
                                "Sukses diinput", Toast.LENGTH_LONG);
                        clearEditText();
                        showData();

                    }

                } catch (RuntimeException exc) {
                    Toast.makeText(sqlite.this, exc.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Toast.makeText(sqlite.this, "Something Wrong !!", Toast.LENGTH_SHORT).show();
                }
            }
            });
        }

    public void showData() {
        final ArrayList<HashMap<String, String>> data = cnt.showAllMahasiswa();
        ListView listView = findViewById(R.id.listViewSQLite);
        ListAdapter adapter = new SimpleAdapter(sqlite.this, data, R.layout.listview_sqlite,
                new String[]{"nim", "nama"},
                new int[]{R.id.EdItemNIM, R.id.EdItemNama}
        );

        listView.setAdapter(adapter);
        if (data.size() == 0) {
            Toast.makeText(getApplicationContext(),
                    "Data Mahasiswa belum tersedia",
                    Toast.LENGTH_LONG).show();
        }
        else {

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    //Toast.makeText(MainActivity.this, dataTeman.get(position).get("id"),
                    // Toast.LENGTH_LONG).show();
                    CharSequence[] dialogitem = {"Lihat", "Edit", "Delete"};
                    idSiswa = data.get(position).get("Id");
                    AlertDialog.Builder builder = new AlertDialog.Builder(sqlite.this);
                    builder.setTitle("Pilihan");
                    builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0:
                                    Intent i = new Intent(getApplicationContext(), Profile.class);
                                    i.putExtra("a", idSiswa);
                                    startActivity(i);
                                    break;

                                case 1:
                                    Intent in = new Intent(getApplicationContext(), UpdateData.class);
                                    in.putExtra("a", idSiswa);
                                    startActivity(in);
                                    break;
                                case 2:
                                    cnt.deleteMhs(idSiswa);
                                    showData();
                                    break;
                            }
                        }
                    });
                    builder.create().show();
                }
            });
        }
    }


    public void clearEditText() {
        editNIM.setText("");
        editNama.setText("");
        editJk.setText("");
        editEmail.setText("");
    }
}