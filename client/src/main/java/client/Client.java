package client;

import GUI.WorkingWindow;
import GUI.entering.EnteringLogic;
import GUI.registration.RegistrationLogic;
import GUI.startPage.StartPageLogic;
import GUI.tablePage.TablePageLogic;
import commands.CommandType;
import exceptions.*;
import inputWorkers.InputHandler;
import inputWorkers.RequestParser;
import managers.RequestManager;
import network.requests.AuthRequest;
import network.requests.CheckIdRequest;
import network.requests.CheckLoginRequest;
import network.requests.Request;
import network.Response;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class Client {
    private static final Logger LOGGER= LogManager.getLogger(Client.class);
    private SocketChannel channel;
    private String host;
    private int port;
    private final InputHandler inputHandler;

    private final RequestParser requestParser;

    private final int attemptsCount  = 300;
    private final int clientSleepTime = 2;

    private boolean isRegistered;
    private WorkingWindow curWorkingWindow;
    private ImmutablePair<String, String> userContainer;
    private final RequestManager requestManager;
    private RegistrationLogic registrationLogic;
    private StartPageLogic startPageLogic;
    private EnteringLogic enteringLogic;
    private TablePageLogic tablePageLogic;


    public Client(RequestManager requestManager, InputHandler inputHandler) {
        this.requestManager = requestManager;
        this.requestParser = new RequestParser(requestManager);
        this.inputHandler = inputHandler;


    }
    private void gui(){
        this.startPageLogic = new StartPageLogic(this);
        this.enteringLogic = new EnteringLogic(this);
        this.registrationLogic = new RegistrationLogic(this);
        this.tablePageLogic = new TablePageLogic(this);
        startPageLogic.setIt();
    }

    private SocketChannel connect(String host, int port){
        while (true){
            try {
                SocketChannel openChannel = SocketChannel.open();
                InetSocketAddress address = new InetSocketAddress(host, port);
                if (!address.isUnresolved()) {
                    openChannel.connect(address);
                    LOGGER.info(String.format("Connected with server: host: %s port: %d%n", host, port));
                    openChannel.configureBlocking(false);
                    this.host = host;
                    this.port = port;
                    return openChannel;
                }
            } catch (IOException ignored){

            }
        }

    }
    public void run(String host, int port) {
        this.channel = this.connect(host, port);
        SwingUtilities.invokeLater(this::gui);
        //this.register();

//            this.requestManager.setUserContainer(this.userContainer);
//            while (this.channel != null){
//                Response res = this.sendReqGetRes();
//                if (res != null){
//                    System.out.println(res.getResponseLine());
//                    if (res.isExit()){
//                        System.exit(0);
//                    }
//                }
//            }

    }
    public Request getRequest(CommandType commandType){
        return requestManager.getRequest(commandType).get(curWorkingWindow, null);
    }

    public Request getRequest(String lineArr) throws IOException {
        return this.requestParser.parse(null, lineArr);
    }

    public void sendRequest(Request req) throws IOException, InterruptedException {
        int remainAttempts = this.attemptsCount;
        ByteBuffer bf = ByteBuffer.wrap(SerializationUtils.serialize(req));
        while (remainAttempts > 0){
            int state = this.channel.write(bf);
            if (state == -1){
                remainAttempts--;
                TimeUnit.MILLISECONDS.sleep(this.clientSleepTime);
            }
            else {
                break;
            }
        }
        if (remainAttempts <= 0){
            throw new TimeLimitException();
        }
    }

    public Response getResponse(int packageCount) throws IOException, InterruptedException {
        byte[] arrSum = new byte[0];
        for (int i = 0; i < packageCount; i++){
            int remainAttempts = this.attemptsCount;
            ByteBuffer bf = ByteBuffer.allocate(8192);
            while (remainAttempts > 0){
                int state = this.channel.read(bf);
                if (state == -1 || state == 0){
                    remainAttempts--;
                    TimeUnit.MILLISECONDS.sleep(this.clientSleepTime);
                }
                else{
                    arrSum = ArrayUtils.addAll(arrSum, bf.array());
                    break;

                }
            }
            if (remainAttempts <= 0){
                throw new TimeLimitException();
            }
        }
        return SerializationUtils.deserialize(arrSum);
    }
    public void sendReqGetRes(CommandType commandType){
        Request req = this.getRequest(commandType);
        if (req != null){
            if (req.getCommandType() == CommandType.EXECUTE_SCRIPT){
                StringBuilder result = new StringBuilder();
                this.inputHandler.getCommands(req.getArg(), result);
                this.sendReqGetRes(result);
            }
            else {
                try {
                    this.sendRequest(req);

                    Response res = this.getResponse(1);
                    int packageCount = res.getPackageCount();
                    if (packageCount > 1) {
                        res = this.getResponse(packageCount);
                    }
                    curWorkingWindow.raiseDialog(res.getResponseLine(), JOptionPane.INFORMATION_MESSAGE);

                } catch (IOException e) {
                    curWorkingWindow.raiseDialog("failed to exchange data with server", JOptionPane.ERROR_MESSAGE);
                    LOGGER.error("Failed to exchange data with server");
                    this.channel = this.reconnect();
                } catch (InterruptedException e) {
                    curWorkingWindow.raiseDialog(e.getMessage(), JOptionPane.ERROR_MESSAGE);
                    LOGGER.error(e.getMessage());
                } catch (TimeLimitException e) {
                    curWorkingWindow.raiseDialog(e.getMessage(), JOptionPane.ERROR_MESSAGE);
                    LOGGER.error(e.getMessage());
                    this.channel = this.reconnect();
                }
            }
        }

    }

    public void sendReqGetRes(StringBuilder result) {
        String lineArr = inputHandler.getLine();
        while (lineArr != null) {
            try {
                Request req = this.getRequest(lineArr);
                if (req.getCommandType() == CommandType.EXECUTE_SCRIPT) {
                    this.inputHandler.getCommands(req.getArg(), result);
                } else {
                    this.sendRequest(req);
                    Response res = this.getResponse(1);
                    int packageCount = res.getPackageCount();
                    if (packageCount > 1) {
                        res = this.getResponse(packageCount);
                    }
                    result.append(res.getResponseLine());
                    result.append(System.lineSeparator());
                }

            }catch (WrongFieldException | NullFieldException | WrongCommandException | MaxRecursionDepthException |
                    InterruptedException e) {
                result.append(e.getMessage());
                result.append(System.lineSeparator());
            } catch (IllegalArgumentException e) {
                result.append("Wrong argument");
                result.append(System.lineSeparator());
            } catch (NullPointerException | IndexOutOfBoundsException e) {
                result.append("Unknown command");
                result.append(System.lineSeparator());
            } catch (IOException e) {
                result.append("Failed to exchange data with server");
                result.append(System.lineSeparator());
                this.channel = this.reconnect();
            } catch (TimeLimitException e) {
                result.append(e.getMessage());
                result.append(System.lineSeparator());
                this.channel = this.reconnect();
            }
            lineArr = inputHandler.getLine();;
        }
        curWorkingWindow.raiseDialog(result.toString(), JOptionPane.INFORMATION_MESSAGE);
    }
    public Response sendReqGetRes(Request req){
        if (req.getUserContainer() == null){
            req.setUserContainer(userContainer);
        }
        Response res = new Response("Internal server error.");
        try{
            this.sendRequest(req);
            res = this.getResponse(1);
            return res;
        } catch (IOException e) {
            LOGGER.error("Failed to exchange data with server");
            this.channel = this.reconnect();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        } catch (TimeLimitException e){
            LOGGER.error(e.getMessage());
            this.channel = this.reconnect();
        }
        return res;
    }

    public SocketChannel reconnect(){
        return this.connect(this.host, this.port);

    }
    public String checkId(String idStr){
        Request req = new CheckIdRequest();
        req.setUserContainer(userContainer);
        req.setArg(idStr);
        return sendReqGetRes(req).getResponseLine();
    }
    public String checkLoginUnique(String login){
        Request req = new CheckLoginRequest();
        req.setUserContainer(new ImmutablePair<>(login, null));
        return sendReqGetRes(req).getResponseLine();

    }

    public void register(ImmutablePair<String, String> userContainer, boolean isRegistration) {
        this.userContainer = userContainer;
        Request req = new AuthRequest();
        req.setUserContainer(this.userContainer);
        req.setRegistration(isRegistration);
        try{
            this.sendRequest(req);
            Response res = this.getResponse(1);
            if (res != null){
                this.isRegistered = res.isRegistered();
                if (!this.isRegistered){
                    curWorkingWindow.raiseDialog(res.getResponseLine(), JOptionPane.ERROR_MESSAGE);
                }
                else {
                    curWorkingWindow.goFurther();
                    requestManager.setUserContainer(this.userContainer);
                }
            }
            else {
                curWorkingWindow.raiseDialog("error while accepting response", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            curWorkingWindow.raiseDialog("failed to exchange data with server", JOptionPane.ERROR_MESSAGE);
            LOGGER.error("failed to exchange data with server");
            this.channel = this.reconnect();
        } catch (InterruptedException e) {
            curWorkingWindow.raiseDialog(e.getMessage(), JOptionPane.ERROR_MESSAGE);
            LOGGER.error(e.getMessage());
        } catch (TimeLimitException e){
            curWorkingWindow.raiseDialog(e.getMessage(), JOptionPane.ERROR_MESSAGE);
            LOGGER.error(e.getMessage());
            this.channel = this.reconnect();
        }
    }
    public String getLogin(){
        return userContainer.getLeft();
    }
    public void loadRegistration(){
        registrationLogic.setIt();
        curWorkingWindow = registrationLogic;
    }
    public void loadStartPage(){
        this.userContainer = null;
        startPageLogic.setIt();
        curWorkingWindow = startPageLogic;
    }
    public void loadEntering(){
        enteringLogic.setIt();
        curWorkingWindow = enteringLogic;
    }
    public void loadTablePage(){
        tablePageLogic.setIt();
        curWorkingWindow = tablePageLogic;
    }
}