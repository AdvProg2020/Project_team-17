package Test;
import Models.Accounts.Customer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class junit {
    @Test
    public void simpleTest() throws IOException {
        ArrayList<Customer> arrayList = new ArrayList<>();
        Customer customer1 = new Customer("1", "1", "1", "1", "1", "1", 1, "1","C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Images\\male_avatar.png");
        Customer customer2 = new Customer("2", "2", "2", "2", "2", "2", 2, "2","C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Images\\male_avatar.png");
        arrayList.add(customer1);
        arrayList.add(customer2);
        Assert.assertEquals(arrayList.size(), Customer.getAllCustomers().size());


    }
}
