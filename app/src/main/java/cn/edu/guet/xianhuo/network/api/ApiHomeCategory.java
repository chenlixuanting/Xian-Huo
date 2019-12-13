package cn.edu.guet.xianhuo.network.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import cn.edu.guet.xianhuo.network.core.ApiInterface;
import cn.edu.guet.xianhuo.network.core.ApiPath;
import cn.edu.guet.xianhuo.network.core.RequestParam;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;
import cn.edu.guet.xianhuo.network.entity.CategoryHome;

/**
 * 首页-商品分类
 */
public class ApiHomeCategory implements ApiInterface {

    @NonNull
    @Override
    public String getPath() {
        return ApiPath.HOME_CATEGORY;
    }

    @Nullable
    @Override
    public RequestParam getRequestParam() {
        return null;
    }

    @NonNull
    @Override
    public Class<? extends ResponseEntity> getResponseType() {
        return Rsp.class;
    }

    public static class Rsp extends ResponseEntity {
        @SerializedName("data")
        private List<CategoryHome> mData;

        public List<CategoryHome> getData() {
            return mData;
        }
    }

}
