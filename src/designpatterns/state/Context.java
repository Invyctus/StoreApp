package designpatterns.state;

/**
 * Author: John Russell
 * Date Created: 11/29/2015
 * Georgia Southern University - 900743229
 */
public class Context {
    private State state;

    public Context() {
        state = null;
    }
    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
