package FrgmentBean;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import lianjinxue201762.bawei.com.todayheadline.GridviewPinDao;
import lianjinxue201762.bawei.com.todayheadline.R;


/**
 * 创建者： 廉锦雪
 * 创建时间:2017/6/715:30
 */

public class Shouye extends Fragment {
    private List<Fragment>list=new ArrayList<>();
   // private List<Titlebean.ResultBean.DateBean>list_Title=new ArrayList<>();
    private  List<String>list_Title=new ArrayList<>();
    private TabLayout tab;
    private ViewPager viewPager;
    private ImageView imageleft;
    private ImageView imageright;
    SlidingMenu localSlidingMenu;
    private TextView login;
    //http://result.eolinker.com/gfGTLlHc049c6b450500b16971f52bd8e83f6b2fed305ab?uri=news
    private String url="http://result.eolinker.com/gfGTLlHc049c6b450500b16971f52bd8e83f6b2fed305ab?uri=news";
    private String uri;
    private ImageView imageViewjia;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shouye1, container, false);
       // x.view().inject(getActivity());
       //initData(view);
        initview(view);
        return view;

    }

    private void initview(View view) {
        tab = (TabLayout) view.findViewById(R.id.tab);
        viewPager = (ViewPager) view.findViewById(R.id.vp);
        imageleft = (ImageView) view.findViewById(R.id.imageleft);
        imageright = (ImageView) view.findViewById(R.id.imageright);
        login = (TextView) view.findViewById(R.id.denglu);
        imageViewjia = (ImageView) view.findViewById(R.id.tabimages2);
        imageViewjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in=new Intent(getActivity(), GridviewPinDao.class);
                startActivity(in);
                Toast.makeText(getActivity(),"点击",Toast.LENGTH_LONG).show();
            }
        });

        list.add(new Tuijian());
        list.add(new Shehui());
        list.add(new Car());
        list.add(new Guoji());
        list.add(new Junshi());
        list.add(new Keji());
        list.add(new Shipin());
        list.add(new Sports());
        list.add(new Tupian());
        list.add(new Yule());
        list.add(new Caijing());
        list.add(new Dingyue());
        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_Title.add("推荐");
        list_Title.add("社会");
        list_Title.add("汽车");
        list_Title.add("国际");
        list_Title.add("军事");
        list_Title.add("科技");
        list_Title.add("食品");
        list_Title.add("体育");
        list_Title.add("图片");
        list_Title.add("娱乐");
        list_Title.add("财经");
        list_Title.add("订阅");


      /*  for (int i = 0; i < list_Title.size(); i++) {
           // uri = list_Title.get(i).getUri();
           tab.addTab(tab.newTab().setText(list_Title.get(i).getTitle()));

            Titlefrgment titlefrgment=new Titlefrgment();
            list.add(titlefrgment);
            Log.d("list----------", "initview: "+list);


        }*/
        //设置TabLayout的模式
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        //tab的字体选择器,默认黑色,选择时红色
        tab.setTabTextColors(Color.BLACK, Color.RED);
        for (int i = 0; i < 12; i++){
            tab.addTab(tab.newTab().setText(list_Title.get(i)));
            Titlefrgment titlefrgment=new Titlefrgment();
            list.add(titlefrgment);
            Log.d("list-----------", "initview: "+list);
        }

        //为TabLayout添加tab名称
        FindAdapter fadapter=new FindAdapter(getActivity().getSupportFragmentManager());
        //viewpager加载adapter
        viewPager.setAdapter(fadapter);
        //TabLayout加载viewpager
        tab.setupWithViewPager(viewPager);



    }

/*
  private void initData(View view) {
           tab = (TabLayout) view.findViewById(R.id.tab);
           viewPager = (ViewPager) view.findViewById(R.id.vp);
           imageleft = (ImageView) view.findViewById(R.id.imageleft);
           imageright = (ImageView) view.findViewById(R.id.imageright);
           login = (TextView) view.findViewById(R.id.denglu);
           RequestParams params=new RequestParams();
           params.setUri(url);
           x.http().get(params, new Callback.CommonCallback<String>() {
              @Override
              public void onSuccess(String result) {
                  if (result!=null){
                      Gson gson=new Gson();
                      Titlebean titlebean = gson.fromJson(result, Titlebean.class);
                      List<Titlebean.ResultBean.DateBean> date = titlebean.getResult().getDate();

                      Log.d("lllllllll", "onSuccess: "+list_Title);

                  }

              }

              @Override
              public void onError(Throwable ex, boolean isOnCallback) {

              }

              @Override
              public void onCancelled(CancelledException cex) {

              }

              @Override
              public void onFinished() {

              }
          });
       }
*/

            class FindAdapter extends FragmentPagerAdapter {


                public FindAdapter(FragmentManager fm) {
                    super(fm);
                }

                @Override
                public Fragment getItem(int position) {
                    return list.get(position);
                }

                @Override
                public int getCount() {
                    return list.size();
                }
                //此方法用来显示tab上的名字
                @Override
                public CharSequence getPageTitle(int position) {

                    return list_Title.get(position%list_Title.size());
                }
            }
}

