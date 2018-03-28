package debate.client;

import debate.core.Tournament;
import debate.gui.Window;

public class Client 
{
	private Window window;
	private Tournament tournament;
	
	public Client(Tournament t)
	{
		System.out.println("DEBUG: Creating Client");
		tournament = t;
		window = new Window(tournament);
		window.setVisible(true);
	}
	
	public void beginTournament()
	{
		// TODO: Generate Matchups
	}
	
	public void startRound()
	{
		
	}
	
	public void endRound()
	{
		
	}
	
	public void cleanUp()
	{
		
	}
	
	
}
