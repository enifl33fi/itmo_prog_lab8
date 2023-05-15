package inputWorkers;

import exceptions.MaxRecursionDepthException;
import fileWorkers.ReaderFiles;
import general.GeneralVars;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.*;
import java.util.*;

public class InputHandler {
    private final ReaderFiles reader = new ReaderFiles();
    private final Stack<String> curExecutionFiles = new Stack<>();
    private final Stack<ArrayDeque<String>> curCommands = new Stack<>();


    public String getLine(){
        if (!this.curCommands.isEmpty() || !this.curExecutionFiles.isEmpty()){
            ArrayDeque<String> lines = this.curCommands.peek();
            if (!lines.isEmpty()) {
                return lines.poll();
            }
            this.curCommands.pop();
            this.curExecutionFiles.pop();
            return getLine();
        }
        return null;
    }


    public void getCommands(String fileName, StringBuilder result){
        if (this.curExecutionFiles.search(fileName) != -1) {
            throw new MaxRecursionDepthException();
        }
        this.curExecutionFiles.push(fileName);
        ArrayDeque<String> lines = new ArrayDeque<>();
        try (BufferedReader bufReader = new BufferedReader(new FileReader(fileName))) {
            String line = this.reader.getLine(bufReader);
            while (line != null) {
                lines.add(line);
                line = this.reader.getLine(bufReader);
            }
            this.curCommands.push(lines);
        } catch (FileNotFoundException | SecurityException | NullPointerException e) {
            result.append("Couldn't read given file.");
            result.append(System.lineSeparator());
        } catch (IOException e) {
            result.append("Unexpected abortion. Some commands will be lost.");
            result.append(System.lineSeparator());
        }
    }

    public String[] readElem() {
        String[] elemParts = new String[GeneralVars.USERS_VAR_COUNT];
        int curSize = this.curCommands.peek().size();
        for (int i = 0; i < GeneralVars.USERS_VAR_COUNT; i++) {
            if (curSize > 0){
                elemParts[i] = this.getLine();
                curSize--;
            }
            else {
                elemParts[i] = "";
            }
        }
        return elemParts;
    }


}

