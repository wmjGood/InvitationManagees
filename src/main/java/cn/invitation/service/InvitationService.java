package cn.invitation.service;

import cn.invitation.pojo.Invitation;
import cn.invitation.pojo.ReplyDetail;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvitationService {

    //条件查询分页
    public List<Invitation> findPageByCondition(@Param("titlee") String title,Integer pageIndex);



    public  int addMessage(ReplyDetail replyDetail);

    //查看回复信息列表
    public  List<ReplyDetail> findAll(@Param("id") int invid);

    public  int del(int id);

    //删除回复
    public  int  delDetail(int ivid);
}
