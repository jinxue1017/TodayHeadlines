package lianjinxue201762.bawei.com.todayheadline;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 创建者： 廉锦雪
 * 创建时间:2017/6/1219:46
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button login=(Button)findViewById(R.id.login_denglu);
        Button zhuce=(Button)findViewById(R.id.login_zhuce);
        ImageView phone=(ImageView)findViewById(R.id.image_phone);
        ImageView weixin=(ImageView) findViewById(R.id.image_weixin);
        ImageView renren=(ImageView) findViewById(R.id.image_renren);
        login.setOnClickListener(this);
        zhuce.setOnClickListener(this);
        phone.setOnClickListener(this);
        weixin.setOnClickListener(this);
        renren.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_denglu:
                Intent in=new Intent(this,Phonelogin.class);
                startActivity(in);
                break;

            case R.id.login_zhuce:
                Intent in2=new Intent(this,ZhuceActivity.class);
                startActivity(in2);
                break;

            case R.id.image_phone:
                break;
            case R.id.image_weixin:
                break;
            case R.id.image_renren:
                break;

        }
    }
}

