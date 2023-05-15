package network.requests;

import GUI.ArgumentType;
import GUI.WorkingWindow;
import commands.CommandType;

public class RemoveByIdRequest extends Request{
    public RemoveByIdRequest(){
        super(CommandType.REMOVE_BY_ID);
    }
    @Override
    public Request get(WorkingWindow workingWindow, String arg){
        if (workingWindow != null){
            workingWindow.raiseCustomDialog(ArgumentType.ID, this);
            if (hasArg()) {
                return this;
            }
            return null;
        } else {
            Long.parseLong(arg);
            this.setArg(arg);
            return this;
        }
    }
}
