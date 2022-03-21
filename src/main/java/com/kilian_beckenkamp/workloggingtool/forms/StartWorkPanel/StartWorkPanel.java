/*
 * Created by JFormDesigner on Fri Jan 28 20:27:05 CET 2022
 */

package com.kilian_beckenkamp.workloggingtool.forms.StartWorkPanel;

import com.kilian_beckenkamp.workloggingtool.forms.logView.WorklogPanel;
import com.kilian_beckenkamp.workloggingtool.forms.newWorklog.NewWorkPanel;
import com.kilian_beckenkamp.workloggingtool.worklogEntry.WorklogEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Kilian Beckenkamp
 */
@Component
public class StartWorkPanel extends JPanel {

    public static JFrame frame = new JFrame();

    private final WorklogEntryService worklogEntryService;


    @Autowired
    public StartWorkPanel(WorklogEntryService worklogEntryService) {
        this.worklogEntryService = worklogEntryService;
        initComponents();
    }

    private void start(ActionEvent e) {
        startButton.setEnabled(false);
        NewWorkPanel.doYourThing(Integer.parseInt(frequencyField.getText()));
    }

    private void cancel(ActionEvent e) {
        // TODO add your code here
        System.exit(0);
    }

    private void showWorklog(ActionEvent e) {
        JFrame frame1 = new JFrame();
        frame1.setContentPane(new WorklogPanel(worklogEntryService));
        frame1.setSize(400,530);
        frame1.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        frequencyField = new JTextField();
        startButton = new JButton();
        cancelButton = new JButton();
        button1 = new JButton();

        //======== this ========

        //---- label1 ----
        label1.setText(" Worklogger V1");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 7f));
        label1.setHorizontalAlignment(SwingConstants.LEFT);

        //---- label2 ----
        label2.setText("Asking Frequency in minutes");

        //---- frequencyField ----
        frequencyField.setText("15");
        frequencyField.setHorizontalAlignment(SwingConstants.RIGHT);

        //---- startButton ----
        startButton.setText("Start working");
        startButton.addActionListener(e -> start(e));

        //---- cancelButton ----
        cancelButton.setText("Stop Working & Close");
        cancelButton.addActionListener(e -> cancel(e));

        //---- button1 ----
        button1.setText("Show Worklog");
        button1.addActionListener(e -> showWorklog(e));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup()
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 391, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(frequencyField, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                            .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(startButton, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cancelButton, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(button1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(frequencyField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(startButton)
                        .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(button1)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JTextField frequencyField;
    private JButton startButton;
    private JButton cancelButton;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
