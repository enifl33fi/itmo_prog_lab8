package network.requests;

import GUI.WorkingWindow;
import commands.CommandType;

public class InfoRequest extends Request {
    public InfoRequest(){
        super(CommandType.INFO);
    }
    @Override
    public Request get(WorkingWindow workingWindow, String arg){
        return this;
    }
}
