package com.example.shubh.collegerank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import stanford.androidlib.SimpleActivity;
import stanford.androidlib.data.SimpleDatabase;

public class add extends SimpleActivity {

    Button ba,br,bd;
    EditText ec,er,ey;
    String b,c,d;
    int x,u;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ba=(Button)findViewById(R.id.ins);
        br=(Button)findViewById(R.id.rep);
        bd=(Button)findViewById(R.id.del);

        ec=(EditText)findViewById(R.id.etcol);
        er=(EditText)findViewById(R.id.etr);
        ey=(EditText)findViewById(R.id.etyr);

        b=ec.getText().toString();
        c=er.getText().toString();

                d=ey.getText().toString();
        x=Integer.parseInt(c);
        u=Integer.parseInt(d);
        SimpleDatabase.with(this).executeSqlFile("dbase");

        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db= openOrCreateDatabase("dbase", Context.MODE_PRIVATE,null);

               // db.insert("college",null,b,"a",x,u);
            }
        });
        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
