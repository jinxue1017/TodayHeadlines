package FrgmentBean;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import BaeaAdapter.VideoAdapter;
import lianjinxue201762.bawei.com.todayheadline.R;
import me.maxwin.view.XListView;

/**
 * 创建者： 廉锦雪
 * 创建时间:2017/6/715:31
 */
public class Shipin extends Fragment implements XListView.IXListViewListener {
    private List<String> mlist = new ArrayList<>();
    private XListView shipin;
    private VideoAdapter madapter;
    private Activity activity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = getActivity();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.shipin,container,false);
      /*  VideoView vv=(VideoView)view.findViewById(R.id.videoView1);
        //设置播放视频的路径
        vv.setVideoPath("/data/iToolsVMShare/minion_08.mp4");
        //创建媒体控制器
        MediaController controll=new MediaController(getActivity());
        controll.setMediaPlayer(vv);
        vv.setMediaController(controll);
        //开始播放
        vv.start();
*/
        shipin = (XListView) view.findViewById(R.id.xlistviewsjipin);
        shipin.setPullRefreshEnable(true);
        shipin.setPullLoadEnable(true);
        shipin.setXListViewListener(this);
        initData();
        madapter = new VideoAdapter(getActivity(),mlist);
        //madapter.setData(mlist);
        shipin.setAdapter(madapter);
        return view;

    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            http://baobab.kaiyanapp.com/api/v1/playUrl?vid=221" + i + "1&editionType=default&source=ucloud
            mlist.add("http://baobab.kaiyanapp.com/api/v1/playUrl?vid=221\" + i + \"1&editionType=default&source=ucloud");
        }
    }

    @Override
    public void onRefresh() {
        mlist.clear();
        initData();
        stopload();

    }
    @Override
    public void onLoadMore() {
        initData();
        stopload();
    }
    public void stopload(){
        shipin.stopRefresh();
        shipin.stopLoadMore();
        shipin.setRefreshTime("更多");
    }
}
