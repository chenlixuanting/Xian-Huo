package cn.edu.guet.xianhuo.network.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import cn.edu.guet.xianhuo.network.core.ApiInterface;
import cn.edu.guet.xianhuo.network.core.ApiPath;
import cn.edu.guet.xianhuo.network.core.RequestParam;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;
import cn.edu.guet.xianhuo.network.entity.CollectGoods;
import cn.edu.guet.xianhuo.network.entity.Paginated;
import cn.edu.guet.xianhuo.network.entity.Pagination;

/**
 * 获取收藏列表.
 */
public class ApiCollectList implements ApiInterface {

    private final Req mReq;

    public ApiCollectList(Pagination pagination) {
        mReq = new Req();
        mReq.mPagination = pagination;
    }

    @NonNull
    @Override
    public String getPath() {
        return ApiPath.COLLECT_LIST;
    }

    @Nullable
    @Override
    public RequestParam getRequestParam() {
        return mReq;
    }

    @NonNull
    @Override
    public Class<? extends ResponseEntity> getResponseType() {
        return Rsp.class;
    }

    public static class Req extends RequestParam {
        @SerializedName("pagination")
        private Pagination mPagination;

        @Override
        protected int sessionUsage() {
            return SESSION_MANDATORY;
        }
    }

    public static class Rsp extends ResponseEntity {
        @SerializedName("paginated")
        private Paginated mPaginated;
        @SerializedName("data")
        private List<CollectGoods> mGoodsList;

        public Paginated getPaginated() {
            return mPaginated;
        }

        public List<CollectGoods> getGoodsList() {
            return mGoodsList;
        }
    }

}
