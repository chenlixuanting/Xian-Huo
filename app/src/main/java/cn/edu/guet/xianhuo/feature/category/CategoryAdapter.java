package cn.edu.guet.xianhuo.feature.category;


import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import cn.edu.guet.xianhuo.R;
import cn.edu.guet.xianhuo.base.BaseListAdapter;
import cn.edu.guet.xianhuo.network.entity.CategoryPrimary;

/**
 * 分类列表Adapter
 */
public class CategoryAdapter extends BaseListAdapter<CategoryPrimary, CategoryAdapter.ViewHolder> {

    @Override
    protected int getItemViewLayout() {
        return R.layout.item_primary_category;
    }

    @Override
    protected ViewHolder getItemViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    class ViewHolder extends BaseListAdapter.ViewHolder {

        @BindView(R.id.text_category)
        TextView tvCategory;

        ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(int position) {
            tvCategory.setText(getItem(position).getName());
        }
    }

}
