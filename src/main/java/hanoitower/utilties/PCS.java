package hanoitower.utilties;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public enum PCS {
    NEW_GAME,

    PAUSE_GAME,

    RESUME_GAME,

    RESTART_GAME,

    WIN_GAME,

    END_GAME,

    SAVE_GAME,

    LOAD_GAME,

    INCREMENT_LEVEL,

    INCREMENT_MOVE,

    UPDATE_PROGRESS,

    MINIMUM_MOVES,

    DECREMENT_TIME,

    RESTART_TIME,

    UPDATE_LEVEL,

    UPDATE_MODE,

    UPDATE_NAME,

    UPDATE_TOWERS,

    PUSH,

    POP;

    private static final PropertyChangeSupport PCS = new PropertyChangeSupport(PCS.class);

    public static void firePropertyChange(final PCS theProperty, final Object theNewValue) {
        PCS.firePropertyChange(theProperty.name(), null, theNewValue);
    }

    public static void addPropertyListener(final PropertyChangeListener theListener) {
        PCS.removePropertyChangeListener(theListener);
        PCS.addPropertyChangeListener(theListener);
    }
}
