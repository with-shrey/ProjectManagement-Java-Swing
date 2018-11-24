package com.shrey.Jframes;

import com.shrey.models.Project;
import com.shrey.models.Task;
import com.shrey.models.User;
import com.shrey.utilities.AuthUserSingleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class TaskForm extends JFrame implements ActionListener {
    Task task;
    JTextField descriptionField;
    JTextField textField;
    JList statusList;
    JButton save;
    Project project;
    public TaskForm(Project project) throws HeadlessException {
        super("Student Signin");
        task = new Task();
        this.project = project;
        task.setProjectId(project.getId());
        initGUI();
    }

    public TaskForm(Project project , Task task) throws HeadlessException {
        this.task = task;
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

        statusList = new JList<>(new String[]{"Assisgned","Started","Completed"});
        statusList.setSelectedIndex(0);
        JPanel statusPane = new JPanel();
        statusPane.setBounds(0,150,400,100);
        statusPane.add(new JLabel("Status"));
        statusPane.add(statusList);
        add(statusPane);

        descriptionField = new JTextField(task.getDescription(),20);
        JPanel passwordPanel = new JPanel();
        passwordPanel.setBounds(0,100,400,50);
        passwordPanel.add(new JLabel("Description"));

        passwordPanel.add(descriptionField);
        add(passwordPanel);

        JPanel enrollemntPanel = new JPanel();
        enrollemntPanel.setBounds(0,50,400,50);
        enrollemntPanel.add(new JLabel("Title"));
        textField = new JTextField(task.getTitle(),20);
        enrollemntPanel.add(textField);
        add(enrollemntPanel);
        setVisible(true);

        save.addActionListener(this);
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
        task.setTitle(textField.getText());
        task.setDescription(descriptionField.getText());
        task.setStatus((String)statusList.getSelectedValue());
        boolean success= false;
        if (task.getId() == 0){
            success = task.insert();
            if (success){
                dispose();
                new TaskList(project);
            }
        }else{
            success = task.update();
            if (success){
                dispose();
                new TaskDetail(project,task);
            }
        }

        if (!success){
            JOptionPane.showMessageDialog(this,"Error Storing to  Database");
        }
    }
}