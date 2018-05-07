package com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener;

/**
 * 下载过程中的回调处理
 * Created by WZG on 2016/10/20.
 */
public interface HttpDownOnNextListener<T> {
    /**
     * 成功后回调方法
     * @param t
     */
     void onNext(T t);

    /**
     * 开始下载
     */
     void onStart();

    /**
     * 完成下载
     */
     void onComplete();



    /**
     * 下载进度
     * @param readLength
     * @param countLength
     */
     void updateProgress(long readLength, long countLength);

    /**
     * 失败或者错误方法
     * 主动调用，更加灵活
     * @param e
     */
       void onError(Throwable e);

    /**
     * 暂停下载
     */
     void onPuase();

    /**
     * 停止下载销毁
     */
     void onStop();
}
