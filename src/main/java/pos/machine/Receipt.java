package pos.machine;

import java.util.List;

public class Receipt {
    private int totalCost;
    private List<Item> itemsWithSubtotal;

    public Receipt(int totalCost, List<Item> itemsWithSubtotal) {
        this.totalCost = totalCost;
        this.itemsWithSubtotal = itemsWithSubtotal;
    }


    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public List<Item> getItemsWithSubtotal() {
        return itemsWithSubtotal;
    }

    public void setItemsWithSubtotal(List<Item> itemsWithSubtotal) {
        this.itemsWithSubtotal = itemsWithSubtotal;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "totalCost=" + totalCost +
                ", itemsWithSubtotal=" + itemsWithSubtotal +
                '}';
    }
}
