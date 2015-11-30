package utils;

import java.util.ArrayList;

/**
 * Calculate tax, order total, item total
 */
public class CalcTotals {
    public static double getItemTotal() {
        double iTotal = 0;
        double[] price = new double[100];
        double[] quantity = new double[100];
        int i = 0, j = 0;
        String custid = FieldVerification.getCustid();
        String getPrice = "SELECT PRICE FROM CART WHERE CUSTID = " + custid;
        ArrayList<ArrayList<Object>> getCost = IODB.getQueryResults(getPrice);
        for (ArrayList<Object> arrayList : getCost) {
            for (Object o : arrayList) {
                price[i] = Double.parseDouble(o.toString());
                i++;
            }
        }
        String getQuant = "SELECT QUANTITY FROM CART WHERE CUSTID = " + custid;
        ArrayList<ArrayList<Object>> getAmt = IODB.getQueryResults(getQuant);
        for (ArrayList<Object> arrayList : getAmt) {
            for (Object o : arrayList) {
                quantity[j] = Double.parseDouble(o.toString());
                j++;
            }
        }

        for(int k = 0; k < price.length; k++) {
            iTotal += price[k] * quantity[k];
        }

        return iTotal;
    }
    public static double getTax(double item) {
        double tax = item * .075;
        return tax;
    }
    public static double getFinal(double shipping, double tax, double item) {
        double finalTotal = tax + shipping + item;
        return finalTotal;
    }
}
