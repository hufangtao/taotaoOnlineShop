package com.taotao.pagehelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestPageHelper {

    @Test
    public void testPageHelper() throws Exception{

        PageHelper.startPage(1,10);

        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:Spring/ApplicationContext-dao.xml");
        TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);

        TbItemExample example=new TbItemExample();
        List<TbItem> list=itemMapper.selectByExample(example);

        PageInfo<TbItem> pageInfo=new PageInfo<>(list);

        System.out.println("总记录页数"+ pageInfo.getTotal());
        System.out.println("总计页数"+pageInfo.getPages());
        System.out.println("返回的记录数"+list.size());

    }

}
