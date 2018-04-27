package cn.youedata.test;

import cn.youedata.action.ElementAction;
import cn.youedata.pageObject.LoginPage;
import cn.youedata.utils.Log;

import java.io.IOException;

public class Test extends TestBase{

    Log log = new Log(this.getClass());
    ElementAction ea = new ElementAction();
    LoginPage lp = new LoginPage();

    @org.testng.annotations.Test
    public void test() throws IOException {

        ea.open("http://www.baidu.com");
        ea.click(lp.userName());
    }

}