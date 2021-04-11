import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class StatusBar extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    NotePad notePad;
    JLabel rowAndColumLabel = new JLabel("Filas: 1, Columnas: 1");
    // JLabel Columnlabel = new JLabel("Columnas: 2");

    StatusBar(NotePad notePad){
        super();
        this.notePad = notePad;
        createStatusBar();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    public void createStatusBar() {
        notePad.window.add(this,BorderLayout.PAGE_END);
        setPreferredSize(new DimensionUIResource(notePad.window.getWidth(), 20));

        // Border border = BorderFactory.createLineBorder(Color.gray);
        Border border = rowAndColumLabel.getBorder();
        Border margin = new EmptyBorder(0,20,0,20);
        rowAndColumLabel.setBorder(new CompoundBorder(border, margin));
    
        rowAndColumLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(rowAndColumLabel);
    }

}
