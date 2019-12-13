package cn.edu.guet.xianhuo.network;

import org.junit.Test;

import java.io.IOException;

import cn.edu.guet.xianhuo.network.api.ApiCollectCreate;

public class ApiCollectCreateTest extends ApiSignInTest {

    @Test public void createCollect() throws IOException {
        signIn();
        ApiCollectCreate apiCollectCreate = new ApiCollectCreate(80);
        client.execute(apiCollectCreate);
    }
}
