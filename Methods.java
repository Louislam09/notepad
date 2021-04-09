import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Methods {
    NotePad notePad;
    String fileName,fileAddress;
    

    public Methods(NotePad notePad) {
        this.notePad = notePad;
    }

    public void createShowWarning(){
        notePad.showWarningDialog =  new JOptionPane();
        int input = JOptionPane.showConfirmDialog(null, "¿ Quieres Guardar Los Cambios de ...?");
        if(input == JOptionPane.YES_OPTION){
            onSave();
        }
    }

    public void onToggleDarkMode(){
        Boolean isDarkMode = !notePad.darkModeCheckBox.isSelected();
        if(!isDarkMode){
            notePad.textArea.setForeground(notePad.darkFgColor);
            notePad.textArea.setBackground(notePad.darkBgColor);
        }else {
            notePad.textArea.setForeground(notePad.lightFgColor);
            notePad.textArea.setBackground(notePad.lightBgColor);
        }

        notePad.darkModeCheckBox.setSelected(!isDarkMode);

    }


    public void onClickNewItem() {
        notePad.textArea.setText("");
        notePad.window.setTitle("Nuevo Archivo");
        fileName = null;
        fileAddress = null;
    }

    public void onClickNewWindowItem() {
        new NewNotepad();
    }

    public void onOpenFile() {
        JFileChooser f = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
        
        f.setFileFilter(filter);
        int returnVal = f.showOpenDialog(notePad.window);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileName = f.getSelectedFile().getName();
            fileAddress = f.getSelectedFile().getPath();
            notePad.window.setTitle(fileName);
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileAddress));          
            
            String line = null;
            notePad.textArea.setText("");

            while((line = reader.readLine()) != null){
                notePad.textArea.append(line + "\n");
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("There was an error");
        }
    }

    public void onSave() {
        if(fileName == null){
            onSaveAs();
        }else{
            try {
                FileWriter fw = new FileWriter(fileAddress);
                fw.write(notePad.textArea.getText());
                fw.close();
            } catch (Exception e) {
                System.out.println("There was an error");
            }
        }
    }

    public void onSaveAs() {
        JFileChooser f = new JFileChooser();
        
        int returnVal = f.showSaveDialog(notePad.window);

        if(returnVal == JFileChooser.APPROVE_OPTION){
            fileName = f.getSelectedFile().getName();
            fileAddress = f.getSelectedFile().getPath();
            notePad.window.setTitle(fileName);
        }

        try {
            FileWriter fw = new FileWriter(fileAddress);
            fw.write(notePad.textArea.getText());
            fw.close();
        } catch (Exception e) {
            System.out.println("There was an error");
        }
    }

    public void onExit(){
        createShowWarning();
        System.exit(0);
    }

    public void onCopy() {
        notePad.selectedText = notePad.textArea.getSelectedText();
    }
    
    public void onPaste() {
        notePad.textArea.insert(notePad.selectedText, notePad.textArea.getCaretPosition());
    }
    
    public void onCut() {
        notePad.selectedText = notePad.textArea.getSelectedText();
        notePad.textArea.replaceRange("", notePad.textArea.getSelectionStart(), notePad.textArea.getSelectionEnd());
    }

    public void onDelete() {
        notePad.textArea.replaceRange("", notePad.textArea.getSelectionStart(), notePad.textArea.getSelectionEnd());
    }
}
