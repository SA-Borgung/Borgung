package ku.cs.models;

import java.util.ArrayList;

public class QCList {
    private ArrayList<QC> qcArrayList;

    public QCList() {
        qcArrayList = new ArrayList<>();
    }

    public void addQC(QC qc){qcArrayList.add(qc);}

    public ArrayList<QC> getAllManagePrawn(){return qcArrayList;}

    public int count() {return qcArrayList.size();}

    public QC getQCNumber(int num){
        QC qc = qcArrayList.get(num);
        return qc;
    }
}
