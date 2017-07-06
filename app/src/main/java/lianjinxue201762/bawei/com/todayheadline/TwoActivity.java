package lianjinxue201762.bawei.com.todayheadline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import FrgmentBean.Login;
import FrgmentBean.Shipin;
import FrgmentBean.Shouye;
import FrgmentBean.Toutiao;
import bean.BaseActivitys;
import bean.BaseApplication;

/**
 * 创建者： 廉锦雪
 * 创建时间:2017/6/714:19
 */

public class TwoActivity extends BaseActivitys implements View.OnClickListener {
    SlidingMenu localSlidingMenu;
    private boolean isNight = false;
    private ImageView images;
    private ImageView imagesqq;
    private View include;
    private TextView nikeName;
    private ImageView touxiang;
    private LinearLayout layout;
    private Tencent mTencent;
    private UserInfo mUserInfo;
    private BaseUiListener mIUiListener;
    private static final String TAG = "MainActivity";
    private static final String APP_ID = "1106162590";//官方获取的APPID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two);
        mTencent = Tencent.createInstance(APP_ID,TwoActivity.this.getApplicationContext());
        RadioGroup radioGroup= (RadioGroup) findViewById(R.id.rg);
        RadioButton but1= (RadioButton) findViewById(R.id.shouye);
        RadioButton but2= (RadioButton) findViewById(R.id.shipin);
        RadioButton but3= (RadioButton) findViewById(R.id.toutiao);
        RadioButton but4= (RadioButton) findViewById(R.id.login);
        but1.setOnClickListener(this);
        but2.setOnClickListener(this);
        but3.setOnClickListener(this);
        but4.setOnClickListener(this);
        addFrgment(new Shouye());
        addSlidingMeau();

    }


   private void addSlidingMeau() {
        localSlidingMenu = new SlidingMenu(this);
        //设置左侧
        localSlidingMenu.setMode(SlidingMenu.LEFT);
        localSlidingMenu.setTouchModeAbove(SlidingMenu.SLIDING_WINDOW);
        //设置宽度
        localSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);//
        localSlidingMenu.setShadowDrawable(R.drawable.shadow);
        localSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);//
        localSlidingMenu.setFadeDegree(0.35F);
        localSlidingMenu.attachToActivity(this, SlidingMenu.RIGHT);
        localSlidingMenu.setMenu(R.layout.left_drawer_fragment);
       include= localSlidingMenu.findViewById(R.id.include);
       nikeName=(TextView) localSlidingMenu.findViewById(R.id.nikeName);
       touxiang=(ImageView) localSlidingMenu.findViewById(R.id.touxiang);
       layout=(LinearLayout) localSlidingMenu.findViewById(R.id.logout_layout);
       TextView text=(TextView)localSlidingMenu.findViewById(R.id.denglu);
        images = (ImageView) localSlidingMenu.findViewById(R.id.img_qiehuan);
       imagesqq = (ImageView) localSlidingMenu.findViewById(R.id.qzone_btn);
       //qq登录
       imagesqq.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              layout.setVisibility(View.GONE);
               mIUiListener = new BaseUiListener();
               //all表示获取所有权限
               //Tencent类是SDK的主要实现类，开发者可通过Tencent类访问腾讯开放的OpenAPI。
               // 其中APP_ID是分配给第三方应用的appid，类型为String。
               mTencent.login(TwoActivity.this,"all", mIUiListener);

           }
       });
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(TwoActivity.this, LoginActivity.class);
                startActivity(in);

            }
        });
        //日夜间模式切换
        Button button=(Button) localSlidingMenu.findViewById(R.id.qiehuan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isNight = !isNight;
                BaseApplication.getInstance().setNight(isNight);
                switchMode(isNight);
                View views = getWindow().getDecorView();
                changeTextColor((ViewGroup) views);
                changeImage();

            }
        });


        localSlidingMenu.setSecondaryShadowDrawable(R.drawable.shadowright);
        localSlidingMenu.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
            public void onOpened() {

            }
        });
        localSlidingMenu.setOnClosedListener(new SlidingMenu.OnClosedListener() {

            @Override
            public void onClosed() {

            }
        });

    }
    private void changeImage() {
        images.setImageResource(BaseApplication.getInstance().getResId(R.mipmap.default_user_leftdrawer));
        //llParent.setBackgroundColor(BaseApplication.getInstance().isNight()?Color.BLACK:Color.WHITE);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shouye:
              addFrgment(new Shouye());
                break;
            case R.id.shipin:
                addFrgment(new Shipin());
                break;
            case R.id.toutiao:
                addFrgment(new Toutiao());
                break;
            case R.id.login:
                addFrgment(new Login());
                break;
        }

    }
    private void addFrgment(Fragment f){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fram,f);
        transaction.commit();

    }
    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
            Toast.makeText(TwoActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken,expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(),qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG,"登录成功"+response.toString());

                        JSONObject obj = (JSONObject) response;
                        try {
                            String figureurl_1 = obj.getString("figureurl_qq_1");

                            String nickname = obj.getString("nickname");

                            include.setVisibility(View.VISIBLE);
                            nikeName.setText(nickname);
                            Glide.with(TwoActivity.this).load(figureurl_1).into(touxiang);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG,"登录失败"+uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG,"登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(TwoActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(TwoActivity.this, "授权取消", Toast.LENGTH_SHORT).show();

        }


    }
    // 在视图可见可交互的时候才能改变视图
    @Override
    protected void onResume() {
        super.onResume();
        isNight=BaseApplication.getInstance().isNight();
        if (isNight){
            switchMode(isNight);
        }
        changeTextColor((ViewGroup) getWindow().getDecorView());
      changeImage();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == Constants.REQUEST_LOGIN){
            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
