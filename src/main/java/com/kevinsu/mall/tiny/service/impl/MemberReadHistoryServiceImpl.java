package com.kevinsu.mall.tiny.service.impl;

import com.kevinsu.mall.tiny.nosql.mongodb.document.MemberReadHistory;
import com.kevinsu.mall.tiny.nosql.mongodb.repository.MemberReadHistoryRepository;
import com.kevinsu.mall.tiny.service.MemberReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @author SuGengTian kevinsu917@126.com
 * @since 2020-12-22-18:09
 */
@Service
public class MemberReadHistoryServiceImpl implements MemberReadHistoryService {
    @Autowired
    private MemberReadHistoryRepository memberReadHistoryRepository;
    @Override
    public int create(MemberReadHistory memberReadHistory) {
        memberReadHistory.setId(null);
        memberReadHistory.setCreateTime(new Date());
        memberReadHistoryRepository.save(memberReadHistory);
        return 1;
    }

    @Override
    public int delete(List<String> ids) {
        List<MemberReadHistory> deleteList = new ArrayList<>();
        for(String id:ids){
            MemberReadHistory memberReadHistory = new MemberReadHistory();
            memberReadHistory.setId(id);
            deleteList.add(memberReadHistory);
        }
        memberReadHistoryRepository.deleteAll(deleteList);
        return ids.size();
    }

    @Override
    public List<MemberReadHistory> list(Long memberId) {
        return memberReadHistoryRepository.findByMemberIdOrderByCreateTimeDesc(memberId);
    }
}