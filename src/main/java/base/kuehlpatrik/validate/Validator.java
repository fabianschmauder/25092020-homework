package base.kuehlpatrik.validate;

public class Validator {
    protected String _password;
    protected String[] _passwords;
    protected String _errorMessage;
    protected int _errorIndexPosition = -1;
    protected String _findNumbersRegex = "\".*\\\\d+.*\"";

    protected static final short PASSWORD_MAXIMUM_LENGTH = 20;

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
        return (this._errorMessage != null) ? this._errorMessage : "";
    }

    public int getErrorIndexPos() {
        return this._errorIndexPosition;
    }

    public boolean performCheck() {
        if (this._password != null) {
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
                return false;
            }
        }

        return true;
    }

    protected boolean isLongEnough(String password) {
        return password.length() >= Validator.PASSWORD_MAXIMUM_LENGTH;
    }

    protected boolean containsNumbers(String password) {
        return password.matches(this._findNumbersRegex);
    }

    protected boolean hasCaseMix(String password) {
        return password != password.toLowerCase();
    }
}
