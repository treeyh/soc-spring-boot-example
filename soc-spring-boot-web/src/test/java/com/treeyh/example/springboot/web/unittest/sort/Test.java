package com.treeyh.example.springboot.web.unittest.sort;

/**
 * @author TreeYH
 * @version 1.0
 * @description 描述
 * @create 2019-06-02 15:18
 */
public class Test {

    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        ICommand cmd = new MyCommand(receiver);
        Invoker invoker = new Invoker(cmd);
        invoker.action();
    }
}
