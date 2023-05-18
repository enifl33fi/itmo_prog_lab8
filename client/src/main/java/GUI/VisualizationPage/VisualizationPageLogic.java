package GUI.VisualizationPage;

import GUI.ArgumentType;
import GUI.DataSynchronizer;
import GUI.WorkingWindowAdapter;
import GUI.dialogs.CategoryAskerDialog;
import GUI.dialogs.ElementAskerDialog;
import GUI.dialogs.IdAskerDialog;
import GUI.dialogs.StringAskerDialog;
import client.Client;
import commands.CommandType;
import custom.PaintComponent;
import custom.RequestButton;
import element.CollectionPart;
import exceptions.NullFieldException;
import general.validator.GeneralValidator;
import network.requests.RemoveByIdRequest;
import network.requests.Request;
import network.requests.UpdateRequest;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.LinkedList;

public class VisualizationPageLogic extends WorkingWindowAdapter {
    private VisualizationPageModel model = new VisualizationPageModel(this);
    private final Client client;
    private final GeneralValidator validator = new GeneralValidator();
    private Thread synchThread;
    private DataSynchronizer dataSynchronizer;

    public VisualizationPageLogic(Client client){
        this.client = client;

    }
    public void execute(RequestButton button){
        client.sendReqGetRes(button.getCommandType());
    }
    public void help(){
        client.sendReqGetRes(CommandType.HELP);
    }
    @Override
    public void raiseCustomDialog(ArgumentType type, Request req){
        switch (type){
            case CATEGORY -> new CategoryAskerDialog(req);
            case STRING -> new StringAskerDialog(req);
            case ID -> new IdAskerDialog(req, this);
            case ELEMENT -> new ElementAskerDialog(req, null);

        }
    }
    @Override
    public void setIt(){
        model.switchLanguage();
        dataSynchronizer = new DataSynchronizer(client, this);
        dataSynchronizer.setWork(true);
        synchThread = new Thread(dataSynchronizer);
        synchThread.start();
        model.setUserName(client.getLogin());
        model.setVisible(true);
    }
    @Override
    public String checkId(String idStr){
        try{
            validator.validateId(idStr);
            return client.checkId(idStr);
        } catch (NullFieldException | NumberFormatException e){
            return e.getMessage();
        }
    }
    @Override
    public void goBack(){
        dataSynchronizer.setWork(false);
        model.dispose();
        client.loadStartPage();
    }
    @Override
    public void goFurther(){
        dataSynchronizer.setWork(false);
        model.dispose();
        client.loadTablePage();
    }
    public void setData(LinkedList<CollectionPart> data){
        model.setData(data);
    }
    public void remove(long id){
        RemoveByIdRequest request = new RemoveByIdRequest();
        request.setArg(String.valueOf(id));
        client.sendReqGetRes(request);
    }
    public void update(PaintComponent component){
        UpdateRequest updateRequest = new UpdateRequest(null);
        updateRequest.setArg(String.valueOf(component.getElem().getId()));
        updateRequest.setUserContainer(new ImmutablePair<>(client.getLogin(), null));
        new ElementAskerDialog(updateRequest, component.getElem());
        if (updateRequest.hasElement()){
            client.sendReqGetRes(updateRequest);
        }
    }
}
