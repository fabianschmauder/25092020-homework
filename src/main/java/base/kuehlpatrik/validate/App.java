package base.kuehlpatrik.validate;

public class App {
    public static void main(String[] args) {
        Validator someSecurePassword = new Validator("~Z7&^6%/'[c!XD;)R-j9{v'>");
        System.out.println( "Password is secure: " + someSecurePassword.performCheck() );
        if ( !someSecurePassword.getErrorMessage().isEmpty() ) {
            System.out.println( "Error: " + someSecurePassword.getErrorMessage() );
        }
        if (someSecurePassword.getErrorIndexPos() != -1) {
            System.out.println( "Index position in array: " + someSecurePassword.getErrorIndexPos() );
        }

        Validator someMoreSecurePasswords = new Validator(new String[]{
                "xV#-c@G['=Q8LMdBc6gD;4&.",
                "%h,%4v6Q`5E&4%'SCNwq8qme",
                "8?uN`k&Qu?vqvT&'C&#b6xd$"
        });
        System.out.println( "Passwords are secure: " + someMoreSecurePasswords.performCheck() );
        if ( !someMoreSecurePasswords.getErrorMessage().isEmpty() ) {
            System.out.println( "Error: " + someMoreSecurePasswords.getErrorMessage() );
        }
        if (someMoreSecurePasswords.getErrorIndexPos() != -1) {
            System.out.println( "Index position in array: " + someMoreSecurePasswords.getErrorIndexPos() );
        }
    }
}
