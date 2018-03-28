package debate.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import debate.core.Tournament;

public class Window extends JFrame
{
	public Window(Tournament tournament)
	{
		//Create and set up the content pane.
        TableRenderer newContentPane = new TableRenderer(tournament);
        newContentPane.setOpaque(true); //content panes must be opaque
        setContentPane(newContentPane);
        
        
        
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Display the window.
        pack();
        setSize(400, 500);
        setVisible(true);
	}

}
