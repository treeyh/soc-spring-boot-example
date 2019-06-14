package com.treeyh.example.springboot.web.unittest.sort;

/**
 * @author TreeYH
 * @version 1.0
 * @description 描述
 * @create 2019-06-02 15:17
 */
public class Invoker {

    private ICommand command;

    public Invoker(ICommand command) {
        this.command = command;
    }

    public void action() {
        command.exe();
    }

}
