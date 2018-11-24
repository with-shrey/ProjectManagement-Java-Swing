package com.shrey.Jframes;

import com.shrey.models.Project;
import com.shrey.models.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskDetail extends JFrame implements ActionListener {
    Task task;
    JButton edit;
    Project project;
    public TaskDetail(Project project, Task task) {
        super(task.getTitle());
        this.project = project;
        this.task = task;
        setMinimumSize(new Dimension(400, 400));
        setBounds(350, 200, 400, 400);
        setLayout(null);
        edit = new JButton("Edit Task");
        edit.setBounds(0,300,400,20);
        add(edit);

        JLabel jLabel = new JLabel("Title: "+task.getTitle());
        jLabel.setBounds(150,0,400,20);
        add(jLabel);

        JLabel desc = new JLabel("Description:" + task.getDescription());
        desc.setBounds(150,20,400,20);
        add(desc);

        JLabel status = new JLabel("Status:" +task.getStatus());
        status.setBounds(150,40,400,20);
        add(status);
        setVisible(true);
        edit.addActionListener(this);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                new TaskList(project);
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        new TaskForm(project,task);
    }
}
