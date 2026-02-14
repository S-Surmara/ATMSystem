package org.example.Entity;

import org.example.State.MachineState;

public class Machine {
    MachineState machineState;
    public void setNextMachineState(MachineState machineState) {
        this.machineState = machineState;
    }
}
