package org.echoed.scripts.superglass.misc;

import org.echoed.scripts.superglass.EchoedSuperGlass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 8/25/12
 * Time: 1:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class SuperGlassGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    public SuperGlassGUI() {
        initComponents();
    }


    private void startBtnActionPerformed(ActionEvent e) {
        EchoedSuperGlass.setMouseSpeed(mouseSpeedSlider.getValue());
        EchoedSuperGlass.setMouseKeys(MouseKeysCheckBox.isSelected());
        Constants.setSecondaryItem(secondaryItemComboBox.getSelectedItem().toString());
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Sync rounous
        label2 = new JLabel();
        mouseSpeedSlider = new JSlider();
        MouseKeysCheckBox = new JCheckBox();
        label3 = new JLabel();
        secondaryItemComboBox = new JComboBox();
        startBtn = new JButton();

        //======== this ========
        setTitle("EchoedSuperGlass");
        Container contentPane = getContentPane();

        //---- label2 ----
        label2.setText("Mouse Speed");

        //---- mouseSpeedSlider ----
        mouseSpeedSlider.setMaximum(10);
        mouseSpeedSlider.setValue(2);
        mouseSpeedSlider.setMinimum(1);

        //---- MouseKeysCheckBox ----
        MouseKeysCheckBox.setText("MouseKeys?    ");

        //---- label3 ----
        label3.setText("Secondary");

        //---- secondaryItemComboBox ----
        secondaryItemComboBox.setModel(new DefaultComboBoxModel(new String[]{
                "Seaweed",
                "Soda Ash",
                "Swamp weed"
        }));

        //---- startBtn ----
        startBtn.setText("Start");
        startBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startBtnActionPerformed(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(label2)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(mouseSpeedSlider, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(label3)
                                                .addGap(18, 18, 18)
                                                .addComponent(secondaryItemComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(startBtn, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(MouseKeysCheckBox))
                                .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label2)
                                        .addComponent(mouseSpeedSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)
                                .addComponent(MouseKeysCheckBox)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label3)
                                        .addComponent(secondaryItemComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addComponent(startBtn)
                                .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Sync rounous
    private JLabel label2;
    private JSlider mouseSpeedSlider;
    private JCheckBox MouseKeysCheckBox;
    private JLabel label3;
    private JComboBox secondaryItemComboBox;
    private JButton startBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
