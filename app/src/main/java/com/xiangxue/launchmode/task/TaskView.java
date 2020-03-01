package com.xiangxue.launchmode.task;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class TaskView extends LinearLayout {

    private Context mContext;

    public TaskView(Context context) {
        this(context, null);
    }

    public TaskView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 20, 20, 20);
        setLayoutParams(layoutParams);
    }

    // Function
    public void set(int taskId, Map<Integer, String> histIdList) {
        //
        TextView taskIdTv = generate(String.format("Task id: %d", taskId));
        addView(taskIdTv);

        if (histIdList != null) {
            for(Map.Entry<Integer, String> entry : histIdList.entrySet()){
                int histIdTv = entry.getKey();
                String activityName = entry.getValue().substring(entry.getValue().lastIndexOf(".") + 1);


                TextView hTv = generate(histIdTv + ", " + activityName);
                addView(hTv );
            }
        }
    }

    // Internal
    private TextView generate(String str) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tv = new TextView(mContext);
        tv.setText(str);
        tv.setLayoutParams(layoutParams);

        return tv;
    }
}
