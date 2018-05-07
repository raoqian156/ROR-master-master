package com.wzgiceman.rxretrofitlibrary.retrofit_rx.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import com.wzgiceman.rxretrofitlibrary.retrofit_rx.download.DownInfo;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.download.HttpDownManager;

import java.util.ArrayList;
import java.util.List;

/**
 * You SHOULD call saveWhenDestroy when destroy View for save downInfo if you need
 */

public class MultipleDownLoadViewHelper {
    private static volatile MultipleDownLoadViewHelper multipleDownLoadViewHelper;
    DbDownUtil db;
    private HttpDownManager manager;

    private MultipleDownLoadViewHelper() {
        manager = HttpDownManager.getInstance();
        db = DbDownUtil.getInstance();
    }

    public static MultipleDownLoadViewHelper instance() {
        if (multipleDownLoadViewHelper == null) {
            synchronized (MultipleDownLoadViewHelper.class) {
                if (multipleDownLoadViewHelper == null) {
                    multipleDownLoadViewHelper = new MultipleDownLoadViewHelper();
                }
            }
        }
        return multipleDownLoadViewHelper;
    }

    public void startDownTask(@NonNull DownInfo daowInfo) {
        synchronized (MultipleDownLoadViewHelper.class) {
            List<DownInfo> data = queryDownAll();
            boolean isAdd = true;
            for (DownInfo item : data) {
                if (daowInfo.isSameDownloadUrl(item)) {
                    Log.e("Multiple...ViewHelper", "isSameDownloadUrl ");
                    isAdd = false;
                } else if (daowInfo.isSameSavePath(item)) {
                    isAdd = false;
                    Log.e("Multiple...ViewHelper", "isSameSavePath ");
                }
            }
            if (isAdd) {
                Log.e("Multiple...ViewHelper", System.currentTimeMillis() + "|" + daowInfo.getId());
                db.save(daowInfo);
            }
            saveWhenDestroy(queryDownAll());
            manager.startDown(daowInfo);
        }
    }


    public List<DownInfo> queryDownAll() {
        return db.queryDownAll() == null ? new ArrayList<DownInfo>() : db.queryDownAll();
    }

    public void saveWhenDestroy(List<DownInfo> data) {
        if (data != null && data.size() > 0) {
            for (DownInfo item : data) {
                db.update(item);
            }
        }
    }
}
