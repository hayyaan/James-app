import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.util.Vector;

public class LoadAction {
	public LoadAction(){}
	
	public Vector<Action> load(String fileName){
		Vector<Action> actions = new Vector<Action>();
		
		try{
			CSVReader reader = new CSVReader(new FileReader(fileName));
			String[] header;
			while( (header= reader.readNext())!=null){
				Action act = null;
//				System.out.println(header[2]);
				if(header[0].equals("app")){
					act = new Action(header[1],header[2],1);
				}
				else if(header[0].equals("site")){
					act = new Action(header[1],header[2],2);
				}
				else if(header[0].equals("search")){
					act = new Action(header[1],header[2],3);
				}
				actions.add(act);
				
			}
			reader.close();
		} catch(Exception e){
			System.out.println("Unable to load config file");
		}
		
		
		return actions;
	}
	
	public static void main(String[]args){
		try {
		CSVReader reader = new CSVReader(new FileReader("bin/config.csv"));
		
			for (int i=0;i<3;i++){
				String[] header = reader.readNext();
				System.out.println(header[0]);
				System.out.println(header[1]);
				System.out.println(header[2]);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
