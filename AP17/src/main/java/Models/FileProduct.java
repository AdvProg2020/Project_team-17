package Models;

import Models.Accounts.Seller;

import java.io.File;

public class FileProduct extends Product {
    File file;

    public FileProduct(String productId, String name, String companyName, double price, Seller seller, Category category, String explanation, double averageScore, String productsSpecialFeature, String path, File file) {
        super(productId, name, companyName, price, seller, category, explanation, averageScore, productsSpecialFeature, path);
        this.file = file;
        /*file.setWritable(true);
        file.*/
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
