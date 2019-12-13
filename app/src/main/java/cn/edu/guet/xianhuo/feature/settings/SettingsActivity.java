package cn.edu.guet.xianhuo.feature.settings;


import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.OnClick;
import cn.edu.guet.xianhuo.R;
import cn.edu.guet.xianhuo.base.BaseActivity;
import cn.edu.guet.xianhuo.base.wrapper.ToolbarWrapper;
import cn.edu.guet.xianhuo.network.UserManager;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;
import cn.edu.guet.xianhuo.network.event.UserEvent;

/**
 * 设置页面
 */
public class SettingsActivity extends BaseActivity {

    @BindView(R.id.button_sign_out) Button btnSignOut;

    @Override protected int getContentViewLayout() {
        return R.layout.activity_settings;
    }

    @Override protected void initView() {
        new ToolbarWrapper(this).setCustomTitle(R.string.settings_title);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, new SettingsFragment());
        transaction.commit();
    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {
    }

    @Override public void onEvent(UserEvent event) {
        super.onEvent(event);
        if (UserManager.getInstance().hasUser()) {
            btnSignOut.setVisibility(View.VISIBLE);
        } else {
            btnSignOut.setVisibility(View.INVISIBLE);
        }
    }

    @OnClick(R.id.button_sign_out) void signOut() {
        UserManager.getInstance().clear();
        finish();
    }

}
