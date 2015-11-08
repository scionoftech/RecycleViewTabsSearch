package com.scionoftech.recycleviewwithtabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scionoftech.recycleviewwithtabs.util.HorizontalDividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;


/**
 * A simple {@link Fragment} subclass.
 */
public class Android extends Fragment {


    public Android() {
        // Required empty public constructor
    }

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_android, container, false);


        mRecyclerView = (RecyclerView) view.findViewById(R.id.android);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.ItemDecoration itemDecoration =
                new HorizontalDividerItemDecoration.Builder(getActivity()).margin(30, 30).build();
        mRecyclerView.addItemDecoration(itemDecoration);

        mAdapter = new Adapter(getActivity(), Getdata());
        mRecyclerView.setAdapter(mAdapter);

        ((Adapter) mAdapter).setOnItemClickListener(new Adapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                try {
                    Toast.makeText(getActivity(), Getdata().get(position).toString(), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        //send fragment tag to tabs fragment
        ((MainFragment)getParentFragment()).setTabAndroid(getTag());

        return view;
    }

    //get search query from tabs fragment
    public void Search(String s) {

        ((Adapter) mAdapter).filter(s);
    }

    //sample data
    public JSONArray Getdata() {
        JSONArray android = new JSONArray();

        android.put("Nexus 5");
        android.put("Nexus 5x");
        android.put("Nexus 6");
        android.put("Nexus 6p");
        android.put("Moto x");
        android.put("Moto G");
        android.put("Moto E");
        android.put("HTC 816");
        android.put("Galaxy s3");
        android.put("Galaxy s4");
        android.put("Galaxy s5");
        android.put("Galaxy s6");
        android.put("Sony Z");
        android.put("Sony C3");
        android.put("Micromax Unite 2");
        android.put("Micromax Canvas 2");
        android.put("LG G pro");
        android.put("Lava iris");

        return android;
    }
}
