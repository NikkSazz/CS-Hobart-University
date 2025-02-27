import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javafx.application.Platform;

/**
 * Provides support for the Observer pattern for Omino!.
 * 
 * @author Stina Bridgeman
 */
public class OminoSubject {

	// properties
	public static final String SCORE_PROPERTY = "score",
	    NUMROWS_PROPERTY = "numrows", NUMPIECES_PROPERTY = "numpieces",
	    CURPIECE_PROPERTY = "curpiece", BOARD_PROPERTY = "board";

	private PropertyChangeSupport support_;

	public OminoSubject () {
		support_ = new PropertyChangeSupport(this);
	}

	/**
	 * @param arg0
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener ( PropertyChangeListener arg0 ) {
		support_.addPropertyChangeListener(arg0);
	}

	/**
	 * @param arg0
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener ( PropertyChangeListener arg0 ) {
		support_.removePropertyChangeListener(arg0);
	}

	public void firePropertyChange ( String propertyName ) {
		// since this property change is going to result in a GUI update, we make
		// sure it is handled in the application thread
		Platform
		    .runLater( () -> support_.firePropertyChange(propertyName,null,this));
	}

}
