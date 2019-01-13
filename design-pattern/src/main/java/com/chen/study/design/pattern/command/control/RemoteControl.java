package com.chen.study.design.pattern.command.control;

import com.chen.study.design.pattern.command.command.Command;
import com.chen.study.design.pattern.command.command.NoCommand;

/**
 * 遥控器
 * @author 陈添明
 * @date 2019/1/8
 */
public class RemoteControl {

   Command[] onCommands;
   Command[] offCommands;
   Command undoCommand;

    /**
     * 有7个插槽
     * 默认都是空命令对象
     */
   public RemoteControl(){
       onCommands = new Command[7];
       offCommands = new Command[7];

       NoCommand noCommand = new NoCommand();
       for (int i = 0; i < 7; i++) {
           onCommands[i] = noCommand;
           offCommands[i] = noCommand;
       }
       undoCommand = noCommand;
   }

    /**
     * 设置命令
     * @param slot 槽位
     * @param onCommand 打开命令
     * @param offCommand 关闭命令
     */
   public void setCommand(int slot, Command onCommand, Command offCommand){
       onCommands[slot] = onCommand;
       offCommands[slot] = offCommand;
   }

    /**
     * 按下打开按钮
     * @param slot 槽位
     */
   public void onButtonPushed(int slot){
       onCommands[slot].execute();
       undoCommand= onCommands[slot];
   }
   /**
     * 按下关闭按钮
     * @param slot 槽位
     */
   public void offButtonPushed(int slot){
       offCommands[slot].execute();
       undoCommand = offCommands[slot];
   }


    /**
     * 按下撤销按钮
     * @return
     */
    public void undoButtonWasPushed(){
        undoCommand.undo();
    }

    @Override
    public String toString() {
       StringBuilder builder = new StringBuilder();
        for (int i = 0; i < onCommands.length; i++) {
            Command onCommand = onCommands[i];
            Command offCommand = offCommands[i];
            builder.append(String.format("slot[%d] on:{%s}  off:{%s}\n", i, onCommand.getClass().getName(), offCommand.getClass().getName()));
        }
        return builder.toString();
    }
}
