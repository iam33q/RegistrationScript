import java.util.Scanner;
import java.io.Console;

public class RegistrationScript {
    public static void main(String[] args){
        String[] unames = {null,"","admin","FireStyleDragonBall","hugedork69","InternetWarriorSupreme","not_my_name"}; // Very appropriate list of usernames to check against
        String uname ="";
        String pass;
        String pass2;
        int chnum;
        boolean criterion1 = false;
        boolean criterion2 = false;
        boolean criterion3 = false;
        boolean criterion4 = false;
        boolean bing = true;

        System.out.println("Welcome to this website!\nIf you are a new user, you can type in your desired (unique) username below:");
        do {
            if (uname == "") // Username Prompt. Skipped if already provided and the password prompt returns an exception.
            {
                try {

                    Scanner input1 = new Scanner(System.in);
                    uname = input1.next();
                    for (String user : unames) // Checking against existing usernames
                    {
                        if (uname.equalsIgnoreCase(user)) {
                            throw new SecurityException("Sorry, this username is already taken!");
                        }
                    }
                }
                catch (SecurityException e) // Yes, they're all security exceptions, because this is all about security.
                {
                    System.out.println(e); // This looks less scary than e.printStackTrace(); .
                }
            }

            try {
                Console console = System.console();
                char[] passArray;
                Scanner input2 = new Scanner(System.in);
                Scanner input3 = new Scanner(System.in);

                System.out.println("""
                        Now type in your password:
                        NOTE: Your password must contain:\s
                         - At least 8 characters, at most 99\s
                         - At least one uppercase letter\s
                         - At least one number AND one special character"""); // the IDE wanted me to do this, and I went along.

                // passArray = console.readPassword();
                // pass = new String(passArray);        // The only class that seems to suppress terminal input is not supported. What a shame.
                pass = input2.next();


                if ((pass.length() < 8)) // Self-explanatory initial password check
                {
                    throw new SecurityException("Your password is too short.");
                } else if ((pass.length() > 99))
                {
                    throw new SecurityException("Your password is so secure that it's actually illegal.");
                } else {
                    criterion1 = true;
                }

                for (int i = 0; i < pass.length(); i++) // Very primitive password checker
                {
                    chnum = pass.charAt(i);
                    if (chnum == 10 || chnum == 32) // Invalid characters. Not sure how they would end up here, maybe through copy/pasting them in.
                    {
                        throw new SecurityException("Invalid character used.");
                    } else if (((chnum > 32) && (chnum < 48))) // Special character check
                    {
                        criterion4 = true;
                    } else if ((chnum > 47) && (chnum < 58))  // Number check
                    {
                        criterion3 = true;
                    } else if ((chnum > 64) && (chnum < 91)) // Capital Letter check
                    {
                        criterion2 = true;
                    }
                }

                if ((criterion1 && criterion2) && (criterion3 && criterion4)) {
                    System.out.println("Now please type in your password again.");
                } else {
                    throw new SecurityException("Your password does not meet the conditions above, please review it.");
                }
                // passArray = console.readPassword();
                // pass2 = new String(passArray);
                pass2 = input3.next();

                if (pass.equals(pass2)) {
                    System.out.println("Registration Successful!");
                    bing = false;
                } else {
                    throw new SecurityException("Your passwords do not match. Please try typing your password again.");
                }
            } catch (SecurityException e) {
                System.out.println(e); // This looks less scary than e.printStackTrace(); .
            }
        } while(bing);
    }
}