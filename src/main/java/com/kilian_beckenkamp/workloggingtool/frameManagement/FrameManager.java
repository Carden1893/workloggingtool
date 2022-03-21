package com.kilian_beckenkamp.workloggingtool.frameManagement;

import com.kilian_beckenkamp.workloggingtool.forms.StartWorkPanel.StartWorkPanel;
import com.kilian_beckenkamp.workloggingtool.worklogEntry.WorklogEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

@Component
public class FrameManager {

    public static JFrame frame = new JFrame();

    private final WorklogEntryService worklogEntryService;


    @Autowired
    public FrameManager(WorklogEntryService worklogEntryService) {
        this.worklogEntryService = worklogEntryService;
    }

    public void showStartPanel() {
        JDialog jDialog = new JDialog();
        jDialog.setContentPane(new StartWorkPanel(worklogEntryService));
        jDialog.setSize( 335,230);
        jDialog.setVisible(true);
        jDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        WindowListener listener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent w) {
                System.exit(0);
            }
        };
        jDialog.addWindowListener(listener);
    }




}
