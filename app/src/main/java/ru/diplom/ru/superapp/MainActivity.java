package ru.diplom.ru.superapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import ru.diplom.ru.superapp.adapter.EventAdapter;
import ru.diplom.ru.superapp.model.Event;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EventAdapter eventAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView);
        eventAdapter = new EventAdapter(MainActivity.this, Collections.<Event>emptyList());
        listView.setAdapter(eventAdapter);

        new JsonTask().execute("http://192.168.56.1:8080/api");
    }
    public class JsonTask extends AsyncTask<String,String, String> {
        List<Event> list;
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection httpURLConnection = null;

            BufferedReader bufferedReader = null;
            try {
                URL url = new URL(params[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder bilder = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    bilder.append(line);
                }

                list = new Gson().fromJson(bilder.toString(), new TypeToken<List<Event>>(){}.getType());
                return  list.toString();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bufferedReader != null)
                        bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (httpURLConnection != null)
                    httpURLConnection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            eventAdapter.notifyDataSetChanged();
            final TextView textView = (TextView)findViewById(R.id.textView);
            textView.setText(s);
//            EventAdapter eventAdapter = new EventAdapter(MainActivity.this, list);
        }
    }
}
