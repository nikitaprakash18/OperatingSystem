import java.util.ArrayList;
import java.util.Collections;


public class SCHEDULER {

	private static final String String = null;
	static ArrayList<PROCESS_MANAGER> intermediate = new ArrayList<PROCESS_MANAGER>();
    /*replica of blockQ*/
    static ArrayList<PROCESS_MANAGER> readyForReadyQueue = new ArrayList<PROCESS_MANAGER>();
    /*elements are put into readyForReadyQ as soon as they complete the 20 units in blockQ*/
    static ArrayList<Integer> temp = new ArrayList<Integer>();
    
    public static void SCHEDULER() throws NumberFormatException, Exception{
		/*calling CPU from SCHEDULAR*/
		CPU.CPU(SYSTEM.StartAdr , String);
		
		}
		public static String BLOCK() throws NumberFormatException, Exception{
				
		/* from block list send back to ready list*/
		SYSTEM.readylist.push(SYSTEM.blockqueue.pop());
		SCHEDULER();
		return null;
			}
		
		static void update(PROCESS_MANAGER p) throws NumberFormatException, Exception {
			
			PROCESS_MANAGER blockedpcb = new PROCESS_MANAGER();
	        blockedpcb = p;
	        SYSTEM.blockqueue.add(blockedpcb);
	       
	        SYSTEM.readylist.add(SYSTEM.blockqueue.remove());
          
	        SCHEDULER();
	    }
		

		public static void checkForBlock() throws NumberFormatException, Exception {
			int sysClockAtBlock =0;
		        int index = 0;
		        for (int x = 0; x < intermediate.size(); x++) {
		            if (SYSTEM.CLOCK - intermediate.get(x).sysClockAtBlock >= 20) {
		                readyForReadyQueue.add(intermediate.get(x));
		                intermediate.remove(x);
		            }
		        }
		        for (int y = 0; y < readyForReadyQueue.size(); y++) {
		            int qr = readyForReadyQueue.get(y).quantumRemainder;
		            temp.add(y, qr);

		        }
		        if (!temp.isEmpty()) {
		            index = temp.indexOf(Collections.max(temp));
		            SYSTEM.readylist.add(readyForReadyQueue.get(index));
		            readyForReadyQueue.remove(index);
		            temp.remove(index);
		        }
		        SCHEDULER();
		    }

}