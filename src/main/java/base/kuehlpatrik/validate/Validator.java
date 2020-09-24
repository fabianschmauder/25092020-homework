package base.kuehlpatrik.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    protected String _password = Validator.EMPTY_STRING;
    protected String[] _passwords;
    protected String _errorMessage = Validator.EMPTY_STRING;
    protected int _errorIndexPosition = -1;

    protected static final short PASSWORD_MINIMUM_LENGTH = 20;

    protected static final String EMPTY_STRING = "";

    protected static final String ERR_INTRO = "The specified password ";
    protected static final String ERR_PASSWORD_TOO_SHORT = Validator.ERR_INTRO + "is too short.";
    protected static final String ERR_PASSWORD_NO_NUMBERS = Validator.ERR_INTRO + "does not contain any numbers.";
    protected static final String ERR_PASSWORD_NO_CASE_MIX = Validator.ERR_INTRO + "needs upper and lower case characters.";

    public Validator(String password) {
        this._password = password;
    }

    public Validator(String[] passwords) {
        this._passwords = passwords;
    }

    public void setPassword(String password) {
        this._password = password;
    }

    public void setPasswords(String[] passwords) {
        this._passwords = passwords;
    }

    public String getPassword() {
        return this._password;
    }

    public String[] getPasswords() {
        return this._passwords;
    }

    public String getErrorMessage() {
        return !this._errorMessage.isEmpty() ? this._errorMessage : "";
    }

    public int getErrorIndexPos() {
        return this._errorIndexPosition;
    }

    public boolean performCheck() {
        if ( !this._password.isEmpty() ) {
            return this.performPasswordCheck(this._password);
        }

        return this.performPasswordsCheck();
    }

    protected boolean performPasswordCheck(String password) {
        if ( !this.isLongEnough(password) ) {
            this._errorMessage = Validator.ERR_PASSWORD_TOO_SHORT;
            return false;
        }

        if ( !this.containsNumbers(password) ) {
            this._errorMessage = Validator.ERR_PASSWORD_NO_NUMBERS;
            return false;
        }

        if ( !this.hasCaseMix(password) ) {
            this._errorMessage = Validator.ERR_PASSWORD_NO_CASE_MIX;
            return false;
        }

        return true;
    }

    protected boolean performPasswordsCheck() {
        for (int index = 0; index <= this._passwords.length - 1; index++) {
            if ( !this.performPasswordCheck(this._passwords[index]) ) {
                this._errorIndexPosition = index;
                return false;
            }
        }

        return true;
    }

    protected boolean isLongEnough(String password) {
        return password.length() >= Validator.PASSWORD_MINIMUM_LENGTH;
    }

    protected boolean containsNumbers(String password) {
        Pattern digits = Pattern.compile("[0-9]");
        Matcher numbers = digits.matcher(password);

        return numbers.find();
    }

    protected boolean hasCaseMix(String password) {
        return password != password.toLowerCase();
    }
}
