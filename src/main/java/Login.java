import java.util.Scanner;
import java.util.regex.Pattern;

public class Login {
    public String firstName;
    public String lastName;
    public String username;
    public String password;
    public boolean userIsLoggedIn = false;
    public boolean userHasRegistered = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        System.out.println("Welcome to LoginApp!");
        System.out.println("Please select an option:");
        System.out.println("1. Login");
        System.out.println("2. Register");
        int option = scanner.nextInt();

        boolean userWantsToLogin = option == 1;
        boolean userWantsToRegister = option == 2;

        if (userWantsToLogin && login.userHasRegistered) {
            login.promptUserToLogin();
        }
        else if (userWantsToLogin) {
            System.out.println("You have to register first.");
            login.promptUserToRegister();
        }
        else if(userWantsToRegister) {
            login.promptUserToRegister();
        }
        else {
            System.out.println("Invalid option selected. Please try again.");
        }

        scanner.close();
    }

    public void promptUserToRegister(){
        Scanner scanner = new Scanner(System.in);
        String registrationMessage;

        System.out.println("Registration");
        System.out.println("Enter your first name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter your last name:");
        String lastName = scanner.nextLine();

        while(!this.userHasRegistered){
            registrationMessage = this.registerUser(firstName, lastName);
            System.out.println(registrationMessage);
        }

        this.promptUserToLogin();

    }

    public void promptUserToLogin(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Login.");

        while(!this.userIsLoggedIn){
            System.out.println("Enter your username:");
            String username = scanner.nextLine();

            System.out.println("Enter your password:");
            String password = scanner.nextLine();

            this.loginUser(username, password);
            System.out.println(this.returnLoginStatus());
        }

    }

    public String registerUser(String firstName, String lastName) {
        if(this.firstName == null && this.lastName == null){
            this.firstName = firstName;
            this.lastName = lastName;
        }

        if(this.username == null){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your username:");
            String username = scanner.nextLine();

            boolean usernameSatisfiesFormat = checkUserName(username);
            if (usernameSatisfiesFormat) {
                this.username = username;
                return "Username successfully captured";
            } else {
                return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
            }
        }

        if(this.password == null){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your password:");
            String password = scanner.nextLine();

            boolean passwordSatisfiesComplexity = checkPasswordComplexity(password);
            if (passwordSatisfiesComplexity) {
                this.password = password;
                return "Password successfully captured";
            } else {
                return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
            }
        }

        this.userHasRegistered = true;
        return "Welcome " + firstName + " " + lastName + ", it is great to see you.";
    }

    public boolean loginUser(String username, String password) {
        boolean validated = this.username.equals(username) && this.password.equals(password);
        this.userIsLoggedIn = validated;
        return validated;
    }

    public boolean checkUserName(String username) {
        Pattern pattern = Pattern.compile("^\\w{1,5}_\\w*$");
        return pattern.matcher(username).matches();
    }

    public boolean checkPasswordComplexity(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!]).{8,}$");
        return pattern.matcher(password).matches();
    }

    public String returnLoginStatus() {
        if(this.userIsLoggedIn){
            return "Welcome " + this.firstName + " " + this.lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }


}
