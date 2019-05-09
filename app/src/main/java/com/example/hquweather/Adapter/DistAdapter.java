package com.example.hquweather.Adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.hquweather.R;

import java.util.List;

public class DistAdapter implements SpinnerAdapter {
    List<String> mDistList=null;
    LayoutInflater mLayoutInflater=null;
    public DistAdapter(Context context, List<String> distList){
        mLayoutInflater=LayoutInflater.from(context);
        mDistList=distList;

    }
    @Override
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return getView(i,view,viewGroup);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return mDistList.size();
    }

    @Override
    public Object getItem(int i) {
        return mDistList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            viewHolder=new ViewHolder();
            view=mLayoutInflater.inflate(R.layout.sp_dist_item,null);
            viewHolder.textView=view.findViewById(R.id.tv_sp_dist_item);
            view.setTag(viewHolder);

        }else{
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.textView.setText(mDistList.get(i));
        return view;
    }

    @Override
    public int getItemViewType(int i) {
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
    private class ViewHolder{
        TextView textView;
    }
}