package pos.machine;

import java.util.*;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        List<Item> itemsList = convertToItems(barcodes);
        Receipt receipt = calculateReceipt(itemsList);

        return generateReceipt(receipt);
    }

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

    public String addItemsDetail(Receipt receipt) {
        String itemsDetail = "";
        for (Item itemDetail : receipt.getItemsWithSubtotal()) {
            itemsDetail = itemsDetail + "Name: " + itemDetail.getName() + ", Quantity: " + itemDetail.getQuantity() +
                    ", Unit price: " + itemDetail.getUnitPrice() +
                    " (yuan), Subtotal: " + itemDetail.getSubTotal() + " (yuan)\n";
        }
        return itemsDetail;
    }

    public String addTotalCost(String itemsDetail, Double totalCost) {
        return "***<store earning no money>Receipt***\n" + itemsDetail +
                "----------------------\n" +
                "Total: " + totalCost.intValue() + " (yuan)\n" +
                "**********************";
    }

    public String generateReceipt(Receipt receipt) {
        String itemsDetail = addItemsDetail(receipt);
        double totalCost = receipt.getTotalCost();
        return addTotalCost(itemsDetail, totalCost);
    }

}
