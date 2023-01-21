package views;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.checklist.showChecklist.ShowChecklistRequest;
import ports.usecases.checklist.showChecklist.ShowChecklistResponse;
import usecases.checklist.ShowChecklistUseCase;

import javax.swing.*;
import java.awt.*;

public class ChecklistView {

    public ChecklistView(EntityGateway entityGateway, EntityFactory entityFactory,
                         String username){

        Font textFont = new Font("Georgia", Font.PLAIN, 14);

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(null);

        frame.setSize(500, 800);
        frame.add(panel);
        frame.setVisible(true);

        JLabel title = new JLabel("Assignment Checklist");
        title.setBounds(150, 0, 200, 25);
        title.setFont(new Font("Georgia", Font.BOLD, 16));
        panel.add(title);

        JCheckBox showTodo = new JCheckBox("Show Tasks Unfinished", true);
        showTodo.setBounds(5, 540, 200, 25);
        showTodo.setFont(textFont);
        panel.add(showTodo);

        JCheckBox showCommitted = new JCheckBox("Show Tasks Committed");
        showCommitted.setBounds(5, 570, 200, 25);
        showCommitted.setFont(textFont);
        panel.add(showCommitted);

        JCheckBox showSubmitted = new JCheckBox("Show Tasks Submitted");
        showSubmitted.setBounds(5, 600, 200, 25);
        showSubmitted.setFont(textFont);
        panel.add(showSubmitted);

        JCheckBox showDetail = new JCheckBox("Show Detail");
        showDetail.setBounds(5, 630, 200, 25);
        showDetail.setFont(textFont);
        panel.add(showDetail);

        JCheckBox sortInDDL = new JCheckBox("Sort In Due Time");
        sortInDDL.setBounds(5, 660, 200, 25);
        sortInDDL.setFont(textFont);
        panel.add(sortInDDL);

        JCheckBox sortInMark = new JCheckBox("Sort In Mark");
        sortInMark.setBounds(5, 690, 200, 25);
        sortInMark.setFont(textFont);
        panel.add(sortInMark);

        ShowChecklistRequest request = new ShowChecklistRequest(username, true, false,
                false, false, false, false);
        ShowChecklistUseCase showChecklistUseCase = new ShowChecklistUseCase(entityGateway);

        ShowChecklistResponse response = showChecklistUseCase.execute(request);

        JTextArea checklistField = new JTextArea();
        checklistField.setBounds(0, 30, 500, 500);
        checklistField.setFont(textFont);
        checklistField.setEditable(false);
        if (response.taskChecklist == null){
            checklistField.append("No Tasks");
        } else {
            checklistField.append(response.taskChecklist.toString());
        }
        panel.add(checklistField);

        JButton refresher = new JButton("Refresh the Checklist");
        refresher.setBounds(200, 690, 200, 25);
        refresher.setFont(textFont);
        panel.add(refresher);

        refresher.addActionListener(e -> {
            if (sortInDDL.isSelected() && sortInMark.isSelected()){
                JOptionPane.showMessageDialog(null, "Cannot Select Both Sort Ways",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (response.taskChecklist != null){
                ShowChecklistRequest request2 = new ShowChecklistRequest(username, showTodo.isSelected(),
                        showCommitted.isSelected(), showSubmitted.isSelected(), showDetail.isSelected(), sortInDDL.isSelected(),
                        sortInMark.isSelected());
                ShowChecklistUseCase showChecklistUseCase2 = new ShowChecklistUseCase(entityGateway);

                ShowChecklistResponse response2 = showChecklistUseCase2.execute(request2);
                checklistField.setText("");
                checklistField.append(response2.taskChecklist.toString());
            } else {
                checklistField.setText("No Tasks");
            }
        });


    }
}
