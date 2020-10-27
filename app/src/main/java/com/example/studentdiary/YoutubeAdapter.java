package com.example.studentdiary;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeViewHolder> {
    ArrayList<DatasetList> arrayList;
    Context context;

    public YoutubeAdapter(ArrayList<DatasetList> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public YoutubeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_per_row, parent, false);
        return new YoutubeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YoutubeViewHolder holder, int position) {
        final DatasetList current = arrayList.get(position);
        holder.webView.loadUrl(current.getLink());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(context, videofullscreen.class);
               intent.putExtra("link", current.getLink());
             /*   Intent i=new Intent(context,MainActivity3.class);
                i.putExtra("link",current.getLink());*/
                Toast.makeText(context,current.getLink(),Toast.LENGTH_LONG).show();
                context.startActivity(intent);

               /* getSupportFragmentManager().beginTransaction().replace(R.id.navcontainer,new HomeFragment()).commit();
                drawerLayout.closeDrawers();*/

            }
        });
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}