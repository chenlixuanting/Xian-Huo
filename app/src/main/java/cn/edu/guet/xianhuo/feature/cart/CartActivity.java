package cn.edu.guet.xianhuo.feature.cart;


import androidx.fragment.app.FragmentTransaction;

import cn.edu.guet.xianhuo.R;
import cn.edu.guet.xianhuo.base.BaseActivity;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;

/**
 * 购物车页面
 */
public class CartActivity extends BaseActivity {

    private static final String CART_FRAGMENT_TAG = CartFragment.class.getSimpleName();

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_cart;
    }

    @Override
    protected void initView() {
        if (getSupportFragmentManager().findFragmentByTag(CART_FRAGMENT_TAG) == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.container, CartFragment.newInstance(), CART_FRAGMENT_TAG);
            transaction.commit();
        }
    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {
    }

}
