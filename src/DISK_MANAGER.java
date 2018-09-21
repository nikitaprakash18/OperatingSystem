import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/*All the variables declared are string, array
 *used to store program segment, Input segment
 *output segment and also convert the input file
 *into binary format and store it in a DISK 
 */

public class DISK_MANAGER extends SYSTEM{
	
private static String String;
public static String sCurrentLine1;
public static String binaryString = null;
public static String binaryString1 = null;
public static String binaryString2 = null;
public static String binaryString3 = null;
public static String input_binaryString = null;
public static int input_index = 0;
public static int pgm_index1 =0;
public static int input_size = 0;
public static int pgm = 0;
public static int input_in =0;
public static int index = 0;
public static int job_input = 0;
public static int pgm_index = 0;
public static String SEG0 = null;
public static String SEG1 = null;
public static String SEG2 = null;
public static int segid = 0;
public static String[] DISK = new String[2048]; 
public static int[] page = new int[256];
public static ArrayList<String> ProgramSegment = new ArrayList<String>();
public static ArrayList<String> InputSegment = new ArrayList<String>();
public static ArrayList OutputSegment = new ArrayList();
public static int inindex = 0;
public static int Finindex = 0;
public static int jobindex = 0;
public static int conflictindex = 0;
public static int temp_pgm_base = 0;
public static int temp_pgm_bound = 0;
public static int temp_input_base = 0;
public static int temp_input_bound = 0;
public static int No_input_index =0;
public static int a = 0,  y=0, m =0;
public static int sizeindex = 0;
public static int pgmsize = 0;
public static int oldflag=0;
public static int x1 = 0;
public static int y1 = 0;
public static int c1 =0;
public static int x11 = 0;
public static int sCurrent = 0;
public static String DISK() throws NumberFormatException, IOException, Exception{


BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
for(int j =0; j<oldflag; j++){
	sCurrentLine1 = br.readLine();	
}
while ((sCurrentLine1 = br.readLine())!=null ) {
	
	oldflag++;

/*calling HEXBIN function where hex values are converted to binary */
HEXBIN(String);
if(binaryString !=null  && input_binaryString == null){
	
ProgramSegment.add(binaryString);
ProgramSegment.add(binaryString1);
ProgramSegment.add(binaryString2);
ProgramSegment.add(binaryString3);

temp_pgm_base = x1;

DISK[x1] = binaryString;
DISK[x1+1] = binaryString1;
DISK[x1+2] = binaryString2;
DISK[x1+3] = binaryString3;
x1 = x1 + 4;

temp_pgm_bound = x1;
}
else if(binaryString !=null && input_binaryString == null){
	
}
if(input_index == SYSTEM.size_InputData_segment){

	input_index = -1;
	int y = (int) Math.round((ProgramSegment.size()+pgm)/ (double)8);
	y = 8* y;
	
	y1 = y-x1;
	input_size = y;
	for(int x = 0; x< InputSegment.size(); x++){
		DISK[y] =InputSegment.get(x) ;
		y++;
	}
	int b = (int) Math.round(InputSegment.size()/(double)8);
	
	if(b == 0){
		b = ((b+1)*8);
	}else{
	b = b*8;
	}
	pgm = b + pgm + y1;
	x1 = input_size + b;
	pgm_index1 = x1;
	InputSegment.clear();
	LOADER.LOADER(String, String);
}

else if(SYSTEM.size_OutputData_segment !=0){
	}
}
if(Finindex == 1 && sCurrentLine1 == null && Finindex != 2){
	Finindex = 2;
	ERROR_HANDLER.ERROR_HANDLER(19);
	
}

	if(sizeindex != Integer.parseInt(SYSTEM.Size_of_Program, 2) && MEMORY.frame17 == 0){
		//ERROR_HANDLER.ERROR_HANDLER(8);
	}
for(int x = 0; x < DISK.length; x++){

}
createpage();
System.exit(0);
return null;
	
}
public static String createpage(){
/*offset*/
SYSTEM.pageindex[0] = 0;
SYSTEM.pageindex[1]=7;
SYSTEM.pageindex[2] = 8;
SYSTEM.pageindex[3]=15;
SYSTEM.pageindex[4] = 16;
SYSTEM.pageindex[5]=23;
SYSTEM.pageindex[6] = 24;
SYSTEM.pageindex[7]=31;
SYSTEM.pageindex[8] = 32;
SYSTEM.pageindex[9]=39;
SYSTEM.pageindex[10] = 40;
SYSTEM.pageindex[11]=47;
SYSTEM.pageindex[12] = 48;
SYSTEM.pageindex[13]=55;
SYSTEM.pageindex[14] = 56;
SYSTEM.pageindex[15]=63;
SYSTEM.pageindex[16] = 64;
SYSTEM.pageindex[17] = 71;
SYSTEM.pageindex[18] = 72;
SYSTEM.pageindex[19] = 79;

return null;
}
public static String HEXBIN(String hexadecimalString) throws Exception, IOException, NumberFormatException {
		
hexadecimalString = sCurrentLine1;
/*To remove space from hexadecimalString if contains*/
String hex = hexadecimalString.replaceAll("\\s","");

if(hex.length()== 9 ||hex.length() ==7 ||hex.length() ==5 || hex.length()== 4 || hex.length() == 8 || hex.length() == 12 || hex.length() == 16){}
else{
	ERROR_HANDLER.ERROR_HANDLER(0);
}
if(hex.contains("**JOB")){
jobindex = 1;
seg_jobid++;
if(seg_jobid == 2 && oldflag == 13){
	segid = 1;
	
}
String xx = hex.substring(5, 7);
String yy = hex.substring(7, 9);
SYSTEM.size_InputData_segment = Integer.parseInt(xx,16);
SYSTEM.size_OutputData_segment = Integer.parseInt(yy, 16);
SEG0 = "seg_jobid" + "0";
SEG1 = "seg_jobid" + "1";
SEG2 = "seg_jobid" + "2";
}
if(hex.length() == 9 && hex.matches("[0-9a-fA-F]+")){
	x11 = 1;
/*Job id*/
String hex1 = hex.substring(0,2);
if(!hex1.matches("[0-9a-fA-F]+")){
ERROR_HANDLER.ERROR_HANDLER(1);
}
int i = Integer.parseInt(hex1,16);
SYSTEM.JobId = Integer.toBinaryString(i);

/* Load address*/
String hex2 = hex.substring(2, 4);
if(!hex2.matches("[0-9a-fA-F]+")){
	
	ERROR_HANDLER.ERROR_HANDLER(2);
}
int j = Integer.parseInt(hex2,16);
SYSTEM.Load_address = Integer.toBinaryString(j);
while (SYSTEM.Load_address.length() < 16)
{
SYSTEM.Load_address = "0" + SYSTEM.Load_address;
}
		       
/*Initial Program Counter*/
String hex3 = hex.substring(4, 6);
if(!hex3.matches("[0-9a-fA-F]+")){

/*ERROR_HANDLER.ERROR_HANDLER(3);*/
}
int k = Integer.parseInt(hex3,16);
CPU.orignalpc = k;
SYSTEM.PC = Integer.toBinaryString(k);
while (SYSTEM.PC.length() < 7)
{
SYSTEM.PC = "0" + SYSTEM.PC;
}   
/*Size of the Program*/
String hex4 = hex.substring(6, 8);
if(!hex4.matches("[0-9a-fA-F]+")){
	
ERROR_HANDLER.ERROR_HANDLER(4);
}
int m = Integer.parseInt(hex4,16);

/*number of pages for program segment*/
double x = Math.round(m / (double)8);

for(int a=0; a<x;a++){
	//page[a]=0;
}

SYSTEM.Size_of_Program = Integer.toBinaryString(m);
while (SYSTEM.Size_of_Program.length() < 16)
{
SYSTEM.Size_of_Program = "0" + SYSTEM.Size_of_Program;
}
		        
/*trace flag*/
String hex5 = hex.substring(8, 9);
SYSTEM.Trace = hex5;
if(SYSTEM.Trace.matches("0") || SYSTEM.Trace.matches("1")){  }
else{
warning = "Trace bit should be 0 or 1";	
  }
}
else if(hex.length() == 16 && index == 0){

	if(x11 == 0){
		ERROR_HANDLER.ERROR_HANDLER(22);
	}
if(hex.matches("[0-9a-fA-F]+")){}
/*checking for loader format*/
else{
ERROR_HANDLER.ERROR_HANDLER(6);
}
	
/*word 1*/
String hex_word1 = hex.substring(0, 4);
int word1 = Integer.parseInt(hex_word1,16);
binaryString = Integer.toBinaryString(word1);
while (binaryString.length() < 16)
{
	binaryString = "0" + binaryString;
}
  
/*word 2*/
String hex_word2 = hex.substring(4, 8);
int word2 = Integer.parseInt(hex_word2,16);
binaryString1 = Integer.toBinaryString(word2);
while (binaryString1.length() < 16)
{
   	binaryString1 = "0" + binaryString1;
}
   
/*word 3*/
String hex_word3 = hex.substring(8, 12);
int word3 = Integer.parseInt(hex_word3,16);
binaryString2 = Integer.toBinaryString(word3);
while (binaryString2.length() < 16)
{
   binaryString2 = "0" + binaryString2;
}
			       
/*word 4*/
String hex_word4 = hex.substring(12, 16);
int word4 = Integer.parseInt(hex_word4,16);
binaryString3 = Integer.toBinaryString(word4);
while (binaryString3.length() < 16)
{
	binaryString3 = "0" + binaryString3;
}   
input_in =3;
inindex = 1;
sizeindex = sizeindex+4;
}
//last words
else if((hex.length() == 4 && index == 0) || (hex.length() == 8 && index == 0) || (hex.length() == 12 && index == 0)){
	if(hex.length() == 4){
		
/*word 1*/
String hex_word1 = hex.substring(0, 4);
int word1 = Integer.parseInt(hex_word1,16);
binaryString = Integer.toBinaryString(word1);
while (binaryString.length() < 16)
{
	binaryString = "0" + binaryString;
}
pgm_index = 3;
//ProgramSegment.add(binaryString);
binaryString1 = null;
binaryString2 = null;
binaryString3 = null;
	}
	else if(hex.length() == 8){
	
	/*word 1*/
	String hex_word1 = hex.substring(0, 4);
	int word1 = Integer.parseInt(hex_word1,16);
	binaryString = Integer.toBinaryString(word1);
	while (binaryString.length() < 16)
	{
		binaryString = "0" + binaryString;
	}
	//ProgramSegment.add(binaryString);
	/*word 2*/
	String hex_word2 = hex.substring(4, 8);
	int word2 = Integer.parseInt(hex_word2,16);
	binaryString1 = Integer.toBinaryString(word2);
	while (binaryString1.length() < 16)
	{
	   	binaryString1 = "0" + binaryString1;
	}
	//ProgramSegment.add(binaryString1);
	pgm_index = 2;
	
	binaryString2 = null;
	binaryString3 = null;
	}
	else if(hex.length() == 12){
       

/*word 1*/
String hex_word1 = hex.substring(0, 4);
int word1 = Integer.parseInt(hex_word1,16);
binaryString = Integer.toBinaryString(word1);
while (binaryString.length() < 16)
{
	binaryString = "0" + binaryString;
}
//ProgramSegment.add(binaryString);
/*word 2*/
String hex_word2 = hex.substring(4, 8);
int word2 = Integer.parseInt(hex_word2,16);
binaryString1 = Integer.toBinaryString(word2);
while (binaryString1.length() < 16)
{
   	binaryString1 = "0" + binaryString1;
}
//ProgramSegment.add(binaryString1);
/*word 3*/
String hex_word3 = hex.substring(8, 12);
int word3 = Integer.parseInt(hex_word3,16);
binaryString2 = Integer.toBinaryString(word3);
while (binaryString2.length() < 16)
{
   binaryString2 = "0" + binaryString2;
}
//ProgramSegment.add(binaryString2);
	pgm_index = 1;
	binaryString3 = null;
}
input_in = 3;

}

else if(hex.contains("**INPUT")){
binaryString = null;
binaryString3 = null;
job_input = 1;
index = 1;
input_in = 1;
//input_index = 1;
inindex ++;		
}
else if(hex.length() == 4 || hex.length() == 8 || hex.length() == 12 || hex.length() == 16){
	input_in = 2;
	String m = null;
	int input = 0;
	if(hex.length() == 4){
/*1st word*/
 m  = hex.substring(0, 4);
InputHex = m;
input  = Integer.parseInt(m, 16);
input_binaryString = Integer.toBinaryString(input);

if(input_binaryString.length()> 16){
input_binaryString = input_binaryString.substring(input_binaryString.length()-16);	
}
while (input_binaryString.length() < 16)
{
	input_binaryString = "0" + input_binaryString;
	}  
	InputSegment.add(input_binaryString);
	SYSTEM.input = input_binaryString;
	conflictindex++;
	Finindex = 1;
	}
else if(hex.length() == 8){
	/*1st word*/
m  = hex.substring(0, 4);
InputHex = m;
input  = Integer.parseInt(m, 16);
input_binaryString = Integer.toBinaryString(input);

if(input_binaryString.length()> 16){
input_binaryString = input_binaryString.substring(input_binaryString.length()-16);	
}
while (input_binaryString.length() < 16)
{
	input_binaryString = "0" + input_binaryString;
}  
InputSegment.add(input_binaryString);
//SYSTEM.input = input_binaryString;


/*2nd word*/
m  = hex.substring(4, 8);
InputHex = m;
input  = Integer.parseInt(m, 16);
input_binaryString = Integer.toBinaryString(input);

if(input_binaryString.length()> 16){
input_binaryString = input_binaryString.substring(input_binaryString.length()-16);	
}
while (input_binaryString.length() < 16)
{
	input_binaryString = "0" + input_binaryString;
			}  
			InputSegment.add(input_binaryString);
		conflictindex++;
		Finindex = 1;
}
else if(hex.length() == 12){
		
		/*1st word*/
m  = hex.substring(0, 4);
InputHex = m;
input  = Integer.parseInt(m, 16);
input_binaryString = Integer.toBinaryString(input);

if(input_binaryString.length()> 16){
input_binaryString = input_binaryString.substring(input_binaryString.length()-16);	
}
while (input_binaryString.length() < 16)
{
	input_binaryString = "0" + input_binaryString;
}  
InputSegment.add(input_binaryString);
//SYSTEM.input = input_binaryString;


/*2nd word*/
m  = hex.substring(4, 8);
InputHex = m;
input  = Integer.parseInt(m, 16);
input_binaryString = Integer.toBinaryString(input);

if(input_binaryString.length()> 16){
input_binaryString = input_binaryString.substring(input_binaryString.length()-16);	
}
while (input_binaryString.length() < 16)
{
	input_binaryString = "0" + input_binaryString;
	}  
	InputSegment.add(input_binaryString);
				
		/*3rd Word*/
				 
m  = hex.substring(8, 12);
InputHex = m;
input  = Integer.parseInt(m, 16);
input_binaryString = Integer.toBinaryString(input);

if(input_binaryString.length()> 16){
input_binaryString = input_binaryString.substring(input_binaryString.length()-16);	
}
while (input_binaryString.length() < 16)
{
	input_binaryString = "0" + input_binaryString;
		}  
		InputSegment.add(input_binaryString);
conflictindex++;
Finindex = 1;
	}
else if(hex.length() == 16){
/*1st word*/
m  = hex.substring(0, 4);
InputHex = m;
input  = Integer.parseInt(m, 16);
input_binaryString = Integer.toBinaryString(input);

if(input_binaryString.length()> 16){
input_binaryString = input_binaryString.substring(input_binaryString.length()-16);	
}
while (input_binaryString.length() < 16)
{
input_binaryString = "0" + input_binaryString;
}  
InputSegment.add(input_binaryString);

/*2nd word*/
m  = hex.substring(4, 8);
InputHex = m;
input  = Integer.parseInt(m, 16);
input_binaryString = Integer.toBinaryString(input);

if(input_binaryString.length()> 16){
input_binaryString = input_binaryString.substring(input_binaryString.length()-16);	
}
while (input_binaryString.length() < 16)
{
	input_binaryString = "0" + input_binaryString;
	}  
	InputSegment.add(input_binaryString);
	
/*3rd Word*/
	 
	m  = hex.substring(8, 12);
	InputHex = m;
	input  = Integer.parseInt(m, 16);
	input_binaryString = Integer.toBinaryString(input);

	if(input_binaryString.length()> 16){
	input_binaryString = input_binaryString.substring(input_binaryString.length()-16);	
	}
	while (input_binaryString.length() < 16)
	{
		input_binaryString = "0" + input_binaryString;
		}  
		InputSegment.add(input_binaryString);
		
/*4th word*/
		
		 
		m  = hex.substring(12, 16);
		InputHex = m;
		input  = Integer.parseInt(m, 16);
		input_binaryString = Integer.toBinaryString(input);

		if(input_binaryString.length()> 16){
		input_binaryString = input_binaryString.substring(input_binaryString.length()-16);	
		}
		while (input_binaryString.length() < 16)
		{
			input_binaryString = "0" + input_binaryString;
			}  
			InputSegment.add(input_binaryString);
conflictindex++;
Finindex = 1;
	}
}

else if(hex.contains("**FIN") && Finindex == 1){
input_index = SYSTEM.size_InputData_segment;
Finindex = 0;
index = 0;
if(size_InputData_segment < conflictindex){
	//ERROR_HANDLER.ERROR_HANDLER(21);
}
if(size_InputData_segment !=conflictindex){
	//ERROR_HANDLER.ERROR_HANDLER(18);
}

if(inindex > 2){
	//ERROR_HANDLER.ERROR_HANDLER(14);
}
/*PCB*/
PROCESS_MANAGER PCB = new PROCESS_MANAGER();
int y1 = (int) Math.round((ProgramSegment.size()+pgm)/ (double)8) ;
y1 = 8* y1;
PCB.in_pgmSize = y1;
PCB.JobId = seg_jobid;

PCB.jobTime = job_time;
PCB.orignal_PC = CPU.orignalpc;
PCB.Pgm_Counter = SYSTEM.PC;
PCB.load_addr = SYSTEM.Load_address;
PCB.pgm_size = SYSTEM.Size_of_Program;
int c = Integer.parseInt(SYSTEM.Size_of_Program, 2);
PCB.innn = InputSegment.size();
PCB.out = SYSTEM.size_OutputData_segment;
CPU.avg_output_code_seg.add(PCB.JobId, PCB.out);
CPU.avg_input_code_seg.add(PCB.JobId, PCB.innn);
CPU.avg_code_seg.add(c, PCB.JobId);
PCB.job_trace = SYSTEM.Trace;
PCB.Program_seg_baseIndex = temp_pgm_base;
PCB.Program_seg_boundIndex = temp_pgm_bound;
PCB.input_seg_baseIndex = temp_input_base;
PCB.input_seg_boundIndex = temp_input_bound;
PCB.pgm_index = pgm_index1;
//PCB.output_seg_baseIndex = ;
readylist.push(PCB);
/*for(int x =0; x< readylist.size(); x++){
	System.out.println("readylist: "+readylist.element().JobId);
	System.out.println("readylist: "+readylist.element().in_pgmSize);
	System.out.println("readylist: "+readylist.element().orignal_PC);
	System.out.println("--------------------------------------------------");
}*/
binaryString = null;
binaryString1 = null;
binaryString2 = null;
binaryString3 = null;
input_binaryString = null;
/*number of frames*/
double Disk_frame = (double)DISK.length / (double)8;
double x = SYSTEM.size_InputData_segment/(double)8;
double y = Math.round(Integer.parseInt(SYSTEM.Size_of_Program,2)/(double)8);
double z = SYSTEM.size_OutputData_segment/(double)8;
if(x < 1){
x = 1;
}
if(y< 1){
y = 1;
}
if(z < 1){
z = 1;
}
double no_frame_used = x + y + z;
Disk_utilization_frame_ratio = no_frame_used / Disk_frame;
Disk_utilization_frame_per = Disk_utilization_frame_ratio * 100;
Disk_utilization_ratio = (double)(SYSTEM.size_InputData_segment+ Integer.parseInt(SYSTEM.Size_of_Program,2)+ SYSTEM.size_OutputData_segment)/ (double)DISK.length ;    
Disk_utilization_percentage = Disk_utilization_ratio * 100;
}
else if(input_in == 3 && Finindex == 0){
	//seg_jobid++;
	ERROR_HANDLER.ERROR_HANDLER(17);
}
if(jobindex ==0 && jobindex !=1){
//ERROR_HANDLER.ERROR_HANDLER(15);
}
if(inindex > 2){
	//ERROR_HANDLER.ERROR_HANDLER(14);
}

return hexadecimalString;
}
}
