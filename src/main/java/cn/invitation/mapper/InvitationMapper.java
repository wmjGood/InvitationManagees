package cn.invitation.mapper;

import cn.invitation.pojo.Invitation;
import cn.invitation.pojo.ReplyDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InvitationMapper {

    //条件查询分页
    public List<Invitation> findPageByCondition(@Param("titlee") String title);

    public  int addMessage(ReplyDetail replyDetail);

    //查看回复信息列表
    public  List<ReplyDetail> findAll(@Param("id") int invid);

    //删除标题
    public  int del(int id);

    //删除回复
    public  int  delDetail(int ivid);


}
