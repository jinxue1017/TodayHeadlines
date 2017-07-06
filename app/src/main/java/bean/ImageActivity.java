package bean;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import lianjinxue201762.bawei.com.todayheadline.R;
import lianjinxue201762.bawei.com.todayheadline.TwoActivity;

/**
 * 创建者： 廉锦雪
 * 创建时间:2017/6/714:50
 */

public class ImageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageactvity);
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent in=new Intent(ImageActivity.this,TwoActivity.class);
                startActivity(in);
            }
        },2000);

    }
}
