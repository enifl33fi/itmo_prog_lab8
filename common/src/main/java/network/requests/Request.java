package network.requests;

import GUI.WorkingWindow;
import commands.CommandType;
import element.CollectionPart;
import exceptions.WrongCommandException;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.Serializable;
import java.util.LinkedList;

public abstract class Request implements Serializable {
    private CommandType commandType;
    private CollectionPart element;
    private String arg;
    private boolean isRegistration;
    private ImmutablePair<String, String> userContainer;
    private long lastUpdTime;

    public Request(CommandType commandType){
        this.commandType = commandType;
    }


    public CommandType getCommandType() {
        return this.commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public CollectionPart getElement() {
        return element;
    }

    public void setElement(CollectionPart element) {
        this.element = element;
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public boolean hasElement(){
        return this.element != null;
    }

    public boolean hasArg(){
        return this.arg != null;
    }
    public Request get(WorkingWindow workingWindow, String arg){
        throw new WrongCommandException();
    }

    public Request get(boolean fromFile){
        throw new WrongCommandException();
    }

    public Request get(boolean fromFile, String arg){
        throw new WrongCommandException();
    }

    public boolean isRegistration() {
        return isRegistration;
    }

    public void setRegistration(boolean registration) {
        isRegistration = registration;
    }

    public ImmutablePair<String, String> getUserContainer() {
        return userContainer;
    }

    public void setUserContainer(ImmutablePair<String, String> personContainer) {
        this.userContainer = personContainer;
    }
    public String getLogin(){
        return userContainer.getLeft();
    }


    public long getLastUpdTime() {
        return lastUpdTime;
    }

    public void setLastUpdTime(long lastUpdTime) {
        this.lastUpdTime = lastUpdTime;
    }

}
