package network.requests;

import GUI.WorkingWindow;
import commands.CommandType;

public class HeadRequest extends Request {
    public HeadRequest(){
        super(CommandType.HEAD);
    }
    @Override
    public Request get(WorkingWindow workingWindow, String arg){
        return this;
    }
}
