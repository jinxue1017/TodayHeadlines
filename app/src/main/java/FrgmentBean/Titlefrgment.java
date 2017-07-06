package FrgmentBean;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import BaeaAdapter.XliAdapter;
import lianjinxue201762.bawei.com.todayheadline.R;
import lianjinxue201762.bawei.com.todayheadline.Webviews;
import me.maxwin.view.XListView;

import static lianjinxue201762.bawei.com.todayheadline.R.id.xListView;

/**
 * 创建者： 廉锦雪
 * 创建时间:2017/6/169:38
 */

public class Titlefrgment extends Fragment implements XListView.IXListViewListener{
   // private String url="http://result.eolinker.com/k2BaduF2a6caa275f395919a66ab1dfe4b584cc60685573?uri=gj";
   //http://v.juhe.cn/toutiao/index?type=top&key=2f092bd9ce76c0257052d6d3c93c11b4
    private String url="http://v.juhe.cn/toutiao/index?type=top&key=2f092bd9ce76c0257052d6d3c93c11b4";
    private XListView xlistview;
    private List<TitleAdapterbean.ResultBean.DataBean>list=new ArrayList<>();
    private SimpleDateFormat simpleDateFormat;
    public static final String KEY = "arg";
    private static Shehui shehui;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.titlefrgment, container, false);
        xlistview = (XListView) view.findViewById(xListView);
        xlistview.setPullRefreshEnable(true);
        xlistview.setPullLoadEnable(true);
        xlistview.setXListViewListener(this);
        xlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in=new Intent(getActivity(), Webviews.class);
                in.putExtra("url",list.get(i).getUrl());
                startActivity(in);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       getUrlData();

    }

    @Override
    public void onRefresh() {

        getUrlData();
        onstopload();


    }

    @Override
    public void onLoadMore() {
         getUrlData();
        onstopload();


    }
    public void onstopload(){
        xlistview.stopLoadMore();
        xlistview.stopRefresh();
          //设置日期显示格式
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("MM-dd-HH:mm:ss");
        //获取当前时间
        Date date=new Date(System.currentTimeMillis());
        String str = simpleDateFormat.format(date);
        //为xListView设置时间
        xlistview.setRefreshTime(str);
    }
    public static Fragment newInstance(ArrayList<String> str) {
        shehui = new Shehui();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(KEY, str);
        shehui.setArguments(bundle);
        return shehui;
    }

  /*public  void  initData(){
       ArrayList<String> stringArrayList = shehui.getArguments().getStringArrayList(KEY);
        for (String s : stringArrayList) {

        }

  }*/
    public void getUrlData() {
        RequestParams params=new RequestParams();
        params.setUri(url);
        x.http().get(params, new Callback.CacheCallback<String>() {

            @Override
            public void onSuccess(String result) {
                if (result!=null){
                    Gson gson=new Gson();
                    TitleAdapterbean titlebean = gson.fromJson(result, TitleAdapterbean.class);
                    List<TitleAdapterbean.ResultBean.DataBean> data = titlebean.getResult().getData();
                    list.addAll(data);
                    XliAdapter adapter=new XliAdapter(getContext(),list);
                    xlistview.setAdapter(adapter);
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

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }
}
