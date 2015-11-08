package com.scionoftech.recycleviewwithtabs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

/**
 * Created by kshediv on 26/10/15.
 */
public class Adapter extends RecyclerView
        .Adapter<Adapter
        .DataObjectHolder> {
    private JSONArray mDataset;
    private static MyClickListener myClickListener;
    private JSONArray item;
    ;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView txt;

        public DataObjectHolder(View itemView) {
            super(itemView);

            txt = (TextView) itemView.findViewById(R.id.txt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getLayoutPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public Adapter(Context c, JSONArray myDataset) {
        mDataset = myDataset;
        item = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.txt, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        try {
            holder.txt.setText(Html.fromHtml(mDataset.get(position).toString()));
            //animate items in recycle view
            com.scionoftech.recycleviewwithtabs.util.AnimationUtils.StartAnimation(holder);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void addItem(JSONObject dataObj, int index) {
        mDataset.put(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        // mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.length();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mDataset = new JSONArray();
        if (charText.length() == 0) {
            mDataset = item;
        } else {
            for (int i = 0; i < item.length(); i++) {
                try {
                    String wp = item.get(i).toString();
                    if (wp.toLowerCase(Locale.getDefault()).replaceAll("\\s", "").contains(charText)) {
                        String rep = wp.toLowerCase(Locale.getDefault()).replace(charText, "<b><font color=#2825A6>" + charText + "</font></b>");
                        mDataset.put(rep);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        notifyDataSetChanged();
    }

}
