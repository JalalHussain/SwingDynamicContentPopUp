import pojo.RequiredComp;
import custom.PromptPopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Main {
    private HashMap<String,String> inputParamValues=new HashMap<>();
    private static Map<String, Component> dynamicComponents = new HashMap<>();

    public static void main(String[] args) {
        RequiredComp requiredComp0 = new RequiredComp();
        RequiredComp requiredComp1 = new RequiredComp();
        RequiredComp requiredComp2 = new RequiredComp();
        RequiredComp requiredComp3 = new RequiredComp();
        RequiredComp requiredComp4 = new RequiredComp();

        requiredComp0.setParameterName("DISTRIBUTOR_CODE");
        requiredComp1.setParameterName("DIST_LOCATION");
        requiredComp2.setParameterName("COMPANY_CODE");
        requiredComp3.setParameterName("FACTORY_ISBN");
        requiredComp4.setParameterName("COUNTRY");

        List<RequiredComp> requiredCompsList = new ArrayList<>();

        requiredCompsList.add(requiredComp0);
        requiredCompsList.add(requiredComp1);
        requiredCompsList.add(requiredComp2);
        requiredCompsList.add(requiredComp3);
        requiredCompsList.add(requiredComp4);

        JFrame frame = new JFrame();
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Show Popup");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PromptPopup promptPopup = new PromptPopup(new JFrame());
                promptPopup.setComponentsDisplayStyle(2/*requiredCompsList.size()*/, 2, 10, 10);
                //promptPopup.setComponentReop(requiredCompsList);
                Component[] headerComponents = new Component[1];
                JLabel headerLabel = new JLabel("I am Header");
                headerComponents[0] = headerLabel;
                promptPopup.setHeaderComponents(headerComponents);
                Component[] footerComponents = new Component[2];
                JButton closeButton = new JButton("Close");
                footerComponents[0] = closeButton;
                JButton continueButton = new JButton("Continue");
                footerComponents[1] = continueButton;
                PromptPopupListener listener = new Main().new PromptPopupListener(promptPopup);
                closeButton.addActionListener(listener);
                continueButton.addActionListener(listener);
                promptPopup.setFooterComponents(footerComponents);
                JTextField dynamicField = new JTextField();
                JTextField dynamicField2 = new JTextField();
                dynamicComponents.put("dynamicField", dynamicField);
                dynamicComponents.put("dynamicField2", dynamicField2);
                promptPopup.setContent(dynamicComponents);
                promptPopup.initPopupUI();
                promptPopup.setPromptPopupTheme("classic");
                promptPopup.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                promptPopup.pack();
                promptPopup.setVisible(true);
            }
        });

        frame.add(button);
    }

    private class PromptPopupListener implements ActionListener {
        private JDialog parent;
        public PromptPopupListener(JDialog parent) {
            this.parent=parent;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            inputParamValues.clear();
            if (((JButton) e.getSource()).getText().equalsIgnoreCase("Close")) {
                System.out.println("close has been clicked");
            } else if (((JButton) e.getSource()).getText().equalsIgnoreCase("continue")) {
                System.out.println("continue has been clicked");
                for (Map.Entry entry :dynamicComponents.entrySet()){
                    inputParamValues.put(entry.getKey().toString(),((JTextField)entry.getValue()).getText());
                }
            }
            System.out.println("Disposing");
            dynamicComponents.clear();
            this.parent.dispose();
        }
    }
}
