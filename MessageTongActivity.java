package com.jixin.studyproject.ui;

import android.app.Activity;
import android.database.Cursor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.jixin.studyproject.R;
import com.jixin.studyproject.adapter.AsyMessageTongAdaper;
import com.jixin.studyproject.adapter.MessageTongListAdapter;
import com.jixin.studyproject.bean.MessageTongBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jixin on 2016/9/13
 */
public class MessageTongActivity extends Activity {


    private ListView msgtong_list;
    private Button save_bt;

    private List<MessageTongBean> mList;
    private MessageTongListAdapter mAdapter;
    private AsyMessageTongAdaper mmAdapter;
    private RingtoneManager rm;

    private Uri defaultUri;
    private Ringtone defaultRingtong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagetong);
        msgtong_list = (ListView) findViewById(R.id.msgtong_list);
        save_bt = (Button) findViewById(R.id.save_bt);
//      initDate();
        newInitData();
        msgtong_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < mList.size(); i++) {
                    mList.get(i).setIsSelected(false);
                }
                mList.get(position).setIsSelected(true);
//              mAdapter.notifyDataSetChanged();
                mmAdapter.notifyDataSetChanged();
                if (position == 0) {
                    if (defaultRingtong.isPlaying()) {
                        defaultRingtong.stop();
                    }
                    defaultRingtong.play();
                } else {
                    Ringtone rt = rm.getRingtone(position);
                    if (null != rt) {
                        if (rt.isPlaying()) {
                            rt.stop();
                        }
                        rt.play();
                    }
                }
            }
        });

    }


    private void initDate() {

        mList = new ArrayList<>();
        rm = new RingtoneManager(getApplicationContext());
        rm.setType(RingtoneManager.TYPE_NOTIFICATION);
        defaultUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        defaultRingtong = RingtoneManager.getRingtone(this, defaultUri);
        MessageTongBean defaultRtb = new MessageTongBean();
        //defaultRtb.setMsgTongName(defaultRingtong.getTitle(this));
        defaultRtb.setMsgTongName("跟随系统");
        defaultRtb.setIsSelected(true);
        mList.add(defaultRtb);
        Cursor cs = rm.getCursor();
        int count = cs.getCount();
        for (int i = 0; i < count; i++) {
            if (i > 9) {
                break;
            }
            Ringtone rt = rm.getRingtone(i);
            MessageTongBean tmp = new MessageTongBean();
            tmp.setMsgTongName(rt.getTitle(this));
            tmp.setIsSelected(false);
            mList.add(tmp);
        }


        mAdapter = new MessageTongListAdapter(mList, this);
        msgtong_list.setAdapter(mAdapter);
    }


    private void newInitData() {
        mList = new ArrayList<>();
        rm = new RingtoneManager(getApplicationContext());
        rm.setType(RingtoneManager.TYPE_NOTIFICATION);
        defaultUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        defaultRingtong = RingtoneManager.getRingtone(this, defaultUri);
        MessageTongBean defaultRtb = new MessageTongBean();
        //defaultRtb.setMsgTongName(defaultRingtong.getTitle(this));
        defaultRtb.setMsgTongName("跟随系统");
        defaultRtb.setIsSelected(true);
        mList.add(defaultRtb);
        Cursor cs = rm.getCursor();
        int count = cs.getCount();
        for (int i = 0; i < count; i++) {
            MessageTongBean tmp = new MessageTongBean();
            tmp.setIsSelected(false);
            mList.add(tmp);
        }

        mmAdapter = new AsyMessageTongAdaper(mList, this, msgtong_list, rm);
        msgtong_list.setAdapter(mmAdapter);
    }
}
