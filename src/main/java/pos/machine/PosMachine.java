package pos.machine;

import java.util.*;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) { return null; }

    public List<ItemInfo> getItemsInfo() {
        return ItemDataLoader.loadAllItemInfos();
    }

    public List<Item> convertToItems(List<String> barcodes) {
        List<ItemInfo> itemsInfo = getItemsInfo();
        List<Item> allItems = new ArrayList<>();

        for (String barcode : barcodes) {
            for (ItemInfo itemInfo : itemsInfo) {
                if (barcode.equals(itemInfo.getBarcode())) {
                    Item newItem = new Item(itemInfo.getName(), Collections.frequency(barcodes, barcode), itemInfo.getPrice());
                    allItems.add(newItem);
                }
            }
        }
        return new ArrayList<>(new LinkedHashSet<>(allItems));
    }

    public List<Item> calculateSubtotal(List<Item> purchasedItems) {
        for (Item itemInfo : purchasedItems) {
            int subTotal = itemInfo.getUnitPrice() * itemInfo.getQuantity();
            itemInfo.setSubTotal(subTotal);
        }
        return purchasedItems;
    }

    public Receipt calculateTotalCost(List<Item> itemsWithSubtotal) {
        int totalCost = 0;
        for (Item itemInfo : itemsWithSubtotal) {
            totalCost += itemInfo.getSubTotal();
        }
        return new Receipt(totalCost, itemsWithSubtotal);
    }

    public Receipt calculateReceipt(List<Item> purchasedItems) {
        List<Item> itemsWithSubtotal = calculateSubtotal(purchasedItems);
        return calculateTotalCost(itemsWithSubtotal);
    }

}
