package com.example.studentdiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {
    private final RecyclerView recyclerView;
    Context context;

    ArrayList<String> subjectnam = new ArrayList<String>();
    ArrayList<String> status=new ArrayList<String>();
    ArrayList<String>  time=new ArrayList<String>();

    public myAdapter(RecyclerView recyclerView,Context context,ArrayList<String> subjectname,ArrayList<String> status,ArrayList<String> time) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.subjectnam=subjectname;
        this.status=status;
        this.time=time;
    }

    @NonNull
    @Override
    public myAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myAdapter.ViewHolder holder, int position) {
        holder.subname.setText(subjectnam.get(position));
        holder.status.setText(status.get(position));
        holder.times.setText(time.get(position));

    }

    @Override
    public int getItemCount() {
        return subjectnam.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView subname,status,times;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subname=itemView.findViewById(R.id.subjectnme);
            status=itemView.findViewById(R.id.status);
            times=itemView.findViewById(R.id.time);

        }
    }
}
