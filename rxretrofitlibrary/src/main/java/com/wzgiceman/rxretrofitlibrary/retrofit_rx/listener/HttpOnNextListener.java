package com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener;

/**
 * 成功回调处理
 * Created by WZG on 2016/7/16.
 */
public interface HttpOnNextListener<T> {
    /**
     * 成功后回调方法
     *
     * @param t
     */
    void onNext(T t);

    /**
     * 緩存回調結果
     *
     * @param string
     */
    void onCacheNext(String string);

    /**
     * 成功后的ober返回，扩展链接式调用
     *
     * @param observable
     */

    /**
     * 失败或者错误方法
     * 主动调用，更加灵活
     *
     * @param e
     */
    void onError(Throwable e);

    /**
     * 取消回調
     */
    void onCancel();
}
