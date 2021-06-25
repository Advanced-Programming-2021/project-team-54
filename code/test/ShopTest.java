import org.junit.jupiter.api.Test;

public class ShopTest {
    @Test
    void shopTest() {
        Shop.shopMenuController("shop show --all");
        Shop.shopMenuController("sfdf");
        Shop.shopMenuController("menu show-current");
    }
}
