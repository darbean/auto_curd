package com.sankuai.lx.idea.plugin.ui;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.intellij.openapi.util.io.FileUtil;
import com.sankuai.lx.idea.plugin.curd.model.CodeInfo;
import com.sankuai.lx.idea.plugin.curd.model.DaoInfo;
import com.sankuai.lx.idea.plugin.curd.template.CodeTemplate;
import com.sankuai.lx.idea.plugin.curd.template.CodeTemplateFactory;
import com.sankuai.lx.idea.plugin.curd.template.DaoTemplate;
import com.sankuai.lx.idea.plugin.db.DBService;
import com.sankuai.lx.idea.plugin.db.model.TableInfo;
import com.sankuai.lx.idea.plugin.utils.GlobelPathContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

/**
 * Description: DbSettingForm
 * Author: liangxiao03
 * Create: 2016-02-25 12:00
 */
public class DbSettingForm {
    private JPanel mainPanel;
    private JTextField hostField;
    private JTextField portField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField databaseField;
    private JButton nextButton;
    private JLabel warningLabel;
    private JPanel dbSettingPanel;
    private JPanel tablePanel;
    private JTextField rootPackageField;
    private JFrame frame;
    private List<JCheckBox> checkBoxes;


    public DbSettingForm() {

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == nextButton) {

                    String host = hostField.getText();
                    int port = 0;
                    try {
                        port = Integer.parseInt(portField.getText());
                    } catch (Exception e1) {

                    }
                    String username = usernameField.getText();
                    String password = passwordField.getText();
                    String db = databaseField.getText();
                    final String rootPackage = rootPackageField.getText();
                    GlobelPathContainer.getInstance().setRootPackage(rootPackage);

                    final DBService dbService = DBService.getInstance(host, port, username, password, db);
                    List<String> tables = Lists.newArrayList();
                    try {
                        tables = dbService.getTables();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                    ((CardLayout) mainPanel.getLayout()).next(mainPanel);
                    tablePanel.setLayout(new FlowLayout());
                    checkBoxes = Lists.newArrayList();

                    for (String table : tables) {
                        JCheckBox checkbox = new JCheckBox(table);
//                        checkbox.setLocation(100,10);
                        checkBoxes.add(checkbox);
                        tablePanel.add(checkbox);
                    }

                    JButton btFinish = new JButton("完成");

                    tablePanel.add(btFinish);
                    btFinish.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (JCheckBox checkBox : checkBoxes) {
                                if (checkBox.isSelected()) {
                                    generate(checkBox.getText());
                                }
                            }
                            frame.dispose();
                        }

                        private void generate(String table) {
                            try {
                                List<TableInfo> tableInfos = dbService.getTableInfo(Lists.newArrayList(table));
                                TableInfo tableInfo = tableInfos.get(0);
                                CodeInfo codeInfo = new CodeInfo(rootPackage, table, tableInfo.getTableFields());
                                for (CodeTemplate codeTemplate : CodeTemplateFactory.getCodeTemplates(codeInfo)) {
                                    writeFile(codeTemplate, codeInfo);
                                }
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }

                        }

                        private void writeFile(CodeTemplate codeTemplate, CodeInfo codeInfo) {
                            try {
                                makeDir(new File(codeTemplate.getFilePath()));
                                File file = new File(codeTemplate.getFilePath() + "/" + codeTemplate.getFileName());
                                if (!file.exists()) {
                                    file.createNewFile();
                                }
                                Files.write(codeTemplate.build(), file, Charset.forName("utf8"));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }

                        public  void makeDir(File file) {
                            if (file.getParentFile().exists()) {
                                file.mkdir();
                            } else {
                                makeDir(file.getParentFile());
                                file.mkdir();
                            }
                        }
                    });

                    tablePanel.validate();
                    tablePanel.repaint();
                    mainPanel.validate();
                    mainPanel.repaint();
                }
            }
        });
    }

    public static void main(String[] args) {
        new DbSettingForm().show();
    }

    public void show() {
        frame = new JFrame("DbSettingForm");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        int windowWidth = frame.getWidth(); //获得窗口宽
        int windowHeight = frame.getHeight(); //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高
        frame.setLocation(screenWidth/2-windowWidth/2, 100);
        frame.pack();
        frame.setVisible(true);
    }

}
