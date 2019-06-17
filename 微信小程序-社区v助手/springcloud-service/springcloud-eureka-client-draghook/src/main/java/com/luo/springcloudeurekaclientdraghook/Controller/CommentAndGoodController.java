package com.luo.springcloudeurekaclientdraghook.Controller;

import com.luo.springcloudeurekaclientdraghook.Service.ActiveMQService;
import com.luo.springcloudeurekaclientdraghook.Service.DraghookService;
import com.luo.springcloudeurekaclientdraghook.Service.RelmeService;
import com.luo.springcloudeurekaclientdraghook.Tools.WebTool.ResultMent;
import com.luo.springcloudeurekaclientdraghook.Tools.WebTool.SendJson;
import com.luo.springcloudeurekaclientdraghook.entity.Comment;
import com.luo.springcloudeurekaclientdraghook.entity.Draghook;
import com.luo.springcloudeurekaclientdraghook.entity.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class CommentAndGoodController {

    @Autowired
    DraghookService draghookService;

    @Autowired
    RelmeService relmeService;

    @Autowired
    ActiveMQService activeMQService;
    //统计是否有与我有关的新消息
    @GetMapping("/CommentAndGoodcount")
    public void CommentAndGoodcountController(HttpServletRequest request, HttpServletResponse response,
                                              @RequestParam(required = true) String curKey
    ) throws IOException
    {

        ResultMent resultMent = new ResultMent();

        if(relmeService.lookRelme(curKey)) {
            resultMent.setStatus(1);//需要提醒
        }else
        {
            resultMent.setStatus(0);//不需要提醒
        }
        SendJson.sendJsonObject(request, response, resultMent);
    }

    //取消提醒
    @GetMapping("/RemoveRelme")
    public void RemoveRelmeController(HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam(required = true) String curKey
    ) throws IOException {



        relmeService.readedRelme(curKey);
    }

    @PostMapping("/CommentNewOne")
    public  void  CreateCommentController(HttpServletRequest request, HttpServletResponse response,
                                          @RequestParam Integer id,
                                          @RequestParam String key,
                                          @RequestParam String name,
                                          @RequestParam String icon,
                                          @RequestParam String content,
                                          @RequestParam String replyKey,
                                          @RequestParam String replyName
    )throws IOException
    {



        System.out.println("获得的评论内容是"+content);
        Comment comment =new Comment(key,name,icon,replyName,replyKey,content);
        Draghook draghook=draghookService.getDraghooksById(id);
        draghook.addComment(comment);

        //在redis中生成提示
        relmeService.SetRelme(replyKey,id);

        //保持这次修改
        draghookService.SaveNewDraghook(draghook);

    }


    //查看是否点赞过
    @GetMapping("/LookGood")
    public void LookIsGood(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(required = true) Integer id,
                           @RequestParam(required = true) String key

    )throws IOException
    {


        Draghook draghook=draghookService.getDraghooksById(id);

        ResultMent resultMent =new ResultMent();

        List<Good> list =draghook.getGoods();

        int flag=1;
        for(Good good:list)
        {
            if(key.equals(good.getKey()))
            {
                flag=0;

                break;

            }
        }
        resultMent.setStatus(flag);
        SendJson.sendJsonObject(request,response,resultMent);

    }




    //创建点赞
    @PostMapping("/GoodNewOne")
    public  void  CreateCommentController(HttpServletRequest request, HttpServletResponse response,
                                          @RequestParam(required = true) Integer id,
                                          @RequestParam(required = true) String key,
                                          @RequestParam(required = true) String name,
                                          @RequestParam(required = true) String icon,
                                          @RequestParam(required = true) Integer type
    )throws IOException
    {


        Draghook draghook = draghookService.getDraghooksById(id);
         if(type==1) { //进行点赞
             Good good = new Good(key, name, icon);
             System.out.println("创建Good" + key + name + icon);

             draghook.addGood(good);
         }
         else {//取消点赞

             List<Good>goods=draghook.getGoods();
             if(goods!=null&&goods.size()!=0) {
                 for (  int i=goods.size()-1;i>=0;i--) {
                     if (goods.get(i).getKey().equals(key)) {
                         goods.remove(goods.get(i));
                     }
                 }
                 draghook.setGoods(goods);
             }
         }
        //保持这次修改
       draghookService.SaveNewDraghook(draghook);
    }


     //撤回自己评论
    @GetMapping("/RemoveComment")
    public void RemoveComment(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(required = true)Integer dynid,
                          @RequestParam(required = true)Integer commentid
    ) throws IOException {



        ResultMent resultMent = new ResultMent();

       Draghook draghook =draghookService.getDraghooksById(dynid);

       List<Comment>list=draghook.getComments();


        if(list!=null&&list.size()!=0) {
            for (  int i=list.size()-1;i>=0;i--) {
                if (list.get(i).getId()==commentid) {
                 list.remove(list.get(i));
                }
            }
        }

        draghook.setComments(list);
       draghookService.SaveNewDraghook(draghook);
        SendJson.sendJsonObject(request, response, resultMent);

    }

}
