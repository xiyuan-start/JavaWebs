package com.luo.springcloudeurekaclientdraghook.Controller;

import com.luo.springcloudeurekaclientdraghook.Service.ActiveMQService;
import com.luo.springcloudeurekaclientdraghook.Service.DraghookService;
import com.luo.springcloudeurekaclientdraghook.Service.RelmeService;
import com.luo.springcloudeurekaclientdraghook.Tools.DateUtils;
import com.luo.springcloudeurekaclientdraghook.Tools.WebTool.ResultMent;
import com.luo.springcloudeurekaclientdraghook.Tools.WebTool.SendJson;
import com.luo.springcloudeurekaclientdraghook.entity.Draghook;
import com.luo.springcloudeurekaclientdraghook.entity.Comment;
import com.luo.springcloudeurekaclientdraghook.entity.Good;
import org.apache.activemq.broker.region.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
public class draghookController {

    @Autowired
    DraghookService draghookService;

    @Autowired
    RelmeService relmeService;

    @Autowired
    ActiveMQService activeMQService;



    //创建新的拉钩task
    @PostMapping("/CreateNewDraghook")
    public void CreateDraghook(HttpServletRequest request, HttpServletResponse response,
                               @RequestBody Draghook draghook

    ) throws IOException ,JMSException{

        //对时间格式进行设置
        draghook.setVartime(DateUtils.parse(draghook.getCreateTime()));



        ResultMent resultMent = new ResultMent();
        try {
            activeMQService.sendMessage(draghook);

            resultMent.setStatus(1);
        } catch (Exception e) {
            resultMent.setMessage("建立拉钩失败");
            resultMent.setStatus(0);
            e.printStackTrace();
        }
        SendJson.sendJsonObject(request, response, resultMent);

    }


    //查看所有
    @GetMapping("/GetDraghooksByPage")
    public void GetDraghooksByPage(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(required = true) Integer offset,
                                   @RequestParam(required = true) Integer limit,
                                   @RequestParam(required = true) double   latitude,
                                   @RequestParam(required = true) double  longitude
    ) throws IOException {

        List<Draghook> list = draghookService.getDraghooksbyPage(offset, limit,latitude,longitude);
        ResultMent resultMent = new ResultMent();
        resultMent.setStatus(offset + list.size());
        resultMent.setData(list);
        SendJson.sendJsonObject(request, response, resultMent);

    }


    //查看被@的Task
    @GetMapping("/GetAboutMyDraghooks")
    public void GetAboutMyDraghooks(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(required = true) String curKey
    ) throws IOException

    {

        Set<Integer>set=relmeService.getDraghookSet(curKey);
        List<Draghook>list=draghookService.getDraghooksByIds(set);

        System.out.println("查看被@Task的长度"+list.size());
        ResultMent resultMent = new ResultMent();
        resultMent.setData(list);
        SendJson.sendJsonObject(request, response, resultMent);

    }



    //查看自己的
    @GetMapping("/GetMyDraghooksByPage")
    public void GetMyDraghooksByPage(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(required = true) Integer pageNum,
                                     @RequestParam(required = true) Integer pageSize,
                                     @RequestParam(required = true) String curKey
    ) throws IOException {

        List<Draghook> list = draghookService.getMyDraghooksbyPage(pageNum,pageSize,curKey);

        ResultMent resultMent = new ResultMent();
        resultMent.setData(list);
        SendJson.sendJsonObject(request, response, resultMent);

    }



    //刷新单个task
    @GetMapping("/UpdateDrahook")
    public void GetDraghookbyIdAndOpenIdController(HttpServletRequest request, HttpServletResponse response,
                                                   @RequestParam(required = true) Integer id

    ) throws IOException {



        Draghook draghook = draghookService.GetDraghookbyIdAndOpenId(id);
        ResultMent resultMent = new ResultMent();
        resultMent.setData(draghook);
        SendJson.sendJsonObject(request, response, resultMent);

    }

    //获得当前时间最近的拉钩
    @GetMapping("/latestDraghookDao")
    public void GetlatestDraghookbyOpenId(HttpServletRequest request, HttpServletResponse response,
                                          @RequestParam(required = true) String curKey,
                                          @RequestParam(required = true) double   latitude,
                                          @RequestParam(required = true) double  longitude
    ) throws IOException {

        List<Draghook> list = draghookService.GetLastestDraghooks(curKey,latitude,longitude);


        ResultMent resultMent = new ResultMent();

        resultMent.setData(list);
        SendJson.sendJsonObject(request, response, resultMent);

    }


    //获取task详情
    @GetMapping("/GetDraghookDaoDetail")
    public void GetDraghookDaoDetail(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(required = true)Integer id
    ) throws IOException {

        Draghook draghook= draghookService.getDraghooksById(id);

        ResultMent resultMent = new ResultMent();

        resultMent.setData(draghook);
        SendJson.sendJsonObject(request, response, resultMent);

    }

    //删除自己的
    @GetMapping("/RemoveDyn")
    public void RemoveDyn(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(required = true)Integer dynid
    ) throws IOException {



        ResultMent resultMent = new ResultMent();

       draghookService.removebydynId(dynid);

        SendJson.sendJsonObject(request, response, resultMent);

    }
}