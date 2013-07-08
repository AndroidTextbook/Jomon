package org.android.textbook.lesson4.httpclientsample;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RssItemAdaper extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    List<RssItem> mItems;

    static class ViewHolder {
        TextView title;
    }

    public RssItemAdaper(Context context, int textViewResourceId,
            List<RssItem> items) {
        super();

        mLayoutInflater = LayoutInflater.from(context);
        mItems = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.text_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(mItems.get(position).getTitle());
        return convertView;
    }
    
    @Override
    public int getCount() {
        if(mItems == null ){
            return 0;
        }
        return mItems.size();
    }

    @Override
    public RssItem getItem(int position) {
        
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
