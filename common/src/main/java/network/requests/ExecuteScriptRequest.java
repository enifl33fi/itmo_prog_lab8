package network.requests;

import GUI.WorkingWindow;
import commands.CommandType;

public class ExecuteScriptRequest extends Request{
    public ExecuteScriptRequest(){
        super(CommandType.EXECUTE_SCRIPT);
    }
    @Override
    public Request get(WorkingWindow workingWindow, String arg){
        if (workingWindow != null){
            workingWindow.raiseFileDialog(this);
            if (hasArg()) {
                return this;
            }
            return null;
        } else {
            this.setArg(arg);
            return this;
        }
    }
}
