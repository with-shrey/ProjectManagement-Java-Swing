package com.shrey.Jframes;

import com.shrey.models.Project;
import com.shrey.utilities.AuthUserSingleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ProjectsList  extends JFrame implements ActionListener {
    JList jList;
    List<Project> projects;
    public ProjectsList() throws HeadlessException {
        super(AuthUserSingleton.getUser().getEnrollment()+" - Projects");
        setMinimumSize(new Dimension(400, 400));
        setBounds(350, 200, 400, 400);
        setLayout(null);
        ArrayList<String> list = new ArrayList<>();
        projects = AuthUserSingleton.getUser().getProject();
        for (Project project: projects){
            list.add(project.getTitle());
        }
        JLabel jLabel = new JLabel("Projects");
        jLabel.setBounds(150,0,400,50);
        add(jLabel);

        jList = new JList<>(list.toArray());
        jList.setBounds(0,50,400,350);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new SelectFeature(projects.get(jList.getSelectedIndex()));
                dispose();
            }
        });
        add(jList);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
               System.exit(0);
            }
        });

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
