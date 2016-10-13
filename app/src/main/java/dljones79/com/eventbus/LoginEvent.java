package dljones79.com.eventbus;

public class LoginEvent {

    public final String mUserName;

    public LoginEvent(String userName) {
        this.mUserName = userName;
    }
}
