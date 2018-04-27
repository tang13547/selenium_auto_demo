package cn.youedata.pageObject;

import cn.youedata.utils.Locator;

import java.io.IOException;

public class LoginPage extends BasePage {
    private String path = "src/main/java/cn/youedata/pageObjectConfig/LoginPage.xml";

    public LoginPage() {
    //工程内读取对象库文件
        setXmlObjectPath(path);
        getLocatorMap();
    }

    /***
     * 账户登录
     * @return
     * @throws IOException
     */
    public Locator userLogin() throws IOException {
        Locator locator = getLocator("userLogin");
        return locator;
    }

    /***
     * 用户名
     * @return
     * @throws IOException
     */
    public Locator userName() throws IOException {
        Locator locator = getLocator("userName");
        return locator;
    }

    /***
     * 密码
     * @return
     * @throws IOException
     */
    public Locator password() throws IOException {
        Locator locator = getLocator("password");
        return locator;
    }

    /***
     * 登录
     * @return
     * @throws IOException
     */
    public Locator LoginBtn() throws IOException {
        Locator locator = getLocator("LoginBtn");
        return locator;
    }
}