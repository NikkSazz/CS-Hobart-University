import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javafx.application.Platform;

/**
 * Provides support for the Observer pattern for maze solvers.
 * 
 * @author Stina Bridgeman
 */
public class SolverSubject {

	// properties
	public static final String SOLVER_PROPERTY = "solver";

	private PropertyChangeSupport support_;

	public SolverSubject () {
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
