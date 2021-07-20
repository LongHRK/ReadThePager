package com.example.app_readbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> listText;
    ArrayList<String> listLink;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_acitivity);

        CreateElement();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,HomeAcitivity.class);
                intent.putExtra("linkhome",listLink.get(position));
                startActivity(intent);
            }
        });
    }

    private void CreateElement() {
        listView = findViewById(R.id.hm_listview);
        listText = new ArrayList<>();
        listText.add("Sức khỏe");
        listText.add("Đời sống");
        listText.add("Thể thao");
        listText.add("Thế giới");
        listText.add("Thời sự");
        listText.add("Du lịch");
        listText.add("Kinh doanh");
        listText.add("Khoa học");
        listText.add("Giải trí");
        listText.add("Pháp luật");
        listText.add("Giáo dục");
        listText.add("Tin xem nhiều");
        listText.add("Tin mới nhất");
        listText.add("Tin nổi bậc");
        listLink = new ArrayList<>();
        listLink.add("https://vnexpress.net/rss/suc-khoe.rss");
        listLink.add("https://vnexpress.net/rss/gia-dinh.rss");
        listLink.add("https://vnexpress.net/rss/the-thao.rss");
        listLink.add("https://vnexpress.net/rss/the-gioi.rss");
        listLink.add("https://vnexpress.net/rss/thoi-su.rss");
        listLink.add("https://vnexpress.net/rss/du-lich.rss");
        listLink.add("https://vnexpress.net/rss/kinh-doanh.rss");
        listLink.add("https://vnexpress.net/rss/khoa-hoc.rss");
        listLink.add("https://vnexpress.net/rss/giai-tri.rss");
        listLink.add("https://vnexpress.net/rss/phap-luat.rss");
        listLink.add("https://vnexpress.net/rss/giao-duc.rss");
        listLink.add("https://vnexpress.net/rss/tin-xem-nhieu.rss");
        listLink.add("https://vnexpress.net/rss/tin-moi-nhat.rss");
        listLink.add("https://vnexpress.net/rss/tin-noi-bat.rss");
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                listText);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}