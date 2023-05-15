package network.requests;

import GUI.ArgumentType;
import GUI.WorkingWindow;
import commands.CommandType;
import element.AstartesCategory;

public class CountByCategoryRequest extends Request {
    public CountByCategoryRequest(){
        super(CommandType.COUNT_BY_CATEGORY);
    }
    @Override
    public Request get(WorkingWindow workingWindow, String arg){
        if (workingWindow != null){
            workingWindow.raiseCustomDialog(ArgumentType.CATEGORY, this);
            if (hasArg()) {
                return this;
            }
            return null;
        } else{
            AstartesCategory.valueOf(arg);
            this.setArg(arg);
            return this;
        }
    }
}
