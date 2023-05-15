package network.requests;

import GUI.WorkingWindow;
import commands.CommandType;

public class HelpRequest extends Request {
    public HelpRequest(){
        super(CommandType.HELP);
    }
    @Override
    public Request get(WorkingWindow workingWindow, String arg){
        return this;
    }
}
