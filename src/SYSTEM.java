/* Name: Nikita Prakash Patil
 * course Number: CS5323
 * Assignment Title: OS Project Phase3
 * Date: 05/01/2018
 * */
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/*Buffer is an array used to store 16 bit binary values
 * MemoryArray is array used to store the binary  
 * values transfered from buffer with size 256
 * CLOCK and IOCLOCK are Execution time and IO time
 * StartAdr is the start address of the program
 * Trace is the trace switch 
 * IR is the Instruction Register
 * JobID is the job identification number*/

public class SYSTEM {
	
public static ArrayList Buffer = new ArrayList();
public static String[] MemoryArray = new String[256]; 
public static String[] STACK = {"0000000000000000", "00000000000000000",
"0000000000000000", "0000000000000000", "0000000000000000",
"0000000000000000", "0000000000000000"};
public static String String;
public static String StartAdr = "0";
public static String Trace = "0";
public static String PC = null;
public static String IR = null;
public static String Top_of_Stack = "000";
public static String input ;
public static int CLOCK =0;
public static int IOCLOCK = 0;
public static String JobId = null;
public static String Load_address = null;
public static String Size_of_Program = null;
public static String warning = null;
public static int size_InputData_segment = 0;
public static int size_OutputData_segment = 0;
public static int seg_jobid = 0;
public static String inpt ;
public static String in ;
public static final String FILE_NAME = "C:/Users/Nikita/Desktop/c0.txt";
public static LinkedList<PROCESS_MANAGER> readylist = new LinkedList<PROCESS_MANAGER>();
public static LinkedList<PROCESS_MANAGER>blockqueue =new LinkedList<PROCESS_MANAGER>();
public static PROCESS_MANAGER dummyPCB;
public static int[] pageindex = new int[256];
public static int Segment_fault_time = 0;
public static int page_fault_time = 0;
public static double Disk_utilization_percentage = 0;
public static double Disk_utilization_ratio = 0;
public static double Disk_utilization_frame_per = 0;
public static double Disk_utilization_frame_ratio = 0;
public static double Memory_utilization_percentage = 0;
public static double Memory_utilization_ratio = 0;
public static double Memory_utilization_frame_per = 0;
public static double Memory_utilization_frame_ratio = 0;
public static double Disk_fragmentation = 0;
public static double Memory_fragmentation = 0;
public static String InputHex = null;
public static String InputHex1 = null;
public static String InputHex2 = null;
public static String InputHex3 = null;
public static int PMTclock = 0;
public static int timeinMills = 0;
public static int timeinMills1 = 0;
public static int job_time = 0;

public static String SYSTEM(String Keyboard) throws  Exception{
	
	LOADER.Memory_Utilization();
	LOADER.Disk_Fragmentation();
	return Keyboard;
		
}

public static void main(String args[]) throws IOException, Exception
{	 
/*FILE_NAME = args[0];*/
	timeinMills=	(int) System.currentTimeMillis();
	MEMORY.reinitialize();
	PGM_PMT.initialize();
DISK_MANAGER.DISK();

LOADER.LOADER(StartAdr, Trace);
CPU.CPU(StartAdr , String);
}
}

