package com.xinlee.demo;

import com.xinlee.demo.mapper.UserMapper;
import com.xinlee.demo.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by xin.lee on 2017/2/22.
 * mybatis使用mapper接口开发DAO 测试类
 */
public class MapperTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        InputStream is = Resources.getResourceAsStream("sqlMapConfig.xml");     // 加载mybatis的环境配置文件
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);           // 并通过输入流构建SQL会话工厂
    }

    @Test
    public void selectUserList() {
        SqlSession sqlSession = sqlSessionFactory.openSession();                // SQL会话工厂创建SQL会话
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);             // 获取Mapper接口
        // 查询所有用户
        List<User> userList = mapper.selectUserList();
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();                                                     // 关闭会话释放资源
    }

    @Test
    public void insertUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 创建User对象
        User user = new User();
        user.setUsername("王五");
        user.setBirthday(new Date());
        // 保存用户
        int count = mapper.insertUser(user);
        System.out.println(count);
        System.out.println(user.getUuid());                                     // 可以获取到<selectKey>的值
        sqlSession.commit();                                                    // 提交操作
        sqlSession.close();
    }

    @Test
    public void deleteUserByName() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 删除用户
        int count = mapper.deleteUserByName("王五");
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

}
