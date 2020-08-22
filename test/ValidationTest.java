package test;

import ir.maktab.service.UserService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidationTest {

    UserService userService = new UserService();

    @Test
    public void testValidPassword() {
        String correct0 = "tina235";
        String uncorrected1 = "tina";
        String uncorrected2 = "235";
        boolean actualResult0 = userService.isValidPassword(correct0);
        Assert.assertTrue(actualResult0);
        boolean actualResult1 = userService.isValidPassword(uncorrected1);
        Assert.assertFalse(actualResult1);
        boolean actualResult2 = userService.isValidPassword(uncorrected2);
        Assert.assertFalse(actualResult2);
    }

    @Test
    public void testValidMobile(){
        String correct0 = "09356445978";
        String uncorrected1 = "sdjdhfd";
        String uncorrected2 = "sara235";
        boolean actualResult0 = userService.isValidMobile(correct0);
        Assert.assertTrue(actualResult0);
        boolean actualResult1 = userService.isValidMobile(uncorrected1);
        Assert.assertFalse(actualResult1);
        boolean actualResult2 = userService.isValidMobile(uncorrected2);
        Assert.assertFalse(actualResult2);
    }

    @Test
    public void testValidAge(){
        String correct0 = "16";
        String uncorrected1 = "sdkjh";
        String uncorrected2 = "5j";
        boolean actualResult0 = userService.isValidAge(correct0);
        Assert.assertTrue(actualResult0);
        boolean actualResult1 = userService.isValidAge(uncorrected1);
        Assert.assertFalse(actualResult1);
        boolean actualResult2 = userService.isValidAge(uncorrected2);
        Assert.assertFalse(actualResult2);
    }

    @Test
    public void testValidMailAddress(){
        String correct0 = "asal@google.com";
        String uncorrected1 = "asal@google";
        String uncorrected2 = "@.com";
        String uncorrected3 = "asal";
        boolean actualResult0 = userService.isValidMailAddress(correct0);
        Assert.assertTrue(actualResult0);
        boolean actualResult1 = userService.isValidMailAddress(uncorrected1);
        Assert.assertFalse(actualResult1);
        boolean actualResult2 = userService.isValidMailAddress(uncorrected2);
        Assert.assertFalse(actualResult2);
        boolean actualResult3 = userService.isValidMailAddress(uncorrected3);
        Assert.assertFalse(actualResult3);
    }

    @Test
    public void testValidZipcode(){
        String correct0 = "1234567890";
        String uncorrected1 = "sdkjhmkhjh";
        String uncorrected2 = "123456789h";
        boolean actualResult0 = userService.isValidZipcode(correct0);
        Assert.assertTrue(actualResult0);
        boolean actualResult1 = userService.isValidZipcode(uncorrected1);
        Assert.assertFalse(actualResult1);
        boolean actualResult2 = userService.isValidZipcode(uncorrected2);
        Assert.assertFalse(actualResult2);
    }
}
