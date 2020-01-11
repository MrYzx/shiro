package com.yzx.shiro.controller;

import com.yzx.shiro.beans.SysUser;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @ApiOperation("启动请假流程实例")
    @RequestMapping("/startHoliday")
    @ResponseBody
    public String startHoliday(){

        //3.创建流程实例  流程定义的key需要知道 holiday
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_1");
        //4.输出实例的相关信息
        System.out.println("流程部署ID"+processInstance.getDeploymentId());//null
        System.out.println("流程定义ID"+processInstance.getProcessDefinitionId());//holiday:1:4
        System.out.println("流程实例ID"+processInstance.getId());//2501
        System.out.println("活动ID"+processInstance.getActivityId());//null
      /*   ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday");
         //4.输出实例的相关信息
         System.out.println("流程部署ID"+processInstance.getDeploymentId());//null
         System.out.println("流程定义ID"+processInstance.getProcessDefinitionId());//holiday:1:4
         System.out.println("流程实例ID"+processInstance.getId());//2501
         System.out.println("活动ID"+processInstance.getActivityId());//null
        return processInstance.getProcessDefinitionId();*/
      return "";
    }

    @ApiOperation("查询当前用户的流程实例，多条查询")
    @RequestMapping("/queryHolidayList")
    @ResponseBody
    public String queryHolidayList(){
        //通过shiro 获取当前的用户信息
        Subject currentUser = SecurityUtils.getSubject();
        String userName = ((SysUser)currentUser.getPrincipal()).getUserName();
        //根据流程定义的key,负责人assignee来实现当前用户的任务列表查询
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("myProcess_1")
                .taskAssignee(userName)
                .list();
        //4.任务列表的展示
        for(Task task :taskList){
            System.out.println("流程实例ID:"+task.getProcessInstanceId());
            System.out.println("任务ID:"+task.getId());
            System.out.println("任务负责人:"+task.getAssignee());
            System.out.println("任务名称:"+task.getName());
        }
        return "";
    }


    @ApiOperation("查询当前用户的流程实例，单条查询")
    @RequestMapping("/queryHoliday")
    @ResponseBody
    public String queryHoliday(){
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("myProcess_1")
                .taskAssignee("yzx")
                .singleResult();
        //4.任务列表的展示
        System.out.println("流程实例ID:"+task.getProcessInstanceId());
        System.out.println("任务ID:"+task.getId());  //5002
        System.out.println("任务负责人:"+task.getAssignee());
        System.out.println("任务名称:"+task.getName());
        return "";
    }

    @ApiOperation("查询当前用户的历史请假记录")
    @RequestMapping("/queryHistoryHolidayList")
    @ResponseBody
    public String queryHistorqueryHistoryHolidayListyHoliday(){
        return "";
    }

    @ApiOperation("完成当前的任务进度")
    @RequestMapping("/completeTask")
    @ResponseBody
    public String completeTask(){
        //通过shiro 获取当前的用户信息
        Subject currentUser = SecurityUtils.getSubject();
        String userName = ((SysUser)currentUser.getPrincipal()).getUserName();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("myProcess_1")
                .taskAssignee(userName)
                .singleResult();
        if(task != null){
            //处理任务,结合当前用户任务列表的查询操作的话,任务ID:task.getId()
            taskService.complete(task.getId());
            System.out.println(task.getId());
        }
        return "任务完成";
    }
}
