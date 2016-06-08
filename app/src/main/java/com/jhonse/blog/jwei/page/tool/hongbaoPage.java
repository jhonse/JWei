package com.jhonse.blog.jwei.page.tool;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.jhonse.blog.jwei.page.common.Page;
import com.jhonse.blog.jwei.service.JWeiAccessibilityService;

import java.util.List;

public class hongbaoPage extends Page {

    private static JWeiAccessibilityService jws;
    private static PowerManager.WakeLock wakeLock = null;

    /**
     * 处理通知栏消息
     * @param event
     */
    public static void notification_os(AccessibilityEvent event){
        List<CharSequence> texts = event.getText();
        if (!texts.isEmpty()) {
            for (CharSequence text : texts) {
                String content = text.toString();
                Log.i("demo", "text:"+content);
                if (content.contains("[微信红包]")) {
                    //模拟打开通知栏消息
                    if (event.getParcelableData() != null
                            &&
                            event.getParcelableData() instanceof Notification) {
                        Notification notification = (Notification) event.getParcelableData();
                        PendingIntent pendingIntent = notification.contentIntent;
                        try {
                            pendingIntent.send();
                        } catch (PendingIntent.CanceledException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * 窗口改变处理
     * @param event
     */
    public static void window_status_os(AccessibilityEvent event,JWeiAccessibilityService jwss){
        String className = event.getClassName().toString();
        Log.i("demo", "classname:"+className);
        if (className.equals("com.tencent.mm.ui.LauncherUI")) {
            jws = jwss;
            //查找红包
            findPacket();
        } else if (className.equals("com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyReceiveUI")) {
            jws = jwss;
            //查看红包
            openPacket();
        } else if (className.equals("com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyDetailUI")) {
            jws = jwss;
            //红包详情
            retunHome();
        }
    }

    /**
     * 查找红包
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private static void findPacket(){
        AccessibilityNodeInfo rootNode = jws.getRootInActiveWindow();
        if(rootNode == null){
            return;
        }
        List<AccessibilityNodeInfo> listNodes = rootNode
                .findAccessibilityNodeInfosByText("领取红包");
        for(int i=listNodes.size() -1 ;i>=0;i--){
            AccessibilityNodeInfo node_parent = listNodes.get(i).getParent();
            if(node_parent != null && node_parent.isClickable() && !node_parent.findAccessibilityNodeInfosByText("微信红包").isEmpty()){
                node_parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                break;
            }
        }
    }

    /**
     * 打开红包
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    private static void openPacket(){
        AccessibilityNodeInfo rootNode = jws.getRootInActiveWindow();
        if(rootNode == null){
            return;
        }
        openPacketList(rootNode);
    }

    /**
     * 打开红包
     * @param info
     */
    private static void openPacketList(AccessibilityNodeInfo info){
        if (info.getChildCount() == 0) {
            if(info.getClassName().equals("android.widget.Button") && info.isClickable()){
                info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }else {
            for (int i = info.getChildCount() - 1; i >= 0; i--) {
                if (info.getChild(i) != null) {
                    openPacketList(info.getChild(i));
                }
            }
        }
    }

    /**
     * 返回主界面事件
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private static void retunHome(){
        if(jws != null){
            jws.performGlobalAction(JWeiAccessibilityService.GLOBAL_ACTION_HOME);
        }
    }

    /**
     * 返回事件
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private static void returnBack(){
        if(jws != null){
            jws.performGlobalAction(JWeiAccessibilityService.GLOBAL_ACTION_BACK);
        }
    }

}
