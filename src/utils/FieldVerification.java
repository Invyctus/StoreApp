package utils;
import gui.*;
import gui.Home.SignUpGUI;

import javax.swing.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Verification utility for varying user inputs
 */
public class FieldVerification {
    public static String custid;
    public static boolean verifyTextInput(String text) {
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Field can't be left blank.");
        }

        else if (!text.matches("[A-Z][a-zA-Z]*")) {
            JOptionPane.showMessageDialog(null, "Field can't contain numbers or special characters.");
            return false;
        }
        return true;
    }

    public static boolean verifyPhone(String phone) {
        if (!phone.matches("^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$")) {
            JOptionPane.showMessageDialog(null, "Invalid phone number.");
            return false;
        }
        else if (phone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Phone number can't be left blank.");
        }
        return true;
    }

    public static boolean verifyEmail(String email) {

        String checkExistingEmails = "SELECT CUSTEMAIL FROM CUSTOMERS";

        ArrayList<ArrayList<Object>> emailList = IODB.getQueryResults(checkExistingEmails);

        for (ArrayList<Object> arrayList : emailList) {
            for (Object o : arrayList) {
                if(email.equals(o.toString())) {
                    JOptionPane.showMessageDialog(null, "Email already registered.");
                    return false;
                }
            }
        }

        if (!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            JOptionPane.showMessageDialog(null, "Invalid  email address.");
            return false;
        }
        else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Email address can't be left blank.");
        }
        return true;
    }

    public static boolean verifyEmail2(String email) {

        if (!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            JOptionPane.showMessageDialog(null, "Invalid  email address.");
            return false;
        }
        else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Email address can't be left blank.");
        }
        return true;
    }


    public static boolean verifyZip(String zip) {
        if (!zip.matches("^[0-9]{5}(?:-[0-9]{4})?$")) {
            JOptionPane.showMessageDialog(null, "Invalid zip code.");
            return false;
        }
        else if (zip.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Zip code can't be left blank.");
        }
        return true;
    }
    public static boolean verifyPassword(char[] pass, char[] confirm) {
        if (!Arrays.equals(pass, confirm)) {
            JOptionPane.showMessageDialog(null, "Password don't match.");
            return false;
        }
        else if (pass.length == 0) {
            JOptionPane.showMessageDialog(null, "Password can't be empty");
            return false;
        }
        return true;
    }
    public static boolean verifyCCNum(String num) {
        if (num.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Credit card number can't be left blank.");
            return false;
        }
        else if(!num.matches("\\d{13,16}")) {
            JOptionPane.showMessageDialog(null, "Invalid credit card number");
            return false;
        }
        return true;
    }
    public static boolean verifyCCName(String name) {
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name on card can't be left blank.");
            return false;
        }
        else if(!name.matches("^[a-zA-Z\\s]+")) {
            JOptionPane.showMessageDialog(null, "Invalid name on card");
            return false;
        }

        return true;
    }
    public static boolean verifyCCV(String ccv) {
        if (ccv.isEmpty()) {
            JOptionPane.showMessageDialog(null, "CCV can't be left blank.");
            return false;
        }
        else if(!ccv.matches("\\d{3,4}")) {
            JOptionPane.showMessageDialog(null, "Invalid ccv");
            return false;
        }

        return true;
    }
    public static boolean verifyAddress(String address) {
        if (address.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Address can't be left blank.");
            return false;
        }
        return true;
    }
    public static boolean login(String username, String password) {

        String checkEmails = "SELECT CUSTEMAIL FROM ACCOUNT";

        ArrayList<ArrayList<Object>> emailList = IODB.getQueryResults(checkEmails);

        for (ArrayList<Object> arrayList1 : emailList) {
            for (Object u : arrayList1) {
                if (username.equals(u.toString())) {
                    String checkPass = "SELECT PASSWORD FROM ACCOUNT WHERE CUSTEMAIL = '" + username + "'";

                    ArrayList<ArrayList<Object>> verifyLogin = IODB.getQueryResults(checkPass);

                    for (ArrayList<Object> arrayList2 : verifyLogin) {
                        for (Object p : arrayList2) {
                            if(password.equals(p.toString())) {
                                String getID = "SELECT CUSTID FROM ACCOUNT WHERE CUSTEMAIL = '" + username + "'";

                                ArrayList<ArrayList<Object>> grabID = IODB.getQueryResults(getID);

                                for (ArrayList<Object> arrayList3 : grabID) {
                                    for (Object i : arrayList3) {
                                        custid = i.toString();
                                    }
                                }
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }
    public static String getCustid() {
        return custid;
    }

}
