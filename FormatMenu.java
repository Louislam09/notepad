import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FormatMenu implements java.awt.event.ActionListener {

    NotePad notePad;
    JMenu formatMenu,fmFontSize;
    String[] fontSizeItems = { "10","12","14", "16","18","20","22","24" };

    
    public FormatMenu(NotePad notePad){
        this.notePad = notePad;
        formatMenu = new JMenu("Formato");
        notePad.topMenuBar.add(formatMenu);
        createFormatMenuItems();
        createFontSizeMenu();
    }

    public void createFormatMenuItems(){
        fmFontSize = new JMenu("Tamaño de Letra");
        fmFontSize.addActionListener(this);
        fmFontSize.setActionCommand("fontSize");
        formatMenu.add(fmFontSize);
    }

    public void createFontSizeMenu(){
        for (String subMenuName : fontSizeItems) {
            addSubMenuToFontSizeMenu(subMenuName);
        }
    }

    public void addSubMenuToFontSizeMenu(String subMenuName){
        JMenuItem subMenu;
        subMenu = new JMenuItem(subMenuName);
        subMenu.addActionListener(this);
        subMenu.setActionCommand("fontSize"+subMenuName);
        fmFontSize.add(subMenu);
    }

    public void setFontSize(int size){
        notePad.textArea.setFont(new Font( Font.SANS_SERIF, Font.PLAIN,size));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch ( command ) {
            case "fontSize10":
                setFontSize(10);
                break;
            case "fontSize12":
                setFontSize(12);
                break;
            case "fontSize14":
                setFontSize(14);
                break;
            case "fontSize16":
                setFontSize(16);
                break;
            case "fontSize18":
                setFontSize(18);
                break;
            case "fontSize20":
                setFontSize(20);
                break;
            case "fontSize22":
                setFontSize(22);
                break;
            case "fontSize24":
                setFontSize(24);
                break;
            default:
                break;

        }
        
    }

}