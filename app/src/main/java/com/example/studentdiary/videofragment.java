package com.example.studentdiary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class videofragment extends Fragment {











    RecyclerView recyclerViewt;
    ArrayList<DatasetList> arrayList;

    public videofragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_videofragment, container, false);
        recyclerViewt=view.findViewById(R.id.recyclerview3);
        recyclerViewt.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewt.setHasFixedSize(true);
        arrayList=new ArrayList<DatasetList>();
        DatasetList datasetList=new DatasetList("https://youtu.be/RBUbja6V9Aw");
        arrayList.add(datasetList);
        DatasetList datasetList1=new DatasetList("https://youtu.be/RBUbja6V9Aw");
        arrayList.add(datasetList1);
        DatasetList datasetList2=new DatasetList("https://youtu.be/RBUbja6V9Aw");
        arrayList.add(datasetList2);
        DatasetList datasetList3=new DatasetList("https://youtu.be/RBUbja6V9Aw");
        arrayList.add(datasetList3);
        YoutubeAdapter youtubeAdapter=new YoutubeAdapter(arrayList,getActivity());
        recyclerViewt.setAdapter(youtubeAdapter);


        return view;
    }
}