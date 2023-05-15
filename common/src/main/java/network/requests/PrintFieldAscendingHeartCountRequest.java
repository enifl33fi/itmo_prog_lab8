package network.requests;

import GUI.WorkingWindow;
import commands.CommandType;

public class PrintFieldAscendingHeartCountRequest extends Request {
    public PrintFieldAscendingHeartCountRequest(){
        super(CommandType.PRINT_FIELD_ASCENDING_HEART_COUNT);
    }
    @Override
    public Request get(WorkingWindow workingWindow, String arg){
        return this;
    }
}
