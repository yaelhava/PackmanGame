package GUI;

import java.awt.Graphics;
import java.awt.Graphics2D;
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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FileDialog;

import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import Algorithms.Path2KML;
import Algorithms.ShortestPathAlgo;
import Coords.MyCoords;
import File_format.CSV2elements;
import File_format.CSVWriter;
import Geom.Point3D;
import TheGame.Fruit;
import TheGame.Game;
import TheGame.Map;
import TheGame.Packman;
import Threads.PackmanThread;

import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;


public class MyFrame extends JFrame implements MouseListener, MenuListener, ActionListener {


	private BufferedImage strawberry, apple, cherry, packmanImage;
	private JMenuBar menuBar;
	private JMenu fileMenu, typeMenu;
	private JMenuItem save, load, clear, exit, export, packman, fruit, run;
	private Game game;
	private ArrayList<BufferedImage> imageList = new ArrayList<BufferedImage>();
	private int type, ID = 0;
	private Map map ;
	private CSVWriter csvWriter;
	private ShortestPathAlgo algo;

	public MyFrame() 
	{
		initGUI();		
		this.addMouseListener(this);

	}
	public MyFrame(int a) { 

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
		export = new JMenuItem("Export to KML");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,  ActionEvent.CTRL_MASK));
		load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,  ActionEvent.CTRL_MASK));
		clear.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,  ActionEvent.CTRL_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,  ActionEvent.CTRL_MASK));
		export.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K,  ActionEvent.CTRL_MASK));
		

		menuBar.add(fileMenu);
		fileMenu.add(save);
		fileMenu.add(load);
		fileMenu.add(export);
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

		run.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				add(e);
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
		
		export.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Path2KML p2k = new Path2KML(game);
			//	game = new Game(game.getPackmanList(), game.getFruitList());
				p2k.exportKmlFile();
				p2k.export();
			}
		});


		setJMenuBar(menuBar);
	}


	public void  add(ActionEvent e) {
		type = 3;
		algo = new ShortestPathAlgo(game);
		algo.ShortestPath();

		repaint();

		for (Packman P : game.getPackmanList()) {
			PackmanThread thread = new PackmanThread(P,this);

			thread.start();

		}
	}


	public void paint(Graphics g){
		g.drawImage(map.getMap(), 0, 0, getWidth() - 8, getHeight() - 8, this);
		setJMenuBar(menuBar);


		if(game.getPackmanList() != null)  {
			for(Packman p : game.getPackmanList()) {
				Point3D temp = map.GPS2Pixel(p.getPoint3D(), getWidth(), getHeight());
				//		System.out.println("temp: " +temp);
				
				g.drawImage(packmanImage,(int)temp.x(), (int)temp.y(), 30, 40, this);

			}		
		}

		if(game.getFruitList() != null) {
			for(Fruit f : game.getFruitList()) {
				Point3D temp = map.GPS2Pixel(f.getPoint3D(), getWidth(), getHeight());
				
				g.drawImage(imageList.get((int)(Math.random() * 3)),(int)temp.x(), (int)temp.y(), 25, 35, this);
			} 
		}

		if(type == 3) {
			for(Packman p : game.getPackmanList()) {
				Point3D right1 = p.getPoint3D();
				Point3D right = map.GPS2Pixel(p.getPackmanRoad().get(0).getPoint3D(), getWidth(), getHeight());
				for(Fruit f: p.getPackmanRoad()) {

					Point3D left = map.GPS2Pixel(f.getPoint3D(), getWidth(), getHeight());

					Graphics2D g2 = (Graphics2D) g;
					g.setColor(Color.PINK);
					g2.setStroke(new BasicStroke(4));
					g2.drawLine((int)right.x(),(int) right.y(),
							(int)left.x(), (int)left.y());
					right = map.GPS2Pixel(f.getPoint3D(), getWidth(), getHeight());


				}
				p.setPoint3D(right1);
			}
		}
	}


	public synchronized void synchronizedPaint() {
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg) {

		if(type == 1) {
			Point3D newPoint = map.pixel2GPS(new Point3D(arg.getX(), arg.getY() ), getWidth(), getHeight());
			Fruit newFruit = new Fruit(newPoint, ID);
			game.getFruitList().add(newFruit);
			repaint();
			ID++;
		}

		if(type == 2) {
			Point3D newPoint = map.pixel2GPS(new Point3D(arg.getX(), arg.getY()), getWidth(), getHeight());
			Packman newPackman = new Packman(newPoint,1,1, ID);
			game.getPackmanList().add(newPackman);
			repaint();
			ID++;
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void menuCanceled(MenuEvent arg0) {}

	@Override
	public void menuDeselected(MenuEvent arg0) {}

	@Override
	public void menuSelected(MenuEvent arg0) {}


	public void actionPerformed(ActionEvent e) {}



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

			CSV2elements c = new CSV2elements(dir + fileName, this.game);
			this.game = c.getGame();
			
			repaint();

			bReader.close();
			fileReader.close();
		} catch (IOException ex) {

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
			csvWriter.writeCSV(fileName, pWriter);
			fileWriter.close();

		} catch (IOException ex) {
			System.out.print("Error writing file  " + ex);
		}
	}


	public ArrayList<Point3D> packmanSteps(Packman packman) {
		ArrayList<Point3D> stepsList = new ArrayList<Point3D>();
		for (int i = 0; i < packman.getPackmanRoad().size() - 1; i++) {
			double stepSize = 0.5;
			MyCoords coord = new MyCoords(0, 0, 0);
			double dis = coord.distance3d(packman.getPackmanRoad().get(i).getPoint3D(), 
					packman.getPackmanRoad().get(i + 1).getPoint3D());
			double x = (stepSize/dis) * ( packman.getPackmanRoad().get(i + 1).getPoint3D().x() -
					packman.getPackmanRoad().get(i).getPoint3D().x())+
					packman.getPackmanRoad().get(i + 1).getPoint3D().x();
			double y = (stepSize/dis) * ( packman.getPackmanRoad().get(i + 1).getPoint3D().y() -
					packman.getPackmanRoad().get(i).getPoint3D().y()) +
					packman.getPackmanRoad().get(i + 1).getPoint3D().y();

			Point3D stepPoint = new Point3D(x, y);
			stepsList.add(stepPoint);

		} 
		return stepsList;
	}

	public static void main(String[] args){

		MyFrame window = new MyFrame();
		window.setVisible(true);

		window.setSize(1100,600);
		//window.setSize(window.map.getMap().getWidth(),window.map.getMap().getHeight());
	}





}



//package GUI;
//
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.image.BufferedImage;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.FilenameFilter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//
//import javax.imageio.ImageIO;
//import javax.swing.JFrame;
//import javax.swing.JMenu;
//import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;
//
//import java.awt.BasicStroke;
//import java.awt.Color;
//import java.awt.FileDialog;
//
//import javax.swing.KeyStroke;
//import javax.swing.event.MenuEvent;
//import javax.swing.event.MenuListener;
//
//import Algorithms.Path2KML;
//import Algorithms.ShortestPathAlgo;
//import Coords.MyCoords;
//import File_format.CSV2elements;
//import File_format.CSVWriter;
//import Geom.Point3D;
//import TheGame.Fruit;
//import TheGame.Game;
//import TheGame.Map;
//import TheGame.Packman;
//import Threads.PackmanThread;
//
//import java.awt.event.ActionListener;
//
//import java.awt.event.KeyEvent;
//import java.awt.event.ActionEvent;
//
//
//public class MyFrame extends JFrame implements MouseListener, MenuListener, ActionListener {
//
//
//	BufferedImage strawberry, apple, cherry, packmanImage;
//	JMenuBar menuBar;
//	JMenu fileMenu, typeMenu;
//	JMenuItem save, load,clear, exit, packman, fruit, run;
//	Game game;
//	ArrayList<BufferedImage> imageList = new ArrayList<BufferedImage>();
//	int type;
//	Map map ;
//	CSVWriter csvWriter;
//	ShortestPathAlgo algo;
//
//	public MyFrame() 
//	{
//		initGUI();		
//		this.addMouseListener(this);
//
//	}
//
//	private void initGUI() {
//
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		map = new Map( "Ariel1.png");
//		game = new Game();
//		csvWriter = new CSVWriter(game);
//		menuBar = new JMenuBar();
//
//
//		fileMenu = new JMenu("File"); 
//		save = new JMenuItem("Save");
//		load = new JMenuItem("Load");
//		clear = new JMenuItem("Clear");
//		exit = new JMenuItem("Exit");
//		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,  ActionEvent.CTRL_MASK));
//		load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,  ActionEvent.CTRL_MASK));
//		clear.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,  ActionEvent.CTRL_MASK));
//		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,  ActionEvent.CTRL_MASK));
//
//		menuBar.add(fileMenu);
//		fileMenu.add(save);
//		fileMenu.add(load);
//		fileMenu.add(clear);
//		fileMenu.add(exit);
//
//		typeMenu = new JMenu("Play");
//		packman = new JMenuItem("Packman");
//		fruit = new JMenuItem("Fruit");
//		run = new JMenuItem("Run");
//
//
//
//		menuBar.add(typeMenu);
//		setJMenuBar(menuBar);
//		typeMenu.add(packman);
//		typeMenu.add(fruit);
//		typeMenu.add(run);
//
//		//	menuBar.setVisible(true);
//
//
//		try {
//			strawberry = ImageIO.read(new File("strawberry.png"));
//			apple = ImageIO.read(new File("apple.png"));
//			cherry = ImageIO.read(new File("cherry.png"));
//			packmanImage = ImageIO.read(new File("pacman.png"));
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//
//		imageList.add(apple);
//		imageList.add(strawberry);
//		imageList.add(cherry);
//
//		fruit.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				type = 1;
//			}
//		});
//
//		packman.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				type = 2;
//			}
//		});
//
//		run.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				add(e);
//
//			}
//		});
//
//		load.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				loadFile();
//				//repaint???????????????????
//			}
//		});
//
//		save.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				saveFile();
//			}
//		});
//		clear.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				game.getPackmanList().clear();
//				game.getFruitList().clear();
//				repaint();
//			}
//		});
//
//		exit.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				System.exit(0);
//
//			}
//		});
//		//		Point3D start = new Point3D(32.106046, 35.212405);
//		//		Point3D end = new Point3D(32.101858, 35.202574);
//
//		setJMenuBar(menuBar);
//		//menuBar.setVisible(true);
//
//	}
//
//
//	public void add(ActionEvent e) {
//		type = 3;
//		algo = new ShortestPathAlgo(game);
//		algo.ShortestPath();
//		repaint();
//		for(Packman p : game.getPackmanList()) {
//			PackmanThread thread = new PackmanThread(p, this);
//			thread.start();
//		}
//	}
//
//
//	//	int x = -1;
//	//	int y = -1;
//
//	public void paint(Graphics g){
//		g.drawImage(map.getMap(), 0, 0,getWidth() - 8,getHeight() - 8, this);
//		setJMenuBar(menuBar);
//
//
//		if(game.getPackmanList().size() != 0)  {
//			for(Packman p : game.getPackmanList()) {
//				Point3D temp = map.GPS2Pixel(p.getPoint3D(), getWidth(), getHeight());
//				System.out.println("temp: " +temp);
//
//				g.drawImage(packmanImage,(int)temp.x(), (int)temp.y(), 30, 40, this);
//
//			}		
//		}
//
//		if(game.getFruitList().size() != 0) {
//			for(Fruit f : game.getFruitList()) {
//			//	System.out.println("fruit: " + f.getPoint3D());
//				Point3D temp = map.GPS2Pixel(f.getPoint3D(), getWidth(), getHeight());
//				//		System.out.println("temp: " +temp);
//			//	System.out.println("temp: " +temp);
//				g.drawImage(imageList.get((int)(Math.random()*3)),(int)temp.x(), (int)temp.y(), 25, 35, this);
//			}
//		}
//
//
//		if(type == 3) {
//			//			ArrayList<Packman> newPackmanList = new ArrayList<Packman>();
//			//			for(Packman p : game.getPackmanList()) {
//			//				newPackmanList.add(p);
//			//			}
//			//	int i = 0;
//			for(Packman p : game.getPackmanList()) {
//				//Point3D left = map.gps2Pixel(p.getPoint3D(), getWidth(), getHeight());
//			//	System.out.println("packman: " + p.getPoint3D());
//				Point3D right1 = p.getPoint3D();
//				Point3D right = map.GPS2Pixel(p.getPoint3D(), getWidth(), getHeight());
//				for(Fruit f : p.getPackmanRoad()) {
//					Point3D left = map.GPS2Pixel(f.getPoint3D(), getWidth(), getHeight());
//				
//					Graphics2D g2 = (Graphics2D) g;
//					g2.setColor(Color.PINK);
//					g2.setStroke(new BasicStroke(6));
//					g2.drawLine((int)left.x(),(int) left.y(),
//							(int)right.x(), (int)right.y());
//					right =  map.GPS2Pixel(f.getPoint3D(), getWidth(), getHeight());
//				}
//				p.setPoint3D(right1);
//			}
//
//
//		}
//
//		//				if(x != -1 && y != -1)
//		//				{
//		//					int r = 100;
//		//					x = x - (r / 2);
//		//					y = y - (r / 2);
//		//					g.fillOval(x, y, r, r);
//		//					g.fillRect(x, y, r, r);
//		//					
//		//				}
//	}
//
//	@Override
//	public void mouseClicked(MouseEvent arg) {
//
//		//map.pixel2gps(new Point3D(arg.getPoint().getX(), arg.getPoint().getY() ), getWidth(), getHeight());
//
//		if(type == 1) {
//			Point3D newPoint = map.pixel2GPS(new Point3D(arg.getX(), arg.getY() ), getWidth(), getHeight());
//			System.out.println("mouse: " + newPoint);
//			Fruit newFruit = new Fruit(newPoint);
//			//System.out.println("newPoint: " +newPoint);
//			game.getFruitList().add(newFruit);
//			repaint();
//		}
//
//		if(type == 2) {
//			Point3D newPoint = map.pixel2GPS(new Point3D(arg.getX(), arg.getY()), getWidth(), getHeight());
//			Packman newPackman = new Packman(newPoint, 1, 1);
//			game.getPackmanList().add(newPackman);
//			repaint();
//		}
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent arg0) {}
//
//	@Override
//	public void mouseExited(MouseEvent arg0) {}
//
//	@Override
//	public void mousePressed(MouseEvent arg0) {}
//
//	@Override
//	public void mouseReleased(MouseEvent arg0) {}
//
//	@Override
//	public void menuCanceled(MenuEvent arg0) {}
//
//	@Override
//	public void menuDeselected(MenuEvent arg0) {}
//
//	@Override
//	public void menuSelected(MenuEvent arg0) {}
//
//	
//	public void actionPerformed(ActionEvent e) {}
//
//	
//	public void loadFile() {
//		//		try read from the file
//		FileDialog fileDialog = new FileDialog(this, "Open CSV file", FileDialog.LOAD);
//		fileDialog.setFile("*.csv");
//		fileDialog.setDirectory("C:\\Users");
//		fileDialog.setFilenameFilter(new FilenameFilter() {
//			@Override
//			public boolean accept(File dir, String name) {
//				return name.endsWith(".csv");
//			}
//		});
//		fileDialog.setVisible(true);
//		String dir = fileDialog.getDirectory();
//		String fileName = fileDialog.getFile();
//		try {
//			FileReader fileReader = new FileReader(dir + fileName);
//			BufferedReader bReader = new BufferedReader(fileReader);
//
//			CSV2elements c = new CSV2elements(dir + fileName, game.getPackmanList(), game.getFruitList());
//			
//			repaint();
//			
//			bReader.close();
//			fileReader.close();
//		} catch (IOException ex) {
//			//			System.out.print("Error reading file " + ex);
//			//			System.exit(2);
//		}
//	}
//
//	public void saveFile() {
//		//		try read from the file
//		FileDialog fileDialog = new FileDialog(this, "Save CSV file", FileDialog.SAVE);
//		fileDialog.setFile("*.csv");
//		fileDialog.setFilenameFilter(new FilenameFilter() {
//			@Override
//			public boolean accept(File dir, String name) {
//				return name.endsWith(".csv");
//			}
//		});
//		fileDialog.setVisible(true);
//		String dir = fileDialog.getDirectory();
//		String fileName = fileDialog.getFile();
//		try {
//			FileWriter fileWriter = new FileWriter(dir + fileName);
//			PrintWriter pWriter = new PrintWriter(fileWriter);
//			//	System.out.println("what i wanted1" + game.getFruitList().get(0));
//			//    game.exportCsvFile(fileName, pWriter);
//			csvWriter.writeCSV(fileName, pWriter);
//			fileWriter.close();
//
//		} catch (IOException ex) {
//			System.out.print("Error writing file  " + ex);
//		}
//
//
//	}
//
//
//
//	public ArrayList<Point3D> packmanSteps(Packman packman){
//		ArrayList<Point3D> stepesList = new ArrayList<Point3D>();
//		for (int i = 0; i < packman.getPackmanRoad().size() - 1; i++) {
//			double stepSize = 0.5;
//			MyCoords coord = new MyCoords(0, 0, 0);
//			double dis = coord.distance3d(packman.getPackmanRoad().get(i).getPoint3D(),
//					packman.getPackmanRoad().get(i + 1).getPoint3D());
//			double x = (stepSize / dis) * (packman.getPackmanRoad().get(i + 1).getPoint3D().x() - 
//					packman.getPackmanRoad().get(i).getPoint3D().x()) +
//					packman.getPackmanRoad().get(i).getPoint3D().x();
//			double y = (stepSize / dis) * (packman.getPackmanRoad().get(i + 1).getPoint3D().y() - 
//					packman.getPackmanRoad().get(i).getPoint3D().y()) +
//					packman.getPackmanRoad().get(i).getPoint3D().y();
//			Point3D stepPoint = new Point3D(x, y);
//			stepesList.add(stepPoint);
//		}
//		return stepesList;
//
//	}
//
//	public synchronized void method() {
//		repaint();
//	}
//
//	public void exportKmlFile(Game game) {
//		Path2KML p2k = new Path2KML();
//		p2k.exportKmlFile(game);
//	}
//
//	public static void main(String[] args){
//
//		MyFrame window = new MyFrame();
//		window.setVisible(true);
//
//		window.setSize(1100,600);
//		//window.setSize(window.map.getMap().getWidth(),window.map.getMap().getHeight());
//		//window.exportKmlFile(game);
//
//	}
//}
//
