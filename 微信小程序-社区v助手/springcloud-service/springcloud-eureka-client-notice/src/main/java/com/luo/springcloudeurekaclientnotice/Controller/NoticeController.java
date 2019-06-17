package com.luo.springcloudeurekaclientnotice.Controller;

import com.luo.springcloudeurekaclientnotice.Service.ActiveMQService;
import com.luo.springcloudeurekaclientnotice.Service.NoticeService;
import com.luo.springcloudeurekaclientnotice.Tools.WebTool.ResultMent;
import com.luo.springcloudeurekaclientnotice.Tools.WebTool.SendJson;
import com.luo.springcloudeurekaclientnotice.entity.Comment;
import com.luo.springcloudeurekaclientnotice.entity.Document;
import com.luo.springcloudeurekaclientnotice.entity.Good;
import com.luo.springcloudeurekaclientnotice.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @Autowired
    ActiveMQService activeMQService;

    //fen分页查询意见
   @GetMapping("/GetNoticeByPage")
    public void GetNoticeByPage(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(required = true) Integer offset


    ) throws IOException {
       request.setCharacterEncoding("UTF-8");
       response.setCharacterEncoding("UTF-8");
        List<Notice>list=noticeService.listNoticeByPage(offset);
       System.out.println("查询意见");
        ResultMent resultMent = new ResultMent();
        resultMent.setStatus(offset+list.size());
        resultMent.setData(list);
        SendJson.sendJsonObject(request, response, resultMent);

    }

   //创建新的意见
    @PostMapping("/GreateNewNotice")
    public void GreateNewNotice(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody Notice notice


    ) throws IOException {


        activeMQService.sendMessageforSaveNotice(notice);
        ResultMent resultMent = new ResultMent();
        resultMent.setStatus(1);
        SendJson.sendJsonObject(request, response, resultMent);
    }
    //根据id获取建议
    @GetMapping("/GetNoticeById")
    public void GreateNewNotice(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(required = true)Integer id


    ) throws IOException {

        Notice notice=noticeService.GetNoticebyId(id);

        ResultMent resultMent = new ResultMent();
        resultMent.setData(notice);
        SendJson.sendJsonObject(request, response, resultMent);
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




        Comment comment =new Comment(key,name,icon,replyName,replyKey,content);
        Notice notice=noticeService.GetNoticebyId(id);
        notice.addComment(comment);
        //保持这次修改
       noticeService.NewNotice(notice);
    }



    //查看是否点赞过
    @GetMapping("/LookGood")
    public void LookIsGood(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(required = true) Integer id,
                           @RequestParam(required = true) String key

    )throws IOException
    {


        Notice notice=noticeService.GetNoticebyId(id);

        ResultMent resultMent =new ResultMent();

        List<Good> list =notice.getGoods();

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
                                          @RequestParam(required = true) String icon
    )throws IOException
    {

        Good good =new Good(key,name,icon);
         Notice notice=noticeService.GetNoticebyId(id);
        notice.addGood(good);

        //保持这次修改
        noticeService.NewNotice(notice);

    }

    //撤回自己评论
    @GetMapping("/RemoveComment")
    public void RemoveComment(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(required = true)Integer noticeid,
                              @RequestParam(required = true)Integer commentid
    ) throws IOException {



        ResultMent resultMent = new ResultMent();

       Notice notice =noticeService.GetNoticebyId(noticeid);

        List<Comment>list=notice.getComments();


        if(list!=null&&list.size()!=0) {
            for (  int i=list.size()-1;i>=0;i--) {
                if (list.get(i).getId()==commentid) {
                    list.remove(list.get(i));
                }
            }
        }

        notice.setComments(list);
        noticeService.NewNotice(notice);
        SendJson.sendJsonObject(request, response, resultMent);

    }


    //根据标题 模糊查询获取文档
    @GetMapping("/ManageNotice")
    public void ManageNotice(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(required = true)Integer id,
                             @RequestParam(required = true)Integer status,
                             @RequestParam(required = true)String input


    ) throws IOException ,Exception{

        Notice notice=noticeService.GetNoticebyId(id);
        notice.setSolution(input);
        notice.setStatus(status);

        noticeService.NewNotice(notice);
        ResultMent resultMent = new ResultMent();
        SendJson.sendJsonObject(request, response, resultMent);
    }
}
