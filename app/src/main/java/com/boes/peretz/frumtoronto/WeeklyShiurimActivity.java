package com.boes.peretz.frumtoronto;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class WeeklyShiurimActivity extends AppCompatActivity {

    public static final String LOG_TAG=WeeklyShiurimActivity.class.getSimpleName();
    WebView webView;
    String result="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_shiurim);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ParseWeeklyShiurimWebPage parseWeeklyShiurimWebPage=new ParseWeeklyShiurimWebPage();
        parseWeeklyShiurimWebPage.execute();
    }

    public class ParseWeeklyShiurimWebPage extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            Document document;
            try {
                document= Jsoup.connect("http://www.frumtoronto.com/Shiurim.asp").get();
                Log.d(LOG_TAG,document.toString());
                result=document.toString();
            }catch (IOException exception){
                exception.printStackTrace();
            }
            return "Executed";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            webView.loadData(result,"text/html",null);
        }
    }

}
