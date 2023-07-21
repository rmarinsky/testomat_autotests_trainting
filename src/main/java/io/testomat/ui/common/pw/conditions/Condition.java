package io.testomat.ui.common.pw.conditions;

import io.testomat.ui.common.pw.LocationActions;

public interface Condition {

    Condition visible = new VisibleCondition();
    Condition hidden = new IsHiddenCondition();

    static Condition text(String expectedText) {
        return new TextCondition(expectedText);
    }

    void verify(LocationActions locationActions);

}
