package GUI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.FileDialog;

import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import File_format.CSV2elements;
import File_format.CSVWriter;
import Geom.Point3D;
import TheGame.Fruit;
import TheGame.Game;
import TheGame.Map;
import TheGame.Packman;

import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;


public class MyFrame extends JFrame implements MouseListener, MenuListener, ActionListener {


	BufferedImage myImage, strawberry, apple, cherry, packmanImage;
	JMenuBar menuBar;
	JMenu fileMenu, typeMenu;
	JMenuItem save, load,clear, exit, packman, fruit, run;
	Game game;
	ArrayList<BufferedImage> imageList = new ArrayList<BufferedImage>();
	int type;
	Map map ;
	CSVWriter csvWriter;


	public MyFrame() 
	{
		initGUI();		
		this.addMouseListener(this);

	}

	private void initGUI() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		map = new Map( "Ariel1.png");
		game = new Game();
		csvWriter = new CSVWriter(game);
		menuBar = new JMenuBar();


		fileMenu = new JMenu("File"); 
		save = new JMenuItem("Save");
		load = new JMenuItem("Load");
		clear = new JMenuItem("Clear");
		exit = new JMenuItem("Exit");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,  ActionEvent.CTRL_MASK));
		load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,  ActionEvent.CTRL_MASK));
		clear.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,  ActionEvent.CTRL_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,  ActionEvent.CTRL_MASK));

		menuBar.add(fileMenu);
		fileMenu.add(save);
		fileMenu.add(load);
		fileMenu.add(clear);
		fileMenu.add(exit);

		typeMenu = new JMenu("Play");
		packman = new JMenuItem("Packman");
		fruit = new JMenuItem("Fruit");
		run = new JMenuItem("Run");



		menuBar.add(typeMenu);
		setJMenuBar(menuBar);
		typeMenu.add(packman);
		typeMenu.add(fruit);
		typeMenu.add(run);

		//	menuBar.setVisible(true);


		try {
			strawberry = ImageIO.read(new File("strawberry.png"));
			apple = ImageIO.read(new File("apple.png"));
			cherry = ImageIO.read(new File("cherry.png"));
			packmanImage = ImageIO.read(new File("pacman.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}


		imageList.add(apple);
		imageList.add(strawberry);
		imageList.add(cherry);

		fruit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				type = 1;
			}
		});

		packman.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				type = 2;
			}
		});

		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadFile();
			}
		});

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveFile();
			}
		});
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.getPackmanList().clear();
				game.getFruitList().clear();
				repaint();
			}
		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);

			}
		});
		//		Point3D start = new Point3D(32.106046, 35.212405);
		//		Point3D end = new Point3D(32.101858, 35.202574);

		setJMenuBar(menuBar);
		//menuBar.setVisible(true);

	}



	int x = -1;
	int y = -1;

	public void paint(Graphics g){
		g.drawImage(map.getMap(), 0, 0,getWidth(),getHeight(), this);
		setJMenuBar(menuBar);


		if(game.getPackmanList() != null)  {
			for(Packman p : game.getPackmanList()) {
				Point3D temp = map.gps2Pixel(p.getPoint3D(), getWidth(), getHeight());
				//		System.out.println("temp: " +temp);

				g.drawImage(packmanImage,(int)temp.x(), (int)temp.y(), 30, 40, this);

			}		
		}

		if(game.getFruitList() != null) {
			for(Fruit f : game.getFruitList()) {
				Point3D temp = map.gps2Pixel(f.getPoint3D(), getWidth(), getHeight());
				//		System.out.println("temp: " +temp);

				g.drawImage(imageList.get((int)(Math.random()*3)),(int)temp.x(), (int)temp.y(), 30, 40, this);
			}
		}

		//				if(x != -1 && y != -1)
		//				{
		//					int r = 100;
		//					x = x - (r / 2);
		//					y = y - (r / 2);
		//					g.fillOval(x, y, r, r);
		//					g.fillRect(x, y, r, r);
		//					
		//				}
	}

	@Override
	public void mouseClicked(MouseEvent arg) {

		//map.pixel2gps(new Point3D(arg.getPoint().getX(), arg.getPoint().getY() ), getWidth(), getHeight());

		if(type == 1) {
			Point3D newPoint = map.pixel2gps(new Point3D(arg.getX(), arg.getY() ), getWidth(), getHeight());
			Fruit newFruit = new Fruit(newPoint);
			//System.out.println("newPoint: " +newPoint);
			game.getFruitList().add(newFruit);
			repaint();
		}

		if(type == 2) {
			Point3D newPoint = map.pixel2gps(new Point3D(arg.getX(), arg.getY()), getWidth(), getHeight());
			Packman newPackman = new Packman(newPoint);
			game.getPackmanList().add(newPackman);
			repaint();
		}


		repaint();



		//		System.out.println("mouse Clicked");
		//		System.out.println("("+ arg.getX() + "," + arg.getY() +")");
		//		x = arg.getX();
		//		y = arg.getY();
		//		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}


	@Override
	public void menuCanceled(MenuEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuDeselected(MenuEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuSelected(MenuEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e) {
		//		Object o = e.getSource();
		//
		//		if(o == fruit) {
		//
		//		}

	}

	public void loadFile() {
		//		try read from the file
		FileDialog fileDialog = new FileDialog(this, "Open CSV file", FileDialog.LOAD);
		fileDialog.setFile("*.csv");
		fileDialog.setDirectory("C:\\Users");
		fileDialog.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		});
		fileDialog.setVisible(true);
		String dir = fileDialog.getDirectory();
		String fileName = fileDialog.getFile();
		try {
			FileReader fileReader = new FileReader(dir + fileName);
			BufferedReader bReader = new BufferedReader(fileReader);
//			String line = new String();
//			while (line != null) {
//				line = bReader.readLine();
//				if (line != null) {
//					System.out.println(line);		//terem hevanti ma ose
//				}
//			}
			CSV2elements c = new CSV2elements(dir + fileName);
			repaint();

			bReader.close();
			fileReader.close();
		} catch (IOException ex) {
			//			System.out.print("Error reading file " + ex);
			//			System.exit(2);
		}
	}

	public void saveFile() {
		//		try read from the file
		FileDialog fileDialog = new FileDialog(this, "Save CSV file", FileDialog.SAVE);
		fileDialog.setFile("*.csv");
		fileDialog.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		});
		fileDialog.setVisible(true);
		String dir = fileDialog.getDirectory();
		String fileName = fileDialog.getFile();
		try {
			FileWriter fileWriter = new FileWriter(dir + fileName);
			PrintWriter pWriter = new PrintWriter(fileWriter);
			//	System.out.println("what i wanted1" + game.getFruitList().get(0));
			//    game.exportCsvFile(fileName, pWriter);
			csvWriter.writeCSV(fileName, pWriter);
			fileWriter.close();

		} catch (IOException ex) {
			System.out.print("Error writing file  " + ex);
		}


	}

	public static void main(String[] args)
	{

		MyFrame window = new MyFrame();
		window.setVisible(true);

		window.setSize(1100,600);
		//window.setSize(window.map.getMap().getWidth(),window.map.getMap().getHeight());

	}

}

