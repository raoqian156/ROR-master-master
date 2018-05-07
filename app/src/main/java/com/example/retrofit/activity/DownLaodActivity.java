package com.example.retrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.retrofit.R;
import com.example.retrofit.activity.adapter.DownAdapter;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.download.DownInfo;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.utils.MultipleDownLoadViewHelper;

import java.io.File;
import java.util.List;

/**
 * 多任務下載
 */
public class DownLaodActivity extends AppCompatActivity {
    List<DownInfo> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_laod);
        initResource();
        initWidget();
    }

    private static final String savePath = "/storage/sdcard0/Download" + File.separator;

    /*数据*/
    private void initResource() {
        MultipleDownLoadViewHelper helper = MultipleDownLoadViewHelper.instance();
        listData = helper.queryDownAll();
        File outFile = new File(savePath + "aaaa.mp4");
        DownInfo daowInfo = new DownInfo("http://www.29160047.com/zljj.mp4", outFile);
        helper.startDownTask(daowInfo);
        listData = helper.queryDownAll();
        /*第一次模拟服务器返回数据掺入到数据库中*/
//        if (listData.isEmpty()) {
//
////            for (int i = 0; i < 4; i++) {
////                File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
////                        "blqx" + ".mp4");
////                DownInfo apkApi = new DownInfo("http://www.29160047.com/blqx.apk");
////                apkApi.setId(i);
////                apkApi.setUpdateProgress(true);
////                apkApi.setSavePath(outputFile.getAbsolutePath());
////                dbUtil.save(apkApi);
////            }
////            listData = MultipleDownLoadViewHelper.instance().queryDownAll();
//        }
    }

    int i = 0;

    public void addItem(View view) {
        i++;
        MultipleDownLoadViewHelper helper = MultipleDownLoadViewHelper.instance();
        File outFile = new File(savePath + "abc" + i + ".mp4");
        DownInfo daowInfo = new DownInfo("http://www.29160047.com/blqx.apk", outFile);
        helper.startDownTask(daowInfo);
        listData = helper.queryDownAll();
        adapter.clear();
        adapter.addAll(listData);
    }

    DownAdapter adapter;

    /*加载控件*/
    private void initWidget() {
        EasyRecyclerView recyclerView = (EasyRecyclerView) findViewById(R.id.rv);
        adapter = new DownAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.addAll(listData);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*记录退出时下载任务的状态-复原用*/
        MultipleDownLoadViewHelper.instance().saveWhenDestroy(listData);
    }
}
