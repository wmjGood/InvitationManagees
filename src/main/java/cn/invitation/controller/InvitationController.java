package cn.invitation.controller;

import cn.invitation.pojo.Invitation;
import cn.invitation.pojo.ReplyDetail;
import cn.invitation.service.InvitationService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class InvitationController {
    @Resource
    private InvitationService invitationService;

    public InvitationService getInvitationService() {
        return invitationService;
    }


    public void setInvitationService(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    //显示信息
    @RequestMapping(value = "/show")
    @ResponseBody
    public void showInfo(@RequestParam(value = "title", required = false) String title,
                         Model model, Integer pageIndex,
                         HttpServletRequest request, HttpServletResponse response) {
        System.out.println("标题----------------------"+title);
        response.setCharacterEncoding("UTF-8");
        int pageIndex1 = pageIndex==null?1:pageIndex;
        List<Invitation> list = invitationService.findPageByCondition(title, pageIndex1);
        PageInfo<Invitation> pageInfo= new PageInfo<Invitation>(list);
        String data = JSON.toJSONString(pageInfo);
        try {
            response.getWriter().write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //回复列表
    @RequestMapping(value = "/messageList")
    public String messageList(Model model, HttpServletRequest request) {
        String id = request.getParameter("id");
        request.setAttribute("id", id);
        Integer invid = Integer.valueOf(id);
        List<ReplyDetail> messageList = invitationService.findAll(invid);
        model.addAttribute("messageList", messageList);
        return "messageList";
    }


    //添加回复信息
    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String messageList(HttpServletRequest request) {
        String id=(String)request.getParameter("id");
        request.setAttribute("id",id);
        return "message";
    }

    //添加信息列表
    @RequestMapping(value = "/message",method = RequestMethod.POST)
    public String messageInfo(Model model, HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String content = request.getParameter("Mycontent");
        String anthor = request.getParameter("anthor");
        String id = request.getParameter("id");
        Integer invid=Integer.valueOf(id);

        ReplyDetail replyDetail = new ReplyDetail();
        //创建时间
        Date day=new Date();
        replyDetail.setCreatedate(day);
        //内容
        replyDetail.setContent(content);
        //发布人
        if (anthor == null || anthor.equals("")){
            replyDetail.setAuthor("匿名用户");
        } else {
            replyDetail.setAuthor(anthor);
        }
        replyDetail.setInvid(invid);
        int count = invitationService.addMessage(replyDetail);
        if (count > 0) {
            return "messageList";
        } else {
            return "message";
        }
    }

    //删除信息
    @RequestMapping(value = "/del",method = RequestMethod.GET)
    public  String del(@RequestParam("id") int id){
        int count = invitationService.del(id);
        if (count>0){
            System.out.println("1212112");
            return "show";

        }else{
            System.out.println("515454565");
            return "0";
        }
    }




}
