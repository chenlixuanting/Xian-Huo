package cn.edu.guet.xianhuo.feature.order.list;


import android.content.Context;
import android.content.Intent;
import android.widget.ListView;

import butterknife.BindView;
import cn.edu.guet.xianhuo.R;
import cn.edu.guet.xianhuo.base.BaseActivity;
import cn.edu.guet.xianhuo.base.wrapper.ProgressWrapper;
import cn.edu.guet.xianhuo.base.wrapper.PtrWrapper;
import cn.edu.guet.xianhuo.base.wrapper.ToastWrapper;
import cn.edu.guet.xianhuo.base.wrapper.ToolbarWrapper;
import cn.edu.guet.xianhuo.network.UserManager;
import cn.edu.guet.xianhuo.network.api.ApiOrderCancel;
import cn.edu.guet.xianhuo.network.api.ApiOrderList;
import cn.edu.guet.xianhuo.network.core.ApiPath;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;
import cn.edu.guet.xianhuo.network.entity.Order;

/**
 * 订单页面
 */
public class OrderListActivity extends BaseActivity {

    private static final String EXTRA_ORDER_TYPE = "EXTRA_ORDER_TYPE";

    public static Intent getStartIntent(Context context, @ApiOrderList.OrderType String type) {
        Intent intent = new Intent(context, OrderListActivity.class);
        intent.putExtra(EXTRA_ORDER_TYPE, type);
        return intent;
    }

    @BindView(R.id.list_order)
    ListView orderListView;

    private PtrWrapper mPtrWrapper;
    private OrderAdapter mOrderAdapter;
    private ProgressWrapper mProgressWrapper;
    @ApiOrderList.OrderType
    private String mOrderType;

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_order_list;
    }

    @Override
    protected void initView() {
        initOrderType();
        mProgressWrapper = new ProgressWrapper();
        mPtrWrapper = new PtrWrapper(this) {
            @Override
            public void onRefresh() {
                ApiOrderList apiOrderList = new ApiOrderList(mOrderType, null);
                enqueue(apiOrderList);
            }
        };
        mOrderAdapter = new OrderAdapter(mOrderType);
        orderListView.setAdapter(mOrderAdapter);
        mPtrWrapper.postRefresh(50);
    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {
        switch (apiPath) {
            case ApiPath.ORDER_LIST:
                mPtrWrapper.stopRefresh();
                if (success) {
                    ApiOrderList.Rsp listRsp = (ApiOrderList.Rsp) rsp;
                    mOrderAdapter.reset(listRsp.getOrderList());
                }
                break;
            case ApiPath.ORDER_CANCEL:
                mProgressWrapper.dismissProgress();
                if (success) {
                    ToastWrapper.show(R.string.order_is_canceled);
                    UserManager.getInstance().retrieveUserInfo();
                    mPtrWrapper.autoRefresh();
                }
                break;
            default:
                throw new UnsupportedOperationException(apiPath);
        }
    }

    private void initOrderType() {
        String type = getIntent().getStringExtra(EXTRA_ORDER_TYPE);
        switch (type) {
            case ApiOrderList.ORDER_AWAIT_PAY:
                mOrderType = ApiOrderList.ORDER_AWAIT_PAY;
                new ToolbarWrapper(this).setCustomTitle(R.string.order_title_wait_pay);
                break;
            case ApiOrderList.ORDER_UNCONFIRMED:
                mOrderType = ApiOrderList.ORDER_UNCONFIRMED;
                new ToolbarWrapper(this).setCustomTitle(R.string.order_title_unconfirmed);
                break;
            case ApiOrderList.ORDER_AWAIT_SHIP:
                mOrderType = ApiOrderList.ORDER_AWAIT_SHIP;
                new ToolbarWrapper(this).setCustomTitle(R.string.order_title_wait_ship);
                break;
            case ApiOrderList.ORDER_SHIPPED:
                mOrderType = ApiOrderList.ORDER_SHIPPED;
                new ToolbarWrapper(this).setCustomTitle(R.string.order_title_shipped);
                break;
            case ApiOrderList.ORDER_FINISHED:
                mOrderType = ApiOrderList.ORDER_FINISHED;
                new ToolbarWrapper(this).setCustomTitle(R.string.order_title_history);
                break;
            default:
                throw new IllegalArgumentException(type);
        }
    }

    private class OrderAdapter extends OrderListAdapter {
        OrderAdapter(@ApiOrderList.OrderType String type) {
            super(type);
        }

        @Override
        protected void onOrderCancel(Order order) {
            mProgressWrapper.showProgress(OrderListActivity.this);
            ApiOrderCancel apiOrderCancel = new ApiOrderCancel(order.getId());
            enqueue(apiOrderCancel);
        }

        @Override
        protected void onOrderPurchase(Order order) {
        }
    }

}
