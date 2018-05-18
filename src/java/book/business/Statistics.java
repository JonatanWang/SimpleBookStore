package book.business;

import java.util.Date;
import java.io.Serializable;

/**
 * JavaBean class Statistics
 * For statistics purpose.
 * Customers download and read a sample chapter of a book
 */
public class Statistics implements Serializable {

    private Long downloadId;
    private Customer user;
    private Date downloadDate;
    private String productCode;

    public Statistics() {
        user = null;
        downloadDate = new Date();
        productCode = "";
    }

    public Long getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(Long downloadId) {
        this.downloadId = downloadId;
    }

    public void setUser(Customer user) {
        this.user = user;
    }

    public Customer getUser() {
        return user;
    }

    public void setDownloadDate(Date date) {
        downloadDate = date;
    }

    public Date getDownloadDate() {
        return downloadDate;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }
}