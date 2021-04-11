import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ColorUIResource;

import java.awt.BorderLayout;
import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


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
    
    Boolean isDarkMode = false,hasChanged = false;

    Methods METHODS = new Methods(this);
    keyHandler KeyMothods = new keyHandler(this);
    FormatMenu fontSizeMenu;
    StatusBar StatusBar;
    ScrollBar ScrollBar;
    int rowNum = 0;
    int columnNum = 0;

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

        textArea.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                textArea.setColumns(textArea.getColumns() + 1);
                StatusBar.rowAndColumLabel.setText(String.format("Linea: %d, Columnas: %d",textArea.getLineCount(),textArea.getColumns()));
               
                if(textArea.getRows() !=textArea.getLineCount()){
                    textArea.setRows(textArea.getLineCount());
                    textArea.setColumns(1);
                    StatusBar.rowAndColumLabel.setText(String.format("Linea: %d, Columnas: %d",textArea.getLineCount(),textArea.getColumns()));
                }


                if(!hasChanged){
                    String wTitle = window.getTitle();
                    window.setTitle("*"+wTitle);
                }
                
                hasChanged = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                textArea.setColumns(textArea.getColumns() - 1);
                if(textArea.getColumns()==0) textArea.setColumns(1);
                StatusBar.rowAndColumLabel.setText(String.format("Linea: %d, Columnas: %d",textArea.getLineCount(),textArea.getColumns()));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                StatusBar.rowAndColumLabel.setText(String.format("Linea: %d, Columnas: %d",textArea.getLineCount(),textArea.getColumns()));
            }
            
        });
           
    }

    public void createWindow(){
        window = new JFrame("NotePad");
        window.setLayout(new BorderLayout());
        window.setSize(800,600);
        window.setBounds(400, 60, window.getWidth(), window.getHeight());
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
        textArea.setColumns(0);
        textArea.setRows(0);

        window.add(textArea);

        ScrollBar = new ScrollBar(this);
        StatusBar = new StatusBar(this);

    }

    public void createTopMenuBar(){
        topMenuBar = new JMenuBar();
        window.setJMenuBar(topMenuBar);

        fileMenu = new JMenu("Archivo");
        topMenuBar.add(fileMenu);

        editMenu = new JMenu("Editar");
        topMenuBar.add(editMenu);
        
        fontSizeMenu = new FormatMenu(this);

        darkModeCheckBox = new JCheckBox("Dark Mode");
        darkModeCheckBox.setSelected(false);
        darkModeCheckBox.addActionListener(this);
        darkModeCheckBox.setActionCommand("toggleDarkMode");
        topMenuBar.add(darkModeCheckBox);

        helpMenu = new JMenu("Ayuda");
        topMenuBar.add(helpMenu);

    }
    
	protected static ImageIcon createIcon(Color color) {
		BufferedImage bi = new BufferedImage(15,15,BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.setColor(color);
		g.fillRect(0,0, 15, 15);
		return new ImageIcon(bi);
	}

    public void createFileMenuItems(){
        // new item
        fmNewItem = new JMenuItem("Nuevo");
        fmNewItem.addActionListener(this);
        fmNewItem.setActionCommand("addNew");
        Color fmNewItemIconColor = new ColorUIResource(29,169,217);
        ImageIcon fmNewItemIcon = createIcon(fmNewItemIconColor);
        fmNewItem.setIcon(fmNewItemIcon);

        // new item
        fmNewWindowItem = new JMenuItem("Nuevo Ventana");
        fmNewWindowItem.addActionListener(this);
        fmNewWindowItem.setActionCommand("addNewWindow");
        Color fmNewWindowItemIconColor = new ColorUIResource(Color.WHITE);
        ImageIcon fmNewWindowItemIcon = createIcon(fmNewWindowItemIconColor);
        fmNewWindowItem.setIcon(fmNewWindowItemIcon);

        // open item
        fmOpenItem = new JMenuItem("Abrir...");
        fmOpenItem.addActionListener(this);
        fmOpenItem.setActionCommand("openFile");
        Color fmOpenItemIconColor = new ColorUIResource(Color.YELLOW);
        ImageIcon fmOpenItemIcon = createIcon(fmOpenItemIconColor);
        fmOpenItem.setIcon(fmOpenItemIcon);

        // save Item
        fmSaveItem = new JMenuItem("Guardar");
        fmSaveItem.addActionListener(this);
        fmSaveItem.setActionCommand("saveFile");
        Color fmSaveIconColor = new ColorUIResource(Color.BLUE);
        ImageIcon fmSaveIcon = createIcon(fmSaveIconColor);
        fmSaveItem.setIcon(fmSaveIcon);

        // saveAs Item
        fmSaveAsItem = new JMenuItem("Guardar como...");
        fmSaveAsItem.addActionListener(this);
        fmSaveAsItem.setActionCommand("saveAsFile");
        Color fmSaveAsItemIconColor = new ColorUIResource(Color.GRAY);
        ImageIcon fmSaveAsItemIcon = createIcon(fmSaveAsItemIconColor);
        fmSaveAsItem.setIcon(fmSaveAsItemIcon);
        
        // exit Item
        fmExitItem = new JMenuItem("Salir");
        fmExitItem.addActionListener(this);
        fmExitItem.setActionCommand("exitNotePad");
        Color fmExitItemIconColor = new ColorUIResource(Color.RED);
        ImageIcon fmExitItemIcon = createIcon(fmExitItemIconColor);
        fmExitItem.setIcon(fmExitItemIcon);
        
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
        amDescriptionItem = new JMenuItem("Presentacion");
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
