package custom;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class PromptPopup extends JDialog{
    private final JScrollPane scrollPane = new JScrollPane();
    private JPanel rootContainer;
    private JPanel header;
    private JPanel contentContainer;
    private JPanel footer;
    private Component[] headerComponents;
    private Component[] contentContainerComponents;
    private Component[] footerComponents;
    private int rows;
    private int cols;
    private int vGap;
    private int hGap;
    private Map<String,Component> initializedContentContainerCompentes;

    public PromptPopup(JFrame parent){
        super(parent);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
    }

    public void setComponentsDisplayStyle(int rows,int cols,int vGap, int hGap){
        this.rows=rows;
        this.cols=cols;
        this.vGap=vGap;
        this.hGap=hGap;
    }

    public void initPopupUI(){
        //Setting content panes layout
        getContentPane().setLayout(new BorderLayout(0, 0));
        //Creating a root panel(root container) to hold the child components
        rootContainer=new JPanel();
        rootContainer.setLayout(new BorderLayout());
        initHeader();
        initContentContainer();
        initFooter();
        // Adding all the created containers to the root container one by one
        rootContainer.add(header,BorderLayout.NORTH);
        rootContainer.add(contentContainer,BorderLayout.CENTER);
        rootContainer.add(footer,BorderLayout.SOUTH);
        //For Layout Adjustment
        rootContainer.add(new JPanel(),BorderLayout.EAST);
        rootContainer.add(new JPanel(),BorderLayout.WEST);

        //Adding the root container to the scroll pane in order the view to be scrollable nno matter how many components are there.
        scrollPane.setViewportView(rootContainer);
        getContentPane().add(scrollPane);
    }

    public void setHeaderComponents(Component[] components){
        this.headerComponents=components;
    }
    public void setFooterComponents(Component[] components){
    this.footerComponents=components;
    }
    public void setContent(Component[] components){
    this.contentContainerComponents=components;
    }
    public void setContent(Map<String,Component> initializedContentComponents){
    this.initializedContentContainerCompentes=initializedContentComponents;
    }

    private void initHeader(){
        //Creating header panel(header container) to hold the header components
        header = new JPanel();
        if (this.headerComponents!=null && this.headerComponents.length>0) {
            for (Component component : headerComponents) {
                header.add(component);
            }
        }
        this.rootContainer.add(header,BorderLayout.NORTH);
    }
    private void initContentContainer(){
        //Creating content panel(content container) to hold the all dynamically generated components
        contentContainer =new JPanel();
        GridLayout gridLayout=new GridLayout(this.rows, this.cols, this.hGap, this.vGap);
        contentContainer.setLayout(gridLayout);
        if (this.contentContainerComponents!=null && this.contentContainerComponents.length>0) {
            for (Component component : contentContainerComponents) {
                contentContainer.add(component);
            }
        }
        if (this.initializedContentContainerCompentes!=null && this.initializedContentContainerCompentes.size()>0){
            for (Map.Entry entry:initializedContentContainerCompentes.entrySet()){
                JLabel dynamicallyGeneratedLabel=new JLabel(entry.getKey().toString());
                contentContainer.add(dynamicallyGeneratedLabel);
                contentContainer.add((Component) entry.getValue());
            }
        }
        this.rootContainer.add(contentContainer,BorderLayout.CENTER);
    }
    private void initFooter(){
        //Creating footer panel(footer container ) to hold the footer components
        footer =new JPanel();
        footer.setLayout(new FlowLayout());
        if (this.footerComponents!=null && this.footerComponents.length>0) {
            for (Component component : footerComponents) {
                footer.add(component);
            }
        }
        this.rootContainer.add(footer,BorderLayout.SOUTH);
    }

    private String resolveComponent(Component component){
        return component.getClass().getName();
    }

    public void setPromptPopupTheme(String themeIdentifier){
        switch (themeIdentifier.toUpperCase()){
            case "CLASSIC":
                initClassicDisplay(this.header,this.headerComponents);
                initClassicDisplay(this.footer,this.footerComponents);
                break;
            case "PROFESSIONAL":
                initProfessionalDisplay(this.header,this.headerComponents);
                initProfessionalDisplay(this.footer,this.footerComponents);
                break;
            case "ELEGANT":
                break;
            case "VIRAL":
                break;
                default:
        }
    }

    private void initClassicDisplay(Component parent,Component[] children){
        if(parent!=null){
            parent.setBackground(Color.BLUE);
            if (children!=null && children.length>0){
                for (Component component:children){
                    if (component instanceof JLabel){
                        component.setForeground(Color.WHITE);
                    }else if (component instanceof JButton){
                        component.setBackground(Color.black);
                        component.setForeground(Color.WHITE);
                    }

                }
            }
        }
    }
    private void initProfessionalDisplay(Component parent,Component[] children){
        if(parent!=null){
            parent.setBackground(Color.BLACK);
            if (children!=null && children.length>0){
                for (Component component:children){
                    if (component instanceof JLabel){
                        component.setForeground(Color.WHITE);
                    }else if (component instanceof JButton){
                        component.setBackground(Color.BLUE);
                        component.setForeground(Color.WHITE);
                    }

                }
            }
        }
    }
    private void initElegantDisplay(){

    }
    private void initViralDisplay(){

    }
}
