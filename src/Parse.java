//import javax.script.*;

import java.util.Vector;

public class Parse {
	static private Parse parse;
	
	String textToParse;
	Vector<Action> actionList;
	
	private Parse(){
		actionList = new LoadAction().load("actions.csv");
	}
	
	static public Parse getInstance(){
		if (parse==null){
			parse = new Parse();
		}
		return parse;
	}

	public void execute(String cmd){
//		String[] launchCmd = {"open","-a",cmd};
		try{
			String[] launchCmd = cmd.split(" ");
//			String[] launchCmd = {cmd};
			Runtime.getRuntime().exec(launchCmd);
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("Unable to execute command "+cmd);
		}
	}
	public void executeApp(String cmd){
		try{
			String[] launchCmd = {cmd};
			Runtime.getRuntime().exec(launchCmd);
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("Unable to execute command "+cmd);
		}
	}
	public void executeWeb(String site,String browser){
		try{
//			String[] launchCmd = {cmd};
			if (browser==null){
				browser = "chrome";
			}
			String launchCmd = "cmd.exe /c start "+browser+" "+site;
			Runtime.getRuntime().exec(launchCmd);
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("Unable to open site "+site);
		}
	}
	
	public void parse(String cmd){
		String[] cmdArray = cmd.split(" ");
//		System.out.println(cmdArray[0]);
		if (cmdArray[0].equals("r")){
			//run
		}
		else if (cmdArray[0].equals("kill") && cmdArray.length>1){
			execute("taskkill /IM "+cmdArray[1]+".exe");
		}
		else if (cmdArray[0].equals("find")){
			//system search
		}
		else if(cmdArray[0]=="shutdown"){
			execute("shutdown /s");
			//find time
			//shutdown
		}
		else if(cmdArray[0]=="restart"){
			execute("shutdown /r");
			//reboot
		}
		else{
			for (int i=0;i<actionList.size();i++){
				if (actionList.get(i).run(cmd) == true){
					return;
				}
			}
			execute(cmd);
		}
		
		//match other set keywords
	}


	public static void main(String[] args) throws Exception {
		

		System.out.println("Launching!");

//		String launch = System.console().readLine("Enter your application: ");

		String launchCmd = "cmd.exe /c start chrome facebook.com";
		// String launchCmd = "/Applications/Firefox.app/Contents/MacOS/firefox";
		Runtime.getRuntime().exec(launchCmd);

			
	}

}