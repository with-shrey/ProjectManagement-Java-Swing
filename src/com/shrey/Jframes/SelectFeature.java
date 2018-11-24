package com.shrey.Jframes;

import com.shrey.models.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectFeature extends JFrame implements ActionListener {
    JButton tasks;
    JButton documents;
    Project project;
    public SelectFeature(Project project) throws HeadlessException {
        super("Select Feature");
        this.project = project;
        setMinimumSize(new Dimension(400, 400));
        setBounds(350, 200, 400, 400);
        setLayout(null);
        tasks = new JButton("Task Management");
        tasks.setBounds(0,50,400,100);
        add(tasks);
        documents = new JButton("Documents Management");
        documents.setBounds(0,150,400,100);
        add(documents);
        tasks.addActionListener(this);
        documents.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tasks){
            new TaskList(project);
        }else{
            new DocumentList(project);
        }
        dispose();
    }
}
