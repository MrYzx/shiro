package com.yzx.shiro.controller;

import com.alibaba.fastjson.JSONObject;
import com.yzx.shiro.beans.SysHoliday;
import com.yzx.shiro.beans.SysUser;
import com.yzx.shiro.service.HolidayService;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请假流程控制器
 * @author yzx
 * @date 2020/01/01
 * @desc 使用工作流引擎，完成简单的流程控制
 */
@Controller
@RequestMapping("/com/yzx/holiday")
public class HolidayController {

    @Autowired
    RuntimeService runtimeService;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    HistoryService historyService;
    @Autowired
    TaskService taskService;
    @Autowired
    HolidayService holidayService;

    @RequestMapping("/ListPage")
    public String ListPage(Model model){
        return "page/holiday/holidayList";
    }

    @ApiOperation(value = "查看请假流程图")
    @RequestMapping("/viewHolidayImg")
    public String viewHolidayImg(Model model){
        return "page/holiday/holidayProcess";
    }

    @RequestMapping("/holidayPage")
    public String holidayPage(Model model){
        model.addAttribute("sysHoliday",new SysHoliday());
        return "page/holiday/holiday";
    }

    @ApiOperation("启动请假流程实例")
    @RequestMapping("/startHoliday")
    @ResponseBody
    public JSONObject startHoliday(SysHoliday holiday,String proessName){
        JSONObject json = new JSONObject();
        try{
            int pk = holidayService.addHoliday(holiday);
            if(pk > 0){
                //创建流程实例  流程定义的key需要知道
                ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(proessName, "holiday_"+pk);
                json.put("flag",true);
                json.put("msg","请假信息提交成功！");
            }else{
                json.put("flag",false);
                json.put("msg","提交请假信息失败！");
            }
        }catch (Exception e){
            json.put("flag",false);
            json.put("msg","系统发生错误："+e);
            e.printStackTrace();
        }
        return json;
    }

    @ApiOperation("查询当前用户的流程实例，多条查询")
    @RequestMapping("/queryHolidayList")
    @ResponseBody
    public JSONObject queryHolidayList(int page,int limit,String proessName){
        List list = new ArrayList(10);
        Map<String,Object>map = new HashMap<>(16);
        JSONObject jsonObject = new JSONObject();
        //通过shiro 获取当前的用户信息
        Subject currentUser = SecurityUtils.getSubject();
        String userName = ((SysUser)currentUser.getPrincipal()).getUserName();
        //根据流程定义的key,负责人assignee来实现当前用户的任务列表查询
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey(proessName)
                .taskAssignee(userName)
                .list();
        if(taskList != null && taskList.size()>0){
            //任务列表的展示
            for(Task task :taskList){
                //ExecutionId
                map.put("executionId",task.getExecutionId());
                //任务名称
                map.put("name",task.getName());
                //任务负责人
                map.put("assignee",task.getAssignee());
                list.add(map);
            }
        }
        jsonObject.put("code","0");
        jsonObject.put("count",taskList.size());
        jsonObject.put("data",list);
        return jsonObject;
    }

    @ApiOperation("查询当前用户的流程实例，单条查询")
    @RequestMapping("/queryHoliday")
    public String queryHoliday(Model model,String proessName){
        //通过shiro 获取当前的用户信息
        Subject currentUser = SecurityUtils.getSubject();
        String userName = ((SysUser)currentUser.getPrincipal()).getUserName();
        //查询当前的任务【act_ru_task】
        try{
            Task task = taskService.createTaskQuery()
                    .processDefinitionKey(proessName)
                    .taskAssignee(userName)
                    .singleResult();
            if(task != null){
                //通过task 中的 getProcessInstanceId 查询指定的 processInstance 实例
                ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                        .processInstanceId(task.getProcessInstanceId())
                        .singleResult();
                if(processInstance != null){
                    //获取关联的业务表id
                    String businessKey = processInstance.getBusinessKey();
                    String id = businessKey.substring(businessKey.length()-2);
                    SysHoliday holiday = holidayService.findHolidayById(Integer.parseInt(id));
                    model.addAttribute("sysHoliday",holiday);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "page/holiday/holiday";
    }

    @ApiOperation("查询当前用户的历史请假记录")
    @RequestMapping("/queryHistoryHolidayList")
    @ResponseBody
    public JSONObject queryHistorqueryHistoryHolidayListyHoliday(){
        JSONObject json = new JSONObject();
        Subject currentUser = SecurityUtils.getSubject();
        String userName = ((SysUser)currentUser.getPrincipal()).getUserName();
        List<HistoricTaskInstance> taskList  = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(userName)
                .finished()
                .list();
        return json;
    }

    @ApiOperation("完成当前的任务进度")
    @RequestMapping("/completeTask")
    @ResponseBody
    public JSONObject completeTask(String proessName,String excutionId){
        JSONObject json = new JSONObject();
        //通过shiro 获取当前的用户信息
        Subject currentUser = SecurityUtils.getSubject();
        String userName = ((SysUser)currentUser.getPrincipal()).getUserName();
        try{
            //查询当前的任务信息
            Task task = taskService.createTaskQuery()
                    .processDefinitionKey(proessName)
                    .executionId(excutionId)
                    .taskAssignee(userName)
                    .singleResult();
            if(task != null){
                //处理任务,结合当前用户任务列表的查询操作的话,任务ID:task.getId()
                taskService.complete(task.getId());
                json.put("flag",true);
                json.put("msg","请假信息发送成功！");
            }else{
                json.put("flag",false);
                json.put("msg","没有查到任务信息！");
            }
        }catch (Exception e){
            json.put("flag",false);
            json.put("msg",e);
            e.printStackTrace();
        }
        return json;
    }

    @ApiOperation("查看流程定义的流程图")
    @RequestMapping("/viewProcessImg")
    public void viewProcessImg() throws IOException {
        // 从仓库中找需要展示的文件
        String deploymentId = "17501";
        List<String> names = repositoryService.getDeploymentResourceNames(deploymentId);
        String imageName = null;
        for (String name : names) {
            if (name.indexOf(".png") >= 0) {
                imageName = name;
            }
        }
        if (imageName != null) {
            System.out.println(imageName);
            File f = new File("d:/" + "ddd.png");
            // 通过部署ID和文件名称得到文件的输入流
            InputStream in = repositoryService.getResourceAsStream(deploymentId, imageName);
            FileUtils.copyInputStreamToFile(in,f);
        }
    }

    @ApiOperation("查看流程定义的流程图")
    @RequestMapping("/viewProcessImg2")
    public String viewProcessImg2(HttpServletResponse resp,String deploymentId) throws IOException {
        resp.setContentType("image/jpeg");
        InputStream in = null;
        deploymentId = "17501";
        try {
            // 从仓库中找需要展示的文件
            List<String> names = repositoryService.getDeploymentResourceNames(deploymentId);
            String imageName = null;
            for (String name : names) {
                if (name.indexOf(".png") >= 0) {
                    imageName = name;
                }
            }
            if (imageName != null) {
                // 通过部署ID和文件名称得到文件的输入流
                in = repositoryService.getResourceAsStream(deploymentId, imageName);
            }
            OutputStream out = resp.getOutputStream();
            // 把图片的输入流程写入resp的输出流中
            byte[] b = new byte[1024];
            for (int len = -1; (len= in.read(b))!=-1; ) {
                out.write(b, 0, len);
            }
            // 关闭流
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation("下载文件")
    @RequestMapping("/download")
    public void download(String fileName, HttpServletResponse response) throws IOException{
        fileName = "ddd.png";
        File file = new File("D:\\ddd.png");
        if(null != file){
            OutputStream out =null;
            InputStream in = null;
            try{
                in = new BufferedInputStream(new FileInputStream(file));
                byte[] buffer = new byte[in.available()];
                in.read(buffer);
                response.reset();
                response.setHeader("content-disposition","attachment;filename=" + encodingFileName(fileName));
                out = new BufferedOutputStream(response.getOutputStream());
                out.write(buffer);
            }catch(IOException e){
                e.printStackTrace();
            }finally{
                if(null != in){
                    in.close();
                }
                if(null != out){
                    out.close();
                }
            }
        }
    }

    //对文件名进行编码，解决下载文件名中文乱码的问题
    private static String encodingFileName(String fileName) {
        String returnFileName = "";
        try {
            returnFileName = URLEncoder.encode(fileName, "UTF-8");
            returnFileName = StringUtils.replace(returnFileName, "+", "%20");
            if (returnFileName.length() > 150) {
                returnFileName = new String(fileName.getBytes("GB2312"), "ISO8859-1");
                returnFileName = StringUtils.replace(returnFileName, " ", "%20");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return returnFileName;
    }
}
