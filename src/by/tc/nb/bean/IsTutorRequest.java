package by.tc.nb.bean;

/**
 * Created by Admin on 26.10.2016.
 */
public class IsTutorRequest extends Request {
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
