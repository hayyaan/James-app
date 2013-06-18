import java.lang.management.*;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class Activity implements Runnable{
	
	 private static Sigar sigar = new Sigar();

	    public static String getMemInfo() {

	        Mem mem = null;
	        try {
	            mem = sigar.getMem();
	        } catch (SigarException se) {
	            se.printStackTrace();
	        }
	            int me = (int)(mem.getUsed() *100 / mem.getTotal());
	            return Integer.toString(me);
	       
	    }
	    
	    public static String getCPUInfo(){
	    	CpuPerc cpu = null;
	    	try{
	    		cpu = sigar.getCpuPerc();	    		
	    	} catch (SigarException se){
	    		se.printStackTrace();
	    	}
	    	
	    	int cp = (int) cpu.getCombined()*100;
	    	return Integer.toString(cp);
	    }
	
	
	
	
	
	public static void main(String[] args){
		getMemInfo();
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try{
				Thread.sleep(1000);
			} catch (Exception e){
				e.printStackTrace();
			}
			Interact.g.cpu.setText("CPU: "+getCPUInfo()+" %                   							  MEM Usage: "+getMemInfo()+" %");
//			Interact.g.mem.setText("MEM Usage: "+getMemInfo());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
