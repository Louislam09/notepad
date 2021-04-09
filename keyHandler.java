import java.awt.event.KeyEvent;

public class keyHandler implements java.awt.event.KeyListener {
    NotePad notePad;
   
    public keyHandler(NotePad notePad) {
        this.notePad = notePad;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // ctrl + s = save
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S){
            notePad.METHODS.onSave();
        }        
        // ctrl + shift + s = saveAs
        if(e.isShiftDown() && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S){
            notePad.METHODS.onSaveAs();
        }        
        
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_O){
            notePad.METHODS.onOpenFile();
        }        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
}
