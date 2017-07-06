package BaeaAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import lianjinxue201762.bawei.com.todayheadline.R;

/**
 * 创建者： 廉锦雪
 * 创建时间:2017/6/2810:28
 */

public class VideoAdapter extends BaseAdapter{
    private Context mContext;
    private List<String> mList;
    /*public VideoAdapter(Context context) {
        this.mContext = context;

    }

    public void setData(List<String> datas){
        if (datas!=null){
            mList.addAll(datas);
        }
    }*/

    public VideoAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
      Viewholder viewholder;
        if (convertView==null){
            convertView = convertView.inflate(mContext, R.layout.videoplyer, null);
            viewholder=new Viewholder();
            viewholder.jcVideoPlayer= (JCVideoPlayerStandard) convertView.findViewById(R.id.shipinbofang2);
            convertView.setTag(viewholder);
        }else {
            viewholder= (Viewholder) convertView.getTag();
        }
        viewholder.jcVideoPlayer.setUp(mList.get(i), JCVideoPlayer.SCREEN_LAYOUT_LIST,"");
        //http://f2.kkmh.com/image/151203/ub3hlynnl.jpg-w180
        Glide.with(mContext).load("http://f2.kkmh.com/image/151203/ub3hlynnl.jpg-w180").into(viewholder.jcVideoPlayer.thumbImageView);

        return convertView;
    }
    class Viewholder{
        private JCVideoPlayerStandard jcVideoPlayer;
    }
}
