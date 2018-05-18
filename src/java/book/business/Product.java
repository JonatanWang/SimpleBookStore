/**
 * Java Bean Product, which represents Book in this case
 */
package book.business;

import java.text.NumberFormat;
import java.io.Serializable;

public class Product implements Serializable {

    private Long productId;    
    private String code;
    private String description;
    private double price;

    public Product() {}

    public Long getId() {
        return productId;
    }

    public void setId(Long productId) {
        this.productId = productId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getWriterName() {
        String writerName = 
                description.substring(0, description.indexOf(" - "));
        return writerName;
    }

    public String getBookName() {
        String bookName = 
                description.substring(description.indexOf(" - ") + 3);
        return bookName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }

    public String getImageURL() {
        String imageURL = "/bookStore/images/" + code + "_cover.jpg";
        return imageURL;
    }

    public String getProductType() {
        return "Book";
    }
}