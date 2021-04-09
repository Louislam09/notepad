import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.plaf.ColorUIResource;

import java.awt.Cursor;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

public class NotePad implements java.awt.event.ActionListener {
    
    JFrame window;
    JTextArea textArea;
    JMenuBar topMenuBar;
    JMenu fileMenu,editMenu,helpMenu;
    JMenuItem fmNewItem,fmNewWindowItem,fmOpenItem,fmSaveItem,fmSaveAsItem,fmExitItem;
    JMenuItem emCopyItem,emPasteItem,emDeleteItem,emCutItem;
    JMenuItem amDescriptionItem;
    JCheckBox darkModeCheckBox;
    JFileChooser fc;
    Cursor cursor;
    ColorUIResource darkBgColor,darkFgColor,lightBgColor,lightFgColor;
    JOptionPane showWarningDialog;

    String selectedText;
    
    Boolean isDarkMode = false;

    Methods METHODS = new Methods(this);
    keyHandler KeyMothods = new keyHandler(this);
    
    public static void main(String[] args) {
        new NotePad();
    }

    public NotePad(){
        createWindow();
        createTextArea();
        createTopMenuBar();
        createFileMenuItems();
        createEditMenuItems();
        createAboutMenuItems();
        fc = new JFileChooser();
        darkBgColor = new ColorUIResource(24,25,26);
        darkFgColor = new ColorUIResource(255,255,255);
        lightBgColor = new ColorUIResource(255,255,255);
        lightFgColor = new ColorUIResource(0,0,0);

        window.setVisible(true);
    }

    public void createWindow(){
        window = new JFrame("NotePad");
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void createTextArea(){
        cursor = new Cursor(Cursor.TEXT_CURSOR);
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setCursor(cursor);
        textArea.setFont(new Font("Arial", Font.PLAIN,14));
        
        textArea.addKeyListener(KeyMothods);
        window.add(textArea);
    }

    public void createTopMenuBar(){
        topMenuBar = new JMenuBar();
        window.setJMenuBar(topMenuBar);

        fileMenu = new JMenu("Archivo");
        topMenuBar.add(fileMenu);

        editMenu = new JMenu("Editar");
        topMenuBar.add(editMenu);

        darkModeCheckBox = new JCheckBox("Dark Mode");
        darkModeCheckBox.setSelected(false);
        darkModeCheckBox.addActionListener(this);
        darkModeCheckBox.setActionCommand("toggleDarkMode");

        topMenuBar.add(darkModeCheckBox);
        
        helpMenu = new JMenu("Ayuda");
        topMenuBar.add(helpMenu);

    }
    
    public void createFileMenuItems(){
        // new item
        fmNewItem = new JMenuItem("Nuevo");
        fmNewItem.addActionListener(this);
        fmNewItem.setActionCommand("addNew");
        // new item
        fmNewWindowItem = new JMenuItem("Nuevo Ventana");
        fmNewWindowItem.addActionListener(this);
        fmNewWindowItem.setActionCommand("addNewWindow");
        
        // open item
        fmOpenItem = new JMenuItem("Abrir...");
        fmOpenItem.addActionListener(this);
        fmOpenItem.setActionCommand("openFile");

        // save Item
        fmSaveItem = new JMenuItem("Guardar");
        fmSaveItem.addActionListener(this);
        fmSaveItem.setActionCommand("saveFile");

        // saveAs Item
        fmSaveAsItem = new JMenuItem("Guardar como...");
        fmSaveAsItem.addActionListener(this);
        fmSaveAsItem.setActionCommand("saveAsFile");
        
        
        // exit Item
        fmExitItem = new JMenuItem("Salir");
        fmExitItem.addActionListener(this);
        fmExitItem.setActionCommand("exitNotePad");
        
        
        fileMenu.add(fmNewItem);
        fileMenu.add(fmNewWindowItem);
        fileMenu.add(fmOpenItem);
        fileMenu.add(fmSaveItem);
        fileMenu.add(fmSaveAsItem);
        fileMenu.add(fmExitItem);
    }

    public void createEditMenuItems(){
        emCopyItem = new JMenuItem("Copiar");
        emCopyItem.addActionListener(this);
        emCopyItem.setActionCommand("onCopy");

        emPasteItem = new JMenuItem("Pegar");
        emPasteItem.addActionListener(this);
        emPasteItem.setActionCommand("onPaste");
        
        emDeleteItem = new JMenuItem("Borrar");
        emDeleteItem.addActionListener(this);
        emDeleteItem.setActionCommand("onDelete");
        
        emCutItem = new JMenuItem("Cortar");
        emCutItem.addActionListener(this);
        emCutItem.setActionCommand("onCut");

        editMenu.add(emCutItem);
        editMenu.add(emCopyItem);
        editMenu.add(emPasteItem);
        editMenu.add(emDeleteItem);
    }

    public void createAboutMenuItems(){
        amDescriptionItem = new JMenuItem("Sobre mi");
        amDescriptionItem.addActionListener(this);
        amDescriptionItem.setActionCommand("aboutMe");

        helpMenu.add(amDescriptionItem);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "toggleDarkMode":
                METHODS.onToggleDarkMode();
                break;
            case "addNew":
                METHODS.onClickNewItem();
                break;
            case "addNewWindow":
                METHODS.onClickNewWindowItem();
                break;
            case "openFile":
                METHODS.onOpenFile();
                break;
            case "saveFile":
                METHODS.onSave();
                break;
            case "saveAsFile":
                METHODS.onSaveAs();
                break;
            case "exitNotePad":
                METHODS.onExit();
                break;
            case "onCopy":
                METHODS.onCopy();
                break;
            case "onPaste":
                METHODS.onPaste();
                break;
            case "onCut":
                METHODS.onCut();
                break;
            case "onDelete":
                METHODS.onDelete();
                break;
            case "aboutMe":
                new About().setVisible(true);
                break;
            default:
                break;
        }
        
    }
}
