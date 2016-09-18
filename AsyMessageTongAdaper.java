package com.jixin.studyproject.adapter;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jixin.studyproject.R;
import com.jixin.studyproject.bean.MessageTongBean;

import java.util.List;

/**
 * Created by jixin on 2016/9/14
 */
public class AsyMessageTongAdaper extends BaseAdapter {

    private List<MessageTongBean> mList;
    private Context context;
    private LayoutInflater layoutInflater;

    private ListView msgtong_list;
    private RingtoneManager rm;

    public AsyMessageTongAdaper(List<MessageTongBean> mList, Context context, ListView msgtong_list, RingtoneManager
            rm) {
        this.mList = mList;
        this.context = context;
        this.msgtong_list = msgtong_list;
        this.rm = rm;
        this.layoutInflater = LayoutInflater.from(this.context);
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

        MessageTongItem item;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listitem_messagetong, null);
            item = new MessageTongItem();
            item.msg_title = (TextView) convertView.findViewById(R.id.msg_title);
            item.sel_img = (ImageView) convertView.findViewById(R.id.sel_img);
            convertView.setTag(item);
        } else {
            item = (MessageTongItem) convertView.getTag();
        }
        item.msg_title.setTag(position);
        String name = mList.get(position).getMsgTongName();

        if (null == name) {
            new GetRingtongName(position).execute(position);
        } else {
            item.msg_title.setText(mList.get(position).getMsgTongName());
        }


        if (mList.get(position).getIsSelected()) {

            item.sel_img.setBackgroundResource(R.drawable.rc);
        } else {

            item.sel_img.setBackgroundResource(R.drawable.rb);
        }
        return convertView;
    }


    public static class MessageTongItem {
        private TextView msg_title;
        private ImageView sel_img;
    }


    private class GetRingtongName extends AsyncTask<Integer, Integer, String> {

        private int pos;

        public GetRingtongName(int pos) {
            this.pos = pos;
        }

        @Override
        protected void onPostExecute(String s) {

            if (null != s) {
                mList.get(pos).setMsgTongName(s);
                TextView tx= (TextView) msgtong_list.findViewWithTag(pos);
                if (tx!=null)
                {
                    tx.setText(s);
                }


            }
        }

        @Override
        protected String doInBackground(Integer... params) {

            String t="";
            Ringtone rt = rm.getRingtone(pos);

            if (rt!=null)
            {
                t=rt.getTitle(context);
            }
            return t;
        }
    }
}
