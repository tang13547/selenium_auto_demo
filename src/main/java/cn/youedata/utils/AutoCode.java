package cn.youedata.utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

public class AutoCode {

    /**
     * 根据页面元素xml生成代码
     * @param path
     */
    public static void  generatCode(String path) throws Exception {

        File file = new File(path);
        if (!file.exists()){
            throw new IOException("Can't find " + path);
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        //获取文件根节点
        Element root = document.getRootElement();
        //遍历根节点下的第一个节点（page节点）
        Iterator iterator = root.elementIterator();
        while (iterator.hasNext()){
            Element page = (Element) iterator.next();
            //获取page节点的name属性值
            String pageName = page.attribute("pagename").getValue();
            //将pageName存储为数组
            String[] pageNameArray = pageName.split("\\.");
            //获取要写入的page所属的类名
            String pageClassName =  pageNameArray[pageNameArray.length - 1].toString();
            //获取对象库包名
            pageNameArray = Arrays.copyOf(pageNameArray,pageNameArray.length-1);
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < pageNameArray.length; i++){
                sb. append(pageNameArray[i]);
                sb.append(".");
            }
            String packageName = sb.toString().substring(0,sb.length() -1);

            //--自动编写对象库代码（XXPage.java）开始--
            StringBuffer sbCode = new StringBuffer("package "+packageName+";\n");
            sbCode.append("public class "+ pageClassName+" extends BaseAction {\n");
            sbCode.append("private String path = \""+ path +"\";\n");
            sbCode.append(" public   "
                    + pageClassName
                    + "() {\n");
            sbCode.append("//工程内读取对象库文件\n	");
            sbCode.append("setXmlObjectPath(path);\n");
            sbCode.append("getLocatorMap();");
            sbCode.append("\n}");
            //sb.append("\n private String path=PageObjectAutoCode.class.getClassLoader().getResource(\"net/hk515/pageObjectConfig/UILibrary.xml\").getPath();");
            //遍历Page节点下的Locator节点
            for(Iterator<?> j=page.elementIterator();j.hasNext();)
            {
                //获取locaror节点
                Element locator =(Element)j.next();
                String locatorName=locator.getTextTrim();
                if(locator.attributeCount()>3)
                {sbCode.append("\n/***\n"
                        + "* "+locator.attribute(3).getValue()+"\n"
                        + "* @return\n"
                        + "* @throws IOException\n"
                        + "*/\n");
                }
                else
                {
                    sbCode.append("\n");
                }
                sbCode.append("public Locator "+locatorName+"() throws IOException\n {\n");
                //sb.append("   setXmlObjectPath(path);\n");
                sbCode.append("   Locator locator=getLocator(\""+locatorName+"\");\n");
                sbCode.append("   return locator;\n }\n");
            }
            sbCode.append("}");
            //将自动生成的PageObject代码写入文件
            StringBuffer stringBuffer = new StringBuffer();
            for(int i = 0; i < pageNameArray.length; i++){
                stringBuffer. append(pageNameArray[i]);
                stringBuffer.append("/");
            }
            String pagePath = stringBuffer.toString();
            pagePath = "src/test/java/"+pagePath+pageClassName+".java";
            File pageObjectFile = new File(pagePath);
            if(pageObjectFile.exists())
            {
                pageObjectFile.delete();;
            }
            try {
                FileWriter fileWriter=new FileWriter(pageObjectFile, false);
                BufferedWriter output = new BufferedWriter(fileWriter);
                output.write(sbCode.toString());
                output.flush();
                output.close();
            } catch (IOException e1) {
                // TODO 自动生成的 catch 块
                e1.printStackTrace();
            }
            Log log=new Log(AutoCode.class);
            log.info("自动生成对象库java代码成功");

        }

    }

    public static void main(String[] args) throws Exception {
        String path = "src/main/java/cn/youedata/pageObjectConfig/LoginPage.xml";
        AutoCode.generatCode(path);
    }
}
