import javax.swing.JScrollPane;

public class ScrollBar extends JScrollPane {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    NotePad notePad;

    ScrollBar(NotePad notePad){
        this.notePad = notePad;
        getViewport().add(notePad.textArea);
        notePad.window.add(this);

    }
}
