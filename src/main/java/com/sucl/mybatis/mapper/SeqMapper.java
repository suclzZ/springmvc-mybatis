package com.sucl.mybatis.mapper;

import com.sucl.mybatis.model.Seq;

public interface SeqMapper {

    void save(Seq seq);

    void update(Seq seq);

    Seq get(String code);

    void delete(String code);
}
