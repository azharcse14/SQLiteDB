package com.azhar.sqlitedb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nameEt, ageEt, genderEt;
    Button button, displayDataBtn, updateBtn, deleteBtn;
    TextView textView;
    MyDBHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEt = findViewById(R.id.nameEt);
        ageEt = findViewById(R.id.ageEt);
        genderEt = findViewById(R.id.genderEt);
        button = findViewById(R.id.button);
        displayDataBtn = findViewById(R.id.displayAllDataId);
        textView = findViewById(R.id.textView);
        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtnM);

        myDBHelper = new MyDBHelper(this);
        SQLiteDatabase sqLiteDatabase = myDBHelper.getReadableDatabase();

        button.setOnClickListener(this);
        displayDataBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String name = nameEt.getText().toString().trim();
        String age = ageEt.getText().toString().trim();
        String gender = genderEt.getText().toString().trim();

        if (v.getId()==R.id.button){
            long rowId = myDBHelper.insertData(name, age, gender);

            if (rowId == -1){
                Toast.makeText(getApplicationContext(),"Unsuccessful", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext(),"Row "+rowId+" is Successfully inserted.", Toast.LENGTH_LONG).show();
            }
        }

        if (v.getId()==R.id.displayAllDataId){

            Cursor cursor = myDBHelper.displayAllData();
            if (cursor.getCount()==0){
                Toast.makeText(getApplicationContext(),"No DATA", Toast.LENGTH_LONG).show();
                showData("Error", "No data found");
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()){
                stringBuffer.append("ID: "+cursor.getString(0)+"\n");
                stringBuffer.append("NAME: "+cursor.getString(1)+"\n");
                stringBuffer.append("AGE: "+cursor.getString(2)+"\n");
                stringBuffer.append("GENDER: "+cursor.getString(3)+"\n");
            }

            showData("ResultSet", stringBuffer.toString());
        }

        if (v.getId()==R.id.updateBtn){

            Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
            startActivity(intent);

        }

        if (v.getId()==R.id.deleteBtnM){

            Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
            startActivity(intent);

        }

    }

    private void showData(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();

        textView.setText(message);

    }
}