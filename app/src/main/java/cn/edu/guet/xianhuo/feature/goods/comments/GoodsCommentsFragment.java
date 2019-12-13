package cn.edu.guet.xianhuo.feature.goods.comments;


import cn.edu.guet.xianhuo.R;
import cn.edu.guet.xianhuo.base.BaseFragment;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;

/**
 * 商品评价页面: 不实现, 用空白页面.
 */
public class GoodsCommentsFragment extends BaseFragment {

    public static GoodsCommentsFragment newInstance() {
        return new GoodsCommentsFragment();
    }

    @Override protected int getContentViewLayout() {
        return R.layout.fragment_goods_comments;
    }

    @Override protected void initView() {
    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {
    }
    
}
