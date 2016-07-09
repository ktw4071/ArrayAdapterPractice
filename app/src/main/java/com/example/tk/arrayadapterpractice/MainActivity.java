package com.example.tk.arrayadapterpractice;

import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView taraList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taraList = (ListView) findViewById(R.id.tara_list);

        ArrayList<TARAValueObject> items = new ArrayList<TARAValueObject>();

        Resources res = getResources();

        items.add(new TARAValueObject(getResources().getString(R.string.member_name_1), getResources().getDrawable(R.drawable.t_ara_icon_soyeon)));
        items.add(new TARAValueObject(getResources().getString(R.string.member_name_2), getResources().getDrawable(R.drawable.t_ara_icon_jiyeon)));
        items.add(new TARAValueObject(getResources().getString(R.string.member_name_3), getResources().getDrawable(R.drawable.t_ara_icon_qri)));
        items.add(new TARAValueObject(getResources().getString(R.string.member_name_4), getResources().getDrawable(R.drawable.t_ara_icon_boram)));
        items.add(new TARAValueObject(getResources().getString(R.string.member_name_5), getResources().getDrawable(R.drawable.t_ara_icon_hyomin)));
        items.add(new TARAValueObject(getResources().getString(R.string.member_name_6), getResources().getDrawable(R.drawable.t_ara_icon_eunjung)));

        TARAArrayAdapter adapter = new TARAArrayAdapter(this, items);
        taraList.setAdapter(adapter);
    }
    private Context currentContext;


    private class TARAArrayAdapter extends ArrayAdapter<TARAValueObject> {
        public TARAArrayAdapter(Context context, List<TARAValueObject> objects) {
            super(context, 0, objects);
            currentContext = context;
        }

       //ViewHolder viewHolder;

        @Override
        public View getView(final int position, View convertView, ViewGroup parent){
            final TARAValueObject valueObject = (TARAValueObject)getItem(position);
            final ViewHolder viewHolder;
            //View rootView;

            if(convertView == null){
                convertView = LayoutInflater.from(currentContext).inflate(R.layout.tara_list_view_item, null);
                viewHolder = new ViewHolder();
                viewHolder.memberNameWD = (TextView)convertView.findViewById(R.id.member_name);
                viewHolder.likeButtonWD = (ImageView)convertView.findViewById(R.id.like_Btn);
                viewHolder.memberImageWD = (ImageView)convertView.findViewById(R.id.member_icon);
                viewHolder.countNumWD = (TextView) convertView.findViewById(R.id.countLike);
                convertView.setTag(viewHolder);
            }

            else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.memberImageWD.setImageDrawable(valueObject.memberImage);
            viewHolder.memberNameWD.setText(valueObject.memberName);
            viewHolder.likeButtonWD.setImageDrawable(getDrawable(R.drawable.like_button));
            viewHolder.countNumWD.setText(Integer.toString(valueObject.count));

            viewHolder.memberImageWD.setOnTouchListener(new View.OnTouchListener(){
                public boolean onTouch(View v, MotionEvent event){
                    Toast.makeText(currentContext, valueObject.memberName + getResources().getString(R.string.select_toast), Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
            viewHolder.likeButtonWD.setOnTouchListener(new View.OnTouchListener(){
                public boolean onTouch(View v, MotionEvent event){
                    valueObject.count++;
                    viewHolder.countNumWD.setText(Integer.toString(valueObject.count));
                    return false;
                }
            });

            return convertView;
        }
    }

    private class ViewHolder {
        public ImageView memberImageWD;
        public TextView memberNameWD;
        public ImageView likeButtonWD;
        public TextView countNumWD;
    }
}
