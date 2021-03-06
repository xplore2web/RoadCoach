/*
 * Code written by Bruno Lowagie in the context of an example.
 */
package com.alcord.invoice;

import java.util.Map;
import java.util.TreeMap;

import com.itextpdf.text.zugferd.checkers.basic.DateFormatCode;
import com.itextpdf.text.zugferd.checkers.basic.DocumentTypeCode;
import com.itextpdf.text.zugferd.checkers.basic.TaxIDTypeCode;
import com.itextpdf.text.zugferd.checkers.basic.TaxTypeCode;
import com.itextpdf.text.zugferd.checkers.comfort.FreeTextSubjectCode;
import com.itextpdf.text.zugferd.checkers.comfort.PaymentMeansCode;
import com.itextpdf.text.zugferd.checkers.comfort.TaxCategoryCode;
import com.itextpdf.text.zugferd.profiles.BasicProfile;
import com.itextpdf.text.zugferd.profiles.BasicProfileImp;
import com.itextpdf.text.zugferd.profiles.ComfortProfile;
import com.itextpdf.text.zugferd.profiles.ComfortProfileImp;

/**
 *
 * @author Bruno Lowagie (iText Software)
 */
public class InvoiceData {
    
    public InvoiceData() {
    }
    
    public BasicProfile createBasicProfileData(Invoice invoice) {
        BasicProfileImp profileImp = new BasicProfileImp();
        importData(profileImp, invoice);
        importBasicData(profileImp, invoice);
        return profileImp;
    }
    
    public ComfortProfile createComfortProfileData(Invoice invoice) {
        ComfortProfileImp profileImp = new ComfortProfileImp();
        importData(profileImp, invoice);
        importComfortData(profileImp, invoice);
        return profileImp;
    }
   
    public void importData(BasicProfileImp profileImp, Invoice invoice) {
        profileImp.setTest(true);
        profileImp.setId(String.format("I/%05d", invoice.getId()));
        profileImp.setName("INVOICE");
        profileImp.setTypeCode(DocumentTypeCode.COMMERCIAL_INVOICE);
        profileImp.setDate(invoice.getInvoiceDate(), DateFormatCode.YYYYMMDD);
		profileImp.setSellerName("Namma Taxi");
		profileImp.setSellerLineOne("Alcord Solutions Pvt Ltd");
		profileImp.setSellerLineTwo("KB Sandra");
		profileImp.setSellerPostcode("560032");
		profileImp.setSellerCityName("Bangalore");
		profileImp.setSellerCountryID("IN");
        profileImp.addSellerTaxRegistration(TaxIDTypeCode.FISCAL_NUMBER, "201/113/40209");
        profileImp.addSellerTaxRegistration(TaxIDTypeCode.VAT, "BE123456789");
        Customer customer = invoice.getCustomer();
        profileImp.setBuyerName(String.format("%s, %s", customer.getLastName(), customer.getFirstName()));
        profileImp.setBuyerPostcode(customer.getPostalcode());
        profileImp.setBuyerLineOne(customer.getStreet());
        profileImp.setBuyerCityName(customer.getCity());
        profileImp.setBuyerCountryID(customer.getCountryId());
        profileImp.setPaymentReference(String.format("%09d", invoice.getId()));
		profileImp.setInvoiceCurrencyCode("INR");
    }
    
    public void importBasicData(BasicProfileImp profileImp, Invoice invoice) {
        profileImp.addNote(
            new String[]{"This is a test invoice.\nNothing on this invoice is real.\nThis invoice is part of a tutorial."});
        profileImp.addPaymentMeans("", "", "BE 41 7360 0661 9710", "", "", "KREDBEBB", "", "KBC");
        profileImp.addPaymentMeans("", "", "BE 56 0015 4298 7888", "", "", "GEBABEBB", "", "BNP Paribas");
        Map<Double,Double> taxes = new TreeMap<Double, Double>();
        double tax;
        for (Item item : invoice.getItems()) {
            tax = item.getProduct().getVat();
            if (taxes.containsKey(tax)) {
                taxes.put(tax, taxes.get(tax) + item.getCost());
            }
            else {
                taxes.put(tax, item.getCost());
            }
            profileImp.addIncludedSupplyChainTradeLineItem(format4dec(item.getQuantity()), "C62", item.getProduct().getName());
        }
        double total, tA;
        double ltN = 0;
        double ttA = 0;
        double gtA = 0;
        for (Map.Entry<Double, Double> t : taxes.entrySet()) {
            tax = t.getKey();
            total = round(t.getValue());
            gtA += total;
            tA = round((100 * total) / (100 + tax));
            ttA += (total - tA);
            ltN += tA;
			profileImp.addApplicableTradeTax(format2dec(total - tA), "INR", TaxTypeCode.VALUE_ADDED_TAX, format2dec(tA),
					"INR", format2dec(tax));
        }
		profileImp.setMonetarySummation(format2dec(ltN), "INR", format2dec(0), "INR", format2dec(0), "INR",
				format2dec(ltN), "INR", format2dec(ttA), "INR", format2dec(gtA), "INR");
    }
   
    public void importComfortData(ComfortProfileImp profileImp, Invoice invoice) {
        profileImp.addNote(
            new String[]{"This is a test invoice.\nNothing on this invoice is real.\nThis invoice is part of a tutorial."},
            FreeTextSubjectCode.REGULATORY_INFORMATION);
        profileImp.addPaymentMeans(
                PaymentMeansCode.PAYMENT_TO_BANK_ACCOUNT,
                new String[]{"This is the preferred bank account."},
                "", "",
                "", "",
                "BE 41 7360 0661 9710", "", "",
                "", "", "",
                "KREDBEBB", "", "KBC");
        profileImp.addPaymentMeans(
                PaymentMeansCode.PAYMENT_TO_BANK_ACCOUNT,
                new String[]{"Use this as an alternative account."},
                "", "",
                "", "",
                "BE 56 0015 4298 7888", "", "",
                "", "", "",
                "GEBABEBB", "", "BNP Paribas");
        Map<Double,Double> taxes = new TreeMap<Double, Double>();
        double tax;
        int counter = 0;
        for (Item item : invoice.getItems()) {
            counter++;
            tax = item.getProduct().getVat();
            if (taxes.containsKey(tax)) {
                taxes.put(tax, taxes.get(tax) + item.getCost());
            }
            else {
                taxes.put(tax, item.getCost());
            }
            profileImp.addIncludedSupplyChainTradeLineItem(
                    String.valueOf(counter),
                    null,
					format4dec(item.getProduct().getPrice()), "INR", null, null,
                    null, null, null, null,
                    null, null, null, null,
                    format4dec(item.getQuantity()), "C62",
                    new String[]{TaxTypeCode.VALUE_ADDED_TAX},
                    new String[1],
                    new String[]{TaxCategoryCode.STANDARD_RATE},
                    new String[]{format2dec(item.getProduct().getVat())},
					format2dec(item.getCost()), "INR",
                    null, null,
                    String.valueOf(item.getProduct().getId()), null,
                    item.getProduct().getName(), null
            );
        }
        double total, tA;
        double ltN = 0;
        double ttA = 0;
        double gtA = 0;
        for (Map.Entry<Double, Double> t : taxes.entrySet()) {
            tax = t.getKey();
            total = round(t.getValue());
            gtA += total;
            tA = round((100 * total) / (100 + tax));
            ttA += (total - tA);
            ltN += tA;
            profileImp.addApplicableTradeTax(
					format2dec(total - tA), "INR", TaxTypeCode.VALUE_ADDED_TAX, null, format2dec(tA), "INR",
                    TaxCategoryCode.STANDARD_RATE, format2dec(tax));
        }
		profileImp.setMonetarySummation(format2dec(ltN), "INR", format2dec(0), "INR", format2dec(0), "INR",
				format2dec(ltN), "INR", format2dec(ttA), "INR", format2dec(gtA), "INR");
    }
    
    public static double round(double d) {
        d = d * 100;
        long tmp = Math.round(d);
        return (double) tmp / 100;
    }
    
    public static String format2dec(double d) {
        return String.format("%.2f", d);
    }
    
    public static String format4dec(double d) {
        return String.format("%.4f", d);
    }
    
}
