package com.sucl.mybatis.service;

import com.sucl.mybatis.mapper.SeqMapper;
import com.sucl.mybatis.model.Seq;
import org.apache.ibatis.session.SqlSession;

import java.util.concurrent.atomic.AtomicLong;

public class SeqService {

    private SqlSession sqlSession;

    private SeqMapper seqMapper;

    public SeqService(SqlSession sqlSession){
        this.sqlSession = sqlSession;
        this.seqMapper = sqlSession.getMapper(SeqMapper.class);
    }

    public SeqService(SeqMapper seqMapper){
        this.seqMapper = seqMapper;
    }

    private AtomicLong atomicLong = new AtomicLong();

    public synchronized long getAndIncrement(String code){
        long value = 1;
        Seq seq = seqMapper.get(code);
        if(seq == null){
            seq = new Seq();
            seq.setCode(code);
            seq.setValue(value);
            seqMapper.save(seq);
        }else{
            value = seq.getValue();
            seq.setValue(++value);
            seqMapper.update(seq);
        }
        if(sqlSession!=null){
            sqlSession.commit(true);
        }
        return value;
    }


}
