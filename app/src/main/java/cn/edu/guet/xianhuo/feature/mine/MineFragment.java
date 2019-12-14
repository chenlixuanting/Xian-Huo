package cn.edu.guet.xianhuo.feature.mine;


import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.edu.guet.xianhuo.R;
import cn.edu.guet.xianhuo.base.BaseFragment;
import cn.edu.guet.xianhuo.base.wrapper.BadgeWrapper;
import cn.edu.guet.xianhuo.feature.address.manage.ManageAddressActivity;
import cn.edu.guet.xianhuo.feature.collect.CollectActivity;
import cn.edu.guet.xianhuo.feature.help.HelpActivity;
import cn.edu.guet.xianhuo.feature.order.list.OrderListActivity;
import cn.edu.guet.xianhuo.feature.settings.SettingsActivity;
import cn.edu.guet.xianhuo.network.UserManager;
import cn.edu.guet.xianhuo.network.api.ApiOrderList;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;
import cn.edu.guet.xianhuo.network.entity.User;
import cn.edu.guet.xianhuo.network.event.UserEvent;

/**
 * 我的页面.
 */
public class MineFragment extends BaseFragment {

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @BindView(R.id.text_username)
    TextView tvName;

    @BindView(R.id.text_wait_pay)
    TextView tvWaitPay;

    @BindView(R.id.text_wait_ship)
    TextView tvWaitShip;

    @BindView(R.id.text_shipped)
    TextView tvShipped;

    @BindView(R.id.text_history)
    TextView tvHistory;

    private BadgeWrapper mWaitPayBadge;
    private BadgeWrapper mWaitShipBadge;
    private BadgeWrapper mShippedBadge;

    @Override
    protected int getContentViewLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        mWaitPayBadge = new BadgeWrapper(tvWaitPay);
        mWaitShipBadge = new BadgeWrapper(tvWaitShip);
        mShippedBadge = new BadgeWrapper(tvShipped);
    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {
    }

    @Override
    public void onEvent(UserEvent event) {
        super.onEvent(event);
        if (UserManager.getInstance().hasUser()) {
            User user = UserManager.getInstance().getUser();
            tvName.setText(user.getName());
            mWaitPayBadge.showNumber(user.getOrderNum().getAwaitPay());
            mWaitShipBadge.showNumber(user.getOrderNum().getAwaitShip());
            mShippedBadge.showNumber(user.getOrderNum().getShipped());
        } else {
            tvName.setText(R.string.mine_sign_in_or_sign_up);
            mWaitPayBadge.hide();
            mWaitShipBadge.hide();
            mShippedBadge.hide();
        }
    }

    /**
     * 当点击到有关用户具体信息的按钮时，则判断当前用户是否登录
     * 如果没有登录，则跳转到登录界面，如果登录了，则通过网络请求去获取用户这部分的数据
     *
     * @param view
     */
    @OnClick({R.id.text_username, R.id.text_manage_address, R.id.text_wait_pay, R.id.text_order_unconfirmed,
            R.id.text_wait_ship, R.id.text_shipped, R.id.text_history, R.id.text_favorite,})
    void onClick(View view) {
        if (!UserManager.getInstance().hasUser()) {
            Intent intent = new Intent(getContext(), SignInActivity.class);
            getActivity().startActivity(intent);
            return;
        }
        switch (view.getId()) {
            case R.id.text_username:
                break;
            case R.id.text_manage_address:
                Intent manageAddress = new Intent(getContext(), ManageAddressActivity.class);
                startActivity(manageAddress);
                break;
            case R.id.text_wait_pay:
                Intent waitPay = OrderListActivity
                        .getStartIntent(getContext(), ApiOrderList.ORDER_AWAIT_PAY);
                startActivity(waitPay);
                break;
            case R.id.text_order_unconfirmed:
                Intent unconfirmed = OrderListActivity
                        .getStartIntent(getContext(), ApiOrderList.ORDER_UNCONFIRMED);
                startActivity(unconfirmed);
                break;
            case R.id.text_wait_ship:
                Intent waitShip = OrderListActivity
                        .getStartIntent(getContext(), ApiOrderList.ORDER_AWAIT_SHIP);
                startActivity(waitShip);
                break;
            case R.id.text_shipped:
                Intent shipped = OrderListActivity
                        .getStartIntent(getContext(), ApiOrderList.ORDER_SHIPPED);
                startActivity(shipped);
                break;
            case R.id.text_history:
                Intent history = OrderListActivity
                        .getStartIntent(getContext(), ApiOrderList.ORDER_FINISHED);
                startActivity(history);
                break;
            case R.id.text_favorite:
                Intent collect = new Intent(getContext(), CollectActivity.class);
                startActivity(collect);
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }

    /**
     * 设置页面
     */
    @OnClick(R.id.button_setting)
    void navigateToSettings() {
        Intent intent = new Intent(getContext(), SettingsActivity.class);
        getActivity().startActivity(intent);
    }

    /**
     * 帮助页面
     */
    @OnClick(R.id.text_help)
    void navigateToHelp() {
        Intent intent = new Intent(getContext(), HelpActivity.class);
        getActivity().startActivity(intent);
    }

}
