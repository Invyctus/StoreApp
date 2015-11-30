package designpatterns.state;

/**
 * Author: John Russell
 * Date Created: 11/29/2015
 * Georgia Southern University - 900743229
 */
public class LoggedOut implements State {

    @Override
    public void doAction(Context context) {
        System.out.println("Logged out");
        context.setState(this);
    }
    public String toString() {
        String text = "Log In";
        return text;
    }
}
