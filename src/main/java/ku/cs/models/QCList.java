package ku.cs.models;

import java.util.ArrayList;

public class QCList {
    private ArrayList<QC> qualityControls;

    public QCList() {
        qualityControls = new ArrayList<>();
    }

    public void addQC(QC qc){
        qualityControls.add(qc);
    }

    public ArrayList<QC> getAllManagePrawn() {
        return qualityControls;
    }

    public int count() {
        return qualityControls.size();
    }

    public QC getQCNumber(int num){
        QC qc = qualityControls.get(num);
        return qc;
    }

    public QC searchQcOrderById(String id){
        for (QC qualityControl : qualityControls) {
            if (qualityControl.checkID(id)) {
                return qualityControl;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return "QCList{" +
                "qualityControl=" + qualityControls +
                '}';
    }
}
