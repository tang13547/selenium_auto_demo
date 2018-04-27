package cn.youedata.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.testng.log4testng.Logger;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.model.Attachment;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestCaseStep;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

/**
 * 连接testlink
 */
public class APIObject {

	private static Logger logger = Logger.getLogger(APIObject.class);
	private static TestLinkAPI api = null;
	private String planName;
	private String projectName;
	private String url;
	private String devKey;
	private TestCase[] tcs;
	private TestPlan tp;
	private String buildName;
	private String platformName;

	public APIObject(String url, String devKey, String projectName,String planName, String buildName,
			String platformName) {
		super();
		this.planName = planName;
		this.projectName = projectName;
		this.url = url;
		this.devKey = devKey;
		this.buildName = buildName;
		this.platformName = platformName;
	}


	public  TestLinkAPI getApi() {
		if(api == null){
			try {
				api = new TestLinkAPI(new URL(url), devKey);
			} catch (TestLinkAPIException e) {
				logger.error(e.getMessage(),e);
			} catch (MalformedURLException e) {
				logger.error(e.getMessage(),e);
			}
		}
		return api;
	}

	public void getTestCases(){
		this.getApi();
		this.planName = planName;
		this.projectName = projectName;
		tp = api.getTestPlanByName(planName, projectName);
		tcs = api.getTestCasesForTestPlan(tp.getId(), null, null, null, null, null, null, null, ExecutionType.AUTOMATED, true, null);
	}
	
	public Integer exectueTestCase(String caseName,int status){
		this.getTestCases();
		Integer tpId = tp.getId();
		Integer tcId = 0;
		for (TestCase testCase : tcs) {
			if(caseName.equals(testCase.getName())){
				tcId = testCase.getId();
			}
		}
		switch (status) {
		case 1:
			api.reportTCResult(tcId, null, tpId, ExecutionStatus.PASSED, null, buildName, "自动化上传的结果", null, null, null, platformName, null, null);
			break;
        case 2:
        	api.reportTCResult(tcId, null, tpId, ExecutionStatus.FAILED, null, buildName, "自动化上传的结果", null, null, null, platformName, null, null);
			break;
        case 3:
        	api.reportTCResult(tcId, null, tpId, ExecutionStatus.BLOCKED, null, buildName, "自动化上传的结果", null, null, null, platformName, null, null);
			break;
		}
		return tcId;
	}
	
	public String getTcId(String caseName){
		this.getTestCases();
		Integer tpId = tp.getId();
		String tcId = null;
		for (TestCase testCase : tcs) {
			if(caseName.equals(testCase.getName())){
				tcId = testCase.getFullExternalId();
			}
		}
		return tcId;
	}
	
	public void testUpload(Integer tcId, String file, String title, String desc, String fileName, String fileType) {
		TestLinkAPI api = getApi();
		File file2 = new File(file);
		String fileContent = "";
		try {
			byte[] byteArray = FileUtils.readFileToByteArray(file2);
			fileContent = new String(Base64.encodeBase64(byteArray));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Attachment attachment = api.uploadTestCaseAttachment(tcId, title, desc, fileName, fileType, fileContent);
	}
	
	
	
	public static void main(String[] args) {
		APIObject a3 = new APIObject("http://172.16.0.15/testlink-1.9.16/lib/api/xmlrpc/v1/xmlrpc.php", "41d391cbbdc3b5241504b40d2ffb26a4", "testProject-001", "系统测试", "v0.003", null);
		a3.exectueTestCase("login003", 1);
	}
}
	
	
	
	
