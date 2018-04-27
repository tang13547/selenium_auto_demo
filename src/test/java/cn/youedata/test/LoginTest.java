package cn.youedata.test;


import cn.youedata.action.ElementAction;
import cn.youedata.action.LoginAction;
import cn.youedata.config.TestTep;
import cn.youedata.utils.Assertion;

import cn.youedata.utils.ExcelReadUtil;
import cn.youedata.utils.JsonUtil;
import cn.youedata.utils.XmlReadUtil;
import org.dom4j.DocumentException;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;

public class LoginTest extends TestBase {

	ElementAction action = new ElementAction();

	@Test(description="登录成功测试")
	@Parameters({"BaseUrl"})//读取testng.xml参数
	public void login(String BaseUrl) throws IOException
	{
		//调用登录方法，输入正确的用户名和密码
		LoginAction loginAction=new LoginAction(BaseUrl+"/new/login.aspx","\n" +
				"13541080141","waini1314");
		action.sleep(2);
		//设置检查点
		Assertion.VerityTextPresentPrecision("jd_8456195","输入正确的用户名和密码，验证是否成功进入主页");
		//设置用例断言，判断用例是否失败
		Assertion.VerityError();
	}
	//数据驱动案例--start
	@DataProvider(name="longinData")
	public Iterator<Object[]> loginData()
	{
		//读取登录用例测试数据
		String filePath="src/main/resources/data/loginData1.xls";
		//读取第一个sheet，第2行到第5行-第2到第4列之间的数据
		return ExcelReadUtil.readExcel(filePath,"sheet2",3);
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


}