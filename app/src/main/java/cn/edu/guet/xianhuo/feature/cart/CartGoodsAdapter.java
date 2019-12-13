package cn.edu.guet.xianhuo.feature.cart;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import cn.edu.guet.xianhuo.R;
import cn.edu.guet.xianhuo.base.BaseListAdapter;
import cn.edu.guet.xianhuo.base.glide.GlideUtils;
import cn.edu.guet.xianhuo.base.widgets.SimpleNumberPicker;
import cn.edu.guet.xianhuo.network.entity.CartGoods;
import cn.edu.guet.xianhuo.network.entity.Picture;

/**
 * 购物车商品列表Adapter
 */
public abstract class CartGoodsAdapter extends BaseListAdapter<CartGoods, CartGoodsAdapter.ViewHolder> {

    @Override
    protected int getItemViewLayout() {
        return R.layout.item_cart_goods;
    }

    @Override
    protected ViewHolder getItemViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    public abstract void numberChanged(CartGoods goods, int number);

    class ViewHolder extends BaseListAdapter.ViewHolder
            implements SimpleNumberPicker.OnNumberChangedListener {

        @BindView(R.id.image_goods)
        ImageView ivGoods;
        @BindView(R.id.text_goods_name)
        TextView tvName;
        @BindView(R.id.text_goods_price)
        TextView tvPrice;
        @BindView(R.id.number_picker)
        SimpleNumberPicker numberPicker;

        private CartGoods mItem;

        ViewHolder(View itemView) {
            super(itemView);
            numberPicker.setOnNumberChangedListener(this);
        }

        @Override
        protected void bind(int position) {
            mItem = getItem(position);
            tvName.setText(mItem.getGoodsName());
            tvPrice.setText(mItem.getTotalPrice());
            numberPicker.setNumber(mItem.getGoodsNumber());
            Picture picture = mItem.getImg();
            GlideUtils.loadPicture(picture, ivGoods);
        }

        @Override
        public void onNumberChanged(int number) {
            numberChanged(mItem, number);
        }
    }

}
