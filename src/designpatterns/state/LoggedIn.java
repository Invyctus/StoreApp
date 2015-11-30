package designpatterns.state;

/**
 * Author: John Russell
 * Date Created: 11/29/2015
 * Georgia Southern University - 900743229
 */
public class LoggedIn implements State {

    @Override
    public void doAction(Context context) {
        System.out.println("Logged In");
        context.setState(this);
    }
    public String toString() {
        String text = "Log Out";
        return text;
    }
}
