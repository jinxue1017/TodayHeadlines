package lianjinxue201762.bawei.com.todayheadline;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import bean.BaseActivitys;
import bean.HttpUtils;
import bean.ImageActivity;

public class MainActivity extends BaseActivitys {
private List<String> list=new ArrayList<>();
    private Banner banner;
    private SharedPreferences.Editor edit;
    private boolean aBoolean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inData();
        SharedPreferences sharedPreferences=getSharedPreferences("User",MODE_PRIVATE);
        edit = sharedPreferences.edit();
        aBoolean = sharedPreferences.getBoolean("flag", false);
         if (aBoolean){
             Intent in=new Intent(MainActivity.this, ImageActivity.class);
             startActivity(in);
             finish();
        }


    }


       private void inData() {
         banner= (Banner) findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideimageLoader());
        //设置图片资源
        new Thread(){
            private String urlConnect2;
            @Override
            public void run() {
                //http://zkread.com/htnewsroom/articles/tops
                super.run();
                String string="http://zkread.com/htnewsroom/articles/tops";
                urlConnect2 = HttpUtils.getUrlConnect(string);
                banner.post(

                        new Runnable() {
                    @Override
                    public void run() {
                        Gson gson=new Gson();
                        Data datalbt = gson.fromJson(urlConnect2, Data.class);
                        List<Data.DataBean> data = datalbt.getData();
                        for (Data.DataBean da: data) {
                          list.add(da.getFirst_image());

                        }
                        edit.putBoolean("flag",true);
                        edit.commit();
                        //设置自动轮播，默认为true
                        banner.isAutoPlay(true);
                        //设置指示器位置（当banner模式中有指示器时）
                        banner.setIndicatorGravity(BannerConfig.CENTER);

                        //设置图片集合
                        banner.setImages(list);
                        //banner设置方法全部调用完毕时最后调用
                        banner.start();



                    }
                });

            }
        }.start();
    }

    class GlideimageLoader extends ImageLoader{
        /**
         注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         */
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

}
