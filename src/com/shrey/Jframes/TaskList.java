package com.shrey.Jframes;

import com.shrey.models.Project;
import com.shrey.models.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class TaskList extends JFrame implements ActionListener {
    Project project;
    JList jList;
    List<String> taskList;
    JButton newTask;
    public TaskList(Project project) throws HeadlessException {
        super(project.getTitle()+" - Tasks");
        this.project = project;
        project.setTasks(Task.getTasksByProject(project.getId()));
        setMinimumSize(new Dimension(400, 400));
        setBounds(350, 200, 400, 400);
        setLayout(null);
        taskList =  new ArrayList<>();
        for (Task task: project.getTasks()){
            taskList.add(task.getTitle()+" - "+task.getStatus());
        }
        newTask = new JButton("New Task");
        newTask.setBounds(0,20,400,20);
        add(newTask);

        JLabel jLabel = new JLabel(project.getTitle()+" - Tasks");
        jLabel.setBounds(150,0,400,20);
        add(jLabel);

        jList = new JList<>(taskList.toArray());
        jList.setBounds(0,50,400,350);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new TaskDetail(project,project.getTasks().get(jList.getSelectedIndex()));
                dispose();
            }
        });
        add(jList);

        setVisible(true);

        newTask.addActionListener(this);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                new ProjectsList();
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        new TaskForm(project);
    }
}
