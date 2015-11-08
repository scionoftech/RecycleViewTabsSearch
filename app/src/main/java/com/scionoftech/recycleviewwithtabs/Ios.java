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
public class Ios extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public Ios() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_ios, container, false);

        //get recycleview
        mRecyclerView = (RecyclerView) view.findViewById(R.id.ios);
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
        ((MainFragment)getParentFragment()).setTabIos(getTag());
        return view;
    }


    //get search query from tabs fragment
        public void Search(String s) {

            ((Adapter) mAdapter).filter(s);
        }

    //sample data
    public JSONArray Getdata() {
        JSONArray android = new JSONArray();

        android.put("iPhone");
        android.put("iPhone 3G");
        android.put("iPhone 3GS");
        android.put("iPhone 4G");
        android.put("iPhone 4S");
        android.put("iPhone 5G");
        android.put("iPhone 5S");
        android.put("iPhone 5C");
        android.put("iPhone 6");
        android.put("iPhone 6S");

        return android;
    }
}
