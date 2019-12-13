package cn.edu.guet.xianhuo.feature.address.manage;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import cn.edu.guet.xianhuo.R;
import cn.edu.guet.xianhuo.base.BaseActivity;
import cn.edu.guet.xianhuo.base.wrapper.ProgressWrapper;
import cn.edu.guet.xianhuo.base.wrapper.ToastWrapper;
import cn.edu.guet.xianhuo.base.wrapper.ToolbarWrapper;
import cn.edu.guet.xianhuo.feature.address.edit.EditAddressActivity;
import cn.edu.guet.xianhuo.network.UserManager;
import cn.edu.guet.xianhuo.network.api.ApiAddressDefault;
import cn.edu.guet.xianhuo.network.api.ApiAddressDelete;
import cn.edu.guet.xianhuo.network.api.ApiAddressInfo;
import cn.edu.guet.xianhuo.network.core.ApiPath;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;
import cn.edu.guet.xianhuo.network.entity.Address;
import cn.edu.guet.xianhuo.network.event.AddressEvent;

/**
 * 管理收货地址.
 */
public class ManageAddressActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ProgressWrapper mProgressWrapper;
    private AddressAdapter mAddressAdapter;

    @Override protected int getContentViewLayout() {
        return R.layout.activity_manage_address;
    }

    @Override protected void initView() {
        new ToolbarWrapper(this).setCustomTitle(R.string.address_title_manage);
        mProgressWrapper = new ProgressWrapper();
        mAddressAdapter = new AddressAdapterImpl();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAddressAdapter);
    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {
        switch (apiPath) {
            case ApiPath.ADDRESS_DEFAULT:
            case ApiPath.ADDRESS_DELETE:
                if (success) {
                    UserManager.getInstance().retrieveAddressList();
                } else {
                    mProgressWrapper.dismissProgress();
                }
                break;
            case ApiPath.ADDRESS_INFO:
                mProgressWrapper.dismissProgress();
                if (success) {
                    ApiAddressInfo.Rsp infoRsp = (ApiAddressInfo.Rsp) rsp;
                    Intent intent = EditAddressActivity.getStartIntent(this, infoRsp.getData());
                    startActivity(intent);
                }
                break;
            default:
                throw new UnsupportedOperationException(apiPath);
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(AddressEvent event) {
        mProgressWrapper.dismissProgress();
        mAddressAdapter.reset(UserManager.getInstance().getAddressList());
    }

    @OnClick(R.id.button_create) void navigateToAddAddress() {
        Intent intent = EditAddressActivity.getStartIntent(this, null);
        startActivity(intent);
    }

    private class AddressAdapterImpl extends AddressAdapter {
        @Override protected void onSetDefault(Address address) {
            mProgressWrapper.showProgress(ManageAddressActivity.this);
            ApiAddressDefault apiAddressDefault = new ApiAddressDefault(address.getId());
            enqueue(apiAddressDefault);
        }

        @Override protected void onDelete(Address address) {
            if (address.isDefault()) {
                ToastWrapper.show(R.string.address_msg_can_not_delete_default);
                return;
            }
            mProgressWrapper.showProgress(ManageAddressActivity.this);
            ApiAddressDelete apiAddressDelete = new ApiAddressDelete(address.getId());
            enqueue(apiAddressDelete);
        }

        @Override protected void onEdit(Address address) {
            mProgressWrapper.showProgress(ManageAddressActivity.this);
            ApiAddressInfo apiAddressInfo = new ApiAddressInfo(address.getId());
            enqueue(apiAddressInfo);
        }
    }

}
