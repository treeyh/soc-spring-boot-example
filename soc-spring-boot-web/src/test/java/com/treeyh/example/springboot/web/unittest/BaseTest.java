package com.treeyh.example.springboot.web.unittest;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author TreeYH
 * @version 1.0
 * @description
 * @create 2019-01-17 8:25 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
@AutoConfigureMockMvc
public class BaseTest {

    public static void main(String[] args) {
        System.out.println("BaseTest");
    }
}
