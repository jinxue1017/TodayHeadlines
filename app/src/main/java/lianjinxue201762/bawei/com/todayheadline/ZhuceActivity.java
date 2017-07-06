package lianjinxue201762.bawei.com.todayheadline;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * 创建者： 廉锦雪
 * 创建时间:2017/6/1221:10
 */

public class ZhuceActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "RegistActivity";
    private static final int FLAG = 0x122;
    private static int count = 60;
    private Button zhuce;
    private Button but;
    private Timer timer;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            if (what == FLAG) {
                int current = (int) msg.obj;
                if (current > 0) {
                    but.setText("(" + current + ")秒钟之后重新获取");
                } else {
                    but.setEnabled(true);
                    but.setText("重新获取");
                    if (timer != null) {
                        timer.cancel();
                    }
                    count = 60;
                }
            }
        }
    };
    private EditText edutname;
    private EditText edutpass;
    private EditText edutpass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuceyemian);
        edutname = (EditText) findViewById(R.id.et_username);
        edutpass = (EditText) findViewById(R.id.et_password);
        edutpass2 = (EditText) findViewById(R.id.et_password_confirm);
        but = (Button) findViewById(R.id.btn_get_code);
        zhuce = (Button) findViewById(R.id.btn_regist_regist);
        but.setOnClickListener(this);
        zhuce.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      switch (view.getId()){
          //获取验证码
          case R.id.btn_get_code:
             but.setEnabled(false);
              SMSSDK.setAskPermisionOnReadContact(true);
              String username = edutname.getText().toString().trim();
              EventHandler eh = new EventHandler() {

                  @Override
                  public void afterEvent(int event, int result, Object data) {

                      if (result == SMSSDK.RESULT_COMPLETE) {
                          Log.i(TAG, "afterEvent: 回调完成");
                          //回调完成
                          if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                              Log.i(TAG, "afterEvent: 提交验证码成功");
                              // 校验成功，可以把数据提交给服务器，做跳转

                              // 提交验证码成功
                          } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                              Log.i(TAG, "afterEvent: 获取验证码成功");
                              //获取验证码成功
                          } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                              Log.i(TAG, "afterEvent: 返回支持发送验证码的国家列表");
                              //返回支持发送验证码的国家列表
                          }
                      } else {
                          ((Throwable) data).printStackTrace();
                      }
                  }
              };

              SMSSDK.registerEventHandler(eh); //注册短信回调
              SMSSDK.getVerificationCode("86", username);
              timer = new Timer();
              timer.schedule(new TimerTask() {
                  @Override
                  public void run() {
                      count--;
                      Message msg = Message.obtain();
                      msg.what = FLAG;
                      msg.obj = count;
                      handler.sendMessage(msg);
                  }
              }, 0, 1000);
              break;
          //注册按钮
          case R.id.btn_regist_regist:
              String phone = edutname.getText().toString().trim();
              String code = edutpass.getText().toString().trim();
              // 短信验证码校验
              SMSSDK.submitVerificationCode("86", phone, code);
              break;
   }

    }
}
