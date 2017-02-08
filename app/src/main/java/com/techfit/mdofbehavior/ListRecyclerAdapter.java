package com.techfit.mdofbehavior;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techfit on 2017/2/8.
 */

public class ListRecyclerAdapter extends RecyclerView.Adapter<ListRecyclerAdapter.DefineViewHolder>{


    private List<String> list = new ArrayList<String>();
    private LayoutInflater inflater;

    public ListRecyclerAdapter(List<String> list) {
        this.list.addAll(list);
        inflater = (LayoutInflater) ApplicationBehavior.getApp().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public DefineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_rv_item, parent,false);
        DefineViewHolder viewHolder = new DefineViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DefineViewHolder holder, int position) {
        holder.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class DefineViewHolder extends RecyclerView.ViewHolder {

        TextView itemTv;

        public DefineViewHolder(View itemView) {
            super(itemView);
            itemTv = (TextView) itemView.findViewById(R.id.item_tv);
        }

        public void setText(String text) {
            itemTv.setText(text);
        }
    }
}

