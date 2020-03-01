package com.xiangxue.launchmode.task;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import com.xiangxue.launchmode.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskData {

    private static TaskData sInstance;

    /**
     * key -> Task id, value -> History
     */
    private Map<Integer, Map<Integer, String>> mData = new HashMap<>();

    private TaskData() {
    }

    public static TaskData getInstance() {
        if (sInstance == null) {
            sInstance = new TaskData();
        }

        return sInstance;
    }

    // Function
    public void add(BaseActivity activity) {
        int taskId = getCurrentTaskId(activity);
        int histId = activity.getId();
        Map<Integer, String> histIdList = mData.get(taskId);
        if (histIdList == null) {
            histIdList = new HashMap<>();
        }
        histIdList.put(histId, activity.getLocalClassName());
        mData.put(taskId, histIdList);
    }

    public void remove(BaseActivity activity) {
        int id = activity.getId();

        for (Map.Entry<Integer, Map<Integer, String>> kv : mData.entrySet()) {
            int taskId = kv.getKey();
            Map<Integer, String> histIdList = kv.getValue();
            histIdList.remove(Integer.valueOf(id));
            mData.put(taskId, histIdList);
        }
    }

    public Map<Integer, Map<Integer, String>> getData() {
        return mData;
    }

    // Internal
    private int getCurrentTaskId(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
        List<ActivityManager.AppTask> appTaskList = activityManager.getAppTasks();
        ActivityManager.RecentTaskInfo taskInfo = appTaskList.get(0).getTaskInfo();

        return taskInfo.persistentId;
    }
}
