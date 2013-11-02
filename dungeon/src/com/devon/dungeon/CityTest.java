package com.devon.dungeon;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class CityTest extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7204918799602570148L;
	public static DungeonGenerator genCity;
	public static CityTest testFrame;

	public CityTest()
	{
		genCity = new DungeonGenerator();
		addMouseListener(new MouseClickHandler());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(genCity, BorderLayout.CENTER);

		setSize(1200, 1200);
		setVisible(true);
	}

	public static void main(String args[])
	{
		testFrame = new CityTest();


	}

	private class MouseClickHandler extends MouseAdapter
	{
		public void mouseClicked(MouseEvent event)
		{
			testFrame.remove(genCity);
			genCity = new DungeonGenerator();
			add(genCity, BorderLayout.CENTER);
			genCity.revalidate();
			testFrame.repaint();
			System.out.println("SS");
		}
	}
}