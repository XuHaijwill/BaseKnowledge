package com.example.springbootdemo1;

import cn.enjoy.utils.TokenManager;
import org.junit.Test;

public class TokenTest {

    @Test
  public void testToken(){
        String tokenStr = TokenManager.createToken("zhangsan","Axmk89Li3Aji9M");
        System.out.println(tokenStr);

        System.out.println(TokenManager.verifyToken("Axmk89Li3Aji9M",tokenStr));
  }
}
