package com.example.ominofinal;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javafx.application.Platform;

public class OminoSubject {
    public static final String SCORE_PROPERTY = "score";
    public static final String NUMROWS_PROPERTY = "numrows";
    public static final String NUMPIECES_PROPERTY = "numpieces";
    public static final String CURPIECE_PROPERTY = "curpiece";
    public static final String BOARD_PROPERTY = "board";

    private final PropertyChangeSupport support;

    public OminoSubject() {
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void firePropertyChange(String propertyName) {
        Platform.runLater(() -> support.firePropertyChange(propertyName, null, this));
    }
}
