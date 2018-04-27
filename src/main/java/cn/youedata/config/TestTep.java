package cn.youedata.config;

/**
 * 用例模板
 */
public class TestTep {

    private String projectPath;//项目路径
    private String actionName;//功能模块
    private String testName;//用例名称
    private String testDesc;//用例描述
    private String opeartor;//操作步骤
    private String testData;//输入数据
    private String expectResult;//预期结果
    private String testTeam;//测试组
    private String executeUser;//执行人
    private String planTime;//计划完成时间
    private String executeTime;//执行时间
    private String executeResult;//执行结果

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestDesc() {
        return testDesc;
    }

    public void setTestDesc(String testDesc) {
        this.testDesc = testDesc;
    }

    public String getOpeartor() {
        return opeartor;
    }

    public void setOpeartor(String opeartor) {
        this.opeartor = opeartor;
    }

    public String getTestData() {
        return testData;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }

    public String getExpectResult() {
        return expectResult;
    }

    public void setExpectResult(String expectResult) {
        this.expectResult = expectResult;
    }

    public String getTestTeam() {
        return testTeam;
    }

    public void setTestTeam(String testTeam) {
        this.testTeam = testTeam;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public String getExecuteResult() {
        return executeResult;
    }

    public void setExecuteResult(String executeResult) {
        this.executeResult = executeResult;
    }

    public String getExecuteUser() {
        return executeUser;
    }

    public void setExecuteUser(String executeUser) {
        this.executeUser = executeUser;
    }
}
