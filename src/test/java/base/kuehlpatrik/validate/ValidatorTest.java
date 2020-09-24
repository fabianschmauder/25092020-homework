package base.kuehlpatrik.validate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ValidatorTest {
    public static Validator tooShort = new Validator("*,_$,+`[5d'.k2s3");
    public static Validator noNumbers = new Validator("UwQDQvLFv:,*Nh[''$C+nV]Nexay_&k+y%>,P");
    public static Validator noCaseMix = new Validator("[767>9j#-j9v~=?[vqtfw$-@f*j85&,a~gpd");
    public static Validator perfectPassword = new Validator("=UAy{tL)8guB<(r5w'#$aNfH");
    public static Validator perfectPasswords = new Validator(new String[]{
        "/W@*yY}T9_:.*7D.~HqA4L8*",
        "$KdTh_7Man%f3B(-a):e-QP&",
        "#St>+H+?tGfUGy<`g*7mg'#q"
    });
    public static Validator notThatPerfectPasswords = new Validator(new String[]{
        "){~ga5'L{w)J[vW}(y{DBS6P",
        "tZ82Hm&AzrX?h!JVTLCcqh;B",
        "iammessingitup",
        "t(TUEf3+*j24]';Gtj2$JDxD"
    });

    @Test
    @DisplayName("Password is too short")
    public void passwordTooShort() {
        Assertions.assertEquals( false, tooShort.performCheck() );
    }

    @Test
    @DisplayName("Password has no numbers")
    public void passwordHasNoNumbers() {
        Assertions.assertEquals( false, noNumbers.performCheck() );
    }

    @Test
    @DisplayName("Password has no case mix")
    public void passwordHasNoCaseMix() {
        Assertions.assertEquals( false, noCaseMix.performCheck() );
    }

    @Test
    @DisplayName("Password respects all requirements")
    public void passwordIsFine() {
        Assertions.assertEquals( true, perfectPassword.performCheck() );
    }

    @Test
    @DisplayName("Any Password respects all requirements")
    public void passwordsAreFine() {
        Assertions.assertEquals( true, perfectPasswords.performCheck() );
    }

    @Test
    @DisplayName("One password does not respect all requirements")
    public void onePasswordIsMessingItUp() {
        Assertions.assertEquals( false, notThatPerfectPasswords.performCheck() );
    }

    @Test
    @DisplayName("\"isLongEnough\" method returns true if password has exceeded minimum length")
    public void isLongEnoughReturnsExpectedTrue() {
        Assertions.assertEquals( true, perfectPassword.isLongEnough( perfectPassword.getPassword() ) );
    }

    @Test
    @DisplayName("\"isLongEnough\" method returns false if password is too short")
    public void isLongEnoughReturnsExpectedFalse() {
        Assertions.assertEquals( false, tooShort.isLongEnough( tooShort.getPassword() ) );
    }

    @Test
    @DisplayName("\"containsNumbers\" method returns true if password contains numbers")
    public void containsNumbersReturnsExpectedTrue() {
        Assertions.assertEquals( true, perfectPassword.containsNumbers( perfectPassword.getPassword() ) );
    }

    @Test
    @DisplayName("\"containsNumbers\" method returns false if password contains no numbers")
    public void containsNumbersReturnsExpectedFalse() {
        Assertions.assertEquals( false, noNumbers.containsNumbers( noNumbers.getPassword() ) );
    }
}
