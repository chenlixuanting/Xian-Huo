package cn.edu.guet.xianhuo.base.widgets.banner;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.edu.guet.xianhuo.R;

public abstract class BannerAdapter<T> extends PagerAdapter {

    private List<T> mDataSet = new ArrayList<>();

    private Queue<ViewHolder> mHolderQueue = new ArrayDeque<>();

    /**
     * 设置ViewPager有几个滑动页面
     *
     * @return
     */
    @Override
    public int getCount() {
        return mDataSet.size();
    }

    /**
     * 官方固定写法
     *
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        ViewHolder holder = (ViewHolder) object;
        return view == holder.itemView;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    /**
     * 当前滑动到的ViewPager页面
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ViewHolder holder = mHolderQueue.poll();

        if (holder == null) {
            LayoutInflater inflater = LayoutInflater.from(container.getContext());
            View itemView = inflater.inflate(R.layout.item_banner, container, false);
            holder = new ViewHolder(itemView);
        }

        container.addView(holder.itemView);
        bind(holder, mDataSet.get(position));
        return holder;
    }

    /**
     * 每次划出当前页面的时候就销毁
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewHolder holder = (ViewHolder) object;
        container.removeView(holder.itemView);
        mHolderQueue.add(holder);
    }

    public void reset(List<T> dataSet) {
        mDataSet.clear();
        mDataSet.addAll(dataSet);
        notifyDataSetChanged();
    }

    protected abstract void bind(ViewHolder holder, T data);

    public static class ViewHolder {

        @BindView(R.id.image_banner_item)
        public ImageView ivBannerItem;
        public View itemView;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

}