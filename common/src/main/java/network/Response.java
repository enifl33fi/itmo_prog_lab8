package network;

import commands.CommandType;
import element.CollectionPart;

import java.io.Serializable;
import java.util.LinkedList;

public class Response implements Serializable {
    private CommandType commandType;
    private String responseLine;

    private int packageCount = 0;

    private boolean isExit;

    private boolean isRegistered;
    private LinkedList<CollectionPart> data;
    private long lastUpdTime;

    public Response(String responseLine){
        this.responseLine = responseLine;
        this.isExit = false;
        this.isRegistered = false;
    }

    public CommandType getCommandType() {
        return this.commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public String getResponseLine() {
        return responseLine;
    }

    public void setResponseLine(String responseLine) {
        this.responseLine = responseLine;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public void setExit(boolean exit) {
        this.isExit = exit;
    }

    public boolean isRegistered() {
        return this.isRegistered;
    }

    public void setRegistered(boolean registered) {
        this.isRegistered = registered;
    }

    public int getPackageCount() {
        return packageCount;
    }

    public void setPackageCount(int packageCount) {
        this.packageCount = packageCount;
    }

    public LinkedList<CollectionPart> getData() {
        return data;
    }

    public void setData(LinkedList<CollectionPart> data) {
        this.data = data;
    }

    public long getLastUpdTime() {
        return lastUpdTime;
    }

    public void setLastUpdTime(long lastUpdTime) {
        this.lastUpdTime = lastUpdTime;
    }
    public boolean hasData(){
        return data != null;
    }
}