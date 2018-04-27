package cn.youedata.pageObject;

import cn.youedata.test.TestBase;
import cn.youedata.utils.Locator;
import cn.youedata.utils.Log;
import cn.youedata.utils.XmlReadUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class BasePage {

	//protected 只在本包之内有权限，可访问
	//protected  WebDriver driver;
	// protected Log log;
	//定位信息图（对象库存储结构）
	protected HashMap<String,Locator>  locatorMap;
	public String path=null;
	public InputStream path_inputStream_1;
	public InputStream path_inputStream_2;
	Log log=new Log(this.getClass());

	public  void setXmlObjectPath(String path) {
		this.path=path;
	}
	public  void setXmlObjectPathForLocator(InputStream path_inputStream) {
		this.path_inputStream_1 = path_inputStream;
	}
	public  void setXmlObjectPathForPageURL(InputStream path_inputStream) {
		this.path_inputStream_2 = path_inputStream;
	}
	/**
	 * 构造方法，创建创建BasePageOpera对象时，需要初始化的信息.传递相关参数
	 * @param driver
	 * @param path 对象库文件位置
	 * this.getClass().getCanonicalName() 获取page类路径，也就是xml文档中的pageName
	 * @throws Exception
	 */
	public BasePage() {


	}
	public void getLocatorMap() {
		XmlReadUtil xmlReadUtil=new XmlReadUtil();
		try {
			if((path == null||path.isEmpty())) {
				locatorMap = xmlReadUtil.readXMLDocument(path_inputStream_1, this.getClass().getCanonicalName());}
			else {
				locatorMap = xmlReadUtil.readXMLDocument(path, this.getClass().getCanonicalName());
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/*static By getBy (ByType byType, Locator locator){
		switch(byType){
			case id:
				return By.id(locator.getElement());
			case cssSelector:
				return By.cssSelector(locator.getElement());
			case name:
				return By.name(locator.getElement());
			case xpath:
				return By.xpath(locator.getElement());
			case className:
				return By.className(locator.getElement());
			case tagName:
				return By.tagName(locator.getElement());
			case linkText:
				return By.linkText(locator.getElement());
			case partialLinkText:
				return By.partialLinkText(locator.getElement());
			//return null也可以放到switch外面
			default:
				return null;
		}


	}*/

	/**
	 * 从对象库获取定位信息
	 * @param locatorName 对象库名字
	 * @return
	 * @throws IOException
	 */
	public Locator getLocator(String locatorName)
	{
		Locator locator;
		/**
		 * 在对象库通过对象名字查找定位信息
		 */
		locator = locatorMap.get(locatorName);
		/**
		 * 加入对象库，找不到该定位信息，就创建一个定位信息
		 */
		if(locator == null)
		{
			log.error("没有找到"+locatorName+"页面元素");
		}
		return locator;

	}

	public String getPageURL() {
		String pageURL=null;
		try {
			if(path==null||path.isEmpty()) {
				pageURL=XmlReadUtil.getXmlPageURL(path_inputStream_1, this.getClass().getCanonicalName());
			}
			else {
				pageURL=XmlReadUtil.getXmlPageURL(path, this.getClass().getCanonicalName());
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return pageURL;
	}


}
