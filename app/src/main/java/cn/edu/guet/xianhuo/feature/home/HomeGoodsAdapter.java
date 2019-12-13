package cn.edu.guet.xianhuo.feature.home;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import cn.edu.guet.xianhuo.R;
import cn.edu.guet.xianhuo.base.BaseListAdapter;
import cn.edu.guet.xianhuo.feature.goods.GoodsActivity;
import cn.edu.guet.xianhuo.network.entity.CategoryHome;

/**
 * 首页商品列表适配器
 */
public class HomeGoodsAdapter extends BaseListAdapter<CategoryHome, HomeGoodsAdapter.ViewHolder> {

    @Override
    protected int getItemViewLayout() {
        return R.layout.item_home_goods;
    }

    @Override
    protected ViewHolder getItemViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    class ViewHolder extends BaseListAdapter.ViewHolder {

//        @BindView(R.id.text_category)
//        TextView tvCategory;
//
//        @BindView(R.id.grid_image)
//        ImageGrid imageGrid;

        private ImageView[] mImageViews;
        private CategoryHome mItem;

        ViewHolder(View itemView) {
            super(itemView);
        }

        /**
         * 加载数据
         *
         * @param position
         */
        @Override
        protected void bind(int position) {
//            imageGrid.shuffle(position);
//            mImageViews = imageGrid.getImageViews();
//            for (int i = 0; i < mImageViews.length; i++) {
//                final int index = i;
//                mImageViews[i].setOnClickListener(v -> navigateToGoodsActivity(index));
//            }
//            mItem = getItem(position);
//            tvCategory.setText(mItem.getName());
//            ImageView[] imageViews = imageGrid.getImageViews();
//            List<SimpleGoods> goodsList = mItem.getHotGoodsList();
//            for (int i = 0; i < imageViews.length; i++) {
//                Picture picture = goodsList.get(i).getImg();
//                GlideUtils.loadPicture(picture, imageViews[i]);
//            }
        }

        /**
         * 跳转到搜索页面
         */
//        @OnClick(R.id.text_category)
//        void navigateToSearch() {
//            Filter filter = new Filter();
//            filter.setCategoryId(mItem.getId());
//            Intent intent = SearchGoodsActivity.getStartIntent(getContext(), filter);
//            getContext().startActivity(intent);
//        }

        /**
         * 跳转到商品详情页面
         *
         * @param index
         */
        private void navigateToGoodsActivity(int index) {
            if (mItem.getHotGoodsList().size() <= index) return;
            int goodsId = mItem.getHotGoodsList().get(index).getId();
            Intent intent = GoodsActivity.getStartIntent(getContext(), goodsId);
            getContext().startActivity(intent);
        }

    }

}
