import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class ERROR_HANDLER extends SYSTEM{
public static String str= null;
public static int clock;
public static int TOS;
public static String error = null;
public static File f = null;
public static int inn = 0;
public static BufferedWriter bw = null;
public static FileWriter fw = null;
public static int error_index=0;
 /*ERROR_HANDLER function handles errors, warnings and also write the error
  * into output file along with JobID, execution time, IO time 
  */
public static int ERROR_HANDLER(int N) throws NumberFormatException, Exception{
	
	error_index++;
	
if(inn == 0){
	
	f = new File("Output_Errorfile.txt");
	if(f.exists()){
		f.delete();
	}
	f.createNewFile();
	inn++;
}
String var = null;
int var2 = 0, x2 = 0, x1 =0;
clock = CLOCK+IOCLOCK;
str = Integer.toHexString(clock);
if(SYSTEM.Top_of_Stack.length() > 16){
String x = SYSTEM.Top_of_Stack.substring(16, 32);
TOS = Integer.parseInt(x, 2);
}
else{
TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
}
fw = new FileWriter(f.getAbsoluteFile(), true);
bw = new BufferedWriter(fw);

switch(N){
/*Invalid file format*/
case 0:
error = "Invalid Loader format";

bw.write("JobID:  "+dummyPCB.JobId+"   (DECIMAL)\n");
bw.write("Error: "+error+"\n");
if(warning == null){
}else{
	 bw.write("Warning MESSAGE: "+warning);
}

if(DISK_MANAGER.segid == 0){
 x2 = Integer.parseInt(dummyPCB.RD_Y_end, 2);
x1 = Integer.parseInt(dummyPCB.RD_Y_start, 2);
}
else{
	x2 =0;
	x1 = 0;
}
bw.write("Input-Data Segment: "+"JOBID: "+("0"+"0"+"0"+dummyPCB.JobId)+"   (HEX)\n");
for(int x =x1; x<x2; x++ ){
CPU.EA = Integer.toBinaryString(x);
while (CPU.EA.length() < 16)
{
CPU.EA = "0" + CPU.EA;
   }
 var = MEMORY.MEMORY("Read", CPU.EA, var);
x++;
var2 = Integer.parseInt(var, 2);
var = Integer.toHexString(var2);
while(var.length() < 4){
	var = "0" + var;
}
bw.write(var+"   ");
	
}
bw.write("(HEX)\n");
bw.write("Output-Data Segment: "+"JOBID: "+("0"+"0"+"0"+dummyPCB.JobId)+"   (HEX)\n");
bw.write("                     "+CPU.out+"    (BIN)\n");
bw.write("TERMINATION:  ABNORMAL\n");
clock = clock + CPU.CPU_exe_time + IOCLOCK + Segment_fault_time + page_fault_time ;
String str = Integer.toHexString(clock);
String str1 = Integer.toHexString(dummyPCB.job_endtime);
String str2 = Integer.toHexString(dummyPCB.jobTime);
bw.write("JOB START time:  "+str2+"  (HEX)\n");
bw.write("JOB END time:  "+str1+"  (HEX)\n");
int f = LOADER.page_fault_no.get(dummyPCB.JobId);
bw.write("NUMBER of PAGE FAULTS:  "+f+"   (DECIMAL)");
bw.write("CLOCK:  "+str+"  (HEX)\n");
bw.write("EXECUTION TIME:  "+CPU.CPU_exe_time+"  (DECIMAL)\n");
bw.write("IO TIME:  "+IOCLOCK+"  (DECIMAL)\n");
bw.write("Page-fault handling time: "+(page_fault_time)+" (DECIMAL)\n");
bw.write("Segment-fault handling time: "+Segment_fault_time+" (DECIMAL)\n");
bw.write("Turn-around time:  "+(dummyPCB.job_endtime - dummyPCB.jobTime)+"  (DECIMAL)\n");
bw.write("\n");
bw.write("\n");
bw.write("---------------------------------------------------------------------------------------------");
bw.close();
fw.close();

/*DISK_MANAGER.oldflag = 1427;*/
CPU.CPU(SYSTEM.StartAdr , String);
break;
/*Invalid JobId format*/
case 1:
error = "Invalid JobId format or value";
output(error);

break;
/*Invalid Load Address format*/
case 2:
error = "Invalid Load Address format or value";
output(error);
 break;
/*Invalid Program Counter format*/
case 3:
error = "Invalid Program Counter format or value";
output(error);
 break;
/*Invalid Size of Program  format*/
case 4:
error = "Invalid Size of Program format or value";
output(error);
 
break;
/*Trace bit should be 1 or 0*/
case 5:
error = "Invalid file format.. Trace bit should be 0 or 1";
output(error);

break;
/*loader format should be hex*/
case 6: 
error = "Loader format should be in HEX";

bw.write("JobID:  "+dummyPCB.JobId+"   (DECIMAL)\n");
bw.write("Error: "+error+"\n");
if(warning == null){
}else{
	 bw.write("Warning MESSAGE: "+warning);
}

if(DISK_MANAGER.segid == 0){
 x2 = Integer.parseInt(dummyPCB.RD_Y_end, 2);
x1 = Integer.parseInt(dummyPCB.RD_Y_start, 2);
}
else{
	x2 =0;
	x1 = 0;
}
bw.write("Input-Data Segment: "+"JOBID: "+("0"+"0"+"0"+dummyPCB.JobId)+"   (HEX)\n");
for(int x =x1; x<x2; x++ ){
CPU.EA = Integer.toBinaryString(x);
while (CPU.EA.length() < 16)
{
CPU.EA = "0" + CPU.EA;
   }
 var = MEMORY.MEMORY("Read", CPU.EA, var);
x++;
var2 = Integer.parseInt(var, 2);
var = Integer.toHexString(var2);
while(var.length() < 4){
	var = "0" + var;
}
bw.write(var+"   ");
	
}
bw.write("(HEX)\n");
bw.write("Output-Data Segment: "+"JOBID: "+("0"+"0"+"0"+dummyPCB.JobId)+"   (HEX)\n");
bw.write("                     "+CPU.out+"    (BIN)\n");
bw.write("TERMINATION:  ABNORMAL\n");
clock = clock + CPU.CPU_exe_time + IOCLOCK + Segment_fault_time + page_fault_time ;
str = Integer.toHexString(clock);
str1 = Integer.toHexString(dummyPCB.job_endtime);
str2 = Integer.toHexString(dummyPCB.jobTime);
bw.write("JOB START time:  "+str2+"  (HEX)\n");
bw.write("JOB END time:  "+str1+"  (HEX)\n");
f = LOADER.page_fault_no.get(dummyPCB.JobId);
bw.write("NUMBER of PAGE FAULTS:  "+f+"   (DECIMAL)");
bw.write("CLOCK:  "+str+"  (HEX)\n");
bw.write("EXECUTION TIME:  "+CPU.CPU_exe_time+"  (DECIMAL)\n");
bw.write("IO TIME:  "+IOCLOCK+"  (DECIMAL)\n");
bw.write("Page-fault handling time: "+(page_fault_time)+" (DECIMAL)\n");
bw.write("Segment-fault handling time: "+Segment_fault_time+" (DECIMAL)\n");
bw.write("Turn-around time:  "+(dummyPCB.job_endtime - dummyPCB.jobTime)+"  (DECIMAL)\n");
bw.write("\n");
bw.write("\n");
bw.write("---------------------------------------------------------------------------------------------");
bw.close();
fw.close();
int x = Integer.parseInt(SYSTEM.Size_of_Program,2);
DISK_MANAGER.oldflag = 46;
x = x/4;
/*DISK_MANAGER.oldflag = DISK_MANAGER.c1+ x + SYSTEM.size_InputData_segment + 2;
DISK_MANAGER.DISK();*/
CPU.CPU(SYSTEM.StartAdr , String);
	
break; 
/* invalid Top of Stack value*/
case 7:
error = "invalid Top of Stack value";
output(error);
 	
break;
/*Address is out of range*/
case 8:
error = "address is out of range";
output(error);
 
break;
/*infinite loop*/
case 9:
error = "Infinite loop";
System.out.println("job id: "+dummyPCB.JobId);
System.out.println(" Infinite loop");
output(error);
	
break;
/*Top of the stack value should be positive*/
case 10:
error = "Top of the stack value should be positive";
output(error);
 
break;
/*overflow*/
case 11:
error = "overflow";
output(error);
break;
/*underflow*/
case 12:
error = "underflow";
output(error);

break;
/*Maximum number of registers in stack cannot be more than 7*/
case 13:
error = "Maximum number of registers in stack cannot be more than 7";
output(error);

break;
/*More than one **INPUT*/
case 14:
error = "More than one **INPUT";
output(error);

break;
/*Missing **JOB*/
case 15:
error = "Missing **JOB";
output(error);

break;
/*Missing Loader format*/
case 16:

error = "Missing Loader format";
output(error);

break;
/*Missing **INPUT*/
case 17:
error = "Missing **INPUT";

bw.write("JobID:  "+dummyPCB.JobId+"   (DECIMAL)\n");
bw.write("Error: "+error+"\n");
if(warning == null){
}else{
	 bw.write("Warning MESSAGE: "+warning);
}
if(DISK_MANAGER.segid == 0){
 x2 = Integer.parseInt(dummyPCB.RD_Y_end, 2);
x1 = Integer.parseInt(dummyPCB.RD_Y_start, 2);
}
else{
	x2 =0;
	x1 = 0;
}
bw.write("Input-Data Segment: "+"JOBID: "+("0"+"0"+"0"+dummyPCB.JobId)+"   (HEX)\n");
for(int x3 =x1; x3<x2; x3++ ){
CPU.EA = Integer.toBinaryString(x3);
while (CPU.EA.length() < 16)
{
CPU.EA = "0" + CPU.EA;
   }
 var = MEMORY.MEMORY("Read", CPU.EA, var);
x3++;
var2 = Integer.parseInt(var, 2);
var = Integer.toHexString(var2);
while(var.length() < 4){
	var = "0" + var;
}
bw.write(var+"   ");
	
}
bw.write("(HEX)\n");
bw.write("Output-Data Segment: "+"JOBID: "+("0"+"0"+"0"+dummyPCB.JobId)+"   (HEX)\n");
bw.write("                     "+CPU.out+"    (BIN)\n");
bw.write("TERMINATION:  ABNORMAL\n");
clock = clock + CPU.CPU_exe_time + IOCLOCK + Segment_fault_time + page_fault_time ;
str = Integer.toHexString(clock);
str1 = Integer.toHexString(dummyPCB.job_endtime);
str2 = Integer.toHexString(dummyPCB.jobTime);
bw.write("JOB START time:  "+str2+"  (HEX)\n");
bw.write("JOB END time:  "+str1+"  (HEX)\n");
f = LOADER.page_fault_no.get(dummyPCB.JobId);
bw.write("NUMBER of PAGE FAULTS:  "+f+"   (DECIMAL)");
bw.write("CLOCK:  "+str+"  (HEX)\n");
bw.write("EXECUTION TIME:  "+CPU.CPU_exe_time+"  (DECIMAL)\n");
bw.write("IO TIME:  "+IOCLOCK+"  (DECIMAL)\n");
bw.write("Page-fault handling time: "+(page_fault_time)+" (DECIMAL)\n");
bw.write("Segment-fault handling time: "+Segment_fault_time+" (DECIMAL)\n");
bw.write("Turn-around time:  "+(dummyPCB.job_endtime - dummyPCB.jobTime)+"  (DECIMAL)\n");
bw.write("\n");
bw.write("\n");
bw.write("---------------------------------------------------------------------------------------------");
bw.close();
fw.close();

/*int x1 = Integer.parseInt(SYSTEM.Size_of_Program,2);
x1 = x1/4;
DISK_MANAGER.oldflag = x1 + SYSTEM.size_InputData_segment + 2;*/
CPU.CPU(SYSTEM.StartAdr , String);

break;
/*conflict between the number of input words specified in the **JOB line and the number of input items given in the INPUT section*/
case 18:
error = "conflict between the number of input words specified in the **JOB line and the number of input items given in the INPUT section";
output(error);

break;
/*Missing **FIN*/
case 19:
error = "Missing **FIN";
output(error);

break;
/*writing beyond the end of file*/
case 20:
error = "writing beyond the end of file";
output(error);

break;
/*Reading beyond the end of file*/
case 21:
	error = "Reading beyond the end of file";
	output(error);
	break;
/*missing job id, load address, program counter and size of the program*/
case 22: 
error = "missing job id, load address, program counter and size of the program";

bw.write("JobID:  "+dummyPCB.JobId+"   (DECIMAL)\n");
bw.write("Error: "+error+"\n");
if(warning == null){
}else{
	 bw.write("Warning MESSAGE: "+warning);
}
if(DISK_MANAGER.segid == 0){
 x2 = Integer.parseInt(dummyPCB.RD_Y_end, 2);
x1 = Integer.parseInt(dummyPCB.RD_Y_start, 2);
}
else{
	x2 =0;
	x1 = 0;
}

bw.write("Input-Data Segment: "+"JOBID: "+("0"+"0"+"0"+dummyPCB.JobId)+"   (HEX)\n");
for(int x4 =x1; x4<x2; x4++ ){
CPU.EA = Integer.toBinaryString(x4);
while (CPU.EA.length() < 16)
{
CPU.EA = "0" + CPU.EA;
   }
 var = MEMORY.MEMORY("Read", CPU.EA, var);
x4++;
var2 = Integer.parseInt(var, 2);
var = Integer.toHexString(var2);
while(var.length() < 4){
	var = "0" + var;
}
bw.write(var+"   ");
	
}
bw.write("(HEX)\n");
bw.write("Output-Data Segment: "+"JOBID: "+("0"+"0"+"0"+dummyPCB.JobId)+"   (HEX)\n");
bw.write("                     "+CPU.out+"    (BIN)\n");
bw.write("TERMINATION:  ABNORMAL\n");
clock = clock + CPU.CPU_exe_time + IOCLOCK + Segment_fault_time + page_fault_time ;
str = Integer.toHexString(clock);
str1 = Integer.toHexString(dummyPCB.job_endtime);
str2 = Integer.toHexString(dummyPCB.jobTime);
bw.write("JOB START time:  "+str2+"  (HEX)\n");
bw.write("JOB END time:  "+str1+"  (HEX)\n");
f = LOADER.page_fault_no.get(dummyPCB.JobId);
bw.write("NUMBER of PAGE FAULTS:  "+f+"   (DECIMAL)");
bw.write("CLOCK:  "+str+"  (HEX)\n");
bw.write("EXECUTION TIME:  "+CPU.CPU_exe_time+"  (DECIMAL)\n");
bw.write("IO TIME:  "+IOCLOCK+"  (DECIMAL)\n");
bw.write("Page-fault handling time: "+(page_fault_time)+" (DECIMAL)\n");
bw.write("Segment-fault handling time: "+Segment_fault_time+" (DECIMAL)\n");
bw.write("Turn-around time:  "+(dummyPCB.job_endtime - dummyPCB.jobTime)+"  (DECIMAL)\n");
bw.write("\n");
bw.write("\n");
bw.write("---------------------------------------------------------------------------------------------");
bw.close();
fw.close();
/*int x2 = Integer.parseInt(SYSTEM.Size_of_Program,2);*/
DISK_MANAGER.oldflag = 11;
/*769;
x2 = x2/4;
DISK_MANAGER.oldflag = DISK_MANAGER.c1+ x + SYSTEM.size_InputData_segment + 2;
DISK_MANAGER.DISK();*/
CPU.CPU(SYSTEM.StartAdr , String);
	
break;
}
return N;
}
public static void output(String x) throws NumberFormatException, Exception{

int x2 = 0, x1 =0, var2 = 0;
String var = null, str1 = null, str2 = null;

bw.write("JobID:  "+dummyPCB.JobId+"   (DECIMAL)\n");
bw.write("Error: "+error+"\n");
if(warning == null){
}else{
	 bw.write("Warning MESSAGE: "+warning);
}
if(DISK_MANAGER.segid == 0){
 x2 = Integer.parseInt(dummyPCB.RD_Y_end, 2);
x1 = Integer.parseInt(dummyPCB.RD_Y_start, 2);
}
else{
	x2 =0;
	x1 = 0;
}

bw.write("Input-Data Segment: "+"JOBID: "+("0"+"0"+"0"+dummyPCB.JobId)+"   (HEX)\n");
for(int x4 =x1; x4<x2; x4++ ){
CPU.EA = Integer.toBinaryString(x4);
while (CPU.EA.length() < 16)
{
CPU.EA = "0" + CPU.EA;
   }
 var = MEMORY.MEMORY("Read", CPU.EA, var);
x4++;
var2 = Integer.parseInt(var, 2);
var = Integer.toHexString(var2);
while(var.length() < 4){
	var = "0" + var;
}
bw.write(var+"   ");
	
}
bw.write("(HEX)\n");
bw.write("Output-Data Segment: "+"JOBID: "+("0"+"0"+"0"+dummyPCB.JobId)+"   (HEX)\n");
bw.write("                     "+CPU.out+"    (BIN)\n");
bw.write("TERMINATION:  ABNORMAL\n");
clock = clock + CPU.CPU_exe_time + IOCLOCK + Segment_fault_time + page_fault_time ;
str = Integer.toHexString(clock);
str1 = Integer.toHexString(dummyPCB.job_endtime);
str2 = Integer.toHexString(dummyPCB.jobTime);
bw.write("JOB START time:  "+str2+"  (HEX)\n");
bw.write("JOB END time:  "+str1+"  (HEX)\n");
int f1 = LOADER.page_fault_no.get(dummyPCB.JobId);
bw.write("NUMBER of PAGE FAULTS:  "+f1+"   (DECIMAL)");
bw.write("CLOCK:  "+str+"  (HEX)\n");
bw.write("EXECUTION TIME:  "+CPU.CPU_exe_time+"  (DECIMAL)\n");
bw.write("IO TIME:  "+IOCLOCK+"  (DECIMAL)\n");
bw.write("Page-fault handling time: "+(page_fault_time)+" (DECIMAL)\n");
bw.write("Segment-fault handling time: "+Segment_fault_time+" (DECIMAL)\n");
bw.write("Turn-around time:  "+(dummyPCB.job_endtime - dummyPCB.jobTime)+"  (DECIMAL)\n");
bw.write("\n");
bw.write("\n");
bw.write("---------------------------------------------------------------------------------------------");
bw.close();
fw.close();
CPU.CPU(SYSTEM.StartAdr , String);

}
}
