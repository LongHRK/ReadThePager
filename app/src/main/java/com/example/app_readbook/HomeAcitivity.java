package com.example.app_readbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeAcitivity extends AppCompatActivity {

    ListView listView;
    ArrayList<ObjRead> listdata;
    AdapterListViewRead adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CreateElement();

        Intent getIntent = getIntent();
        String linkhome = getIntent.getStringExtra("linkhome");

        AsyncTask<String,Void,String> content = new ReadBook().execute(linkhome);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeAcitivity.this,WebViewActivity.class);
                intent.putExtra("link",listdata.get(position).getLink());
                startActivity(intent);
            }
        });
    }

    private void CreateElement() {
        listView = findViewById(R.id.listview);
        listdata = new ArrayList<>();
        adapter = new AdapterListViewRead(HomeAcitivity.this,
                R.layout.itemlistview,
                listdata);
        listView.setAdapter(adapter);
    }

    private class ReadBook extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();

            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line;

                while ((line = bufferedReader.readLine()) != null){
                    content.append(line);
                }
                bufferedReader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            return String.valueOf(content);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                XMLParese parser = new XMLParese();
                Document document = parser.getDocument(s);

                NodeList nodeListcontent = document.getElementsByTagName("item");
                NodeList nodeListDescription = document.getElementsByTagName("description");

                String title = "";
                String link = "";
                String image = "";

                for(int i = 0; i < nodeListcontent.getLength(); i++){
                    Element element = (Element) nodeListcontent.item(i);
                    String cdata = nodeListDescription.item(i + 1).getTextContent();
                    Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                    Matcher m = p.matcher(cdata);
                    if(m.find()){
                        image = m.group(1);
                    }
                    title = parser.getValues(element,"title");
                    link = parser.getValues(element,"link");
                    listdata.add(new ObjRead(title,link,image));
                }
                adapter.notifyDataSetChanged();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}