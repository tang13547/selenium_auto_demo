package cn.youedata.test;

import cn.youedata.action.ElementAction;
import cn.youedata.action.LoginAction;
import cn.youedata.config.TestTep;
import cn.youedata.utils.*;
import org.dom4j.DocumentException;
import org.json.JSONObject;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;

/**
 * 测试使用testlink
 */
public class TestLink extends  TestBase{

    ElementAction action = new ElementAction();
    private APIObject apiObject;
    private String caseName;
    @Parameters({"url","devKey","projectName","planName","buildName","platformName","caseName"})
    @BeforeMethod
    public void init(String url,String devKey,String projectName,String planName,String buildName,String platformName,String caseName){
        apiObject = new APIObject(url,devKey,projectName,planName,buildName,platformName);
        this.caseName = caseName;
    }

    //数据驱动案例--start
    @DataProvider(name="longinData")
    public Iterator<Object[]> loginData()
    {
        //读取登录用例测试数据
        String filePath="src/main/resources/data/loginData1.xls";
        //读取第二个sheet2
        return ExcelReadUtil.readExcel(filePath,"sheet2",4);
    }
    @Test(description="登录失败用例",dataProvider = "longinData")
    public void loginFail (TestTep tep) throws IOException, DocumentException {
        //代替testng参数化的方法
        String BaseUrl= XmlReadUtil.getTestngParametersValue("testng.xml","BaseUrl");
        //调用登录方法
        JSONObject json = JsonUtil.getJson(tep.getTestData());
        LoginAction loginAction = new LoginAction(BaseUrl+"/new/login.aspx",(String)json.get("用户名"),(String)json.get("密码"));
        action.sleep(1);
        //设置检查点
        Assertion.VerityTextPresent(tep.getExpectResult());
        //设置断言
        Assertion.VerityError();
    }
    //数据驱动案例--end

    public void testLinkd(ITestResult t){
        Integer tcId = apiObject.exectueTestCase(caseName, t.getStatus());
        if(t.getStatus() == 2){
            ScreenShot ss = new ScreenShot(driver);
            apiObject.testUpload(tcId, ss.takeScreenshot(), caseName, "附件的描述信息", "caseName附件","");
        }
    }
    @AfterMethod
    public void afterTest(ITestResult t){
        testLinkd(t);
    }


}
