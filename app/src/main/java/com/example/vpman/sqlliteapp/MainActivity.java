package com.example.vpman.sqlliteapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ed,ed1,ed2,ed3;
    Button b,b1,b2,b3;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new DatabaseHelper(this);
        ed=findViewById(R.id.et);
        ed1=findViewById(R.id.et1);
        ed2=findViewById(R.id.et2);
        ed3=findViewById(R.id.et3);

        b=findViewById(R.id.b);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        b3=findViewById(R.id.b3);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean inserted=myDb.insertData(ed1.getText().toString(),ed2.getText().toString(),ed3.getText().toString());
                if (inserted==true)
                {
                    Toast.makeText(getApplicationContext(),"Suceefully Inserted",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Failed Insertion",Toast.LENGTH_LONG).show();
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRow=myDb.deleteData(ed.getText().toString());
                if (deletedRow>0)
                {
                    Toast.makeText(getApplicationContext(),"Data deleted",Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Data is not deleted",Toast.LENGTH_LONG).show();

                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isupdate=myDb.upDateData(ed.getText().toString(),ed1.getText().toString(),ed2.getText().toString(),ed3.getText().toString());
                if (isupdate==true)
                {
                    Toast.makeText(getApplicationContext(),"Data is updated",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Error not updated",Toast.LENGTH_LONG).show();
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=myDb.getAllData();
                if (res.getCount()==0)
                {
                    //show message
                    showMaessage("Error","Nothing is Found");
                    return;
                }

                else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("Id :" + res.getString(0) + "\n");
                        buffer.append("Name :" + res.getString(1) + "\n");
                        buffer.append("Surname :" + res.getString(2) + "\n");
                        buffer.append("Marks :" + res.getString(3) + "\n\n");
                    }
                    showMaessage("Data", buffer.toString());
                }
            }
        });





    }
    public void showMaessage(String title,String Message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
