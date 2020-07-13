package html2xml;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frame;
	private JTextField filePathTextField;
	private JRadioButton dropFormatTagsRadioButton;
	private JRadioButton includeXmlAttibutesRadioButton;
	private JLabel lblNewLabel;
	private JButton parseButton;
	private JLabel lblSelectInputFile;

	static JFileChooser inPutChooser = new JFileChooser();
	private String filePath = "";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 660, 377);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);

		lblNewLabel = new JLabel("html2xml");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		lblSelectInputFile = new JLabel("Select input file");
		GridBagConstraints gbc_lblSelectInputFile = new GridBagConstraints();
		gbc_lblSelectInputFile.gridwidth = 2;
		gbc_lblSelectInputFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectInputFile.gridx = 1;
		gbc_lblSelectInputFile.gridy = 3;
		frame.getContentPane().add(lblSelectInputFile, gbc_lblSelectInputFile);

		filePathTextField = new JTextField();
		filePathTextField.setEditable(false);
		GridBagConstraints gbc_filePathTextField = new GridBagConstraints();
		gbc_filePathTextField.insets = new Insets(0, 0, 5, 5);
		gbc_filePathTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_filePathTextField.gridx = 1;
		gbc_filePathTextField.gridy = 4;
		frame.getContentPane().add(filePathTextField, gbc_filePathTextField);
		filePathTextField.setColumns(10);

		JButton selectFileButton = new JButton("");
		selectFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int returnVal = inPutChooser.showOpenDialog(inPutChooser);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = inPutChooser.getSelectedFile();
					if(file.getAbsolutePath().endsWith(".html")) {
						filePath = file.getAbsolutePath();
						filePathTextField.setText(filePath);
						parseButton.setEnabled(true);
					}
				}
			}
		});
		selectFileButton.setIcon(new ImageIcon(MainWindow.class.getResource("/com/sun/java/swing/plaf/motif/icons/TreeOpen.gif")));
		GridBagConstraints gbc_selectFileButton = new GridBagConstraints();
		gbc_selectFileButton.insets = new Insets(0, 0, 5, 5);
		gbc_selectFileButton.gridx = 2;
		gbc_selectFileButton.gridy = 4;
		frame.getContentPane().add(selectFileButton, gbc_selectFileButton);

		dropFormatTagsRadioButton = new JRadioButton("Drop format tags");
		GridBagConstraints gbc_dropFormatTagsRadioButton = new GridBagConstraints();
		gbc_dropFormatTagsRadioButton.gridwidth = 2;
		gbc_dropFormatTagsRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_dropFormatTagsRadioButton.gridx = 1;
		gbc_dropFormatTagsRadioButton.gridy = 6;
		frame.getContentPane().add(dropFormatTagsRadioButton, gbc_dropFormatTagsRadioButton);

		includeXmlAttibutesRadioButton = new JRadioButton("Include attibutes");
		GridBagConstraints gbc_includeXmlAttibutesRadioButton = new GridBagConstraints();
		gbc_includeXmlAttibutesRadioButton.gridwidth = 2;
		gbc_includeXmlAttibutesRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_includeXmlAttibutesRadioButton.gridx = 1;
		gbc_includeXmlAttibutesRadioButton.gridy = 7;
		frame.getContentPane().add(includeXmlAttibutesRadioButton, gbc_includeXmlAttibutesRadioButton);

		parseButton = new JButton("Convert");
		parseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] args = new String[3];
				args[0] = filePath;
				if(dropFormatTagsRadioButton.isSelected()) { 
					args[1] = "true";
				}else {
					args[1] = "false";
				}
				if(includeXmlAttibutesRadioButton.isSelected()) { 
					args[2] = "true";
				}else {
					args[2] = "false";
				}
				Munger.main(args);
			}
		});
		parseButton.setEnabled(false);
		GridBagConstraints gbc_parseButton = new GridBagConstraints();
		gbc_parseButton.gridwidth = 2;
		gbc_parseButton.insets = new Insets(0, 0, 5, 5);
		gbc_parseButton.gridx = 1;
		gbc_parseButton.gridy = 9;
		frame.getContentPane().add(parseButton, gbc_parseButton);
	}

}