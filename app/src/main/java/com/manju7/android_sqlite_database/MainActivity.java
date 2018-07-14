package com.manju7.android_sqlite_database;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText textid,textname,textcity;
    Button buttonAdd,buttonView,buttonUpdate,buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb =new DatabaseHelper(this);

        textid=(EditText)findViewById(R.id.empid);
        textname=(EditText)findViewById(R.id.name);
        textcity=(EditText)findViewById(R.id.city);

        buttonAdd=(Button) findViewById(R.id.add1);
        buttonView=(Button) findViewById(R.id.view);
        buttonUpdate=(Button) findViewById(R.id.update1);
        buttonDelete=(Button) findViewById(R.id.delete1);

        AddData();
        viewAll();
        UpdateData();
        DeleteData();


    }


    public void AddData(){

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isInserted = myDb.insertData(textid.getText().toString(),
                        textname.getText().toString(),
                        textcity.getText().toString());

                if (isInserted=true){

                    Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();

                }else {

                    Toast.makeText(MainActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
                }
            }
        });

    }



    public  void  viewAll(){


        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = myDb.getAllData();
                if(res.getCount()==0){

                    showMessage("Empty","no data");

                }

                StringBuffer buffer =new StringBuffer();
                while (res.moveToNext()){

                    buffer.append("Id :"+res.getString(0)+"\n");
                    buffer.append("Name :"+res.getString(1)+"\n");
                    buffer.append("City :"+res.getString(2)+"\n\n");

                }

                showMessage("Data", buffer.toString());
            }
        });
    }

    public void showMessage(String title, String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void UpdateData(){

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isUpdate = myDb.updateDate(textid.getText().toString(),
                        textname.getText().toString(),
                        textcity.getText().toString());

                if (isUpdate=true){

                    Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_LONG).show();

                }else {

                    Toast.makeText(MainActivity.this,"Data Not Updated",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void DeleteData(){

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer deleteRows = myDb.deleteData(textid.getText().toString());

                if (deleteRows>0){

                    Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();


                }else {

                    Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

}
