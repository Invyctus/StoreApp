package utils;

import gui.Home.HomeGUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds all methods that return data to aspects of gui
 */
public class Processes {
    public static boolean verifySignup(char[] passw, char[] verifyPass, int eVerify, String ... args) {
        while(eVerify == 0){
            if (!FieldVerification.verifyPhone(args[0])
                    || !FieldVerification.verifyAddress(args[1])
                    || !FieldVerification.verifyEmail(args[2])
                    || !FieldVerification.verifyZip(args[3])
                    || !FieldVerification.verifyTextInput(args[4])
                    || !FieldVerification.verifyTextInput(args[5])
                    || !FieldVerification.verifyTextInput(args[6])
                    || !FieldVerification.verifyTextInput(args[7])
                    || !FieldVerification.verifyTextInput(args[8])
                    || !FieldVerification.verifyPassword(passw, verifyPass)) {
                return false;
            } else {

                String insertCustomer = "INSERT INTO CUSTOMERS (CUSTFIRSTNAME, CUSTLASTNAME, CUSTEMAIL, CUSTPHONE) VALUES ('" + args[4] + "',"
                        + " '" + args[5] + "'," + " '" + args[2] + "'," + " '" + args[0] + "')";
                IODB.executeQueries(insertCustomer);
                char[] pass = passw;
                String passString = new String(pass);

                String getCustID = "SELECT MAX(CUSTID) FROM CUSTOMERS";
                ArrayList<ArrayList<Object>> retrieveID = IODB.getQueryResults(getCustID);

                for (ArrayList<Object> arrayList : retrieveID) {
                    for (Object o : arrayList) {
                        String insertAccount = "INSERT INTO ACCOUNT (CUSTEMAIL, PASSWORD, CUSTID)  VALUES ('" + args[2] + "',"
                                + " '" + passString + "'," + " '" + o.toString() + "')";

                        String insertAddress = "INSERT INTO ADDRESS (STATE, CITY, COUNTRY, ZIP, ADDRESSL1, CUSTID, ADDRESSL2) VALUES ('" + args[6] + "',"
                                + " '" + args[7] + "'," + " '" + args[8] + "'," + " '" + args[3] + "'," + " '" + args[1] + "',"
                                + " '" + o.toString() + "'," + " '" + args[9] + "')";

                        IODB.executeQueries(insertAccount);
                        IODB.executeQueries(insertAddress);

                    }
                }
                return true;
            }
        }

        while(eVerify == 1) {
            if (!FieldVerification.verifyPhone(args[0])
                    || !FieldVerification.verifyAddress(args[1])
                    || !FieldVerification.verifyEmail2(args[2])
                    || !FieldVerification.verifyZip(args[3])
                    || !FieldVerification.verifyTextInput(args[4])
                    || !FieldVerification.verifyTextInput(args[5])
                    || !FieldVerification.verifyTextInput(args[6])
                    || !FieldVerification.verifyTextInput(args[7])
                    || !FieldVerification.verifyTextInput(args[8])) {
                return false;
            } else {
                return  true;
            }
        }

        return false;
    }
    public static boolean verifyLogin(String ... args) {
        if(!FieldVerification.login(args[0], args[1])) {
            JOptionPane.showMessageDialog(null, "Username and password don't match");
            return false;
        }

        return true;
    }
    public static String[] getCustomerInfo() {
        String[] info = new String[100];
        String custid = FieldVerification.getCustid();
        int i = 0;

        String getInfo = "SELECT * FROM CUSTOMERS WHERE CUSTID = " + custid;

        ArrayList<ArrayList<Object>> getCInfo = IODB.getQueryResults(getInfo);
        for (ArrayList<Object> arrayList1 : getCInfo) {
            for (Object o : arrayList1) {
                info[i] = o.toString();
                i++;
            }
        }

        String getAInfo = "SELECT STATE, CITY, COUNTRY, ZIP, ADDRESSL1 FROM ADDRESS WHERE CUSTID = " + custid;

        ArrayList<ArrayList<Object>> getAddrInfo = IODB.getQueryResults(getAInfo);
        for (ArrayList<Object> arrayList1 : getAddrInfo) {
            for (Object a : arrayList1) {
                info[i] = a.toString();
                i++;
            }
        }

        return info;
    }
    public static boolean verifyPayment(String ... args) {
        if(!FieldVerification.verifyCCName(args[0])
                || !FieldVerification.verifyCCNum(args[1])
                || !FieldVerification.verifyCCV(args[2])
                || !FieldVerification.verifyAddress(args[3])
                || !FieldVerification.verifyZip(args[4])
                || !FieldVerification.verifyTextInput(args[5])
                || !FieldVerification.verifyTextInput(args[6])
                || !FieldVerification.verifyTextInput(args[7])) {
            return false;
        }
        return true;
    }
    public static double[] getTotals(double shipping) {
        double[] totals = new double[3];

        totals[0] = CalcTotals.getItemTotal();
        totals[1] = CalcTotals.getTax(totals[0]);
        totals[2] = CalcTotals.getFinal(shipping, totals[1], totals[0]);

        return totals;
    }
    public static void completeOrder(int isShipping, double[] totals, String ... args) {
        System.out.println("okay");
        String custid = FieldVerification.getCustid();

        if (isShipping == 1) {
            String insertAddress = "INSERT INTO ADDRESS (STATE, CITY, COUNTRY, ZIP, ADDRESSL1, CUSTID, ADDRESSL2) VALUES ('" + args[0] + "', '" + args[1]  + "', '"
                    + args[2] + "', '" + args[3]  + "', '" + args[4]  + "', '" + custid + "', '" + args[5]  + "')";

            IODB.executeQueries();
        } else {
            String insertSAddress = "INSERT INTO ADDRESS (STATE, CITY, COUNTRY, ZIP, ADDRESSL1, CUSTID, ADDRESSL2) VALUES ('" + args[0] + "', '" + args[1]  + "', '"
                    + args[2] + "', '" + args[3]  + "', '" + args[4]  + "', '" + custid + "', '" + args[5]  + "')";
            String insertBAddress = "INSERT INTO ADDRESS (STATE, CITY, COUNTRY, ZIP, ADDRESSL1, CUSTID, ADDRESSL2) VALUES ('" + args[6] + "', '" + args[7] + "', '"
                    + args[8] + "', '" + args[9] + "', '" + args[10] + "', '" + custid + "', '" + args[11] + "')";

            IODB.executeQueries(insertSAddress);
            IODB.executeQueries(insertBAddress);
        }
        String insertPaymentMethod = "INSERT INTO PAYMENT_METHOD ( CUSTID, PAYMENTMETHOD) VALUES ('" + custid + "', '" + args[12] + ", "
                + args[13] + ", " + args[14] + "')";
        IODB.executeQueries(insertPaymentMethod);

        String insertOrder = "INSERT INTO ORDERS (ORDERSUBTOTAL, ORDERTAX, ORDERTOTAL, CUSTID) VALUES ('" + totals[0] + "', '" + totals[1] + "', '"
                + totals[2] + "', '" + custid + "')";
        IODB.executeQueries(insertOrder);

        String insertPayment = "INSERT INTO PAYMENT (PAYMENTMETHOD) VALUES ('" + args[12] + ", " + args[13] + ", " + args[14] + "')";

        String clearIt = "delete from cart where custid = " + custid;
        IODB.executeQueries(clearIt);
    }
    public static void addToCart(String ... args) {
        String custid = FieldVerification.getCustid();

        String insertCart = "INSERT INTO CART (ITEMDESC, PRICE, QUANTITY, CUSTID) VALUES ('" + args[0] + "', " + "'" + args[1] + "', "
                + "'" + args[2] + "', " + "'" + custid + "')";

        IODB.executeQueries(insertCart);
    }
    public static String[] setImages(boolean isMens, boolean isRotate, int color, int pattern) {
        String shirt = null;
        System.out.println(isMens);
        System.out.println(color);
        System.out.println(isRotate);

        String[][] menShirtColor = {
                {"C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\MensShirtImgs\\mwhitefront.png", "C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\MensShirtImgs\\mwhiteback.png"},
                {"C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\MensShirtImgs\\mgreenfront.png", "C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\MensShirtImgs\\mgreenback.png"},
                {"C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\MensShirtImgs\\mblackfront.png", "C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\MensShirtImgs\\mblackback.png"},
                {"C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\MensShirtImgs\\mredfront.png", "C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\MensShirtImgs\\mredback.png"},
                {"C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\MensShirtImgs\\mbluefront.png", "C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\MensShirtImgs\\mblueback.png"},
                {"C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\MensShirtImgs\\morangefront.png", "C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\MensShirtImgs\\morangeback.png"},
                {"C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\MensShirtImgs\\myellowfront.png", "C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\MensShirtImgs\\myellowback.png"},

        };
        String[][] womenShirtColor = {
                {"C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\WomensShirtImgs\\wwhitefront.png", "C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\WomensShirtImgs\\wwhiteback.png"},
                {"C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\WomensShirtImgs\\wgreenfront.png", "C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\WomensShirtImgs\\wgreenback.png"},
                {"C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\WomensShirtImgs\\wblackfront.png", "C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\WomensShirtImgs\\wblackback.png"},
                {"C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\WomensShirtImgs\\wredfront.png", "C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\WomensShirtImgs\\wredback.png"},
                {"C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\WomensShirtImgs\\wbluefront.png", "C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\WomensShirtImgs\\wblueback.png"},
                {"C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\WomensShirtImgs\\worangefront.png", "C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\WomensShirtImgs\\worangeback.png"},
                {"C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\WomensShirtImgs\\wyellowfront.png", "C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\WomensShirtImgs\\wyellowback.png"},

        };

        if(isMens) {
            if(isRotate) {
                shirt = menShirtColor[color][1];
            } else {
                shirt = menShirtColor[color][0];
            }
        } else {
            if(isRotate) {
                shirt = womenShirtColor[color][1];
            } else {
                shirt = womenShirtColor[color][0];
            }
        }

        String decal = setDecal(isMens, pattern);
        String icons[] = {shirt, decal};
        return icons;

    }
    public static String setDecal(boolean isMens, int pattern) {
        String icon = null;

        String[][] decal = {
                {" ", " "},
                {"C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\men-decal\\mcomputerdecal.png", "C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\women-decal\\wcomputerdecal.png"},
                {"C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\men-decal\\mdaddy-odecal.png", "C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\women-decal\\wdaddy-odecal.png"},
                {"C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\men-decal\\mfucksnotfounddecal.png", "C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\women-decal\\wfucksnotfounddecal.png"},
                {"C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\men-decal\\mleavemealonedecal.png", "C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\women-decal\\wleavemealonedecal.png"},
                {"C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\men-decal\\mwheresthebeoufdecal.png", "C:\\Users\\User\\IdeaProjects\\StoreApp\\src\\resources\\Shirts\\women-decal\\wwherestheboeufdecal.png"},

        };

        if(isMens) {
            icon = decal[pattern][0];
        } else {
            icon = decal[pattern][1];
        }

        return icon;
    }
    public static boolean isWaitlisted(String color, String size, boolean isMens) {
        int quantity = 0;
        String getQuantity  = "SELECT QUANTITYINSTOCK FROM ITEMS WHERE ITEMCOLOR = '" + color + "' AND ITEMSIZE = '" + size + "'";

        if (!isMens) {
           getQuantity = "SELECT QUANTITYINSTOCK FROM ITEMS WHERE ITEMCOLOR = '" + color + "' AND ITEMSIZE = '" + size + "' AND ITEMID > 35";
        }

        ArrayList<ArrayList<Object>> inStock = IODB.getQueryResults(getQuantity);

        for (ArrayList<Object> arrayList : inStock) {
            for (Object o : arrayList) {
                quantity = Integer.parseInt(o.toString());
            }
        }

        if(quantity <= 0) {
            return false;
        }
        return true;
    }
}
