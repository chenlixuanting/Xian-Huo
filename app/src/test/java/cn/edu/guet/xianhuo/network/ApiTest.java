package cn.edu.guet.xianhuo.network;


import org.junit.Before;

public class ApiTest {

    XHClient client;

    @Before public void setUp() throws Exception {
        client = XHClient.getInstance();
        client.setShowLog(true);
    }

}
