package com.jhonse.blog.jwei.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import com.jhonse.blog.jwei.page.tool.hongbaoPage;

import java.util.List;

public class JWeiAccessibilityService extends AccessibilityService {

    String installPackge[] = {
            "com.tencent.mm"
    };

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        switch (eventType) {
            //监听通知栏消息
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:{
                hongbaoPage.notification_os(event);
                break;
            }
            //监听是否进入微信红包消息界面
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:{
                hongbaoPage.window_status_os(event,this);
                break;
            }
        }
    }

    @Override
    public void onInterrupt() {
        Toast.makeText(this, "中断辅助服务", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Toast.makeText(this, "连接辅助服务", Toast.LENGTH_SHORT).show();
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.packageNames = installPackge; //监听过滤的包名
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK; //监听哪些行为
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC; //反馈
        info.notificationTimeout = 100; //通知的时间
        setServiceInfo(info);
    }

}
