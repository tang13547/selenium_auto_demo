package cn.youedata.action;



import cn.youedata.pageObject.LoginPage;
import cn.youedata.test.TestBase;


import java.io.IOException;

public class LoginAction extends TestBase {
    public LoginAction(String Url, String UserName, String PassWord) throws IOException
    {
        //此driver变量继承自TestBase变量
        ElementAction el = new ElementAction();
        LoginPage loginPage = new LoginPage();
        el.open(Url);
        System.out.println(driver.getCurrentUrl());
        ElementAction action=new ElementAction();
        action.click(loginPage.userLogin());
        action.sleep(1);
        action.clear(loginPage.userName());
        action.type(loginPage.userName(),UserName);
        action.clear(loginPage.password());
        action.type(loginPage.password(),PassWord);
        action.click(loginPage.LoginBtn());
    }
}
