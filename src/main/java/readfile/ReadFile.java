package readfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {
	static String nameFile = "testTopo.txt";
	
	public static ArrayList<LinkCost> getLinkCostsFromFile() {
		
		ArrayList<LinkCost> linkCosts = new ArrayList<LinkCost>();
		int min_bw = 0;
		int max_delay = 0;
		int max_loss = 0;
		int costs = 0;
		int tmp = 0;
		try {
			File file = new File(nameFile);
			
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String line;
			
			line = bufferedReader.readLine();
			line = bufferedReader.readLine();
			
			while ((line = bufferedReader.readLine()) != null) {
				String[] content = line.split(" ");
				if(content[0].startsWith("h") == false && content[1].startsWith("h") == false) {
					//Get Bandwidth
					String bw = content[2].replace("bw=", "");
					int bandwidth = Integer.parseInt(bw);
					if (tmp == 0){
						min_bw =  bandwidth;
						tmp = 1;
					}
					if (bandwidth < min_bw) {
						min_bw = bandwidth;
					}
					//Get Delay
					String dl = content[3].replace("delay=", "").replace("ms", "");
					int delay = Integer.parseInt(dl);
					if(delay > max_delay) {
						max_delay = delay;
					}
					//Get Loss	
					String lost = content[4].replace("loss=", "");
					int loss = Integer.parseInt(lost);
					if (loss > max_loss) {
						max_loss = loss;
					}
					
					LinkCost lt = new LinkCost(content[0], content[1], bandwidth, delay, loss);
					
					linkCosts.add(lt);
				}
			}
			
			fileReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (LinkCost ls: linkCosts ) {
			costs = (10000*min_bw*30)/(ls.getBw()*100) + (10000*20)/100 + (ls.getLoss()*10000*50)/(max_loss*100);
			ls.setCosts(costs);
		}
		
		return linkCosts;
	}
	
	public static ArrayList<ListHost> getListHostsFromFile() {
		
		ArrayList<ListHost> listHosts = new ArrayList<ListHost>();
		
		try {
			File file = new File(nameFile);
			
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String line;
			
			line = bufferedReader.readLine();
			line = bufferedReader.readLine();
			
			while ((line = bufferedReader.readLine()) != null) {
				String[] content = line.split(" ");
				if(content[0].startsWith("h") == true) {
					ListHost lhost = new ListHost(content[0].substring(1), content[1]);
					listHosts.add(lhost);
				} else if(content[1].startsWith("h") == true) {
					ListHost lhost = new ListHost(content[1].substring(1), content[0]);
					listHosts.add(lhost);
				}
			}
			
			fileReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return listHosts;
	}
}
