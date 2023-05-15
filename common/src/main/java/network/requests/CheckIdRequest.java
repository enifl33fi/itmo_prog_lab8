package network.requests;

import commands.CommandType;

public class CheckIdRequest extends Request{
    public CheckIdRequest(){
        super(CommandType.CHECK_ID);
    }
    @Override
    public Request get(boolean fromFile){
        return this;
    }
}