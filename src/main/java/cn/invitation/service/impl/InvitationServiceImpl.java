package cn.invitation.service.impl;

import cn.invitation.mapper.InvitationMapper;
import cn.invitation.pojo.Invitation;
import cn.invitation.pojo.ReplyDetail;
import cn.invitation.service.InvitationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("InvitationService")
public class InvitationServiceImpl implements InvitationService {

    @Resource
    private InvitationMapper invitationMapper;


    public List<Invitation> findPageByCondition(String title,Integer pageIndex) {
        PageHelper.startPage(pageIndex,3);
        List<Invitation> lists = invitationMapper.findPageByCondition(title);
        return lists;
    }

    public int addMessage(ReplyDetail replyDetail) {
        return invitationMapper.addMessage(replyDetail);
    }

    public List<ReplyDetail> findAll(int invid) {
        return invitationMapper.findAll(invid);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int del(int id) {
        int count,count1=0;
        count=invitationMapper.del(id);
        count1=invitationMapper.delDetail(id);
        if (count==0|| count1==0){
            throw  new RuntimeException("回滚");
        }
        return  count;
    }

    public int delDetail(int ivid) {
        return invitationMapper.delDetail(ivid);
    }

    public InvitationMapper getInvitationMapper() {
        return invitationMapper;
    }

    public void setInvitationMapper(InvitationMapper invitationMapper) {
        this.invitationMapper = invitationMapper;
    }
}
