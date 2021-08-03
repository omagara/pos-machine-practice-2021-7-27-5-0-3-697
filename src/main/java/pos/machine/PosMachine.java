package pos.machine;

import java.util.List;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        return null;
    }

    public static List<ItemInfo> getItemsInfo (){
        ItemDataLoader items = new ItemDataLoader();
        List<ItemInfo> itemsInfo= items.loadAllItemInfos();

        return itemsInfo;
    }
}
