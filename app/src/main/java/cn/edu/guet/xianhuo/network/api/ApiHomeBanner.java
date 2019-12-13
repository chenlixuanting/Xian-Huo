package cn.edu.guet.xianhuo.network.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import cn.edu.guet.xianhuo.network.core.ApiInterface;
import cn.edu.guet.xianhuo.network.core.ApiPath;
import cn.edu.guet.xianhuo.network.core.RequestParam;
import cn.edu.guet.xianhuo.network.core.ResponseEntity;
import cn.edu.guet.xianhuo.network.entity.Banner;
import cn.edu.guet.xianhuo.network.entity.SimpleGoods;

/**
 * 服务器接口: 轮播图和促销商品.
 */
public class ApiHomeBanner implements ApiInterface {

    @NonNull
    @Override
    public String getPath() {
        return ApiPath.HOME_DATA;
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
        private Data mData;

        public Data getData() {
            return mData;
        }

        public static class Data {
            @SerializedName("player")
            private List<Banner> mBanners;
            @SerializedName("promote_goods")
            private List<SimpleGoods> mGoodsList;

            public List<Banner> getBanners() {
                return mBanners;
            }

            public List<SimpleGoods> getGoodsList() {
                return mGoodsList;
            }
        }
    }

}
