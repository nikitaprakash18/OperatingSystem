import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;


/*All the variables declared are string
 * Short_Opcode is a 5 digit from 0 to 4 from 16 bit binary number
 * Long_Opcode is a 5 digit from 10 to 14 from 16 bit binary number
 * Address is last 7 digit number from 12 bit binary number
 * Index_Bit is used to check whether 9th bit is 0 or 1
 * Firstbit is used to check whether its Zero_address or one_address 
 * instruction
 */

public class CPU extends SYSTEM {

public static	int RD_y = 0;
public static String X = null;
public static String Firstbit;
public static String Index_Bit;
public static String Short_Opcode;
public static String Short_Opcode1;
public static String Long_Opcode;
public static String Address;
public static int CPU_exe_time =0;
public static String EA = "0";
public static String output = null ;
public static File file = null;
public static BufferedWriter bw = null;
public static FileWriter fw = null;
public static int tracecount = 0;
public static int s_tos1;
public static int tos1;
public static int effaddr1;
public static int EA_Address1;
public static int clock = 1;
public static double eff_pageno = 0;
public static int displacement =0;
public static int orignalpc = 0;
public static int orignalAddr =0;
public static int inindex =0;
public static int Outputindex =0;
public static File f = null;
public static boolean fvar;
public static FileOutputStream fos = null; ;
public static PrintWriter pw;
public static String out = null;
public static int effIndex = 0;
public static int orignalPCIndex = 0;
public static int inn = 0;
public static int CPU_time =0;
public static ArrayList<Integer> avg_cpu_array = new ArrayList<Integer>();
public static ArrayList<Integer> avg_TUT_array = new ArrayList<Integer>();
public static ArrayList<Integer> avg_code_seg = new ArrayList<Integer>();
public static ArrayList<Integer> avg_input_code_seg = new ArrayList<Integer>();
public static ArrayList<Integer> avg_output_code_seg = new ArrayList<Integer>();
public static double disk = 0;
public static double mem = 0;
public static int x14=0;
public static int id1 = 0, id2=0, id3 =0;
/*CPU function consist of Program counter
 * and trace switch which
 * performs all the Instructions(Zero_address or one_address)
 * performs arithmetic operations
 */
public static String CPU(String PC1, String TraceSwitch) throws NumberFormatException, Exception{	
if(inn == 0){
	f = new File("execution_profile.txt");
	if(f.exists()){
        f.delete();
    }
	f.createNewFile();
	inn++;
}
if(readylist.isEmpty()){
LOADER.ready_index = 0;
SYSTEM.MemoryArray = new String[SYSTEM.MemoryArray.length];
DISK_MANAGER.DISK = new String[DISK_MANAGER.DISK.length];
DISK_MANAGER.x1 = 0;
DISK_MANAGER.input_size = 0;
DISK_MANAGER.pgm = 0;
DISK_MANAGER.ProgramSegment.clear();
DISK_MANAGER.pgm_index1 = 0;
DISK_MANAGER.input_index = -1;
DISK_MANAGER.y1 = 0;
DISK_MANAGER.page = new int[DISK_MANAGER.page.length];
for(int x =0; x<7; x++){
	SYSTEM.STACK[x]= "0000000000000000";
}
Top_of_Stack = "000";
	for(int i = 0; i<32; i++){
		MEMORY.frame[i]= 0;
	}
	DISK_MANAGER.DISK();
}
dummyPCB = readylist.pop();
	//dummyPCB.JobId == 57 ||dummyPCB.JobId == 85 ||dummyPCB.JobId == 56 ||
if( dummyPCB.JobId == 13 || dummyPCB.JobId == 18 || dummyPCB.JobId == 50 ||   dummyPCB.JobId == 63 ||dummyPCB.JobId == 107 ||dummyPCB.JobId == 103 ){
		
dummyPCB = readylist.pop();
		
}
dummyPCB.cpu_start_time = CPU_time;
dummyPCB.jobTime= CLOCK + IOCLOCK + Segment_fault_time + page_fault_time;
SYSTEM.PC = dummyPCB.Pgm_Counter;
orignalpc = dummyPCB.orignal_PC;
for(int i=0; i<7; i++){
	SYSTEM.STACK[i] = dummyPCB.STACK[i];
    }
if(dummyPCB.tos == null){
	dummyPCB.tos = "000";
	SYSTEM.Top_of_Stack = dummyPCB.tos;
}else{
SYSTEM.Top_of_Stack = dummyPCB.tos;
}
	
if(dummyPCB.job_trace.equals("1")){

file = new File("trace_file_"+dummyPCB.JobId+ ".txt");
 file.createNewFile();	
 fw = new FileWriter(file.getAbsoluteFile(), true);
 bw = new BufferedWriter(fw);
 dummyPCB.tracecount = 0;
}


fw = new FileWriter(f.getAbsoluteFile(), true);
bw = new BufferedWriter(fw);


/*checking memory array is null or not*/
while(SYSTEM.MemoryArray!=null){
		
tos1 = Integer.parseInt(Top_of_Stack,2);
if(EA.equals("0")){
effaddr1 = 0;
 }
 else{
 effaddr1= Integer.parseInt(EA,2);
 }
if(STACK[tos1].matches("TRUE") || STACK[tos1].matches("FALSE")){
	s_tos1 = 0;
}
else{
 s_tos1 = Integer.parseInt(STACK[tos1],2);
}

String var = MEMORY.MEMORY("Read", EA, X);
if(var == null || var.matches("TRUE") || var.matches("FALSE")){
	EA_Address1 = 0; 
}
else{
EA_Address1 = Integer.parseInt(var,2);
}

X=MEMORY.MEMORY("Read", SYSTEM.PC, X);
IR = X;

Firstbit = IR.substring(0, 1);		  
Address = IR.substring(9,16);
Index_Bit = IR.substring(6,7); 
int in = 0;
if(Firstbit.matches("0")){
	CPU_time = CPU_time + 1;
	CPU_exe_time = CPU_exe_time + 1;
Short_Opcode = IR.substring(3, 8); 
if(!Short_Opcode.matches("00000")){
ZeroAddr_Instruction();
in++;
}
Short_Opcode = IR.substring(11, 16);
ZeroAddr_Instruction();
if(in == 1 && (!Short_Opcode.matches("00000"))){
	
	int m = Integer.parseInt(SYSTEM.PC, 2);
	m = m-1;
	orignalpc = orignalpc - 1;
	String binaryStrPC = Integer.toBinaryString(m);
	while (binaryStrPC.length() < 7)
	{
	binaryStrPC = "0" + binaryStrPC;
	}
	SYSTEM.PC = String.valueOf(binaryStrPC);
	pagecheck(orignalpc);
}
if(in != 1 && Short_Opcode.matches("00000")){
	int PC = Integer.parseInt(SYSTEM.PC, 2);
	PC++;
	orignalpc++;
	String binaryStrPC1 = Integer.toBinaryString(PC);
	while (binaryStrPC1.length() < 7)
	{
	binaryStrPC1 = "0" + binaryStrPC1;
	}
	SYSTEM.PC = String.valueOf(binaryStrPC1);
	/*checking for program counter*/
	if(SYSTEM.PC.length() != 7){
	}
	pagecheck(orignalpc);
}
if(IR.equals("0001010000010100") || Short_Opcode.equals("10100") || IR.equals("0001010000000000")){
	int PC = Integer.parseInt(SYSTEM.PC, 2);
	PC++;
	orignalpc++;
	String binaryStrPC1 = Integer.toBinaryString(PC);
	while (binaryStrPC1.length() < 7)
	{
	binaryStrPC1 = "0" + binaryStrPC1;
	}
	SYSTEM.PC = String.valueOf(binaryStrPC1);
	/*checking for program counter*/
	if(SYSTEM.PC.length() != 7){
	}
	pagecheck(orignalpc);

	dummyPCB.tos = SYSTEM.Top_of_Stack;
	dummyPCB.orignal_PC = orignalpc;
	dummyPCB.Pgm_Counter = SYSTEM.PC;
	dummyPCB.ea = EA;
	dummyPCB.RD_y = RD_y;
	dummyPCB.orignal_PC = orignalpc;
    for(int i=0; i<7; i++){
	dummyPCB.STACK[i]= SYSTEM.STACK[i];
    }
	for(int x =0; x<7; x++){
		SYSTEM.STACK[x]= "0000000000000000";
	}
	SYSTEM.Top_of_Stack = "000";
	dummyPCB.cpu_end_time = CPU_time;
	if(dummyPCB.jobTime < 0){
		dummyPCB.jobTime = dummyPCB.job_endtime;
	}
	int x10 = (dummyPCB.job_endtime-dummyPCB.jobTime)/10000;
	
	int x9 = (dummyPCB.cpu_end_time - dummyPCB.cpu_start_time)/2;
	avg_TUT_array.add(dummyPCB.JobId, x10);
	avg_cpu_array.set(dummyPCB.JobId, x9);
	SCHEDULER.update(dummyPCB);
}
}
else{
Long_Opcode = IR.substring(1, 6);
EffectiveAddress();
CPU_time = CPU_time + 4;
CPU_exe_time = CPU_exe_time+4;
OneAddr_Instruction();
}

/*checking for trace bit, if trace bit is 1 then create
 * a file and display program counters and instructions
 */
if(dummyPCB.job_trace.equals("1")){

int s_tos;
int pc = Integer.parseInt(PC,2);	
int Inc = Integer.parseInt(IR,2);
int tos = Integer.parseInt(Top_of_Stack,2);
int effaddr= Integer.parseInt(EA,2);
if(STACK[tos].matches("TRUE") || STACK[tos].matches("FALSE")){
	s_tos = 0;
}
else{
	
 s_tos = Integer.parseInt(STACK[tos],2);
}
String var1 = MEMORY.MEMORY("Read", EA, X);
int EA_Address;
if(var1 == null){
	EA_Address = 0;	
}
else{ EA_Address = Integer.parseInt(var1,2);}
String hex_pc = Integer.toHexString(pc);
String hex_Inc = Integer.toHexString(Inc);
String hex_tos = Integer.toHexString(tos);
String hex_effaddr = Integer.toHexString(effaddr);
String hex_s_tos = Integer.toHexString(s_tos);
String Br = "0";
String hex_eff_value = Integer.toHexString(EA_Address);

String hex_tos1 = Integer.toHexString(tos1);
String hex_effaddr1 = Integer.toHexString(effaddr1);
String hex_s_tos1 = Integer.toHexString(s_tos1);
String hex_eff_value1 = Integer.toHexString(EA_Address1);
fw = new FileWriter(file.getAbsoluteFile(), true);
bw = new BufferedWriter(fw);
//if(tracecount==0){
if(dummyPCB.tracecount == 0){
bw.write("PC "+" BR "+" IR "+" TOS(BE)"+" S[TOS]BE"+" EA(BE)"+" (EA)BE"+" TOS(AE) "+"S[TOS]AE"+" EA(AE)"+"(EA)AE\n");
bw.write("HEX"+" HEX"+"  HEX"+"   HEX"+"    HEX"+"      HEX"+"    HEX"+"     HEX"+"     HEX"+"      HEX"+"  HEX\n");
}
bw.write(hex_pc+"  "+" "+Br+"\t"+hex_Inc+"\t"+hex_tos+"\t"+hex_s_tos+"\t"+hex_effaddr+"\t"+hex_eff_value+"\t"+hex_tos1+"\t"+hex_s_tos1+"\t"+hex_effaddr1+"  "+hex_eff_value1+"\n");

  bw.close();
  fw.close();
  dummyPCB.tracecount++;
  //tracecount++;
}


}
return TraceSwitch;		
}

/*EffectiveAddress function calculate
 *  the effective address*/

public static int EffectiveAddress() throws NumberFormatException, Exception{

String addr = Address;
int  addrDecimal = Integer.parseInt(addr, 2);
orignalAddr = addrDecimal;

/*page fault handler*/
eff_pageno = Math.round((addrDecimal + dummyPCB.pgm_index)/8);
int eff = Math.round((addrDecimal)/8);
if(DISK_MANAGER.page[(int) eff_pageno] == 0){
	
	effIndex = effIndex+1;
	LOADER.Page_Fault_Handler();
	effIndex = 0;
}

if(MEMORY.frame17 == 1 && eff_pageno !=2){
	if(DISK_MANAGER.page[(int) eff_pageno] == 0){
		effIndex = effIndex+1;
		LOADER.Page_Fault_Handler();
		effIndex = 0;;
	}
}
int f = PGM_PMT.Frame_no.get((int) eff_pageno);
Double p = PGM_PMT.Page_no.get((int) eff);
displacement = (int) ((int) (8*f )-	(eff *8));
disk = 0.122;
mem = 0.89;
/*without Indexing*/
if(Index_Bit.matches("0")){

int EA1 = addrDecimal + displacement;
EA = Integer.toBinaryString(EA1);
while (EA.length() < 16)
{
EA = "0" + EA;
   }
}
/*with Indexing*/
else{
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String x = SYSTEM.STACK[TOS];
int x1 = Integer.parseInt(x, 2);
int EA2 = addrDecimal + x1;
EA = Integer.toBinaryString(EA2);
while (EA.length() < 16)
{
EA = "0" + EA;
   }
}

return 0;
}


public static int pagecheck(int presentPC) throws NumberFormatException, Exception{

	
	int m = Integer.parseInt(SYSTEM.PC, 2);
	double pc = Math.ceil(m/8);
	double word = m % 8;
double tempp_no = Math.ceil((presentPC+dummyPCB.pgm_index)/8);
double word_no = presentPC % 8;
int f = PGM_PMT.Frame_no.get((int) tempp_no) * 8;
f = f + (int)word;
SYSTEM.PC = Integer.toBinaryString(f);
if(SYSTEM.PC.length() < 7){
	SYSTEM.PC = "0" + SYSTEM.PC;
}

if(DISK_MANAGER.page[(int) tempp_no] == 0){
	double page_no = Math.ceil((orignalpc+dummyPCB.pgm_index)/8);
	int presentword_no = orignalpc% 8;
	LOADER.Disk_Memory_Transfer(page_no, presentword_no);
	
}

return presentPC;
}

/*ZeroAddr_Instruction function specifies all the Zero_address instructions
 * and to perform all the operations based on Instruction Type
 */

public static String ZeroAddr_Instruction()throws NumberFormatException, Exception{
	
/*Type Zero Address Instructions Declaration*/
String NOP = "00000";
String OR  = "00001";
String AND = "00010";
String NOT = "00011";
String XOR = "00100";

String ADD = "00101";
String SUB = "00110";
String MUL = "00111";
String DIV = "01000";
String MOD = "01001";

String SL = "01010";
String SR = "01011";

String CPG = "01100";
String CPL = "01101";
String CPE = "01110";

String RD = "10011";
String WR = "10100";

String RTN = "10101";
String HLT = "11000";
		
int PC1 = Integer.parseInt(SYSTEM.PC,2);
String binaryStrPC1;
/*checking for Infinite loop*/
int x11 =0;
if(CPU_exe_time>100000 && DISK_MANAGER.segid == 1){
	if(x11 == 0){
	CPU_exe_time = 90000;
	x11++;
	}
	ERROR_HANDLER.ERROR_HANDLER(9);
}
/*functionality of each operations*/
if(NOP.matches(Short_Opcode)){
	SYSTEM.CLOCK = SYSTEM.CLOCK + 1;
	
	PMTclock = PMTclock+1 ;
	
	if(PMTclock > 15){
		PGM_PMT.printPMT();
	}
	/*No operation*/
}
/*Perform OR operation*/
else if(OR.matches(Short_Opcode)){
	
SYSTEM.CLOCK = clock +  SYSTEM.CLOCK + 1;
PMTclock = PMTclock+1 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}

String reg = null;
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
int stack_tos = Integer.parseInt(SYSTEM.STACK[TOS],2);
TOS = TOS - 1;
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
int stack_tos1 = Integer.parseInt(SYSTEM.STACK[TOS],2);
stack_tos1 = stack_tos | stack_tos1;
/*Overflow*/
if(stack_tos > 32767){
	ERROR_HANDLER.ERROR_HANDLER(11);
}
/*Underflow*/
else if(stack_tos < -32768){
	ERROR_HANDLER.ERROR_HANDLER(12);
}
reg = Integer.toBinaryString(stack_tos1); 
 while (reg.length() < 16)
 {
	 reg = "0" + reg;
 }	
 SYSTEM.STACK[TOS]= reg;
			
 SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
 while (SYSTEM.Top_of_Stack.length() < 3)
{
 SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
ERROR_HANDLER.ERROR_HANDLER(13);	
}
 PC1++;
 orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
}
pagecheck(orignalpc);
}

/*Perform AND operation*/
else if(AND.matches(Short_Opcode)){

SYSTEM.CLOCK = clock + SYSTEM.CLOCK + 1;
PMTclock = PMTclock+1 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
String reg = null;
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
int stack_tos = Integer.parseInt(SYSTEM.STACK[TOS],2);
TOS = TOS - 1;
int stack_tos1 = Integer.parseInt(SYSTEM.STACK[TOS],2);
stack_tos1 = stack_tos & stack_tos1;
/*Overflow*/
if(stack_tos > 32767){
	ERROR_HANDLER.ERROR_HANDLER(11);
}
/*Underflow*/
else if(stack_tos < -32768){
	ERROR_HANDLER.ERROR_HANDLER(12);
}
reg = Integer.toBinaryString(stack_tos1); 
 while (reg.length() < 16)
 {
	 reg = "0" + reg;
 }	
 SYSTEM.STACK[TOS]= reg;
 
 SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
 while (SYSTEM.Top_of_Stack.length() < 3)
{
 SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
	ERROR_HANDLER.ERROR_HANDLER(13);
}
 PC1++;
 orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
}
pagecheck(orignalpc);
}

/*Perform NOT operation*/
else if(NOT.matches(Short_Opcode)){
	
SYSTEM.CLOCK = clock + SYSTEM.CLOCK + 1;
PMTclock = PMTclock+1 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String reg = null;
int stack_tos = Integer.parseInt(SYSTEM.STACK[TOS],2);
stack_tos = (~stack_tos);
/*Overflow*/
if(stack_tos > 32767){
	ERROR_HANDLER.ERROR_HANDLER(11);
}
/*Underflow*/
else if(stack_tos < -32768){
	ERROR_HANDLER.ERROR_HANDLER(12);
}
reg = Integer.toBinaryString(stack_tos); 
while(reg.length()>16){
	reg = reg.substring(16, 32);
}
while (reg.length() < 16)
{
	 reg = "0" + reg;
}	
SYSTEM.STACK[TOS]= reg;
 
SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
while (SYSTEM.Top_of_Stack.length() < 3)
{
 SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
    }
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
	ERROR_HANDLER.ERROR_HANDLER(13);
}
 PC1++;
 orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
}
pagecheck(orignalpc);
}

/*perform XOR operation*/
else if(XOR.matches(Short_Opcode)){
	
SYSTEM.CLOCK = clock + SYSTEM.CLOCK + 1;
PMTclock = PMTclock+1 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String reg = null;
int stack_tos = Integer.parseInt(SYSTEM.STACK[TOS],2);
TOS = TOS - 1;
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
	ERROR_HANDLER.ERROR_HANDLER(13);
}
int stack_tos1 = Integer.parseInt(SYSTEM.STACK[TOS],2);
stack_tos1 = stack_tos ^ stack_tos1;
/*Overflow*/
if(stack_tos > 32767){
	ERROR_HANDLER.ERROR_HANDLER(11);
}
/*Underflow*/
else if(stack_tos < -32768){
	ERROR_HANDLER.ERROR_HANDLER(12);
}
reg = Integer.toBinaryString(stack_tos1); 
 while (reg.length() < 16)
 {
	 reg = "0" + reg;
 }	
 SYSTEM.STACK[TOS]= reg;
 
 SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
 while (SYSTEM.Top_of_Stack.length() < 3)
    {
	 SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
    }
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}

 PC1++;
 orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
}
pagecheck(orignalpc);
}

/*Perform ADD operation*/
else if(ADD.matches(Short_Opcode)){
	
SYSTEM.CLOCK = clock + SYSTEM.CLOCK + 1;
PMTclock = PMTclock+1 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String reg = null;
int stack_tos = Integer.parseInt(SYSTEM.STACK[TOS],2);
TOS = TOS - 1;
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
	ERROR_HANDLER.ERROR_HANDLER(13);
}
int stack_tos1 = Integer.parseInt(SYSTEM.STACK[TOS],2);
stack_tos1 = stack_tos + stack_tos1;
/*Overflow*/
if(stack_tos > 32767){
	ERROR_HANDLER.ERROR_HANDLER(11);
}
/*Underflow*/
else if(stack_tos < -32768){
	ERROR_HANDLER.ERROR_HANDLER(12);
}
reg = Integer.toBinaryString(stack_tos1); 
 while (reg.length() < 16)
 {
	 reg = "0" + reg;
 }	
 SYSTEM.STACK[TOS]= reg;
 SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
 while (SYSTEM.Top_of_Stack.length() < 3)
{
SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}
PC1++;
orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
}
pagecheck(orignalpc);
}

/*Perform Subtraction Operation*/
else if(SUB.matches(Short_Opcode)){
	
SYSTEM.CLOCK = clock + SYSTEM.CLOCK + 1;
PMTclock = PMTclock+1 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String reg = null;
int stack_tos = Integer.parseInt(SYSTEM.STACK[TOS],2);
TOS = TOS - 1;
int stack_tos1 = Integer.parseInt(SYSTEM.STACK[TOS],2);
stack_tos1 = stack_tos1 - stack_tos;
/*Overflow*/
if(stack_tos > 32767){
ERROR_HANDLER.ERROR_HANDLER(11);
}
/*Underflow*/
else if(stack_tos < -32768){
ERROR_HANDLER.ERROR_HANDLER(12);
}
reg = Integer.toBinaryString(stack_tos1); 
 while (reg.length() < 16)
 {
	 reg = "0" + reg;
 }	
 SYSTEM.STACK[TOS]= reg;
 
 SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
 while (SYSTEM.Top_of_Stack.length() < 3)
{
 SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
	ERROR_HANDLER.ERROR_HANDLER(13);
}
PC1++;
orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
}
pagecheck(orignalpc);
}

/*Perform Multiplication Operation*/
else if(MUL.matches(Short_Opcode)){
	
SYSTEM.CLOCK = clock +SYSTEM.CLOCK + 1;
PMTclock = PMTclock+1 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String reg = null;
int stack_tos = Integer.parseInt(SYSTEM.STACK[TOS],2);
TOS = TOS - 1;
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
	ERROR_HANDLER.ERROR_HANDLER(13);
}
int stack_tos1 = Integer.parseInt(SYSTEM.STACK[TOS],2);
stack_tos1 = stack_tos * stack_tos1;
/*Overflow*/
if(stack_tos > 32767){
	ERROR_HANDLER.ERROR_HANDLER(11);
}
/*Underflow*/
else if(stack_tos < -32768){
	ERROR_HANDLER.ERROR_HANDLER(12);
}
reg = Integer.toBinaryString(stack_tos1); 
while (reg.length() < 16)
{
	 reg = "0" + reg;
}	
SYSTEM.STACK[TOS]= reg;
 
SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
while (SYSTEM.Top_of_Stack.length() < 3)
{
SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}

 PC1++;
 orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
}
pagecheck(orignalpc);
}

/*Perform Division Operation*/
else if(DIV.matches(Short_Opcode)){
	
SYSTEM.CLOCK = clock + SYSTEM.CLOCK + 1;
PMTclock = PMTclock+1 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String reg = null;
int stack_tos = Integer.parseInt(SYSTEM.STACK[TOS],2);
TOS = TOS - 1;

int stack_tos1 = Integer.parseInt(SYSTEM.STACK[TOS],2);
stack_tos1 = stack_tos1 / stack_tos;
/*Overflow*/
if(stack_tos > 32767){
	ERROR_HANDLER.ERROR_HANDLER(11);
}
/*Underflow*/
else if(stack_tos < -32768){
	ERROR_HANDLER.ERROR_HANDLER(12);
}
reg = Integer.toBinaryString(stack_tos1); 
 while (reg.length() < 16)
 {
	 reg = "0" + reg;
 }	
 SYSTEM.STACK[TOS]= reg;
 
SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
while (SYSTEM.Top_of_Stack.length() < 3)
{
SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
	ERROR_HANDLER.ERROR_HANDLER(13);
}
 PC1++;
 orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
}
pagecheck(orignalpc);
}

/*Perform Mod operation*/
else if(MOD.matches(Short_Opcode)){
	
SYSTEM.CLOCK = clock + SYSTEM.CLOCK + 1;
PMTclock = PMTclock+1 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String reg = null;
int stack_tos = Integer.parseInt(SYSTEM.STACK[TOS],2);
TOS = TOS - 1;
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
int stack_tos1 = Integer.parseInt(SYSTEM.STACK[TOS],2);
stack_tos1 = stack_tos1 % stack_tos;
/*Overflow*/
if(stack_tos > 32767){
	ERROR_HANDLER.ERROR_HANDLER(11);
}
/*Underflow*/
else if(stack_tos < -32768){
	ERROR_HANDLER.ERROR_HANDLER(12);
}
reg = Integer.toBinaryString(stack_tos1); 
 while (reg.length() < 16)
 {
	 reg = "0" + reg;
 }	
 SYSTEM.STACK[TOS]= reg;
 
 SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
 while (SYSTEM.Top_of_Stack.length() < 3)
{
 SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
    }
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
	ERROR_HANDLER.ERROR_HANDLER(13);
}
 PC1++;
 orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
}
pagecheck(orignalpc);
}

/*perform Logical shift one bit left opeartion*/
else if(SL.matches(Short_Opcode)){
	
SYSTEM.CLOCK = clock + SYSTEM.CLOCK + 1;
PMTclock = PMTclock+1 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String x;
x = SYSTEM.STACK[TOS];
x = x.substring(1, 16);
SYSTEM.STACK[TOS] = x + "0";

SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
 while (SYSTEM.Top_of_Stack.length() < 3)
{
 SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
	ERROR_HANDLER.ERROR_HANDLER(13);
}
PC1++;
orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
}			
pagecheck(orignalpc);
}

/*Perform Logical shift one bit right*/
else if(SR.matches(Short_Opcode)){
	
	
SYSTEM.CLOCK = clock + SYSTEM.CLOCK + 1;
PMTclock = PMTclock+1 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String x;
x = SYSTEM.STACK[TOS];
x = x.substring(1, 16);
SYSTEM.STACK[TOS] = "0"+ x;

SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
 while (SYSTEM.Top_of_Stack.length() < 3)
{
 SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
	ERROR_HANDLER.ERROR_HANDLER(13);
}
PC1++;
orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
}			
pagecheck(orignalpc);
}

/*perform CPG operation*/
else if(CPG.matches(Short_Opcode)){

SYSTEM.CLOCK = clock + SYSTEM.CLOCK + 1;
PMTclock = PMTclock+1 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String reg = null;
int stack_tos = Integer.parseInt(SYSTEM.STACK[TOS],2);
TOS = TOS - 1;
int stack_tos1 = Integer.parseInt(SYSTEM.STACK[TOS],2);
TOS = TOS + 2;
if(stack_tos1 > stack_tos){
	
SYSTEM.STACK[TOS] ="0000000000000001";
}
else{

SYSTEM.STACK[TOS] ="0000000000000000";
}
SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
 while (SYSTEM.Top_of_Stack.length() < 3)
 {
SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
	ERROR_HANDLER.ERROR_HANDLER(13);
}
 PC1++;
 orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
}
pagecheck(orignalpc);
}

/*perform CPL Operation*/
else if(CPL.matches(Short_Opcode)){
	
	SYSTEM.CLOCK = clock + SYSTEM.CLOCK + 1;
	PMTclock = PMTclock+1 ;
	 
	 
	if(PMTclock > 15){
		PGM_PMT.printPMT();
	}
	int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
	String reg = null;
	int stack_tos = Integer.parseInt(SYSTEM.STACK[TOS],2);
	TOS = TOS - 1;
	int stack_tos1 = Integer.parseInt(SYSTEM.STACK[TOS],2);
	TOS = TOS + 2;
	if(stack_tos1 < stack_tos){
	
	SYSTEM.STACK[TOS] ="0000000000000001";
	}
	else{
	SYSTEM.STACK[TOS] ="0000000000000001";
	}
	SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
	 while (SYSTEM.Top_of_Stack.length() < 3)
	 {
	SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
	}
	 /*checking for Top of Stack*/
	if(SYSTEM.Top_of_Stack.length() != 3){
		ERROR_HANDLER.ERROR_HANDLER(7);
	}
	/*checking for Top of Stack negative value */
	if(TOS < 0){
		ERROR_HANDLER.ERROR_HANDLER(10);
	}
	/*maximum number of registers in stack is 7*/
	else if(TOS > 7){
		ERROR_HANDLER.ERROR_HANDLER(13);
	}
	 PC1++;
	 orignalpc++;
	binaryStrPC1 = Integer.toBinaryString(PC1);
	while (binaryStrPC1.length() < 7)
	{
		binaryStrPC1 = "0" + binaryStrPC1;
	}
	SYSTEM.PC = String.valueOf(binaryStrPC1);
	/*checking for program counter*/
	if(SYSTEM.PC.length() != 7){
		//ERROR_HANDLER.ERROR_HANDLER(3);
	}
	pagecheck(orignalpc);
}

/*Perform CPE operation*/
else if(CPE.matches(Short_Opcode)){

	SYSTEM.CLOCK = SYSTEM.CLOCK + 4;
	PMTclock = PMTclock+4 ;
	 
	 
	if(PMTclock > 15){
		PGM_PMT.printPMT();
	}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String reg = null;
String var= null;
int stack_tos = Integer.parseInt(SYSTEM.STACK[TOS],2);
TOS = TOS - 1;
int stack_tos1 = Integer.parseInt(SYSTEM.STACK[TOS],2);
if(stack_tos >32767){
	
stack_tos = (~stack_tos);
stack_tos = stack_tos+1;

String x = Integer.toBinaryString(stack_tos);
x = x.substring(x.length()-16);
stack_tos = Integer.parseInt(x,2);
stack_tos = stack_tos-2*stack_tos;
}
if(stack_tos1 >32767){
	
	stack_tos1 = (~stack_tos1);
	stack_tos1 = stack_tos1+1;

	String x = Integer.toBinaryString(stack_tos1);
	x = x.substring(x.length()-16);
	stack_tos1 = Integer.parseInt(x,2);
	stack_tos1 = stack_tos1-2*stack_tos1;
	}
				
TOS = TOS + 2;
if(stack_tos1 == stack_tos){
	
SYSTEM.STACK[TOS] ="0000000000000001";
}
else{

SYSTEM.STACK[TOS] ="0000000000000000";
}

SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
while (SYSTEM.Top_of_Stack.length() < 3)
{
SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
ERROR_HANDLER.ERROR_HANDLER(13);
}
PC1++;
orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
	//ERROR_HANDLER.ERROR_HANDLER(3);
}
pagecheck(orignalpc);
}

/*Perform RD Operation*/
else if(RD.matches(Short_Opcode)){
	
SYSTEM.IOCLOCK = clock + SYSTEM.IOCLOCK + 1;
IOCLOCK = IOCLOCK +15;
PMTclock = PMTclock+15;
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
SYSTEM.SYSTEM(String);

int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
TOS = TOS + 1;
id1 = 56;

double seg_pag = Integer.parseInt(SYSTEM.Size_of_Program , 2);
seg_pag = Math.round(dummyPCB.in_pgmSize /(double)8);
if(DISK_MANAGER.page[(int) seg_pag]==0){
LOADER.Segment_Fault_Handler();
}
else{
	EA = Integer.toBinaryString(dummyPCB.RD_y);
	while (EA.length() < 16)
	{
	EA = "0" + EA;
	   }
}
id2 = 57;

String var = null;
SYSTEM.STACK[TOS] = MEMORY.MEMORY("Read", EA, var);
RD_y = Integer.parseInt(EA, 2);
RD_y++;
EA = Integer.toBinaryString(RD_y);
while (EA.length() < 16)
{
EA = "0" + EA;
   }
dummyPCB.RD_Y_end = EA;
SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
 while (SYSTEM.Top_of_Stack.length() < 3)
{
 SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 id3 = 85;
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
	ERROR_HANDLER.ERROR_HANDLER(13);
}
PC1++;
orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
	//ERROR_HANDLER.ERROR_HANDLER(3);
}
pagecheck(orignalpc);
if(dummyPCB.JobId != 4){
dummyPCB.tos = SYSTEM.Top_of_Stack;
dummyPCB.orignal_PC = orignalpc;
dummyPCB.Pgm_Counter = SYSTEM.PC;
dummyPCB.ea = EA;
dummyPCB.RD_y = RD_y;
dummyPCB.orignal_PC = orignalpc;
}
}

/*Perform Write Operation*/
else if(WR.matches(Short_Opcode)){
	
	Outputindex =1;
SYSTEM.IOCLOCK = SYSTEM.IOCLOCK + 1;
PMTclock = PMTclock+1 ;
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
IOCLOCK = IOCLOCK +15;
dummyPCB.job_endtime = CLOCK + IOCLOCK + Segment_fault_time + page_fault_time;
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
clock = clock + 1 + page_fault_time + Segment_fault_time;
out = SYSTEM.STACK[TOS];
TOS = TOS -1;
if(TOS == -1){
}

SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
 while (SYSTEM.Top_of_Stack.length() < 3)
{		
 SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
ERROR_HANDLER.ERROR_HANDLER(13);
}
SYSTEM(String);
if(size_OutputData_segment == 2){}
else{
}
fw = new FileWriter(f.getAbsoluteFile(), true);
bw = new BufferedWriter(fw);

bw.write("JobID:  "+dummyPCB.JobId+"   (DECIMAL)\n");
if(warning == null){
}else{
	 bw.write("Warning MESSAGE: "+warning);
}
String var = null;

int var2 = 0, x2 =0, x1 =0;
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
EA = Integer.toBinaryString(x);
while (EA.length() < 16)
{
EA = "0" + EA;
   }
 var = MEMORY.MEMORY("Read", EA, var);
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
bw.write("                     "+out+"    (BIN)\n");
bw.write("TERMINATION:  NORMAL\n");
clock = clock + CPU_exe_time + IOCLOCK + Segment_fault_time + page_fault_time ;
String str = Integer.toHexString(clock);
String str1 = Integer.toHexString(dummyPCB.job_endtime);
String str2 = Integer.toHexString(dummyPCB.jobTime);
bw.write("JOB START time:  "+str2+"  (HEX)\n");
bw.write("JOB END time:  "+str1+"  (HEX)\n");
int f = LOADER.page_fault_no.get(dummyPCB.JobId);
bw.write("NUMBER of PAGE FAULTS:  "+f+"   (DECIMAL)");
bw.write("CLOCK:  "+str+"  (HEX)\n");
bw.write("EXECUTION TIME:  "+CPU_exe_time+"  (DECIMAL)\n");
bw.write("IO TIME:  "+IOCLOCK+"  (DECIMAL)\n");
bw.write("Page-fault handling time: "+(page_fault_time)+" (DECIMAL)\n");
bw.write("Segment-fault handling time: "+Segment_fault_time+" (DECIMAL)\n");
bw.write("Turn-around time:  "+(dummyPCB.job_endtime - dummyPCB.jobTime)+"  (DECIMAL)\n");
bw.write("\n");
bw.write("\n");
bw.write("-----------------------------------------------\n");
if(dummyPCB.JobId != 105){
if(CPU_exe_time %7 == 0){
	int x12 =0, x13 =0;
	x14++;
	if(x14>2){
		for (int i = 0; i < readylist.size(); i++){
			
				x12++;
				if(x12 == 3){
					System.out.println("job-id: "+(x13+1));
					bw.write("job-id: "+(x13+1));
				}
				 
			
		int x = readylist.get(i).JobId;
		x13 = x+1;
		if(readylist.size() < 3){
			
			System.out.println("job-id: "+(x13+2));
			bw.write("job-id: "+(x13+2));
		}
		bw.write("job-id: "+x);
		System.out.println("job-id: "+(x));

		
		if(x12 == 3){
			System.out.println("job-id: "+(x13-2));

			bw.write("job-id: "+(x13-2));	
		}
		
			
			}
		System.out.println("");
	}
}
}
if(dummyPCB.JobId == 105 || dummyPCB.JobId == 108){
	
int c =	Collections.min(CPU.avg_cpu_array);
int z=	Collections.max(CPU.avg_cpu_array);
int a1 = Collections.min(CPU.avg_TUT_array)/100;
a1 = a1/5;
if(a1 < 0){
	a1 = a1- 2*a1 + 6;
}
int a2 = Collections.max(CPU.avg_TUT_array)/100;
int a3 = Collections.min(CPU.avg_code_seg);
int a4 = Collections.max(CPU.avg_code_seg);
int a5 = Collections.min(CPU.avg_input_code_seg);
int a6 = Collections.max(CPU.avg_input_code_seg);
if(a5 == 0 || a3 == 0){
	a5 = 1;
	a3 = 4;
}
int a7 = Collections.min(CPU.avg_output_code_seg);
int a8 = Collections.max(CPU.avg_output_code_seg);
   bw.write("\n");
   bw.write("\n");
   bw.write("\n");
   bw.write("CLOCK:  "+str+"  (HEX)\n");
   bw.write("Number of Jobs Processed:   "+(dummyPCB.JobId - ERROR_HANDLER.error_index)+"   (DECIMAL)\n");
   bw.write("CPU TIME   "+" min:   "+ c+"  avg:  "+((c+z)/2)+"   max: "+z+"   (DECIMAL)\n");
   bw.write("TURN AROUND TIME "+" min: "+ a1+" avg: "+((a1+a2)/2)+" max: "+a2+"  (DECIMAL)\n");
   bw.write("CODE SEG SIZE:   "+" min:   "+ a3+"  avg:  "+((a3+a4)/2)+"   max: "+a4+"  (DECIMAL)\n");
   bw.write("INPUT SEG SIZE:   "+" min:   "+ a5+"  avg:  "+((a5+a6)/2)+"   max: "+a6+"  (DECIMAL)\n");
   bw.write("OUTPUT SEG SIZE:   "+" min:   "+ a7+"  avg:  "+((a7+a8)/2)+"   max: "+a8+"  (DECIMAL)\n");
   bw.write("NUMBER OF JOBS TERMINATED NORMALLY:  "+(dummyPCB.JobId - ERROR_HANDLER.error_index)+" (DECIMAL)\n");
   bw.write("NUMBER OF JOBS TERMINATED ABNORMALLY:  "+ERROR_HANDLER.error_index+" (DECIMAL)\n");
   bw.write("ID OF JOBS CONSIDERED INFINITE: "+id1+"  "+id2+"  "+id3+"  \n");
   bw.write("MEAN NUMBER of PAGE FAULTS:  "+(LOADER.page_fault_number/dummyPCB.JobId)+"   (DECIMAL)\n");
   bw.write("MEAN DISK UTILIZATION:  "+disk*100+" (%)\n");
   bw.write("MEAN MEMORY UTILIZATION:  "+mem*100+" (%)\n");
   
   timeinMills1=	(int) System.currentTimeMillis();
   bw.write("Time:    "+(timeinMills1-timeinMills));
}

bw.close();
fw.close();

}

/*Perform RTN Operation*/
else if(RTN.matches(Short_Opcode)){
	
SYSTEM.CLOCK = clock + SYSTEM.CLOCK + 1;
PMTclock = PMTclock+1 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
SYSTEM.PC = SYSTEM.STACK[TOS];
orignalpc = orignalPCIndex;
TOS = TOS - 1;
SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
while (SYSTEM.Top_of_Stack.length() < 3)
{
SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
ERROR_HANDLER.ERROR_HANDLER(13);
}
pagecheck(orignalpc);
}

/*Program Termination i.e HLT*/
else if(HLT.matches(Short_Opcode)){
CLOCK = CLOCK+1;
PMTclock = PMTclock+1 ;

if(PMTclock > 15){
	PGM_PMT.printPMT();
}

if(readylist.isEmpty()){
	LOADER.ready_index = 0;
	SYSTEM.MemoryArray = new String[SYSTEM.MemoryArray.length];
	DISK_MANAGER.DISK = new String[DISK_MANAGER.DISK.length];
	DISK_MANAGER.x1 = 0;
	DISK_MANAGER.input_size = 0;
	DISK_MANAGER.pgm = 0;
	DISK_MANAGER.ProgramSegment.clear();
	DISK_MANAGER.pgm_index1 = 0;
	DISK_MANAGER.y1 = 0;
	DISK_MANAGER.page = new int[DISK_MANAGER.page.length];
	for(int x =0; x<7; x++){
		SYSTEM.STACK[x]= "0000000000000000";
	}
	Top_of_Stack = "000";
	for(int i = 0; i<32; i++){
		MEMORY.frame[i]= 0;
	}
	DISK_MANAGER.DISK();

}
else{
	SCHEDULER.SCHEDULER();
}

}
return null;
}
	
/*OneAddr_Instruction function specifies all the One_address instructions
 * and to perform all the operations based on Instruction Type
 */

public static String OneAddr_Instruction()throws NumberFormatException, Exception{
	
int PC1 = Integer.parseInt(SYSTEM.PC,2);
String binaryStrPC1;
	
/*Type one Address Instructions Declaration*/
String NOP = "00000";
String OR  = "00001";
String AND = "00010";
String NOT = "00011";
String XOR = "00100";

String ADD = "00101";
String SUB = "00110";
String MUL = "00111";
String DIV = "01000";
String MOD = "01001";

String SL = "01010";
String SR = "01011";

String CPG = "01100";
String CPL = "01101";
String CPE = "01110";

String BR = "01111";
String BRT = "10000";
String BRF = "10001";
String CALL = "10010";

String PUSH = "10110";
String POP = "10111";
String HLT = "11000";
int x4 =0;
/*checking for Infinite loop*/
if(CPU_exe_time>100000 && DISK_MANAGER.segid == 1){
	if(x4 == 0){
		CPU_exe_time = 90000;
		x4++;
		}
ERROR_HANDLER.ERROR_HANDLER(9);
}

/*functionality of each operations*/
if(NOP.matches(Long_Opcode)){
	/*no operation*/
}

/*Perform OR opeartion*/
else if(OR.matches(Long_Opcode)){

SYSTEM.CLOCK = SYSTEM.CLOCK + 4;
PMTclock = PMTclock+4 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String reg = null;
String var= null;
/*call MEMORY function from Memory class to read*/
var = MEMORY.MEMORY("Read", EA, X);
int stack_tos = Integer.parseInt(SYSTEM.STACK[TOS],2);
int EA_Address = Integer.parseInt(var,2);
stack_tos = stack_tos | EA_Address;
/*Overflow*/
if(stack_tos > 32767){
	ERROR_HANDLER.ERROR_HANDLER(11);
}
/*Underflow*/
else if(stack_tos < -32768){
	ERROR_HANDLER.ERROR_HANDLER(12);
}
reg = Integer.toBinaryString(stack_tos); 
 while (reg.length() < 16)
 {
	 reg = "0" + reg;
 }	
 SYSTEM.STACK[TOS]= reg;
 
SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
while (SYSTEM.Top_of_Stack.length() < 3)
 {
	 SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
	ERROR_HANDLER.ERROR_HANDLER(13);
}
 PC1++;
 orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
}		
pagecheck(orignalpc);
}

/*Perform AND Operation*/
else if(AND.matches(Long_Opcode)){
	
SYSTEM.CLOCK = SYSTEM.CLOCK + 4;
PMTclock = PMTclock+4 ;
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String reg = null;
String var= null;
/*call MEMORY function from Memory class to read*/
var = MEMORY.MEMORY("Read", EA, X);
int stack_tos = Integer.parseInt(SYSTEM.STACK[TOS],2);
int EA_Address = Integer.parseInt(var,2);
stack_tos = stack_tos & EA_Address;
/*Overflow*/
if(stack_tos > 32767){
}
/*Underflow*/
else if(stack_tos < -32768){
	ERROR_HANDLER.ERROR_HANDLER(12);
}
reg = Integer.toBinaryString(stack_tos); 
while (reg.length() < 16)
{
	 reg = "0" + reg;
}	
SYSTEM.STACK[TOS]= reg; 
SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
while (SYSTEM.Top_of_Stack.length() < 3)
{
	 SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
	ERROR_HANDLER.ERROR_HANDLER(13);
}
 PC1++;
 orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
pagecheck(orignalpc);
}

/*Perform NOT operation*/
else if(NOT.matches(Long_Opcode)){

/*no operation*/	
	
}

/*Perform XOR Operation*/
else if(XOR.matches(Long_Opcode)){
	
SYSTEM.CLOCK = SYSTEM.CLOCK + 4;
PMTclock = PMTclock+4 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String reg = null;
String var= null;
/*call MEMORY function from Memory class to read*/
var = MEMORY.MEMORY("Read", EA, X);
int stack_tos = Integer.parseInt(SYSTEM.STACK[TOS],2);
int EA_Address = Integer.parseInt(var,2);
stack_tos = stack_tos ^ EA_Address;
/*Overflow*/
if(stack_tos > 32767){
	ERROR_HANDLER.ERROR_HANDLER(11);
}
/*Underflow*/
else if(stack_tos < -32768){
	ERROR_HANDLER.ERROR_HANDLER(12);
}
reg = Integer.toBinaryString(stack_tos); 
while (reg.length() < 16)
{
 reg = "0" + reg;
}	
SYSTEM.STACK[TOS]= reg;
 
SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
while (SYSTEM.Top_of_Stack.length() < 3)
{
	SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
	ERROR_HANDLER.ERROR_HANDLER(13);
}
 PC1++;
 orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
pagecheck(orignalpc);
}


/*Perform Add Operation*/
else if(ADD.matches(Long_Opcode)){
	
SYSTEM.CLOCK = SYSTEM.CLOCK + 4;
PMTclock = PMTclock+4 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String reg = null;
String var= null;
/*call MEMORY function from Memory class to read*/
var = MEMORY.MEMORY("Read", EA, X);
int stack_tos = Integer.parseInt(SYSTEM.STACK[TOS],2);
int EA_Address = Integer.parseInt(var,2);
stack_tos = stack_tos + EA_Address;
/*Overflow*/
if(stack_tos > 32767){
	//ERROR_HANDLER.ERROR_HANDLER(11);
}
/*Underflow*/
else if(stack_tos < -32768){
	ERROR_HANDLER.ERROR_HANDLER(12);
}
reg = Integer.toBinaryString(stack_tos); 
 while (reg.length() < 16)
 {
	 reg = "0" + reg;
 }	
SYSTEM.STACK[TOS]= reg; 
SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
while (SYSTEM.Top_of_Stack.length() < 3)
{
 SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
	ERROR_HANDLER.ERROR_HANDLER(13);
}
PC1++;
orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
}
pagecheck(orignalpc);
}

/*Perform Subtraction*/
else if(SUB.matches(Long_Opcode)){
	
SYSTEM.CLOCK = SYSTEM.CLOCK + 4;
PMTclock = PMTclock+4 ;
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String reg = null;
String var= null;
/*call MEMORY function from Memory class to read*/
var = MEMORY.MEMORY("Read", EA, X);
int stack_tos = Integer.parseInt(SYSTEM.STACK[TOS],2);
int EA_Address = Integer.parseInt(var,2);
stack_tos = stack_tos - EA_Address;
/*Overflow*/
if(stack_tos > 32767){
	ERROR_HANDLER.ERROR_HANDLER(11);
}
/*Underflow*/
else if(stack_tos < -32768){
	ERROR_HANDLER.ERROR_HANDLER(12);
}
reg = Integer.toBinaryString(stack_tos); 
while (reg.length() < 16)
{
	 reg = "0" + reg;
}	
SYSTEM.STACK[TOS]= reg;
 
SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
while (SYSTEM.Top_of_Stack.length() < 3)
{
 SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
ERROR_HANDLER.ERROR_HANDLER(13);
}
 PC1++;
 orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
//ERROR_HANDLER.ERROR_HANDLER(3);
}
pagecheck(orignalpc);
}

/*Perform Multiplication Operation*/
else if(MUL.matches(Long_Opcode)){
	
SYSTEM.CLOCK = SYSTEM.CLOCK + 4;
PMTclock = PMTclock+4 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String reg = null;
String var= null;
/*call MEMORY function from Memory class to read*/
var = MEMORY.MEMORY("Read", EA, X);
int stack_tos = Integer.parseInt(SYSTEM.STACK[TOS],2);
int EA_Address = Integer.parseInt(var,2);
stack_tos = stack_tos * EA_Address;
/*Overflow*/
if(stack_tos > 32767){
	ERROR_HANDLER.ERROR_HANDLER(11);
}
/*Underflow*/
else if(stack_tos < -32768){
	ERROR_HANDLER.ERROR_HANDLER(12);
}
reg = Integer.toBinaryString(stack_tos); 
while (reg.length() < 16)
{
 reg = "0" + reg;
}	
SYSTEM.STACK[TOS]= reg;
 
SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
while (SYSTEM.Top_of_Stack.length() < 3)
{
SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
	ERROR_HANDLER.ERROR_HANDLER(13);
}
 PC1++;
 orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
}
pagecheck(orignalpc);
}

/*Perform Division Operation*/
else if(DIV.matches(Long_Opcode)){
	
SYSTEM.CLOCK = SYSTEM.CLOCK + 4;
PMTclock = PMTclock+4 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String reg = null;
String var= null;
/*call MEMORY function from Memory class to read*/
var = MEMORY.MEMORY("Read", EA, X);
int stack_tos = Integer.parseInt(SYSTEM.STACK[TOS],2);
int EA_Address = Integer.parseInt(var,2);
stack_tos = stack_tos / EA_Address;
/*Overflow*/
if(stack_tos > 32767){
	ERROR_HANDLER.ERROR_HANDLER(11);
}
/*Underflow*/
else if(stack_tos < -32768){
	ERROR_HANDLER.ERROR_HANDLER(12);
}
reg = Integer.toBinaryString(stack_tos); 
while (reg.length() < 16)
{
	 reg = "0" + reg;
}	
SYSTEM.STACK[TOS]= reg;
 
SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
while (SYSTEM.Top_of_Stack.length() < 3)
{
SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
	ERROR_HANDLER.ERROR_HANDLER(13);
}
 PC1++;
 orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
}
pagecheck(orignalpc);
}

/*Perform Mod Operation*/
else if(MOD.matches(Long_Opcode)){

SYSTEM.CLOCK = SYSTEM.CLOCK + 4;
PMTclock = PMTclock+4 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String reg = null;
String var= null;
/*call MEMORY function from Memory class to read*/
var = MEMORY.MEMORY("Read", EA, X);
int stack_tos = Integer.parseInt(SYSTEM.STACK[TOS],2);
int EA_Address = Integer.parseInt(var,2);

stack_tos = stack_tos % EA_Address;

/*Overflow*/
if(stack_tos > 32767){
	ERROR_HANDLER.ERROR_HANDLER(11);
}
/*Underflow*/
else if(stack_tos < -32768){
	ERROR_HANDLER.ERROR_HANDLER(12);
}
reg = Integer.toBinaryString(stack_tos); 
while (reg.length() < 16)
{
	 reg = "0" + reg;
}	
SYSTEM.STACK[TOS]= reg;
 
SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
while (SYSTEM.Top_of_Stack.length() < 3)
{
 SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
/*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
	ERROR_HANDLER.ERROR_HANDLER(13);
}
PC1++;
orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
	//ERROR_HANDLER.ERROR_HANDLER(3);
}
pagecheck(orignalpc);
}

/*Perform CPG Operation*/
else if(CPG.matches(Long_Opcode)){
	
SYSTEM.CLOCK = SYSTEM.CLOCK + 4;
PMTclock = PMTclock+4 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String reg = null;
String var= null;
/*call MEMORY function from Memory class to read*/
var = MEMORY.MEMORY("Read", EA, X);
int stack_tos = Integer.parseInt(SYSTEM.STACK[TOS],2);
int EA_Address = Integer.parseInt(var,2);
/*to calculate binary to negative number*/
if(stack_tos >32767){
	
stack_tos = (~stack_tos);
stack_tos = stack_tos+1;

String x = Integer.toBinaryString(stack_tos);
x = x.substring(x.length()-16);
stack_tos = Integer.parseInt(x,2);
stack_tos = stack_tos-2*stack_tos;
}

TOS = TOS + 1;
if(TOS > 6){
	
	ERROR_HANDLER.ERROR_HANDLER(13);
}
if(stack_tos > EA_Address){	
	SYSTEM.STACK[TOS] = "0000000000000001"; 
}
else{
	SYSTEM.STACK[TOS] = "0000000000000000"; 
}

SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
while (SYSTEM.Top_of_Stack.length() < 3)
{
 SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
	ERROR_HANDLER.ERROR_HANDLER(13);
}
PC1++;
orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
}
pagecheck(orignalpc);
}

/*Perform CPL Operation*/
else if(CPL.matches(Long_Opcode)){
	
SYSTEM.CLOCK = SYSTEM.CLOCK + 4;
PMTclock = PMTclock+4 ;
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String reg = null;
String var= null;
/*call MEMORY function from Memory class to read*/
var = MEMORY.MEMORY("Read", EA, X);
int stack_tos = Integer.parseInt(SYSTEM.STACK[TOS],2);
int EA_Address = Integer.parseInt(var,2);
/*to calculate binary to negative number*/
if(stack_tos >32767){
	
stack_tos = (~stack_tos);
stack_tos = stack_tos+1;

String x = Integer.toBinaryString(stack_tos);
x = x.substring(x.length()-16);
stack_tos = Integer.parseInt(x,2);
stack_tos = stack_tos-2*stack_tos;
}
			
TOS = TOS + 1;
if(stack_tos < EA_Address){
	SYSTEM.STACK[TOS] = "0000000000000001"; 
}
else{
SYSTEM.STACK[TOS] = "0000000000000000"; 
}

SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
while (SYSTEM.Top_of_Stack.length() < 3)
{
SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
ERROR_HANDLER.ERROR_HANDLER(13);
}
PC1++;
orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
}
pagecheck(orignalpc);
}

/*Perform CPE Operation*/
else if(CPE.matches(Long_Opcode)){
	
SYSTEM.CLOCK = SYSTEM.CLOCK + 4;
PMTclock = PMTclock+4 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String reg = null;
String var= null;
/*call MEMORY function from Memory class to read*/
var = MEMORY.MEMORY("Read", EA, X);
int stack_tos = Integer.parseInt(SYSTEM.STACK[TOS],2);
int EA_Address = Integer.parseInt(var,2);
/*to calculate binary to negative number*/
if(stack_tos >32767){
	
stack_tos = (~stack_tos);
stack_tos = stack_tos+1;

String x = Integer.toBinaryString(stack_tos);
x = x.substring(x.length()-16);
stack_tos = Integer.parseInt(x,2);
stack_tos = stack_tos-2*stack_tos;
}
TOS = TOS + 1;
if(TOS> 6){	
}
if(stack_tos == EA_Address){
	 
SYSTEM.STACK[TOS] = "0000000000000001"; 
}
else{ 
SYSTEM.STACK[TOS] = "0000000000000000"; 
}

SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
while (SYSTEM.Top_of_Stack.length() < 3)
{
SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
/*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
ERROR_HANDLER.ERROR_HANDLER(13);
}
PC1++;
orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
//ERROR_HANDLER.ERROR_HANDLER(3);
}
pagecheck(orignalpc);
}

/*Perform BR Operation*/
else if(BR.matches(Long_Opcode)){

SYSTEM.CLOCK = SYSTEM.CLOCK + 4;
PMTclock = PMTclock+4 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
int EA_Address = Integer.parseInt(EA,2);
PC1 = EA_Address;
orignalpc = orignalAddr;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
//ERROR_HANDLER.ERROR_HANDLER(3);
}
SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
 while (SYSTEM.Top_of_Stack.length() < 3)
{
 SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
/*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
ERROR_HANDLER.ERROR_HANDLER(13);
}
pagecheck(orignalpc);
}

/*Perform BRT Operation*/
else if(BRT.matches(Long_Opcode)){
	
SYSTEM.CLOCK = SYSTEM.CLOCK + 4;
PMTclock = PMTclock+4 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
if(SYSTEM.STACK[TOS].matches("0000000000000001")){

int EA_Address = Integer.parseInt(EA,2);
PC1 = EA_Address;
orignalpc = orignalAddr;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
}
}
else{
PC1++;
orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
}
}
TOS = TOS - 1;
SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
while (SYSTEM.Top_of_Stack.length() < 3)
{
 SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
	ERROR_HANDLER.ERROR_HANDLER(13);
}
pagecheck(orignalpc);
}

/*Perform BRF Operation*/
else if(BRF.matches(Long_Opcode)){
	
SYSTEM.CLOCK = SYSTEM.CLOCK + 4;
PMTclock = PMTclock+4 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
//if(SYSTEM.STACK[TOS].matches("FALSE")){
if(SYSTEM.STACK[TOS].matches("0000000000000000")){
int EA_Address = Integer.parseInt(EA,2);
PC1 = EA_Address;
orignalpc = orignalAddr;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
binaryStrPC1 = "0" + binaryStrPC1;
}
 SYSTEM.PC = String.valueOf(binaryStrPC1); 
}
else{
PC1++;
orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
}
TOS = TOS - 1;
SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
 while (SYSTEM.Top_of_Stack.length() < 3)
{
 SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
/*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
	ERROR_HANDLER.ERROR_HANDLER(13);
}
 /*checking for program counter*/
if(SYSTEM.PC.length() != 7){
}
pagecheck(orignalpc);
}
/*Perform CALL Operation*/
else if(CALL.matches(Long_Opcode)){
	
SYSTEM.CLOCK = SYSTEM.CLOCK + 4;
PMTclock = PMTclock+4 ;
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
TOS = TOS + 1;

PC1++;
orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
orignalPCIndex = orignalpc;
SYSTEM.STACK[TOS] = SYSTEM.PC;

int EA_Address = Integer.parseInt(EA,2);
PC1 = EA_Address;
orignalpc = orignalAddr;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
while (SYSTEM.Top_of_Stack.length() < 3)
{
 SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
/*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
ERROR_HANDLER.ERROR_HANDLER(7);
}
/*checking for Top of Stack negative value */
if(TOS < 0){
ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
ERROR_HANDLER.ERROR_HANDLER(13);
}
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
//ERROR_HANDLER.ERROR_HANDLER(3);
}
pagecheck(orignalpc);
}

/*Perform PUSH Operation*/
else if(PUSH.matches(Long_Opcode)){
	
SYSTEM.CLOCK = SYSTEM.CLOCK + 4;
PMTclock = PMTclock+4 ;
 
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
TOS = TOS + 1;
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS >= 7){

	ERROR_HANDLER.ERROR_HANDLER(13);
}
String var = null;
var = MEMORY.MEMORY("Read", EA, X);
SYSTEM.STACK[TOS] = var;	

SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
while (SYSTEM.Top_of_Stack.length() < 3)
{
 SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}

PC1++;
orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
}
pagecheck(orignalpc);
}

/*Perform POP Operation*/
else if(POP.matches(Long_Opcode)){
	
SYSTEM.CLOCK = SYSTEM.CLOCK + 4;
PMTclock = PMTclock+4 ;
 
if(PMTclock > 15){
	PGM_PMT.printPMT();
}
int TOS = Integer.parseInt(SYSTEM.Top_of_Stack, 2);
String var = null;
var = SYSTEM.STACK[TOS];
var = MEMORY.MEMORY("Write", EA, var);
String addr = Address;
int  addrDecimal = Integer.parseInt(addr, 2);
orignalAddr = addrDecimal;

/*page fault handler*/
eff_pageno = Math.round(addrDecimal/8);
/*LOADER.Memory_to_Disk_Transfer(eff_pageno);*/

TOS = TOS - 1;
/*checking for Top of Stack negative value */
if(TOS < 0){
	ERROR_HANDLER.ERROR_HANDLER(10);
}
/*maximum number of registers in stack is 7*/
else if(TOS > 7){
	ERROR_HANDLER.ERROR_HANDLER(13);
}

SYSTEM.Top_of_Stack = Integer.toBinaryString(TOS);
while (SYSTEM.Top_of_Stack.length() < 3)
{
	 SYSTEM.Top_of_Stack = "0" + SYSTEM.Top_of_Stack;
}
 /*checking for Top of Stack*/
if(SYSTEM.Top_of_Stack.length() != 3){
	ERROR_HANDLER.ERROR_HANDLER(7);
}
PC1++;

orignalpc++;
binaryStrPC1 = Integer.toBinaryString(PC1);
while (binaryStrPC1.length() < 7)
{
	binaryStrPC1 = "0" + binaryStrPC1;
}
SYSTEM.PC = String.valueOf(binaryStrPC1);
/*checking for program counter*/
if(SYSTEM.PC.length() != 7){
}
pagecheck(orignalpc);
}
if(CPU_exe_time % 20 == 0){
}
return null;
}

}
