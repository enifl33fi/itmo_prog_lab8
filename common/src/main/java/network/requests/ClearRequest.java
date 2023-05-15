package network.requests;

import GUI.WorkingWindow;
import commands.CommandType;

public class ClearRequest extends Request {

    public ClearRequest(){
        super(CommandType.CLEAR);
    }
    @Override
    public Request get(WorkingWindow workingWindow, String arg){
        return this;
    }
}
