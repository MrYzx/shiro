package com.yzx.shiro.controller;

import com.alibaba.fastjson.JSONObject;
import com.yzx.shiro.beans.SysHoliday;
import com.yzx.shiro.beans.SysUser;
import com.yzx.shiro.service.HolidayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
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
 * 请假流程控制器2【带有条件判断的流程】
 * @author yzx
 * @date 2020/01/01
 * @desc
 */
@Api("带有条件的请假流程")
@Controller
@RequestMapping("/com/yzx/holiday2")
public class Holiday2Controller {

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
        return "page/holiday2/holidayList";
    }

    @ApiOperation(value = "查看请假流程图")
    @RequestMapping("/viewHolidayImg")
    public String viewHolidayImg(Model model){
        return "page/holiday2/holidayProcess2";
    }

    @ApiOperation("到请假历史信息页面")
    @RequestMapping("/holidayHistory")
    public String holidayHistory(Model model){
        return "page/holiday2/holidayHistory";
    }

    @RequestMapping("/holidayPage")
    public String holidayPage(Model model){
        model.addAttribute("sysHoliday",new SysHoliday());
        return "page/holiday2/holiday";
    }

    @ApiOperation(value = "消息信息页面")
    @RequestMapping("/messageInfo")
    public String messageInfo(Model model,String taskId,String processInstanceId){
        model.addAttribute("taskId",taskId);
        model.addAttribute("processInstanceId",processInstanceId);
        return "page/holiday2/messageInfo";
    }

    @ApiOperation("启动请假流程实例")
    @RequestMapping("/startHoliday")
    @ResponseBody
    public JSONObject startHoliday(SysHoliday holiday,String proessName){
        JSONObject json = new JSONObject();
        Map<String,Object>map = new HashMap<>();
        Subject currentUser = SecurityUtils.getSubject();
        String userName = ((SysUser)currentUser.getPrincipal()).getUserName();
        map.put("user1",userName);
        try{
            int pk = holidayService.addHoliday(holiday);
            if(pk > 0){
                //启动流程实例，并设置流程执行人员
                runtimeService.startProcessInstanceByKey(proessName, "holiday_"+pk,map);
                json.put("flag",true);
                json.put("msg","请假信息提交成功！");
            }else{
                json.put("flag",false);
                json.put("msg","提交请假信息失败！");
            }
        }catch (ActivitiException e){
            json.put("flag",false);
            json.put("msg","抱歉，请假流程被终止，暂时无法请假！");
        } catch(Exception e){
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
                Map<String,Object>map = new HashMap<>();
                //taskId
                map.put("id",task.getId());
                //processInstanceId
                map.put("processInstanceId",task.getProcessInstanceId());
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
            //因为加了单条数据的验证，所以不会出现查到多条数据的情况
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
    public JSONObject queryHistorqueryHistoryHolidayListyHoliday(int page,int limit){
        JSONObject json = new JSONObject();
        Subject currentUser = SecurityUtils.getSubject();
        String userName = ((SysUser)currentUser.getPrincipal()).getUserName();
        //查询总的数据
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(userName)
                //.processFinished()
                .list();
        //分页查询数据
        List<HistoricTaskInstance> taskList  = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(userName)
                //.processFinished() //查询流程结束的历史任务
                //第一个参数是从第几条开始，第二个参数是每页显示几条数据
                .listPage(page==1?page:((page-1)*limit+1),limit);
        json.put("code","0");
        json.put("count",list.size());
        json.put("data",taskList);
        return json;
    }

    @ApiOperation("完成当前的任务进度")
    @RequestMapping("/completeTask")
    @ResponseBody
    public JSONObject completeTask(String proessName,String taskId,String messageInfo){
        SysHoliday holiday = new SysHoliday();
        holiday.setHtime(6);
        JSONObject json = new JSONObject();
        //通过shiro 获取当前的用户信息
        Subject currentUser = SecurityUtils.getSubject();
        String userName = ((SysUser)currentUser.getPrincipal()).getUserName();
        try{
            if(StringUtils.isNotEmpty(taskId)){
                //查询当前的任务信息
                Task task = taskService.createTaskQuery()
                        .processDefinitionKey(proessName)
                        .taskId(taskId)
                        .taskAssignee(userName)
                        .singleResult();
                if(task != null){
                    //处理任务,结合当前用户任务列表的查询操作的话,任务ID:task.getId()
                    //批注人的名称  一定要写，不然查看的时候不知道人物信息
                    Authentication.setAuthenticatedUserId(userName);
                    //设置条件变量
                    taskService.setVariable(task.getId(),"holiday",holiday);
                    // 添加批注信息  comment为批注内容
                    taskService.addComment(task.getId(), null, messageInfo);
                    taskService.complete(task.getId());
                    json.put("flag",true);
                    json.put("msg","请假信息发送成功！");
                }else{
                    json.put("flag",false);
                    json.put("msg","没有查到任务信息！");
                }
            }else{
                json.put("flag",false);
                json.put("msg","任务Id 为空，请检查信息！");
            }
        }catch (Exception e){
            json.put("flag",false);
            json.put("msg",e);
            e.printStackTrace();
        }
        return json;
    }

    @ApiOperation("判断是否存在任务信息")
    @RequestMapping("/jugementTask")
    @ResponseBody
    public JSONObject jugementTask(String proessName){
        JSONObject json = new JSONObject();
        List list = new ArrayList(10);
        Map<String,Object>map = new HashMap<>(16);
        JSONObject jsonObject = new JSONObject();
        Subject currentUser = SecurityUtils.getSubject();
        String userName = ((SysUser)currentUser.getPrincipal()).getUserName();
        //根据流程定义的key,负责人assignee来实现当前用户的任务列表查询
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey(proessName)
                .taskAssignee(userName)
                .list();
        if(taskList != null && taskList.size()>0){
            json.put("flag",false);
            json.put("msg","抱歉，你还有未审核完的请假,不能再次进行操作！");
        }else{
            json.put("flag",true);
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
        deploymentId = "130001";
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

    /**
     * 对文件名进行编码，解决下载文件名中文乱码的问题
     */
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

    @ApiOperation("获取对应任务的评论信息")
    @RequestMapping("/getProcessComments")
    public String getProcessComments(String taskId,Model model) {
        List<Comment> historyCommnets = new ArrayList<>();
        //通过流程实例查询所有的(用户任务类型)历史活动
        List<HistoricActivityInstance> hais = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(taskId).list();
        //查询每个历史任务的批注
        for (HistoricActivityInstance hai : hais) {
            String historytaskId = hai.getTaskId();
            List<Comment> comments = taskService.getTaskComments(historytaskId);
            //如果当前任务有批注信息,添加到集合中
            if(comments!=null && comments.size()>0){
                historyCommnets.addAll(comments);
            }
        }
        model.addAttribute("comments",historyCommnets);
        return "page/holiday/holidayComment";
    }

    @ApiOperation("激活或者挂载全部的流程定义")
    @RequestMapping("/activeOrSuspend")
    @ResponseBody
    public JSONObject activeOrSuspend(String proessName){
        JSONObject json = new JSONObject();
        try{
            //查询流程定义的对象
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionKey("myProcess_1").singleResult();
            //得到当前流程定义的实例是否都为暂停状态
            boolean suspended = processDefinition.isSuspended();
            String processDefinitionId = processDefinition.getId();
            //判断
            if(suspended){
                //说明是暂停，就可以激活操作
                repositoryService.activateProcessDefinitionById(processDefinitionId,true
                        ,null);
                json.put("msg","流程定义："+processDefinitionId+"激活");
            }else{
                repositoryService.suspendProcessDefinitionById(processDefinitionId,true,null);
                json.put("msg","流程定义："+processDefinitionId+"挂载");
            }
        }catch (Exception e){
            json.put("错误信息：",e);
            e.printStackTrace();
        }
        return json;
    }

    @ApiOperation("激活或者挂载指定的单个流程")
    @RequestMapping("/activeOrSuspendSingle")
    @ResponseBody
    public JSONObject activeOrSuspendSingle(String processInstanceId){
        JSONObject json = new JSONObject();
        try{
            //查询流程实例对象
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(processInstanceId).singleResult();
            //得到当前流程定义的实例是否都为暂停状态
            boolean suspended = processInstance.isSuspended();
            //判断
            if(suspended){
                //说明是暂停，就可以激活操作
                runtimeService.activateProcessInstanceById(processInstanceId);
                json.put("msg","激活成功！");
            }else{
                runtimeService.suspendProcessInstanceById(processInstanceId);
                json.put("msg","挂载成功！");
            }
            json.put("flag",true);
        }catch (Exception e){
            json.put("msg",e);
            json.put("flag",false);
            e.printStackTrace();
        }
        return json;
    }

    @ApiOperation("激活或者挂载指定的单个流程")
    @RequestMapping("/jugementSate")
    @ResponseBody
    public JSONObject jugementSate(String processInstanceId){
        JSONObject json = new JSONObject();
        try{
            //查询流程实例对象
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(processInstanceId).singleResult();
            //得到当前流程定义的实例是否都为暂停状态
            boolean suspended = processInstance.isSuspended();
            //任务挂载
            if(suspended){
                json.put("flag",false);
                json.put("msg","当前请假处于暂停状态，不能进行处理,请先进行激活！");
            }else{//任务激活
                json.put("flag",true);
            }
        }catch (Exception e){
            json.put("msg",e);
            json.put("flag",false);
            e.printStackTrace();
        }
        return json;
    }
}
