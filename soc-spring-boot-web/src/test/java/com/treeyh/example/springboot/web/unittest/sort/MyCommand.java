package com.treeyh.example.springboot.web.unittest.sort;

/**
 * @author TreeYH
 * @version 1.0
 * @description 描述
 * @create 2019-06-02 15:16
 */
public class MyCommand implements ICommand {

    private Receiver receiver;

    public MyCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void exe() {
        receiver.action();
    }
}