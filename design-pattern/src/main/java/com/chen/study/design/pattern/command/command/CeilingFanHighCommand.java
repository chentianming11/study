package com.chen.study.design.pattern.command.command;

import com.chen.study.design.pattern.command.device.CeilingFan;

/**
 * @author 陈添明
 * @date 2019/1/13
 */
public class CeilingFanHighCommand extends AbstractCeilingFanCommand {

    public CeilingFanHighCommand(CeilingFan ceilingFan) {
        super(ceilingFan);
    }

    @Override
    public void doExecute() {
        this.ceilingFan.high();
    }
}
