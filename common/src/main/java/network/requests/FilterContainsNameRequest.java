package network.requests;

import GUI.ArgumentType;
import GUI.WorkingWindow;
import commands.CommandType;

public class FilterContainsNameRequest extends Request {
    public FilterContainsNameRequest(){
        super(CommandType.FILTER_CONTAINS_NAME);
    }
    @Override
    public Request get(WorkingWindow workingWindow, String arg){
        if (workingWindow != null){
            workingWindow.raiseCustomDialog(ArgumentType.STRING, this);
            if (hasArg()) {
                return this;
            }
            return null;
        }
        else{
            this.setArg(arg);
            return this;
        }
    }
}
