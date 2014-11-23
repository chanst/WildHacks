import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebScraper {

	private final static ImageIcon icon1 = new ImageIcon(
			"C:\\Users\\chanst1995\\Desktop\\code\\pics\\nic_cage1.jpg");
	private final static ImageIcon icon2 = new ImageIcon(
			"C:\\Users\\chanst1995\\Desktop\\code\\pics\\niccag3.jpg");

	public static String findingprice(String x) {
		x = x.trim();
		Scanner sc = new Scanner(x);
		String line;
		String price = "";
		if (x.contains("Newegg")) {
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				if (line.contains("product_sale_price")) {
					String priceun = line.substring(
							line.indexOf("product_sale_price") + 21,
							line.length());
					priceun = priceun.replace('.', ',');
					String[] splitted = priceun.split(",");
					for (int i = 0; i < 2; i++) {
						if (i == 1) {
							price += ".";
						}
						for (int j = 0; j < splitted[i].length(); j++) {
							if (Character.isDigit(splitted[i].charAt(j))) {
								price += splitted[i].charAt(j);
							}
						}
					}
					x = price;
				}

			}
		} else {
			x = "Invalid URL";
		}

		return x;
	}

	public static void handler() {
		boolean ender = false;
		Document doc;
		do {
			JOptionPane.showMessageDialog(null, "Welcome to PriceLookerUper!",
					"What is Life?", JOptionPane.INFORMATION_MESSAGE, icon1);
			try {
				String query1 = "Please enter a valid item URL from NewEgg";
				String url = "";
				url = JOptionPane.showInputDialog(null, query1, "Nic Cage",
						JOptionPane.QUESTION_MESSAGE);

				doc = Jsoup.connect(url).get();
				Element body = doc.body();
				String x = body.toString();
				String cost = findingprice(x);
				if (!cost.equals("Invalid URL")) {
					JOptionPane.showMessageDialog(null,
							"The price for " + doc.title() + " is $" + cost,
							"What is Life without Dodge?",
							JOptionPane.INFORMATION_MESSAGE);
					int response2 = JOptionPane.showConfirmDialog(null,
							"Would you like to perform another price lookup?",
							"Are you done?", JOptionPane.YES_NO_OPTION);
					if (response2 == 1) {
						ender = true;
					}
				}
				int response2 = JOptionPane.showConfirmDialog(null,
						"Would you like to perform another price lookup?",
						"Are you done?", JOptionPane.YES_NO_OPTION);
				if (response2 == 1) {
					ender = true;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERROR",
						"AN ERROR HAS OCCURRED!", JOptionPane.ERROR_MESSAGE);
				int response2 = JOptionPane.showConfirmDialog(null,
						"Would you like to perform another price lookup?",
						"Are you done?", JOptionPane.YES_NO_OPTION);
				if (response2 == 1) {
					ender = true;
				}

			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERROR",
						"AN ERROR HAS OCCURRED!", JOptionPane.ERROR_MESSAGE);
				int response2 = JOptionPane.showConfirmDialog(null,
						"Would you like to perform another price lookup?",
						"Are you done?", JOptionPane.YES_NO_OPTION);
				if (response2 == 1) {
					ender = true;
				}

			}
		} while (ender != true);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		handler();

	}
}
