package com.pddstudio.openpocket.model;

/**
 * This Class was created by Patrick J
 * on 15.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public enum Action {
    INPUT_0("0"),
    INPUT_1("1"),
    INPUT_2("2"),
    INPUT_3("3"),
    INPUT_4("4"),
    INPUT_5("5"),
    INPUT_6("6"),
    INPUT_7("7"),
    INPUT_8("8"),
    INPUT_9("9"),
    INPUT_DEL("x"),
    INPUT_DOT(".");

    private final String actionString;

    Action(String action) {
        this.actionString = action;
    }

    public String getActionString() {
        return actionString;
    }

}
