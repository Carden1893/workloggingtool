/*
 * Created by JFormDesigner on Sat Feb 12 13:36:04 CET 2022
 */

package com.kilian_beckenkamp.workloggingtool.forms.logView;

import java.awt.*;
import java.awt.event.*;
import com.kilian_beckenkamp.workloggingtool.worklogEntry.WorklogEntry;
import com.kilian_beckenkamp.workloggingtool.worklogEntry.WorklogEntryService;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.stream.Collectors.toList;

/**
 * @author Kilian Beckenkamp
 */
public class WorklogPanel extends JPanel {

    private static WorklogEntryService worklogEntryService;

    public WorklogPanel(WorklogEntryService worklogEntryService) {
        this.worklogEntryService = worklogEntryService;
        initComponents();
        initWorklogview();
    }

    public void initWorklogview(){
        List<WorklogEntry> worklogEntries = worklogEntryService.getWorklogEntries();
        String col[] = {"Date", "Logentry"};
        printTable(worklogEntries, col);

        List<String> existingEntrydays = worklogEntryService.getExistingEntrydays();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Collections.sort(existingEntrydays, Comparator.comparing(s -> LocalDate.parse(s, formatter)));

        for (String dateString : existingEntrydays) {
            comboBox1.addItem(dateString);
        }
    }

    public void setFilteredWorklog(){
        List<WorklogEntry> worklogEntries = worklogEntryService.getWorklogEntries();
        String col[] = {"Date", "Logentry"};
        printTable(worklogEntries, col);
    }

    private void printTable(List<WorklogEntry> worklogEntries, String[] col) {
        DefaultTableModel dtm = new DefaultTableModel(col,0);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");

        Comparator<WorklogEntry> comparator = Comparator.comparing(WorklogEntry::getEntryDate);
        List<WorklogEntry> sortedList = worklogEntries.stream().sorted(comparator).collect(toList());

        for (int i = 0; i < worklogEntries.size(); i++) {
            Object[] obs = {
                    format.format(sortedList.get(i).getEntryDate()),
                    sortedList.get(i).getWorkDescription()
            };
            dtm.addRow(obs);
        }

        table1 = new JTable(dtm);
        table1.setRowSelectionAllowed(false);
        table1.setGridColor(Color.black);
        table1.setShowGrid(true);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollPane1.setViewportView(table1);
    }

    private void comboBox1ItemStateChanged(ItemEvent e) {
        List<WorklogEntry> worklogEntries = worklogEntryService.getWorklogEntriesForSpecificDay(comboBox1.getSelectedItem().toString());
        String col[] = {"Date", "Logentry"};
        printTable(worklogEntries, col);

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label1 = new JLabel();
        label2 = new JLabel();
        comboBox1 = new JComboBox();

        //======== this ========

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setRowSelectionAllowed(false);
            table1.setGridColor(Color.black);
            table1.setShowGrid(true);
            table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            scrollPane1.setViewportView(table1);
        }

        //---- label1 ----
        label1.setText("Worklog");

        //---- label2 ----
        label2.setText("show Log from:");

        //---- comboBox1 ----
        comboBox1.addItemListener(e -> comboBox1ItemStateChanged(e));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(62, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup()
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 390, GroupLayout.PREFERRED_SIZE)
                                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                            .addContainerGap(9, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label1;
    private JLabel label2;
    private JComboBox comboBox1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
