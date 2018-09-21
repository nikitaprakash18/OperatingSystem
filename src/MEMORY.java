
import java.io.IOException;

public class MEMORY extends SYSTEM{
	
/*Read, Write  are strings whose initial
* values are assigned to 0, 1
 */
public static String Read = "0";
public static String Write = "1";
public static int[] frame = new int[32];	
public static int frame_no =0;
public static int reference_bit = 0;
public static int dirty_bit = 1;
public static int frame17 = 0;
public static int frame20 = 1;
public static int frameindex = 0;
/*MEMORY function has READ, effective 
 * address and a variable
 * and perform the 
 * operation based on those
 */
public static void reinitialize()
{
	for(int i = 0; i<32; i++){
		frame[i]= 0;
	}

frame[5]=0;
frame[8]=0;
frame[10]=0;
frame[17]=0;
frame[20]=0;
frame[31]=0;
	
}
public static String MEMORY(String X, String Y, String Z ) throws NumberFormatException, Exception{
	
if(X.matches("Read")){
	
/*checking whether effective address is greater 
*than 256 then it will call Error hander class*/
int EA1 = Integer.parseInt(Y,2);
if(EA1 > 255){
ERROR_HANDLER.ERROR_HANDLER(8);
}

/*assigns MemoryArray[EA1] to variable*/
Z = SYSTEM.MemoryArray[EA1];
CPU.X = Z;  
}
else if(X.matches("Write")){
int EA1;
if(Y.length()> 10){
EA1 = Integer.parseInt(Y,2);
}
else{
String EA2 = Integer.toBinaryString(Integer.parseInt(Y));
EA1 = Integer.parseInt(EA2,2);	     	
}

/*checking whether effective address is greater 
 *than 255 then it will call Error hander class*/

if(EA1 > 255){
ERROR_HANDLER.ERROR_HANDLER(8);
}	  	
/*assigns variable to MemoryArray[EA1]*/
SYSTEM.MemoryArray[EA1] = Z;
}
return Z;	
}
}
