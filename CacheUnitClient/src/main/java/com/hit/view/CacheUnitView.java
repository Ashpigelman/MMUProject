package com.hit.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("deprecation")
public class CacheUnitView extends Observable implements View{

	private JPanel container=new JPanel(new BorderLayout());
	private JPanel request;
	private JPanel panelTop = new JPanel();
	private JTextArea requesTxt= new JTextArea();
	private Font f =new Font("Ariel", Font.BOLD, 11);
	private Font f2 =new Font("Arial", Font.BOLD,11);
	private JTextArea cacheText= new JTextArea();
	private int reqnum,dmnum,swapnum=0;
	private JLabel label3 = new JLabel("Total Number Of Request:"+reqnum);
	private JLabel label4 = new JLabel("Total Number Of DataModels:"+dmnum);
	private JLabel label5 = new JLabel("Total Number Of DataModels swaps:"+swapnum);

	public CacheUnitView()
	{
		cacheText.setText(" ");
		cacheText.setFont(f);
		requesTxt.setText(" ");
		requesTxt.setFont(f2);
	}

	protected void createAndShowGUI()
	{
		//Create and set up the window.
		JFrame frame = new JFrame("MemoryManagementUnit");
		frame.setIconImage(new ImageIcon("lib\\icon.png").getImage());
		/*try {
			frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("lib\\motherboard.jpg")))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel titleEnd = new JLabel("© Programing by Alexey & Sapir");
		JPanel panel=new JPanel(new FlowLayout());
		panelTop.setLayout(new BoxLayout(panelTop,BoxLayout.PAGE_AXIS));
		panelTop.setBorder(BorderFactory.createTitledBorder("Statistics"));
		panelTop.setPreferredSize(new Dimension(255,100));
		panelTop.setBackground(new Color(153, 223, 255));
		request=new JPanel();
		request.setLayout(new BoxLayout(request,BoxLayout.PAGE_AXIS));
		request.setBorder(BorderFactory.createTitledBorder("The Info"));
		request.setPreferredSize(new Dimension(255,100));
		request.setBackground(new Color(230, 247, 255));

		//add label
		JLabel label = new JLabel("capacity: 3");
		JLabel label2 = new JLabel("algorithm: LRU ");
		panelTop.add(label);
		panelTop.add(label2);
		panelTop.add(label3);
		panelTop.add(label4);
		panelTop.add(label5);

		//Add buttons
		JButton b1,b2;
		b1=new JButton("Load a Request");
		b2=new JButton("Show statistics");
		panel.add(b1);
		panel.add(b2);
		request.add(new JLabel("Cache"));
		request.add(cacheText);
		request.add(new JLabel("Request"));
		request.add(requesTxt);
		cacheText.setText("Cache content");
		requesTxt.setText("The content of Request...");
		cacheText.setEditable(false);
		requesTxt.setEditable(false);
		panelTop.setVisible(false);
		container.add(titleEnd,BorderLayout.SOUTH);
		container.add(panel,BorderLayout.NORTH);
		container.add(panelTop, BorderLayout.EAST);
		container.add(request, BorderLayout.WEST);

		//Display the window.
		frame.add(container);
		b1.addActionListener(new Load());
		b2.addActionListener(new Statistics());
		frame.setSize(700,500);
		//frame.pack();
		frame.setVisible(true);	

	}
	
	public void start()
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

	}
	
	public <T>void updateUIData(T t) {

		if(t!=null)
		if(((String)t).length()>3)
		{
			cacheText.removeAll();
			cacheText.setText((String) t);
		}
		//update swaps
		else
			swapnum=Integer.parseInt((String)t);
		dmnum=cacheText.getLineCount()-1;
		label5.setText("Total Number Of DataModels swaps:"+swapnum);
		label4.setText("Total Number Of DataModels:"+dmnum);
	}

public class Load implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		JFileChooser fc=new JFileChooser();
		fc.showOpenDialog(null);
		try
		{
			BufferedReader br=new BufferedReader(new FileReader(fc.getSelectedFile()));
			requesTxt.read(br, null);
			request.add(requesTxt);
			reqnum++;
			label3.setText("Total Number Of Request:"+reqnum);
			requesTxt.setEditable(false);
			//send to controller by notify!!!!!
			String req=(requesTxt.getText());
			setChanged();
			notifyObservers(req);

		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}
}
public class Statistics implements ActionListener
{
	public Statistics() 
	{

	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		panelTop.setVisible(true);
		label5.setText("Total Number Of DataModels swaps:"+swapnum);
	}

}

}
