package BaeaAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import org.xutils.x;
import java.util.List;
import FrgmentBean.TitleAdapterbean;
import lianjinxue201762.bawei.com.todayheadline.R;


/**
 * 创建者： 廉锦雪
 * 创建时间:2017/6/1614:49
 */

public class XliAdapter extends BaseAdapter {
    final int TYPE1 = 0;
    final int TYPE2 = 1;
    Context context;
    private List<TitleAdapterbean.ResultBean.DataBean>list;

    public XliAdapter(Context context, List<TitleAdapterbean.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 1:
                return TYPE1;
            case 2:
                return TYPE2;
            default:
                return TYPE1;
        }

    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        TitleAdapterbean.ResultBean.DataBean dataBean = list.get(i);

        int type = getItemViewType(i);
        view=null;
        if (view==null){
            viewHolder=new ViewHolder();
            switch (type){
                case TYPE1:
                view= View.inflate(context, R.layout.titlebean,null);
                viewHolder.beanimage1= (ImageView) view.findViewById(R.id.beanimage1);
                break;
                case TYPE2:
                    view= View.inflate(context, R.layout.titlebean2,null);
                    viewHolder.beanimage1= (ImageView) view.findViewById(R.id.beanimage1);
                    viewHolder.beanimage2= (ImageView) view.findViewById(R.id.beanimage2);
                    viewHolder.beanimage3= (ImageView) view.findViewById(R.id.beanimage3);
                    break;
            }
            viewHolder.titletext= (TextView) view.findViewById(R.id.titletext);
            viewHolder.beantextview= (TextView) view.findViewById(R.id.titletext);
            view.setTag(viewHolder);
        }else {
           viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.titletext.setText(list.get(i).getTitle());
        viewHolder.beantextview.setText(list.get(i).getAuthor_name());


        switch (type){
            case TYPE1:
                // Glide.with(context).load(list.get(i).getThumbnail_pic_s()).placeholder(R.mipmap.ic_launcher).into(viewHolder.beanimage1);
                x.image().bind(viewHolder.beanimage1,list.get(0).getThumbnail_pic_s());
                break;
            case TYPE2:
              /*  Glide.with(context).load(list.get(i).getThumbnail_pic_s()).placeholder(R.mipmap.ic_launcher).into(viewHolder.beanimage1);
                Glide.with(context).load(list.get(i).getThumbnail_pic_s02()).placeholder(R.mipmap.ic_launcher).into(viewHolder.beanimage2);
                Glide.with(context).load(list.get(i).getThumbnail_pic_s03()).placeholder(R.mipmap.ic_launcher).into(viewHolder.beanimage3);
*/
                 x.image().bind(viewHolder.beanimage1,list.get(0).getThumbnail_pic_s());
                x.image().bind(viewHolder.beanimage2,list.get(1).getThumbnail_pic_s02());
                x.image().bind(viewHolder.beanimage3,list.get(2).getThumbnail_pic_s03());
                break;

        }
        return view;

    }


    class ViewHolder{
     TextView titletext,beantextview;
        ImageView beanimage1,beanimage2,beanimage3;
    }
}
