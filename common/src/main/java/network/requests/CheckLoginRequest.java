package network.requests;

import commands.CommandType;

public class CheckLoginRequest extends Request{
    public CheckLoginRequest(){
        super(CommandType.CHECK_LOGIN);
    }
    @Override
    public Request get(boolean fromFile){
        return this;
    }
}
