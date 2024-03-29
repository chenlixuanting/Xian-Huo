package cn.edu.guet.xianhuo.network;


import cn.edu.guet.xianhuo.network.api.ApiRegion;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ApiRegionTest extends ApiTest {

    @Test public void getRegion() throws IOException {
        ApiRegion apiRegion = new ApiRegion(ApiRegion.ID_CHINA);
        ApiRegion.Rsp rsp = client.execute(apiRegion);

        Assert.assertTrue(rsp.getStatus().isSucceed());
    }
}
