package com.sciencemeetspianos.kuroneko;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class KuroNeko extends JFrame {

	private static final long serialVersionUID = Long.MAX_VALUE;
	private JFileChooser fc;
	private JButton browse;
	private JTextField downloadDir;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JScrollPane jScrollPane1;
	private JTextArea jTextArea1;
	private JTextField link;
	private JButton startBtn;

	public KuroNeko() {
		this.fc = new JFileChooser();
		this.fc.setFileSelectionMode(1);

		initComponents();
	}

	private void initComponents() {
		this.jLabel1 = new JLabel();
		this.link = new JTextField();
		this.jScrollPane1 = new JScrollPane();
		this.jTextArea1 = new JTextArea();
		this.startBtn = new JButton();
		this.jLabel2 = new JLabel();
		this.downloadDir = new JTextField();
		this.browse = new JButton();

		setDefaultCloseOperation(3);

		this.jLabel1.setText("Link to download: ");

		this.link.setText("e.g. http://www.kissanime.com/Anime/Angel-Beats");
		this.link.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				KuroNeko.this.linkActionPerformed(evt);
			}
		});
		this.jTextArea1.setColumns(20);
		this.jTextArea1.setRows(5);
		this.jScrollPane1.setViewportView(this.jTextArea1);

		this.startBtn.setText("Start!");
		this.startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				KuroNeko.this.startBtnActionPerformed(evt);
			}
		});
		this.jLabel2.setText("File locations: ");

		this.browse.setText("Browse...");
		this.browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				KuroNeko.this.browseActionPerformed(evt);
			}
		});
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addComponent(this.jScrollPane1)
												.addComponent(this.startBtn,
														-1, -1, 32767)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.TRAILING)
																				.addComponent(
																						this.jLabel1)
																				.addComponent(
																						this.jLabel2))
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										this.downloadDir)
																								.addPreferredGap(
																										LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										this.browse))
																				.addComponent(
																						this.link,
																						-1,
																						619,
																						32767))))
								.addContainerGap()));

		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(this.jLabel1)
												.addComponent(this.link, -2,
														-1, -2))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED,
										-1, 32767)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(this.jLabel2)
												.addComponent(this.downloadDir,
														-2, -1, -2)
												.addComponent(this.browse))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(this.jScrollPane1, -2, 220, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.startBtn).addContainerGap()));

		pack();
	}

	private void startBtnActionPerformed(ActionEvent evt) {
		startMainProcess();
	}

	private void linkActionPerformed(ActionEvent evt) {
	}

	private void browseActionPerformed(ActionEvent evt) {
		int returnVal = this.fc.showDialog(this, null);
		if (returnVal == 0)
			try {
				this.downloadDir.setText(this.fc.getSelectedFile()
						.getCanonicalPath());
				this.downloadDir.setEnabled(false);
			} catch (IOException ex) {
				Logger.getLogger(KuroNeko.class.getName()).log(Level.SEVERE,
						null, ex);
			}
	}

	private static String humanReadableBytes(long bytes, boolean si) {
		int unit = si ? 1000 : 1024;
		if (bytes < unit) {
			return bytes + "B";
		}
		int exp = (int) +(Math.log(bytes) / Math.log(unit));
		String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1)
				+ (si ? "" : "i");
		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}

	private void startMainProcess() {

		Logger l = Logger.getLogger("KuroNeko.log");
		try {
			FileHandler fh = new FileHandler(System.getProperty("user.home")
					+ File.separator + "Desktop" + File.separator
					+ "KuroNeko.log");
			l.addHandler(fh);
			l.setLevel(Level.ALL);
			fh.setFormatter(new LogBasicFormatter());
			l.fine("Purging temp files directory");
			System.out.println("Purging temp files directory");
			jTextArea1.append("Purging temp files directory \n");
			FileUtils.cleanDirectory(new File(System.getProperty("user.home")
					+ "/.kuroneko/tempfiles/"));
			l.fine("Finished");
			l.fine("Setting user agent");
			System.out.println("Setting user agent");
			jTextArea1.append("Setting user agent \n");
			System.setProperty(
					"http.agent",
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
			l.fine("Finished");
			Download d = new Download(new URL(link.getText()));
			l.finer("Downloading root webpage");
			System.out.println("Downloading root webpage");
			jTextArea1.append("Downloading root webpage \n");
			d.fileFromURL();
			l.fine("Finished");
			ArrayList<String> links0 = new ArrayList<String>();
			l.fine("Retrieving elements in table");
			System.out.println("Retrieving elements in table");
			jTextArea1.append("Retrieving elements in table \n");
			int var0 = Util.getTableList(d.theFile);
			int p = 0;
			l.fine("Finished");
			for (int i = 0; i < var0; i++) {
				l.finer("Downloading element webpages: " + (i + 1) + " of "
						+ var0);
				System.out.println("Downloading element webpages: " + (i + 1)
						+ " of " + var0);
				jTextArea1.append("Downloading element webpages: " + (i + 1)
						+ " of " + var0 + "\n");
				Download d1 = new Download(new URL(Util.getLinkFromRoot(
						d.theFile, i)), new File(
						System.getProperty("user.home")
								+ "/.kuroneko/tempfiles/" + "ep" + i + ".html"));
				d1.fileFromURL();
				
				boolean b = Util.checkIfEpisode(Util.getLinkFromRoot(d.theFile, i));
				String flags = "Flags: " + "\n    Link: " + Util.getLinkFromRoot(d.theFile, i) + "\n    Is Episode: " + Boolean.toString(b) + "\n    Episode Name: " + Util.getName(d.theFile, i, b) + "\n    Episode Link: " + Util.convertURL(Util.getDownloadLink(d1.theFile));
				System.out.println(flags);
				String s = Util.convertURL(Util.getDownloadLink(d1.theFile))
						+ "\n";
				if(b)
				{
					links0.add(s);
				}
				else
				{
					p++;
				}
			}
			System.out.println(links0.toString());
			long b = 0;
			System.out.println(var0 + " elements in table, " + (var0-p) + "are episodes.");
			System.out.println("Calculating size of all downloads");
			for (int a = 0; a < links0.size(); a++) {
				URL u = new URL(links0.get(a));
				HttpURLConnection conn = (HttpURLConnection) u.openConnection();
				b += conn.getContentLengthLong();
			}
			System.out.println("The download size is " + b + " bytes in size, or " + humanReadableBytes(b, true) + " in disk space.");
			String message = "This download will take "
					+ humanReadableBytes(b, true)
					+ " of disk space. Are you sure you want to continue?";
			int i = JOptionPane.showConfirmDialog(this, message, "Confirm",
					JOptionPane.YES_NO_OPTION);
			l.info("JOPTIONPANE Message: " + message);

			if (i == JOptionPane.YES_OPTION) {
				l.info("User chose YES_OPTION, continuing.");
				for (int m = 0; m < links0.size(); m++) {
					int k = m + 1;
					System.out.println("Starting download of episode " + k);
					l.fine("Starting download of episode " + k);
					jTextArea1.append("Starting download of episode " + k
							+ "\n");
					URL website = new URL(links0.get(m));
					HttpURLConnection conn = (HttpURLConnection) website
							.openConnection();
					int length = (int) conn.getContentLengthLong();
					long startTime = System.currentTimeMillis();
					Download d2 = new Download(website,
							new File(downloadDir.getText() + File.separator
									+ Util.getName(d.theFile, m, true) + ".mp4"));
					d2.fileFromURL();
					long endTime = System.currentTimeMillis();
					System.out.println(k + ": Finished");
					l.finest(k + ": Finished");
					jTextArea1.append(k + ": Finished" + "\n");
					System.out
							.println(k
									+ ": "
									+ length
									+ " bytes downloaded in "
									+ ((endTime - startTime))
									+ " milliseconds ("
									+ humanReadableBytes(length, true)
									+ " in approximately "
									+ ((endTime - startTime) / 60000)
									+ " minutes, average download speed "
									+ ((length / 125000) / ((double) ((endTime - startTime) / 1000)))
									+ " Mbps.)");
					l.finest(k
							+ ": "
							+ length
							+ " bytes downloaded in "
							+ ((endTime - startTime))
							+ " milliseconds ("
							+ humanReadableBytes(length, true)
							+ " in approximately "
							+ ((endTime - startTime) / 60000)
							+ " minutes, average download speed "
							+ ((length / 125000) / ((double) ((endTime - startTime) / 1000)))
							+ " Mbps.)");
					jTextArea1
							.append(k
									+ ": "
									+ length
									+ " bytes downloaded in "
									+ ((endTime - startTime))
									+ " milliseconds ("
									+ humanReadableBytes(length, true)
									+ " in approximately "
									+ ((endTime - startTime) / 60000)
									+ " minutes, average download speed "
									+ ((length / 125000) / ((double) ((endTime - startTime) / 1000)))
									+ " Mbps.) \n");
					System.out.println("Starting next download in 10 seconds.");
					l.fine("Starting next download in 10 seconds.");
					jTextArea1.append("Starting next download in 10 seconds.");
					Thread.sleep(10000);
				}
			} else {
				System.out.println("User canceled operation. Exiting \n");
				l.info("User canceled operation. Exiting");
				jTextArea1.append("User canceled option. Exiting \n");
				System.exit(0);
			}
		} catch (IOException e) {
			l.severe("FATAL ERROR! " + ExceptionUtils.getStackTrace(e));
			jTextArea1
					.append("An error has occurred. Please refer to the log files for more details.");
			e.printStackTrace();
		} catch (SecurityException e) {
			l.severe("FATAL ERROR! " + ExceptionUtils.getStackTrace(e));
			jTextArea1
					.append("An error has occurred. Please refer to the log files for more details.");
			e.printStackTrace();
		} catch (InterruptedException e) {
			l.severe("FATAL ERROR! " + ExceptionUtils.getStackTrace(e));
			jTextArea1
					.append("An error has occurred. Please refer to the log files for more details.");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("Welcome to KuroNeko version 1.2.2!");
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new KuroNeko().setVisible(true);
			}
		});
	}
}