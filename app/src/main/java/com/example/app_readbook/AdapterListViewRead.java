package com.example.app_readbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterListViewRead extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<ObjRead> list;

    public AdapterListViewRead(Context context, int layout, ArrayList<ObjRead> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class SaveStatus{
        ImageView img;
        TextView lbtitle;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SaveStatus ss;

        if(convertView == null){
            ss = new SaveStatus();

            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(layout,null);

            ss.lbtitle = convertView.findViewById(R.id.i_title);
            ss.img = convertView.findViewById(R.id.i_img);

            convertView.setTag(ss);
        }else {
            ss = (SaveStatus) convertView.getTag();
        }
            ObjRead obj = list.get(position);
            ss.lbtitle.setText(obj.getTitle());
            Picasso.get().load(obj.getImage()).into(ss.img);
        return convertView;
    }
}
