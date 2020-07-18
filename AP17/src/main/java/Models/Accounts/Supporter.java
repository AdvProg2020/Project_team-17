package Models.Accounts;

public class Supporter extends Account {
    public Supporter(String userName, String firstName, String lastName, String email, String phoneNumber, String password, double credit) {
        super(userName, firstName, lastName, email, phoneNumber, password, credit);
    }

    @Override
    public String toString() {
        return "Supporter{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", credit=" + credit +
                '}';
    }
}
