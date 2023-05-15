package network.requests;

import GUI.WorkingWindow;
import commands.CommandType;

public class RemoveFirstRequest extends Request {
    public RemoveFirstRequest(){
        super(CommandType.REMOVE_FIRST);
    }
    @Override
    public Request get(WorkingWindow workingWindow, String arg){
        return this;
    }
}
