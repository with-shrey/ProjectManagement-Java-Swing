package com.shrey.Jframes;

import com.shrey.models.Project;
import com.shrey.models.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocumentList extends JFrame implements ActionListener {
    Project project;
    JList jList;
    List<String> documentList;
    JButton newDocument;
    public DocumentList(Project project) throws HeadlessException {
        super(project.getTitle()+" - Documents");
        this.project = project;
        project.setDocuments(Document.getDocumentsByProject(project.getId()));
        setMinimumSize(new Dimension(400, 400));
        setBounds(350, 200, 400, 400);
        setLayout(null);
        documentList =  new ArrayList<>();
        for (Document document: project.getDocuments()){
            documentList.add(document.getTitle());
        }
        newDocument = new JButton("New Document");
        newDocument.setBounds(0,20,400,20);
        add(newDocument);

        JLabel jLabel = new JLabel(project.getTitle()+" - Documents");
        jLabel.setBounds(150,0,400,20);
        add(jLabel);

        jList = new JList<>(documentList.toArray());
        jList.setBounds(0,50,400,350);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int reply = JOptionPane.showConfirmDialog(null, "Yes - Edit || No - View", "Options Dialog", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    new DocumentForm(project,(project.getDocuments().get(jList.getSelectedIndex())));
                    dispose();
                }
                else {
                    try {
                        Desktop.getDesktop().open(new File(project.getDocuments().get(jList.getSelectedIndex()).getPath()));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                }
        });
        add(jList);

        setVisible(true);

        newDocument.addActionListener(this);
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
        new DocumentForm(project);
        dispose();
    }
}
