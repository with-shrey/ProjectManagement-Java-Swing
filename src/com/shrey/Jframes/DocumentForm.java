package com.shrey.Jframes;

import com.shrey.models.Project;
import com.shrey.models.Document;

import javax.swing.*;
import java.awt.*;
import javax.swing.filechooser.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DocumentForm  extends JFrame implements ActionListener {
    Document document;
    JTextField titleField;
    JButton selectFile;
    JLabel selectedPath;
    JButton save;
    Project project;
    public DocumentForm(Project project) throws HeadlessException {
        super("Student Signin");
        document = new Document();
        this.project = project;
        document.setProjectId(project.getId());
        initGUI();
    }

    public DocumentForm(Project project , Document document) throws HeadlessException {
        this.document = document;
        this.project = project;
        initGUI();
    }

    private void initGUI(){
        setMinimumSize(new Dimension(400,400));
        setBounds(350,200,400,400);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(150,250,100,50);
        save = new JButton("SAVE");
        buttonPanel.add(save);
        add(buttonPanel);

        selectedPath = new JLabel(document.getPath());
        selectFile = new JButton("SELECT FILE");
        JPanel statusPane = new JPanel();
        statusPane.setBounds(0,150,400,100);
        statusPane.add(new JLabel("FILE"));
        statusPane.add(selectFile);
        statusPane.add(selectedPath);
        add(statusPane);

        JPanel titlepane = new JPanel();
        titlepane.setBounds(0,50,400,50);
        titlepane.add(new JLabel("Title"));
        titleField = new JTextField(document.getTitle(),20);
        titlepane.add(titleField);
        add(titlepane);
        setVisible(true);
        selectFile.addActionListener(this);
        save.addActionListener(this);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

                new DocumentList(project);

                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectFile){
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            // invoke the showsSaveDialog function to show the save dialog
            int r = j.showSaveDialog(this);

            if (r == JFileChooser.APPROVE_OPTION)

            {
                selectedPath.setText(j.getSelectedFile().getAbsolutePath());
            }
            else
                selectedPath.setText("");
        }else{
            document.setTitle(titleField.getText());
            document.setPath(selectedPath.getText());
            boolean success = false;
            if (document.getId() == 0){
            success = document.insert();
            if (success){
                new DocumentList(project);
                dispose();
            }
        }else{
            success = document.update();
            if (success){
                new DocumentList(project);
                dispose();
            }
        }
            if (!success){
                JOptionPane.showMessageDialog(this,"Error Storing to  Database");
            }
        }
    }
}