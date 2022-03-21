/*
 * Created by JFormDesigner on Fri Jan 28 20:47:19 CET 2022
 */

package com.kilian_beckenkamp.workloggingtool.forms.newWorklog;

import com.kilian_beckenkamp.workloggingtool.worklogEntry.WorklogEntry;
import com.kilian_beckenkamp.workloggingtool.worklogEntry.WorklogEntryRepository;
import com.kilian_beckenkamp.workloggingtool.worklogEntry.WorklogEntryService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Kilian Beckenkamp
 */
@Component
public class NewWorkPanel extends JPanel{
    private static JFrame frame;
    private static int freq = 0;

    @Autowired
    private ApplicationContext applicationContext;

    private static WorklogEntryService worklogEntryService;

    public NewWorkPanel(WorklogEntryService worklogEntryService)
    {
        this.worklogEntryService = worklogEntryService;
        initComponents();
    }

    public static void doYourThing(int frequency) {
        freq = frequency;
        java.util.Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                frame = new JFrame();
                frame.setContentPane(new NewWorkPanel(worklogEntryService));
                frame.pack();
                frame.setVisible(true);
                Toolkit.getDefaultToolkit().beep();
                Thread.sleep(250L);
                Toolkit.getDefaultToolkit().beep();
                Thread.sleep(250L);
                Toolkit.getDefaultToolkit().beep();
            }
        }, freq * 60 * 1000);
    }

    private void log(ActionEvent e) {
        WorklogEntry newEntry = new WorklogEntry();
        newEntry.setEntryDate(LocalDateTime.now());
        newEntry.setWorkDescription(workDescription.getText());
        worklogEntryService.saveNewWorklogEntry(newEntry);
        frame.dispose();
        doYourThing(freq);
    }

    private void cancelOnce(ActionEvent e) {
        WorklogEntry newEntry = new WorklogEntry();
        newEntry.setEntryDate(LocalDateTime.now());
        newEntry.setWorkDescription("No work was logged");
        worklogEntryService.saveNewWorklogEntry(newEntry);
        frame.dispose();
        doYourThing(freq);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        workDescription = new JTextArea();
        label2 = new JLabel();
        logButton = new JButton();
        cancelOnceButton = new JButton();

        //======== this ========

        //---- label1 ----
        label1.setText("Worklogger V1");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 7f));
        label1.setHorizontalAlignment(SwingConstants.LEFT);

        //---- workDescription ----
        workDescription.setLineWrap(true);

        //---- label2 ----
        label2.setText("What did you work on in the last period?");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 5f));
        label2.setHorizontalAlignment(SwingConstants.LEFT);

        //---- logButton ----
        logButton.setText("Log described work");
        logButton.addActionListener(e -> log(e));

        //---- cancelOnceButton ----
        cancelOnceButton.setText("Cancel once");
        cancelOnceButton.addActionListener(e -> cancelOnce(e));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(12, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createParallelGroup()
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 391, GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(logButton, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancelOnceButton, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
                            .addComponent(workDescription, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE))
                        .addComponent(label1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 391, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(label2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(workDescription, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(cancelOnceButton)
                        .addComponent(logButton))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextArea workDescription;
    private JLabel label2;
    private JButton logButton;
    private JButton cancelOnceButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
