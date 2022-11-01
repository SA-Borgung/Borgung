package ku.cs.models;

import java.util.ArrayList;

public class VendorOrderList {
    private ArrayList<VendorOrder> vendorOrders;

    public VendorOrderList() {
        vendorOrders = new ArrayList<>();
    }

    public void addVendorOrder(VendorOrder vendorOrder){vendorOrders.add(vendorOrder);}

    public ArrayList<VendorOrder> getAllVendorOrder(){return vendorOrders;}

    public int count() {return vendorOrders.size();}

    public VendorOrder getVendorOrderNumber(int num){
        VendorOrder vendorOrder = vendorOrders.get(num);
        return vendorOrder;
    }

    public VendorOrder getVendorOrderById(String id){
        for (VendorOrder vendorOrder : vendorOrders) {
            if (vendorOrder.checkId(id)) {
                return vendorOrder;
            }
        }
        return null;
    }


}
