package com.azhar.sqlitedb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText nameEt, ageEt, genderEt, idEt;
    Button button;
    MyDBHelper myDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nameEt = findViewById(R.id.nameEtU);
        ageEt = findViewById(R.id.ageEtU);
        genderEt = findViewById(R.id.genderEtU);
        idEt = findViewById(R.id.idEtU);

        button = findViewById(R.id.saveBtn);



        myDBHelper = new MyDBHelper(this);
        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEt.getText().toString().trim();
                String age = ageEt.getText().toString().trim();
                String gender = genderEt.getText().toString().trim();
                String id = idEt.getText().toString().trim();
                Boolean isUpdated = myDBHelper.updateData(name,age,gender,id);

                if (isUpdated == true){
                    Toast.makeText(getApplicationContext(),"Updated", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Update Unsuccessful", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}