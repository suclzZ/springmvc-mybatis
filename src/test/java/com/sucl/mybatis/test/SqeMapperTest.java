package com.sucl.mybatis.test;

import com.sucl.mybatis.core.SqlSessionFactoryResource;
import com.sucl.mybatis.mapper.SeqMapper;
import com.sucl.mybatis.model.Seq;
import com.sucl.mybatis.service.SeqService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SqeMapperTest{

    public static List<Long> values = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {

        SqlSessionFactoryResource sqlSessionFactoryResource = new SqlSessionFactoryResource();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryResource.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        SeqMapper seqMapper = sqlSession.getMapper(SeqMapper.class);

//        getAndAddBatch(sqlSession);

        sqlSession.commit(true);
    }

    public static void getAndAddBatch(SqlSession sqlSession){
        SeqService seqService = new SeqService(sqlSession);
        ExecutorService es = Executors.newFixedThreadPool(100);
        for(int i=0 ; i<10000 ; i++){
            es.submit(new Runnable() {
                @Override
                public void run() {
                    long value = seqService.getAndIncrement("xyz");
                    System.out.println("value :"+value);
                    values.add(value);
                }
            });
        }
        es.shutdown();
        while (true){
            if(es.isTerminated()){
                System.out.println("生成个数 ："+values.size());
                break;
            }
        }
    }

    public static void getAndAdd(SeqMapper seqMapper){
        SeqService seqService = new SeqService(seqMapper);
        long value = seqService.getAndIncrement("abc");
        System.out.println("value : "+value);
    }

    public static void test(SeqMapper seqMapper){
        Seq seq = new Seq();
        seq.setCode("abc");
        seq.setValue(1);
        seqMapper.save(seq);

        seq.setValue(2);
        seqMapper.update(seq);

        Seq _seq = seqMapper.get("abc");
//        seqMapper.delete("abc");
    }

}
