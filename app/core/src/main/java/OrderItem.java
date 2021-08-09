import java.math.BigDecimal;

public class OrderItem {

    private final String itemId;
    private final BigDecimal price;
    private final int quantity;

    public OrderItem(String itemId, BigDecimal price, int quantity) {
        this.itemId = itemId;
        this.price = price;
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return this.price.multiply(BigDecimal.valueOf(quantity));
    }

}
