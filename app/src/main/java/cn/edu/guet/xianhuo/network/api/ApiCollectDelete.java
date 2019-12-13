package cn.edu.guet.xianhuo.network.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import cn.edu.guet.xianhuo.network.core.ApiInterface;
import cn.edu.guet.xianhuo.network.core.ApiPath;
import cn.edu.guet.xianhuo.network.core.RequestParam;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;

/**
 * 删除收藏
 */
public class ApiCollectDelete implements ApiInterface {

    private final Req mReq;

    public ApiCollectDelete(int recId) {
        mReq = new Req();
        mReq.mRecId = recId;
    }

    @NonNull
    @Override
    public String getPath() {
        return ApiPath.COLLECT_DELETE;
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
        @SerializedName("rec_id")
        private int mRecId;

        @Override
        protected int sessionUsage() {
            return SESSION_MANDATORY;
        }
    }

    public static class Rsp extends ResponseEntity {
    }

}
