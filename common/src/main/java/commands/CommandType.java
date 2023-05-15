package commands;

import exceptions.WrongCommandException;

import java.util.ArrayList;

public enum CommandType {
    ADD("add", true),
    CLEAR("clear", true),
    COUNT_BY_CATEGORY("count_by_category", true),
    EXECUTE_SCRIPT("execute_script", true),
    EXIT("exit", false),
    FILTER_CONTAINS_NAME("filter_contains_name", true),
    HEAD("head", true),
    HELP("help", false),
    INFO("info", true),
    PRINT_FIELD_ASCENDING_HEART_COUNT("print_field_ascending_heart_count", true),
    REMOVE_BY_ID("remove_by_id", true),
    REMOVE_FIRST("remove_first", true),
    REMOVE_LOWER("remove_lower", true),
    SHOW("show", false),
    UPDATE("update", true),
    AUTH("auth", false),
    CHECK_LOGIN("check_login", false),
    CHECK_ID("check_id", false);


    private final String name;
    private final boolean isDisplaying;

    CommandType(String name, boolean isDisplaying) {
        this.name = name;
        this.isDisplaying = isDisplaying;

    }

    @Override
    public String toString() {
        return this.name;
    }

    public static CommandType getByName(String name){
        for (CommandType commandType : CommandType.values()){
            if (commandType.name.equals(name)){
                return commandType;
            }
        }
        throw new WrongCommandException();
    }

    public boolean isDisplaying() {
        return isDisplaying;
    }
}