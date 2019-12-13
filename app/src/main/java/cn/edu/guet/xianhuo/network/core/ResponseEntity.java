package cn.edu.guet.xianhuo.network.core;

import com.google.gson.annotations.SerializedName;

import cn.edu.guet.xianhuo.network.entity.Status;

/**
 * 响应体的基类.
 */
public abstract class ResponseEntity {

    @SerializedName("status") private Status mStatus;

    public Status getStatus() {
        return mStatus;
    }

}
