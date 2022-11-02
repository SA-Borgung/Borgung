package ku.cs.models;

import java.util.ArrayList;

public class PurchaseOrderList {
    private ArrayList<PurchaseOrder> purchaseOrders;

    public PurchaseOrderList() {
        purchaseOrders = new ArrayList<>();
    }

    public void addPurchaseOrder(PurchaseOrder purchaseOrder){purchaseOrders.add(purchaseOrder);}

    public ArrayList<PurchaseOrder> getPurchaseOrders(){return purchaseOrders;}

    public int count() {return purchaseOrders.size();}

    public PurchaseOrder getPurchaseOrderNumber(int num){
        PurchaseOrder purchaseOrder = purchaseOrders.get(num);
        return purchaseOrder;
    }
}
