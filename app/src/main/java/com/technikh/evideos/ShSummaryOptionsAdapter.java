package com.technikh.evideos;

/*
 * Copyright (c) 2019. Nikhil Dubbaka from TechNikh.com under GNU AFFERO GENERAL PUBLIC LICENSE
 * Copyright and license notices must be preserved.
 * When a modified version is used to provide a service over a network, the complete source code of the modified version must be made available.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.technikh.evideos.models.slideshow.ShQuestionOption;

import java.util.ArrayList;
import java.util.List;

public class ShSummaryOptionsAdapter extends ArrayAdapter<ShQuestionOption>{
    private List<ShQuestionOption> mlistData;
    private static final int RESOURCE = R.layout.row_sh_summary_option;
    private LayoutInflater inflater;
    private static String TAG = "ShSummaryOptionsAdapter";
    int selectedPosition = 0;

    //@SuppressWarnings("unchecked")
    public ShSummaryOptionsAdapter(Context context, List<ShQuestionOption> objects)
    {
        super(context, RESOURCE, objects);
        mlistData = objects;
        //inflater = LayoutInflater.from(context);
    }

    public void setData(List<ShQuestionOption> list) {
        mlistData.clear();
        mlistData.addAll(list);
    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount: "+mlistData.size());
        return mlistData.size();
    }

    @Nullable
    @Override
    public ShQuestionOption getItem(int position) {
        Log.d(TAG, "getItem: "+position);
        return mlistData.get(position);
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Log.d(TAG, "getView: ");
        OptionsViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_sh_summary_option, parent, false);
            holder = new OptionsViewHolder();
            holder.simpleCheckedTextView = convertView.findViewById(R.id.tv_option_message);
            convertView.setTag( holder );
        }else{
            holder = (OptionsViewHolder) convertView.getTag();
        }

        //CheckedTextView simpleCheckedTextView = (CheckedTextView) convertView.findViewById(R.id.tv_option_message);

        //RadioButton simpleCheckedTextView = (RadioButton) convertView.findViewById(R.id.tv_option_message);
        holder.simpleCheckedTextView.setText(getItem(position).getMessage());
        holder.simpleCheckedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "simpleCheckedTextView onClick: ");
                if (holder.simpleCheckedTextView.isChecked()) {
                    //simpleCheckedTextView.setCheckMarkDrawable(0);
                    holder.simpleCheckedTextView.setChecked(false);
                } else {
                    //simpleCheckedTextView.setCheckMarkDrawable(R.drawable.checked);
                    holder.simpleCheckedTextView.setChecked(true);
                }
            }
        });
        /*
        holder.simpleCheckedTextView.setChecked(position == selectedPosition);
        holder.simpleCheckedTextView.setTag(position);
        holder.simpleCheckedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPosition = (Integer)view.getTag();
                notifyDataSetChanged();
            }
        });*/

        return convertView;
    }

    static class OptionsViewHolder {
        CheckedTextView simpleCheckedTextView;
    }
}