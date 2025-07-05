import entities.Customer;
import org.junit.jupiter.api.Test;

public class ValueTest {
    @Test
    void test1(){
        Customer c1 = Customer.createCustomer("Susie", -79);
        Customer c2 = Customer.createCustomer("kris", 99);
    }
}
