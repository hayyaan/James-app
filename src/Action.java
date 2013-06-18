
public class Action {
	String keyword;
	String action;
	int type; 
	//1 application
	//2 site
	//3 site search
	
	public Action(String _key, String _act,int _type){
		keyword = _key;
		action = _act;
		type = _type;
	}
	
	public boolean run(String key){
		String[] keyArray = key.split(" ");
		if (keyArray[0].equals(keyword)){
			if (type == 1){ //application
				if (keyArray.length>=2){
					Parse.getInstance().executeWeb(keyArray[1],keyword);
					return true;
				}
				Parse.getInstance().executeApp(action);
				return true;
			}
			else if (type == 2){ //site
				Parse.getInstance().executeWeb(action,null);
				return true;
			}
			else if (type == 3){//site search
				keyArray[0]="";
				String search = "";
				for (int i=1;i<keyArray.length;i++){
					if (search.equals("")){
						search=keyArray[i];
					}
					else{
						search=search+"+"+keyArray[i];
					}
				}
				System.out.println(search);
				String run = action.replace("[term]",search);
				System.out.println(run);
				Parse.getInstance().executeWeb(run,null);
				return true;
			}
		}
		return false;
	}
}
