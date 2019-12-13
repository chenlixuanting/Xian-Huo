package cn.edu.guet.xianhuo.network;


import cn.edu.guet.xianhuo.network.api.ApiHomeCategory;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ApiHomeCategoryTest extends ApiTest {

    @Test public void getHomeCategories() throws IOException {

        ApiHomeCategory apiHomeCategory = new ApiHomeCategory();
        ApiHomeCategory.Rsp rsp = client.execute(apiHomeCategory);
        Assert.assertTrue(rsp.getStatus().isSucceed());
    }
}
