package com.azhar.sqlitedb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    MyDBHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        editText = findViewById(R.id.idEtD);
        button = findViewById(R.id.deleteBtnD);

        myDBHelper = new MyDBHelper(this);
        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editText.getText().toString().trim();

                int value = myDBHelper.deleteData(id);

                if (value>0){
                    Toast.makeText(getApplicationContext(),"Data Delete Successfully", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Data Delete Unsuccessfully", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}