package com.example.springbootdemo1;

import cn.enjoy.App;
import cn.enjoy.dao.UsersMapper;
import cn.enjoy.model.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * xhj 测试GaussDB的增删改
 */
@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class UserTest {

    @Resource
    private UsersMapper usersMapper;

    /**
     * 增加
     */
    @Test
    public void testAdd() {
        Users user = new Users() ;
        user.setId(1);
        user.setPasswd("1234");
        user.setUsername("enjoy");
        System.out.println(usersMapper.insertSelective(user));
    }

    /**
     * 修改
     */
    @Test
    public void testUp() {
        Users user = new Users() ;
        user.setId(2);
        user.setPasswd("123444");
        System.out.println(usersMapper.updateByPrimaryKeySelective(user));
    }

    /**
     * 删除
     */
    @Test
    public void testDel() {
//        Users user = new Users() ;
//        user.setId(10);
//        user.setPasswd("1234");
//        user.setUsername("enjoy");
//        usersMapper.insertSelective(user);

        System.out.println(usersMapper.deleteByPrimaryKey(10));
    }


    /**
     * 查询
     */
    @Test
    public void testFindUser() {
        Users enjoy = usersMapper.selectByPrimaryKey(1);
        System.out.println(enjoy.toString());
    }

    /**
     * 简单测试下 事务的支持
     */
    @Test
    public void testSw()  {
        String url = "jdbc:postgresql://192.168.1.42:25308/db_szch";
        String username = "szch";
        String password = "Szch@2020";

        Connection connection = null;
        //加载驱动
        try {
            Class.forName("org.postgresql.Driver");
            //连接数据库
            connection = DriverManager.getConnection(url, username, password);

            //通知数据库开启事务 false开启
            connection.setAutoCommit(false);
            String sql1 = "update szch_demo.account set money = money - 100 where name ='A'";
            connection.prepareStatement(sql1).executeUpdate();

            //制造错误
            //int i = 1/0;
            String sql2 = "update szch_demo.account set money = money + 100 where name ='B'";
            connection.prepareStatement(sql2).executeUpdate();

            connection.commit();//以上两条sql都执行成功就提交事务
            System.out.println("success");
        } catch (Exception e){
            try {
                if(connection != null){
                    connection.rollback();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if(connection != null){
                    connection.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
