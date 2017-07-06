package BaeaAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import lianjinxue201762.bawei.com.todayheadline.R;

/**
 * 创建者： 廉锦雪
 * 创建时间:2017/6/2210:02
 */

public class OtherAdapter extends BaseAdapter {
    private Context context;
    public List<String> channelList;
    private TextView item_text;
    /** 是否可见 在移动动画完毕之前不可见，动画完毕后可见*/
    boolean isVisible = true;
    /** 要删除的position */
    public int remove_position = -1;
    /** 是否是用户频道 */
    private boolean isUser = false;


    public OtherAdapter(Context context, List<String> channelList ,boolean isUser) {
        this.context = context;
        this.channelList = channelList;
        this.isUser = isUser;
    }

    @Override
    public int getCount() {
        return channelList == null ? 0 : channelList.size();
    }

    @Override
    public Object getItem(int i) {
        if (channelList != null && channelList.size() != 0) {
            return channelList.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       View view1= View.inflate(context,R.layout.gridviewitam,null);
        item_text = (TextView) view1.findViewById(R.id.text_item);
       String channel = (String) getItem(i);
        item_text.setText(channel);
        if(isUser){
            if ((i == 0) || (i == 1)){
                item_text.setEnabled(false);
            }
        }
        if (!isVisible && (i == -1 + channelList.size())){
            item_text.setText("");
            item_text.setSelected(true);
            item_text.setEnabled(true);
        }
        if(remove_position == i){
            item_text.setText("");
        }
        return view1;
    }

    /** 获取频道列表 */
    public List<String> getChannnelLst() {
        return channelList;
    }

    /** 添加频道列表 */
    public void addItem(String channel) {
        channelList.add(channel);
        notifyDataSetChanged();
    }

    /** 设置删除的position */
    public void setRemove(int position) {
        remove_position = position;
        notifyDataSetChanged();
        // notifyDataSetChanged();
    }

    /** 删除频道列表 */
    public void remove() {
        channelList.remove(remove_position);
        remove_position = -1;
        notifyDataSetChanged();
    }
    /** 设置频道列表 */
    public void setListDate(List<String> list) {
        channelList = list;
    }

    /** 获取是否可见 */
    public boolean isVisible() {
        return isVisible;
    }

    /** 设置是否可见 */
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

}

