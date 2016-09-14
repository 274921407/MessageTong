package com.jixin.studyproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jixin.studyproject.R;
import com.jixin.studyproject.bean.MessageTongBean;
import com.jixin.studyproject.tools.LogTools;

import java.util.List;

/**
 * Created by jixin on 2016/9/13
 */
public class MessageTongListAdapter extends BaseAdapter {

    private List<MessageTongBean> mList;
    private Context context;
    private LayoutInflater layoutInflater;


    public MessageTongListAdapter(List<MessageTongBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
        this.layoutInflater=LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MessageTongItem item=null;
        if (convertView==null)
        {
            convertView=layoutInflater.inflate(R.layout.listitem_messagetong,null);
            item=new MessageTongItem();
            item.msg_title= (TextView) convertView.findViewById(R.id.msg_title);
            item.sel_img= (ImageView) convertView.findViewById(R.id.sel_img);
            convertView.setTag(item);
        }
        else
        {
            item= (MessageTongItem) convertView.getTag();
        }
        item.msg_title.setText(mList.get(position).getMsgTongName());
        if (mList.get(position).getIsSelected())
        {

            item.sel_img.setBackgroundResource(R.drawable.rc);
        }else
        {

            item.sel_img.setBackgroundResource(R.drawable.rb);
        }
        return convertView;
    }




    public static class MessageTongItem
    {
        private TextView msg_title;
        private ImageView sel_img;
    }




}
