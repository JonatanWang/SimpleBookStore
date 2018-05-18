package book.business;

import java.util.*;
import java.text.*;
import java.io.Serializable;

/**
 * Java Bean Invoice, which to be processed by administrator
 * 
 */
public class Invoice implements Serializable {
    
    private Customer user;
    private List<LineItem> lineItems;
    private Date invoiceDate;
    private Long invoiceNumber;
    private boolean isProcessed;

    public Invoice() {
    }

    public void setUser(Customer user) {
        this.user = user;
    }

    public Customer getUser() {
        return user;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public String getInvoiceDateDefaultFormat() {
        DateFormat dateFormat = DateFormat.getDateInstance();
        String invoiceDateFormatted = dateFormat.format(invoiceDate);
        return invoiceDateFormatted;
    }

    public void setInvoiceNumber(Long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Long getInvoiceNumber() {
        return invoiceNumber;
    }

    public boolean isIsProcessed() {
        return isProcessed;
    }

    public void setIsProcessed(boolean isProcessed) {
        this.isProcessed = isProcessed;
    }

    public double getInvoiceTotal() {
        double invoiceTotal = 0.0;
        for (LineItem item : lineItems) {
            invoiceTotal += item.getTotal();
        }
        return invoiceTotal;
    }

    public String getInvoiceTotalCurrencyFormat() {
        double total = this.getInvoiceTotal();
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String formattedTotal = currency.format(total);
        return formattedTotal;
    }
}