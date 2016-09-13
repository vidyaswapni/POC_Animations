package com.rapidbizapps.chartanimations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by svuppala on 13-09-2016.
 */
public class ListExampleAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<DataList> dataList;
    private View view;

    public ListExampleAdapter(Context c,ArrayList<DataList> dataList) {
        mContext = c;
        this.dataList = dataList;
    }
    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            view = new View(mContext);

            view = inflater.inflate(R.layout.list_row_view, null);

        } else {

            view = convertView;
        }

        viewHolder.textView = (TextView) view.findViewById(R.id.tv_list_item);

        viewHolder.textView.setText(dataList.get(position).getData());

        return view;
    }
    static class ViewHolder{
        TextView textView;

    }
}
