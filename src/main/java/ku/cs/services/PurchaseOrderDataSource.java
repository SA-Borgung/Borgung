package ku.cs.services;

import ku.cs.models.PreparePond;
import ku.cs.models.PreparePondList;
import ku.cs.models.PurchaseOrder;
import ku.cs.models.PurchaseOrderList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PurchaseOrderDataSource implements DataSource<PurchaseOrderList> {

    private DatabaseConnection databaseConnection;

    public PurchaseOrderDataSource() {}

    public PurchaseOrderDataSource(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    @Override
    public PurchaseOrderList readData() {
        PurchaseOrderList list = new PurchaseOrderList();

        databaseConnection = new DatabaseConnection();
        Connection connectDB = databaseConnection.getConnection();
        String connectQuery = "SELECT * FROM purchase_order";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(connectQuery);

            while (queryOutput != null && queryOutput.next()){
                String id = queryOutput.getString("O_ID");
                String weight = queryOutput.getString("P_WEIGHT");
                String species = queryOutput.getString("P_SPECIES");
                String age = queryOutput.getString("P_AGE");
                String customerID = queryOutput.getString("C_ID");



                list.addPurchaseOrder(
                        new PurchaseOrder(
                                id,
                                weight,
                                species,
                                Integer.parseInt(age),
                                customerID

                        )
                );

            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void insertData(PurchaseOrderList purchaseOrderList) {

    }

}
