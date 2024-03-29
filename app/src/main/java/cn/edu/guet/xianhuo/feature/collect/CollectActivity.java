package cn.edu.guet.xianhuo.feature.collect;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import cn.edu.guet.xianhuo.R;
import cn.edu.guet.xianhuo.base.BaseActivity;
import cn.edu.guet.xianhuo.base.wrapper.AlertWrapper;
import cn.edu.guet.xianhuo.base.wrapper.PtrWrapper;
import cn.edu.guet.xianhuo.base.wrapper.ToastWrapper;
import cn.edu.guet.xianhuo.base.wrapper.ToolbarWrapper;
import cn.edu.guet.xianhuo.network.api.ApiCollectDelete;
import cn.edu.guet.xianhuo.network.api.ApiCollectList;
import cn.edu.guet.xianhuo.network.core.ApiPath;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;
import cn.edu.guet.xianhuo.network.entity.CollectGoods;
import cn.edu.guet.xianhuo.network.entity.Pagination;

/**
 * 我的收藏页面.
 */
public class CollectActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private PtrWrapper mPtrWrapper;
    private CollectGoodsAdapter mGoodsAdapter;

    private Pagination mPagination;

    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;

    @Override protected int getContentViewLayout() {
        return R.layout.activity_collect;
    }

    @Override protected void initView() {
        new ToolbarWrapper(this).setCustomTitle(R.string.collect_title);
        mPagination = new Pagination();
        mPtrWrapper = new PtrWrapper(this) {
            @Override public void onRefresh() {
                ApiCollectList apiCollectList = new ApiCollectList(mPagination);
                enqueue(apiCollectList);
            }
        };
        mGoodsAdapter = new GoodsAdapter();
        recyclerView.setAdapter(mGoodsAdapter);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mGridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        //刷新
        mPtrWrapper.postRefresh(50);
    }

    @Override
    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {
        mPtrWrapper.stopRefresh();
        switch (apiPath) {
            case ApiPath.COLLECT_LIST:
                if (success) {
                    ApiCollectList.Rsp listRsp = (ApiCollectList.Rsp) rsp;
                    mGoodsAdapter.reset(listRsp.getGoodsList());
                }
                break;
            case ApiPath.COLLECT_DELETE:
                if (success) {
                    ToastWrapper.show(R.string.collect_msg_delete_success);
                    recyclerView.scrollToPosition(0);
                    mPtrWrapper.autoRefresh();
                }
                break;
            default:
                throw new UnsupportedOperationException(apiPath);
        }
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.acitivity_collect, menu);
        MenuItem grid = menu.findItem(R.id.menu_grid);
        MenuItem list = menu.findItem(R.id.menu_list);
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            grid.setVisible(false);
            list.setVisible(true);
        } else {
            grid.setVisible(true);
            list.setVisible(false);
        }
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_grid) {
            recyclerView.setLayoutManager(mGridLayoutManager);
            mGoodsAdapter.setType(CollectGoodsAdapter.TYPE_GRID);
            invalidateOptionsMenu();
            return true;
        }
        if (id == R.id.menu_list) {
            recyclerView.setLayoutManager(mLinearLayoutManager);
            mGoodsAdapter.setType(CollectGoodsAdapter.TYPE_LIST);
            invalidateOptionsMenu();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class GoodsAdapter extends CollectGoodsAdapter {
        private AlertWrapper mAlertWrapper;

        GoodsAdapter() {
            mAlertWrapper = new AlertWrapper().setAlertText(R.string.collect_msg_confirm_delete);
        }

        @Override public void onLongClicked(final CollectGoods collectGoods) {
            View.OnClickListener listener = v -> {
                int recId = collectGoods.getRecId();
                ApiCollectDelete apiCollectDelete = new ApiCollectDelete(recId);
                enqueue(apiCollectDelete);
                mAlertWrapper.dismiss();
            };
            mAlertWrapper.setConfirmListener(listener)
                    .showAlert(CollectActivity.this);
        }
    }

}
