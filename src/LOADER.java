import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

/* Fetching the data from the disk and assigning to memory
 * based on the page, frame and also replacement algorithm
 * and also creating PMT table
 */
public class LOADER extends SYSTEM {
	
public static ArrayList<Integer> page_fault_no = new ArrayList<Integer>(150);
public static double page_no = 0; 
public static int presentword_no = 0;
public static int index = 0;
public static int frame_index = 0;
public static int frame_No_frame_index = 0;
public static int unused_words = 0;
//public static Queue<PGM_PMT> SYS_PMT = new LinkedList<PGM_PMT>();
public static PGM_PMT dummy_pmt;
public static int segindex =0;
public static int frame_number_index = 0;
public static int pc_index = 0;
public static int ready_index = 0;
public static int page_fault_number = 0;
public static String LOADER(String StrAddr, String Trace) throws NumberFormatException, Exception{
	
	PROCESS_MANAGER PCB = new PROCESS_MANAGER();
dummyPCB = readylist.pop();
int x = (dummyPCB.orignal_PC + dummyPCB.pgm_index);
page_no = Math.ceil(x/8);
presentword_no = dummyPCB.orignal_PC% 8;
//page_no = Math.ceil(CPU.orignalpc/8);
//presentword_no = CPU.orignalpc % 8;

Disk_Memory_Transfer(page_no, presentword_no);
return null;	
}

/*frame number of the memory*/
public static void frame_number(){
		
	if(MEMORY.frame[0]== 0){
		frame_No_frame_index = 0;	
		frame_number_index = frame_number_index + 1;
	}
	else if(MEMORY.frame[1]== 0){
		frame_No_frame_index = 1;
		frame_number_index = frame_number_index + 1;
	}
	else if(MEMORY.frame[2]== 0){
		frame_No_frame_index = 2;
		frame_number_index = frame_number_index + 1;
	}
    else if(MEMORY.frame[3]== 0){
    	frame_No_frame_index = 3;
    	frame_number_index = frame_number_index + 1;
	}
    else if(MEMORY.frame[4]== 0){
    	frame_No_frame_index = 4;
    	frame_number_index = frame_number_index + 1;
	}
   else if(MEMORY.frame[5]== 0){
	   frame_No_frame_index = 5;
		frame_number_index = frame_number_index + 1;
	}
	else if(MEMORY.frame[6]== 0){
		frame_No_frame_index = 6;
		frame_number_index = frame_number_index + 1;
	}
    else if(MEMORY.frame[7]== 0){
    	frame_No_frame_index = 7;
    	frame_number_index = frame_number_index + 1;
	}
    else if(MEMORY.frame[8]== 0){
    	frame_No_frame_index = 8;
    	frame_number_index = frame_number_index + 1;
	}
    else if(MEMORY.frame[9]== 0){
    	frame_No_frame_index = 9;
    	frame_number_index = frame_number_index + 1;
	}
	else if(MEMORY.frame[10]== 0){
		frame_No_frame_index = 10;
		frame_number_index = frame_number_index + 1;
	}
    else if(MEMORY.frame[11]== 0){
    	frame_No_frame_index = 11;
    	frame_number_index = frame_number_index + 1;
	}
    else if(MEMORY.frame[12]== 0){
    	frame_No_frame_index = 12;
    	frame_number_index = frame_number_index + 1;
	}
    else if(MEMORY.frame[13]== 0){
    	frame_No_frame_index = 13;
    	frame_number_index = frame_number_index + 1;
	}
	else if(MEMORY.frame[14]== 0){
		frame_No_frame_index = 14;
		frame_number_index = frame_number_index + 1;
	}
    else if(MEMORY.frame[15]== 0){
    	frame_No_frame_index = 15;
    	frame_number_index = frame_number_index + 1;
	}
    else if(MEMORY.frame[16]== 0){
    	frame_No_frame_index = 16;
    	frame_number_index = frame_number_index + 1;
	}
    else if(MEMORY.frame[17]== 0){
    	frame_No_frame_index = 17;
    	frame_number_index = frame_number_index + 1;
	}
	else if(MEMORY.frame[18]== 0){
		frame_No_frame_index = 18;
		frame_number_index = frame_number_index + 1;
	}
    else if(MEMORY.frame[19]== 0){
    	frame_No_frame_index = 19;
    	frame_number_index = frame_number_index + 1;
	}
    else if(MEMORY.frame[20]== 0){
    	frame_No_frame_index = 20;
    	frame_number_index = frame_number_index + 1;
	}
    else if(MEMORY.frame[21]== 0){
    	frame_No_frame_index = 21;
    	frame_number_index = frame_number_index + 1;
	}
	else if(MEMORY.frame[22]== 0){
		frame_No_frame_index = 22;
		frame_number_index = frame_number_index + 1;
	}
    else if(MEMORY.frame[23]== 0){
    	frame_No_frame_index = 23;
    	frame_number_index = frame_number_index + 1;
	}
    else if(MEMORY.frame[24]== 0){
    	frame_No_frame_index = 24;
    	frame_number_index = frame_number_index + 1;
	}
    else if(MEMORY.frame[25]== 0){
    	frame_No_frame_index = 25;
    	frame_number_index = frame_number_index + 1;
	}
	else if(MEMORY.frame[26]== 0){
		frame_No_frame_index = 26;
		frame_number_index = frame_number_index + 1;
	}
    else if(MEMORY.frame[27]== 0){
    	frame_No_frame_index = 27;
    	frame_number_index = frame_number_index + 1;
	}
    else if(MEMORY.frame[28]== 0){
    	frame_No_frame_index = 28;
    	frame_number_index = frame_number_index + 1;
	}
    else if(MEMORY.frame[29]== 0){
	frame_No_frame_index = 29;
	frame_number_index = frame_number_index + 1;
	}
	else if(MEMORY.frame[30]== 0){
		frame_No_frame_index = 30;
		frame_number_index = frame_number_index + 1;
	}
    else if(MEMORY.frame[31]== 0){
    	frame_No_frame_index = 31;
    	frame_number_index = frame_number_index + 1;
	}
	frame_index(frame_No_frame_index);
	if(frame_number_index == 31){
		for(int i = 0; i<32; i++){
			MEMORY.frame[i]= 0;
		}
		
	}	
}
/*Transfer data into memory to disk*/

public static void Memory_to_Disk_Transfer(double page_no)throws NumberFormatException, Exception{
	
	page_fault_number++;
	int f = PGM_PMT.Frame_no.get((int) page_no);
	Double p = PGM_PMT.Page_no.get((int) page_no);
	String x = null;
	String y = null;
	int f1 = f*8;
	int frame = f*8;
	int page = (int) (p *8);
	x = Integer.toBinaryString(frame);
	while(x.length() < 16){
		x = "0"+ x;
	}
	for(int m =frame; m<f1+8; m++){
		
		DISK_MANAGER.DISK[page] = MEMORY.MEMORY("Read",x, y);
	    page++;
	    frame++;
	    x = Integer.toBinaryString(frame);
		while(x.length() < 16){
			x = "0"+ x;
		}
	}
}

/*Transfer input from disk to memory*/
public static void Disk_Memory_Transfer(double page_no2, int presentword_no2) throws NumberFormatException, Exception {
	
	page_index(page_no2);
	frame_number();
	SYSTEM.page_fault_time = SYSTEM.page_fault_time + 20;
	int m = index;
	for(int i=index; i< m+8; i++){
		SYSTEM.Buffer.add(DISK_MANAGER.DISK[index]);
		index++;
	}
	
	/*Transferring buffer array values to Memmory array*/
	for(int x =0; x<SYSTEM.Buffer.size(); x++){
		int y = frame_index;
	                              
	MEMORY.MEMORY("Write", java.lang.String.valueOf(x+y),  (String) SYSTEM.Buffer.get(x));
	
	}
	MEMORY.frame[frame_No_frame_index]=1;
	DISK_MANAGER.page[(int) page_no2]=1;
	
	MEMORY.reference_bit = 0;
	MEMORY.dirty_bit = 0;
	PGM_PMT PMT = new PGM_PMT();
	PMT.Page_no.set((int) page_no2, page_no2);
	PMT.Frame_no .set((int) page_no2, frame_No_frame_index);
	PMT.reference_bit.set((int) page_no2,MEMORY.reference_bit);
	PMT.dirty_bit.set((int) page_no2,MEMORY.dirty_bit );

	//SYS_PMT.add(PMT);
	int presentpc = presentword_no2 + (frame_No_frame_index * 8);
	if(!readylist.isEmpty()){
	//dummyPCB = readylist.poll();
	}
	String d = Integer.toBinaryString(presentpc);
	dummyPCB.Pgm_Counter = d;
	while(dummyPCB.Pgm_Counter.length() <7){
	dummyPCB.Pgm_Counter= "0" + dummyPCB.Pgm_Counter;
	}
	if(CPU.effIndex == 0){
	SYSTEM.PC = dummyPCB.Pgm_Counter;
	}
	SYSTEM.Buffer.clear();
	
	if(ready_index == 0 || ready_index == 1 || ready_index == 2 || ready_index == 3){
		
	readylist.push(dummyPCB);
	ready_index ++;
	
	}
	
	if(seg_jobid %4 == 0 && ready_index == 4 || seg_jobid %16 == 0 && DISK_MANAGER.segid == 1 ){
		ready_index++;
	SCHEDULER.SCHEDULER();
	}
	/*if(ready_index == 0 ){
		
		readylist.push(dummyPCB);
		ready_index ++;
		//SCHEDULER.SCHEDULER();
		}
	if(seg_jobid %1 == 0 && ready_index == 1){
	ready_index++;
SCHEDULER.SCHEDULER();
}*/
	if(seg_jobid == 105 && ready_index == 1  || seg_jobid == 108){
		ready_index++;
		SCHEDULER.SCHEDULER();
	}
}
public static String Memory_Utilization(){
	
Memory_utilization_percentage = 0;
Memory_utilization_ratio = 0;
Memory_utilization_frame_per = 0;
Memory_utilization_frame_ratio = 0;
double no_of_frames = (double)MemoryArray.length / (double)8;
Memory_utilization_frame_ratio = (double)6 / no_of_frames; 
Memory_utilization_frame_per = Memory_utilization_frame_ratio * 100;
Memory_utilization_ratio = (double)(SYSTEM.size_InputData_segment+ Integer.parseInt(SYSTEM.Size_of_Program,2)+ SYSTEM.size_OutputData_segment) / (double)MemoryArray.length;
Memory_utilization_percentage = Memory_utilization_ratio * 100;

return null;
}
	
public static String Disk_Fragmentation(){
double x = 8 - SYSTEM.size_InputData_segment;
double y = 8 - SYSTEM.size_OutputData_segment;
double z = 8 - (Integer.parseInt(SYSTEM.Size_of_Program,2) % 8 );
Disk_fragmentation = (x + y + z)/ (double)3;

Memory_fragmentation = unused_words ;
if(MEMORY.frame17 == 1){
	Memory_fragmentation = x+y+z;
}
return null;
}

/*Page fault handler*/
public static String Page_Fault_Handler()throws NumberFormatException, Exception{
	
	dummyPCB.page_fault_no++;
	SYSTEM.page_fault_time = SYSTEM.page_fault_time + 10;
	page_no = CPU.eff_pageno;
	presentword_no = Integer.parseInt(PC, 2)%8;
	page_fault_no.set((int) dummyPCB.JobId, dummyPCB.page_fault_no);   
	Disk_Memory_Transfer(page_no, presentword_no);
	
	return null;
}
/*get page index of the disk*/
public static double page_index(double page_no2) {
	index = (int) (page_no2 * 8);
return index;
	
}
/*get frame index of the memory*/
public static double frame_index(double page_no2) {

	frame_index = frame_No_frame_index * 8;
return frame_index;
	
}
/*Segment fault handler*/
public static String Segment_Fault_Handler() throws NumberFormatException, Exception{
	
	frame_number();
	
	if(segindex < 2){
SYSTEM.Segment_fault_time = SYSTEM.Segment_fault_time + 5;
	}
	segindex++;
double seg_pag = Integer.parseInt(SYSTEM.Size_of_Program , 2);
seg_pag = Math.round(dummyPCB.in_pgmSize / (double)8);
double seg_out = seg_pag + 1;
page_index(seg_pag);
SYSTEM.page_fault_time = SYSTEM.page_fault_time + 10;
int m = index;
for(int i=index; i< m+8; i++){
	SYSTEM.Buffer.add(DISK_MANAGER.DISK[index]);
	index++;
}

/*Transferring buffer array values to Memmory array*/
for(int x =0; x<SYSTEM.Buffer.size(); x++){
	int y = frame_index;
                              
MEMORY.MEMORY("Write", java.lang.String.valueOf(x+y),  (String) SYSTEM.Buffer.get(x));

}

MEMORY.frame[frame_No_frame_index]=1;
DISK_MANAGER.page[(int) seg_pag]=1;
CPU.EA = Integer.toBinaryString(frame_index);
dummyPCB.RD_Y_start = CPU.EA;
while(CPU.EA.length()< 16){
	CPU.EA = "0" + CPU.EA;
}
SYSTEM.Buffer.clear();
MEMORY.reference_bit = 0;
MEMORY.dirty_bit = 0;
PGM_PMT PMT = new PGM_PMT();
PMT.Page_no.set((int) seg_pag, seg_pag);
PMT.Frame_no.set((int) seg_pag, frame_No_frame_index);
PMT.reference_bit.set((int) seg_pag, MEMORY.reference_bit);
PMT.dirty_bit.set((int) seg_pag,MEMORY.dirty_bit);
int presentpc = presentword_no + (MEMORY.frame_no * 8);
String d = Integer.toBinaryString(presentpc);
dummyPCB.Pgm_Counter = d;
while(dummyPCB.Pgm_Counter.length() <7){
dummyPCB.Pgm_Counter = "0" + dummyPCB.Pgm_Counter;
}

return null;
}
}

class PGM_PMT
{
/* global variables of the class*/
public static ArrayList<Double> Page_no = new ArrayList<Double>(32);
public static ArrayList<Integer> Frame_no = new ArrayList<Integer>(32);
public static ArrayList<Integer> reference_bit = new ArrayList<Integer>(32);
public static ArrayList<Integer> dirty_bit = new ArrayList<Integer>(32);

public static void initialize(){
	for(int x = 0; x < 1000; x++){
		double y = 0;
		PGM_PMT.Page_no.add(y);
		PGM_PMT.Frame_no.add((int) y);
		PGM_PMT.reference_bit.add((int) y);
		PGM_PMT.dirty_bit.add((int) y);
		LOADER.page_fault_no.add((int) y);
		CPU.avg_cpu_array.add((int)y);
		CPU.avg_TUT_array.add((int)y);
		CPU.avg_code_seg.add((int)y);
		CPU.avg_input_code_seg.add((int)y);
		CPU.avg_output_code_seg.add((int)y);
	}
}
 public static void printPMT() throws IOException{
	 
 PrintWriter pw = null;
 //CPU.Output(pw);
 SYSTEM.PMTclock = 0;
}
}