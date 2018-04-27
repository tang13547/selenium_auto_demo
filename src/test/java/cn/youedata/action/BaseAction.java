package cn.youedata.action;

import cn.youedata.test.TestBase;
import cn.youedata.utils.Locator;
import cn.youedata.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseAction extends TestBase {

    Log log=new Log(this.getClass());

    /**
     * 打开浏览器
     * @param url
     */
    public void open(String url)
    {
        driver.navigate().to(url);
        log.info("打开浏览器，访问"+url+"网址!");
        int i=1;

    }
    /***
     * 关闭浏览器窗口
     */
    public void close()
    {
        driver.close();
        log.info("关闭浏览器窗口");
    }
    /**
     * 退出浏览器
     */
    public void quit()
    {
        driver.quit();
        log.info("退出浏览器");
    }
    /**
     * 浏览器前进
     */
    public void forward()
    {
        driver.navigate().forward();
        log.info("浏览器前进");
    }
    /**
     * 浏览器后退
     */
    public void back()
    {
        driver.navigate().back();
        log.info("浏览器后退");
    }
    /**
     * 刷新浏览器
     */
    public void refresh()
    {
        driver.navigate().refresh();
        log.info("浏览器刷新");
    }
    public WebElement findElement(final Locator locator) throws IOException
    {
        /**
         * 查找某个元素等待几秒
         */
        Waitformax(Integer.valueOf(locator.getWaitSec()));
        WebElement webElement;
        webElement=getElement(locator);
        return webElement;


    }
    public void Waitformax(int t)
    {
        driver.manage().timeouts().implicitlyWait(t, TimeUnit.SECONDS);
    }
    /**
     * 通过定位信息获取元素
     * @param locator
     * @return
     * @throws NoSuchElementException
     */
    public WebElement getElement(Locator locator)
    {
        /**
         * locator.getElement(),获取对象库对象定位信息
         */
        //locator=getLocator(locatorMap.get(key));
        WebElement webElement;
        switch (locator.getBy())
        {
            case xpath :
                //log.info("find element By xpath");
                webElement=driver.findElement(By.xpath(locator.getElement()));
                /**
                 * 出现找不到元素的时候，记录日志文件
                 */
                break;
            case id:
                //log.info("find element By xpath");
                webElement=driver.findElement(By.id(locator.getElement()));
                break;
            case cssSelector:
                //log.info("find element By cssSelector");
                webElement=driver.findElement(By.cssSelector(locator.getElement()));
                break;
            case name:
                //log.info("find element By name");
                webElement=driver.findElement(By.name(locator.getElement()));
                break;
            case className:
                //log.info("find element By className");
                webElement=driver.findElement(By.className(locator.getElement()));
                break;
            case linkText:
                //log.info("find element By linkText");
                webElement=driver.findElement(By.linkText(locator.getElement()));
                break;
            case partialLinkText:
                //log.info("find element By partialLinkText");
                webElement=driver.findElement(By.partialLinkText(locator.getElement()));
                break;
            case tagName:
                //log.info("find element By tagName");
                webElement=driver.findElement(By.partialLinkText(locator.getElement()));
                break;
            default :
                //log.info("find element By xpath");
                webElement=driver.findElement(By.xpath(locator.getElement()));
                break;

        }
        return webElement;
    }



}
