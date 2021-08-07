package pos.machine;

import java.util.*;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        return null;
    }

    public List<ItemInfo> getItemsInfo() {
           return ItemDataLoader.loadAllItemInfos();
    }

    public List<Item> convertToItems(List<String> barcodes) {
        List<ItemInfo> itemsInfo = getItemsInfo();
        List<Item> purchasedItems = new ArrayList<>(new HashSet<>());
        int quantity;
        int subTotal = 0;
        int i = 0;
        for (String barcode : barcodes) {
            if (barcode.equals(itemsInfo.get(i).getBarcode())) {
                quantity = Collections.frequency(barcodes, barcode);
                Item newItem = new Item(itemsInfo.get(i).getName(), quantity, itemsInfo.get(i).getPrice(), subTotal);
                purchasedItems.add(newItem);
                i++;
            }

        }
        return purchasedItems;
    }

    public List<Item> calculateSubtotal(List<Item> purchasedItems) {
        List<Item> itemsWithSubtotal = new ArrayList<>();
        for (Item itemInfo : purchasedItems) {
            int subTotal = itemInfo.getUnitPrice() * itemInfo.getQuantity();
            Item updatedItem = new Item(itemInfo.getName(), itemInfo.getQuantity(), itemInfo.getUnitPrice(), subTotal);
            itemsWithSubtotal.add(updatedItem);

        }
        return itemsWithSubtotal;
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
        Receipt receipt = calculateTotalCost(itemsWithSubtotal);

        return receipt;
    }
}
