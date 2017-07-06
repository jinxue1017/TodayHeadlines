package lianjinxue201762.bawei.com.todayheadline;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * 创建者： 廉锦雪
 * 创建时间:2017/6/1221:08
 */

public class Phonelogin extends AppCompatActivity implements View.OnClickListener {

    private EditText nameedit;
    private EditText passedit;
    private Button denglu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.phonelogin);
        nameedit = (EditText) findViewById(R.id.et_username);
        passedit = (EditText) findViewById(R.id.et_password);
        denglu = (Button)findViewById(R.id.btn_login_login);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login_login:
                String username = nameedit.getText().toString().trim();
                String password = passedit.getText().toString().trim();
                break;
            case R.id.txt_login_regist:
                Intent intent = new Intent(this, ZhuceActivity.class);
                startActivity(intent);
                break;
            case R.id.txt_login_forget_password:

                break;
        }
    }
}
