package cn.edu.guet.xianhuo.feature.home;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import cn.edu.guet.xianhuo.R;
import cn.edu.guet.xianhuo.base.BaseFragment;
import cn.edu.guet.xianhuo.base.glide.GlideUtils;
import cn.edu.guet.xianhuo.base.widgets.banner.BannerAdapter;
import cn.edu.guet.xianhuo.base.widgets.banner.BannerLayout;
import cn.edu.guet.xianhuo.base.wrapper.PtrWrapper;
import cn.edu.guet.xianhuo.base.wrapper.ToolbarWrapper;
import cn.edu.guet.xianhuo.feature.goods.GoodsActivity;
import cn.edu.guet.xianhuo.network.api.ApiHomeBanner;
import cn.edu.guet.xianhuo.network.api.ApiHomeCategory;
import cn.edu.guet.xianhuo.network.core.ApiPath;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;
import cn.edu.guet.xianhuo.network.entity.Banner;
import cn.edu.guet.xianhuo.network.entity.SimpleGoods;

/**
 * 首页面.
 */
public class HomeFragment extends BaseFragment {

    private static final int[] PROMOTE_COLORS = {R.color.purple, R.color.orange, R.color.pink, R.color.colorPrimary};

    private static final int[] PROMOTE_PLACE_HOLDER = {
            R.drawable.mask_round_purple, R.drawable.mask_round_orange,
            R.drawable.mask_round_pink, R.drawable.mask_round_yellow
    };

    private SimpleGoods simpleGoods;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @BindView(R.id.list_home_goods)
    ListView goodsListView;

    @BindView(R.id.home_add_fab)
    FloatingActionButton floatingActionButton;

    @BindView(R.id.home_sale_btn)
    FloatingActionButton homeSaleBtn;

    @BindView(R.id.home_buy_btn)
    FloatingActionButton homeBuyBtn;

    Boolean fabOpened = false;

    private HomeGoodsAdapter mGoodsAdapter; // 首页商品列表适配器.
    private BannerAdapter<Banner> mBannerAdapter; //轮播图适配器.
    private PtrWrapper mPtrWrapper;//下拉刷新组件

    private boolean mBannerRefreshed = false;
    private boolean mCategoryRefreshed = false;

    private ImageView[] mIvPromotes = new ImageView[4];
    private TextView mTvPromoteGoods;

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_home;
    }

    /**
     * 创建顶部菜单时调用
     *
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_category, menu);
    }

    /**
     * 开启菜单蒙版
     *
     * @param view
     */
    private void openMenu(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0, -155, -135);
        animator.setDuration(500);
        animator.start();
//        cloud.setVisibility(View.VISIBLE);
        homeBuyBtn.show();
        homeSaleBtn.show();
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 0.7f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);
        homeBuyBtn.startAnimation(alphaAnimation);
        homeSaleBtn.startAnimation(alphaAnimation);
        fabOpened = true;
    }

    /**
     * 关闭菜单蒙版
     */
    private void closeMenu(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", -135, 20, 0);
        animator.setDuration(500);
        animator.start();
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.7f, 0);
        alphaAnimation.setDuration(500);
        homeBuyBtn.setAnimation(alphaAnimation);
        homeSaleBtn.setAnimation(alphaAnimation);
//        cloud.setVisibility(View.GONE);
        homeSaleBtn.hide();
        homeBuyBtn.hide();
        fabOpened = false;
    }

    @Override
    protected void initView() {

        //设置标题
        new ToolbarWrapper(this).setCustomTitle(R.string.home_title);

        //商品列表
        mGoodsAdapter = new HomeGoodsAdapter();
        goodsListView.setAdapter(mGoodsAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fabOpened) {
                    openMenu(v);
                } else {
                    closeMenu(v);
                }
            }
        });

        //List-Header-View
//        LayoutInflater inflater = LayoutInflater.from(getContext());
//        View view = inflater.inflate(R.layout.partial_home_header, goodsListView, false);

        //轮播图
//        BannerLayout bannerLayout = getActivity().findViewById(R.id.layout_banner);
//        mBannerAdapter = new BannerAdapter<Banner>() {
//            @Override
//            protected void bind(ViewHolder holder, Banner data) {
//                GlideUtils.loadBanner(data.getPicture(), holder.ivBannerItem);
//            }
//        };
//
//        bannerLayout.setBannerAdapter(mBannerAdapter);

        //促销单品
        mIvPromotes[0] = getActivity().findViewById(R.id.image_promote_one);
        mIvPromotes[1] = getActivity().findViewById(R.id.image_promote_two);
        mIvPromotes[2] = getActivity().findViewById(R.id.image_promote_three);
        mIvPromotes[3] = getActivity().findViewById(R.id.image_promote_four);

        mTvPromoteGoods = getActivity().findViewById(R.id.text_promote_goods);
//        goodsListView.addHeaderView(view);
        goodsListView.setDividerHeight(0);

        //下拉刷新
        mPtrWrapper = new PtrWrapper(this) {
            @Override
            public void onRefresh() {
                mBannerRefreshed = false;
                mCategoryRefreshed = false;
                // 获取轮播图和促销商品数据.
                enqueue(new ApiHomeBanner());
                // 获取首页商品分类数据.
                enqueue(new ApiHomeCategory());
            }
        };
        mPtrWrapper.postRefresh(50);
    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {
        switch (apiPath) {
            case ApiPath.HOME_DATA:
                mBannerRefreshed = true;
                if (success) {
                    ApiHomeBanner.Rsp bannerRsp = (ApiHomeBanner.Rsp) rsp;
//                    mBannerAdapter.reset(bannerRsp.getData().getBanners());
                    setPromoteGoods(bannerRsp.getData().getGoodsList());
                }
                break;
            case ApiPath.HOME_CATEGORY:
                mCategoryRefreshed = true;
                if (success) {
                    ApiHomeCategory.Rsp categoryRsp = (ApiHomeCategory.Rsp) rsp;
                    mGoodsAdapter.reset(categoryRsp.getData());
                }
                break;
            default:
                throw new UnsupportedOperationException(apiPath);
        }

        if (mBannerRefreshed && mCategoryRefreshed) {
            // 两个接口的数据都返回了, 才停止下拉刷新.
            mPtrWrapper.stopRefresh();
        }
    }

    /**
     * 设置显示促销商品.
     *
     * @param simpleGoodsList
     */
    private void setPromoteGoods(final List<SimpleGoods> simpleGoodsList) {
        mTvPromoteGoods.setVisibility(View.VISIBLE);
        for (int i = 0; i < mIvPromotes.length; i++) {
            mIvPromotes[i].setVisibility(View.VISIBLE);
            simpleGoods = null;
            if (i < simpleGoodsList.size()) {
                simpleGoods = simpleGoodsList.get(i);
            } else {
                simpleGoods = simpleGoodsList.get(i - 1);
            }
            /**
             * 从网络加载图片
             */
//            GlideUtils.loadPromote(simpleGoods.getImg(), mIvPromotes[i], PROMOTE_PLACE_HOLDER[i], PROMOTE_COLORS[i]);
            mIvPromotes[i].setOnClickListener(v -> {
                Intent intent = GoodsActivity.getStartIntent(getContext(), simpleGoods.getId());
                getActivity().startActivity(intent);
            });
        }
    }

}
