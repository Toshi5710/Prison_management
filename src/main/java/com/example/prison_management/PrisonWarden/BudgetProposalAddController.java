package com.example.prison_management.PrisonWarden;

import com.example.prison_management.utils.BinaryFileUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class BudgetProposalAddController {

    @FXML
    private TextField ProposalIdTF;
    @FXML
    private TextField ProposalAmountTF;
    @FXML
    private TextField FiscalyearTF;
    @FXML
    private TextField NarrativeTF;

    @FXML
    public void AddProposalOA(ActionEvent actionEvent) {
        String id = ProposalIdTF.getText();
        String amount = ProposalAmountTF.getText();
        String year = FiscalyearTF.getText();
        String narrative = NarrativeTF.getText();

        BudgetProposal proposal = new BudgetProposal(id, amount, narrative, year, "Pending");

        File f = new File("BudgetProposal.bin");
        FileOutputStream fos;
        ObjectOutputStream oos;

        try {
            if (f.exists() && f.length() > 0) {
                fos = new FileOutputStream(f, true);
                oos = new BinaryFileUtil.AppendableObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }

            oos.writeObject(proposal);
            oos.close();

            ProposalIdTF.clear();
            ProposalAmountTF.clear();
            FiscalyearTF.clear();
            NarrativeTF.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}