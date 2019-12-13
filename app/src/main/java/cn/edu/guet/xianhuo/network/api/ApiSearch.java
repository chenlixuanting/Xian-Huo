package cn.edu.guet.xianhuo.network.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import cn.edu.guet.xianhuo.network.core.ApiInterface;
import cn.edu.guet.xianhuo.network.core.ApiPath;
import cn.edu.guet.xianhuo.network.core.RequestParam;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;
import cn.edu.guet.xianhuo.network.entity.Filter;
import cn.edu.guet.xianhuo.network.entity.Paginated;
import cn.edu.guet.xianhuo.network.entity.Pagination;
import cn.edu.guet.xianhuo.network.entity.SimpleGoods;

/**
 * 服务器接口: 搜索商品.
 */
public class ApiSearch implements ApiInterface {

    private Req mReq;

    public ApiSearch(Filter filter, Pagination pagination) {
        mReq = new Req();
        mReq.mFilter = filter;
        mReq.mPagination = pagination;
    }

    @NonNull
    @Override
    public String getPath() {
        return ApiPath.SEARCH;
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
        @SerializedName("filter")
        private Filter mFilter;
        @SerializedName("pagination")
        private Pagination mPagination;

        @Override
        protected int sessionUsage() {
            return SESSION_NO_NEED;
        }
    }

    public static class Rsp extends ResponseEntity {
        @SerializedName("data")
        private List<SimpleGoods> mData;
        @SerializedName("paginated")
        private Paginated mPaginated;

        public List<SimpleGoods> getData() {
            return mData;
        }

        public Paginated getPaginated() {
            return mPaginated;
        }
    }

}
