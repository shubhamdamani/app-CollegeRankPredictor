package com.example.shubh.collegerank;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;
import com.jjoe64.graphview.series.BaseSeries;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.BarGraphSeries;

import java.io.IOException;

import stanford.androidlib.SimpleActivity;
import stanford.androidlib.data.SimpleDatabase;


public class MainActivity extends SimpleActivity {

   // GraphView
    Button b,b2,b3,clear;
    EditText ed1,ed2;
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    TextView t1,t2;
    Spinner s1;
    int pred=0,tyr=0,fin=0;
    String y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // b=(Button)findViewById(R.id.button);
      //  ed1=(EditText)findViewById(R.id.editText);
        //ed2=(EditText)findViewById(R.id.editText2);
        mDBHelper = new DatabaseHelper(this);
        clear=(Button)findViewById(R.id.button2);
        s1=(Spinner)findViewById(R.id.spinner);
        t1=(TextView)findViewById(R.id.textView);
        t2=(TextView)findViewById(R.id.textView2);
       /* b=(Button)findViewById(R.id.button3);
        b2=(Button)findViewById(R.id.button4);
        b3=(Button)findViewById(R.id.button5);*/


        SimpleDatabase.with(this).executeSqlFile("dbase");




        GraphView graph=(GraphView)findViewById(R.id.graph);
	//set graphview attributes
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(2005);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxX(2020);
        graph.getViewport().setMaxY(95);


        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedClass = parent.getItemAtPosition(position).toString();
                switch (selectedClass)
                {
                    case "mnnit":
                        String nam="mnnit";
                        String cou="a";
                        SQLiteDatabase db= openOrCreateDatabase("dbase", Context.MODE_PRIVATE,null);
                        Cursor cr=db.rawQuery("SELECT year,rank FROM college WHERE country='"+cou+"' AND name='"+nam+"' ORDER BY year",null);

                        if(cr.moveToFirst()){

                            LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

                            do{
                                int yr=cr.getInt(cr.getColumnIndex("year"));
                                int rk=cr.getInt(cr.getColumnIndex("rank"));
                                pred+=yr*rk;
                                tyr+=yr;
                                //       Log.d("year"k"+rk)

                                series.appendData(new DataPoint(yr,rk),false,100);

                            }while(cr.moveToNext());
                            cr.close();
                            GraphView graph=(GraphView)findViewById(R.id.graph);
                            if(nam=="mnnit") {


                                graph.setTitle("moti");
                                //graph.setTitleTextSize();
                                graph.setTitleColor(82315);
                                graph.addSeries(series);
                            }
                            if(nam=="nnit"){
                                graph.setTitle("mti");
                                graph.setTitleTextSize(1);
                                graph.setTitleColor(82375);
                                // graph.setL;
                                graph.addSeries(series);
                            }
                            else{
                                graph.setTitle("ti");
                                //graph.setTitleTextSize();
                                graph.setTitleColor(82475);
                                graph.addSeries(series);
                                // graph.setLayerPaint(34, Paint.Cap.valueOf(o));

                            }
                            // Toast.ma
                            series.setOnDataPointTapListener(new OnDataPointTapListener() {
                                @Override
                                public void onTap(Series series, DataPointInterface dataPoint) {
                                    Toast.makeText(getBaseContext(),"Series1: On Data Point clicked: "+dataPoint, Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                         fin=pred/tyr;
                        pred=0;
                        tyr=0;
                         y= Integer.toString(fin);
                        t2.setText(y);



                        break;

                    case "nnit":
                        String namw="nnit";
                        String couw="a";
                        SQLiteDatabase dbw= openOrCreateDatabase("dbase", Context.MODE_PRIVATE,null);
                        Cursor crw=dbw.rawQuery("SELECT year,rank FROM college WHERE country='"+couw+"' AND name='"+namw+"' ORDER BY year",null);

                        if(crw.moveToFirst()){

                            LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

                            do{
                                int yr=crw.getInt(crw.getColumnIndex("year"));
                                int rk=crw.getInt(crw.getColumnIndex("rank"));
                                //       Log.d("year"k"+rk)
                                pred+=yr*rk;
                                tyr+=yr;

                                series.appendData(new DataPoint(yr,rk),false,100);

                            }while(crw.moveToNext());
                            crw.close();
                            GraphView graph=(GraphView)findViewById(R.id.graph);
                            if(namw=="mnnit") {


                                graph.setTitle("moti");
                                //graph.setTitleTextSize();
                                graph.setTitleColor(82315);
                                graph.addSeries(series);
                            }
                            if(namw=="nnit"){
                                graph.setTitle("mti");
                                graph.setTitleTextSize(1);
                                graph.setTitleColor(82375);
                                // graph.setL;
                                graph.addSeries(series);
                            }
                            else{
                                graph.setTitle("ti");
                                //graph.setTitleTextSize();
                                graph.setTitleColor(82475);
                                graph.addSeries(series);
                                // graph.setLayerPaint(34, Paint.Cap.valueOf(o));

                            }
                            // Toast.ma
                            series.setOnDataPointTapListener(new OnDataPointTapListener() {
                                @Override
                                public void onTap(Series series, DataPointInterface dataPoint) {
                                    Toast.makeText(getBaseContext(),"Series1: On Data Point clicked: "+dataPoint, Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                        fin=pred/tyr;
                        pred=0;
                        tyr=0;
                        y= Integer.toString(fin);
                        t2.setText(y);


                        break;

                    case "knit":

                        String namq="knit";
                        String couq="a";
                        SQLiteDatabase dbq= openOrCreateDatabase("dbase", Context.MODE_PRIVATE,null);
                        Cursor crq=dbq.rawQuery("SELECT year,rank FROM college WHERE country='"+couq+"' AND name='"+namq+"' ORDER BY year",null);

                        if(crq.moveToFirst()){

                            LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

                            do{
                                int yr=crq.getInt(crq.getColumnIndex("year"));
                                int rk=crq.getInt(crq.getColumnIndex("rank"));
                                //       Log.d("year"k"+rk)
                                pred+=yr*rk;
                                tyr+=yr;

                                series.appendData(new DataPoint(yr,rk),false,100);

                            }while(crq.moveToNext());
                            crq.close();
                            GraphView graph=(GraphView)findViewById(R.id.graph);
                            if(namq=="mnnit") {


                                graph.setTitle("moti");
                                //graph.setTitleTextSize();
                                graph.setTitleColor(82315);
                                graph.addSeries(series);
                            }
                            if(namq=="nnit"){
                                graph.setTitle("mti");
                                graph.setTitleTextSize(1);
                                graph.setTitleColor(82375);
                                // graph.setL;
                                graph.addSeries(series);
                            }
                            else{
                                graph.setTitle("ti");
                                //graph.setTitleTextSize();
                                graph.setTitleColor(82475);
                                graph.addSeries(series);
                                // graph.setLayerPaint(34, Paint.Cap.valueOf(o));

                            }
                            // Toast.ma
                            series.setOnDataPointTapListener(new OnDataPointTapListener() {
                                @Override
                                public void onTap(Series series, DataPointInterface dataPoint) {
                                    Toast.makeText(getBaseContext(),"Series1: On Data Point clicked: "+dataPoint, Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                        fin=pred/tyr;
                        pred=0;
                        tyr=0;
                        y= Integer.toString(fin);
                        t2.setText(y);

                        break;

                    case "mnit":
                        String namz="mnit";
                        String couz="a";
                        SQLiteDatabase dbz= openOrCreateDatabase("dbase", Context.MODE_PRIVATE,null);
                        Cursor crz=dbz.rawQuery("SELECT year,rank FROM college WHERE country='"+couz+"' AND name='"+namz+"' ORDER BY year",null);

                        if(crz.moveToFirst()){

                            LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

                            do{
                                int yr=crz.getInt(crz.getColumnIndex("year"));
                                int rk=crz.getInt(crz.getColumnIndex("rank"));
                                pred+=yr*rk;
                                tyr+=yr;
                                       Log.d("year"k"+rk)

                                series.appendData(new DataPoint(yr,rk),false,100);

                            }while(crz.moveToNext());
                            crz.close();
                            GraphView graph=(GraphView)findViewById(R.id.graph);
                            if(namz=="mnnit") {


                                graph.setTitle("moti");
                                //graph.setTitleTextSize();
                                graph.setTitleColor(82315);
                                graph.addSeries(series);
                            }
                            if(namz=="nnit"){
                                graph.setTitle("mti");
                                graph.setTitleTextSize(1);
                                graph.setTitleColor(82375);
                                // graph.setL;
                                graph.addSeries(series);
                            }
                            else{
				//set Title of graph
                                graph.setTitle("ti");
                                //graph.setTitleTextSize();
                                graph.setTitleColor(82475);
                                graph.addSeries(series);
                                // graph.setLayerPaint(34, Paint.Cap.valueOf(o));

                            }
                            // Toast.ma
                            series.setOnDataPointTapListener(new OnDataPointTapListener() {
                                @Override
                                public void onTap(Series series, DataPointInterface dataPoint) {
                                    Toast.makeText(getBaseContext(),"Series1: On Data Point clicked: "+dataPoint, Toast.LENGTH_SHORT).show();
                                }
                            });

                        }

                        fin=pred/tyr;
                        pred=0;
                        tyr=0;
                        y= Integer.toString(fin);
                        t2.setText(y);


                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // can leave this empty
            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GraphView gra=(GraphView)findViewById(R.id.graph);
                gra.removeAllSeries();
                t2.setText("FutureRank");
            }
        });

       /* b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,add.class);
                startActivity(i);
            }
        });*/

    }


}
