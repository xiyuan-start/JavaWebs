package com.luo.springcloudeurekaclientnotice.Service;

import com.luo.springcloudeurekaclientnotice.Dao.NoticeDao;
import com.luo.springcloudeurekaclientnotice.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

@Autowired
    NoticeDao noticeDao;

public List<Notice>listNoticeByPage(Integer offset)
{

    Pageable pageable=new PageRequest(offset/10,10);
    return noticeDao.findAllBy(pageable);

}

    //创建新的意见
    public void NewNotice(Notice notice)
    {
        noticeDao.save(notice);
    }

    //根据id获取意见
    public Notice GetNoticebyId(Integer id)
    {
        return noticeDao.findById(id);
    }
}
