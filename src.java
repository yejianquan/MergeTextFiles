package b0sd;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;
public class MergeTxts {
	static int index = 0;
//test change
	public static void main(String[] args) throws Exception {
		
		//String sFilePath = "C:\\i711206\\cbp";
		String sFilePath = "C:\\Users\\i309997\\Desktop\\Data\\Data\\b0sdsyst_I71_180320";
		String sOutPutFileName = "\\missedSyst.txt";
		String sOutPutFilePath = "C:\\cbp1207";
		String sOutPutFileCorrect = "\\success.txt";
		String sOutPutFileFail = "\\fail.txt";
		File fileDir = new File(sFilePath);
		File[] textFiles = fileDir.listFiles();
		//Hashtable htMissedSystems =prepareHashTable();
		Set sysNrSet = new HashSet();
		for (int i = 0; i < textFiles.length; i++) {
			if (textFiles[i].isFile() && textFiles[i].getName().endsWith(".txt")) {
				System.out.println("Absolute path:" + textFiles[i].getAbsolutePath());
				System.out.println("File name:" + textFiles[i].getName());
				FileReaderAll(textFiles[i].getCanonicalPath(), "GBK", sFilePath + sOutPutFileName, sysNrSet);
				//insertIntoDB(textFiles[i].getCanonicalPath(), "GBK", "i71");
				//SplitIntoTxts(textFiles[i].getCanonicalPath(), "GBK", sOutPutFilePath);
				//splitIntoSE(textFiles[i].getCanonicalPath(), "GBK", sFilePath+sOutPutFileCorrect, sFilePath+sOutPutFileFail);
				//findMissedSystems(textFiles[i].getCanonicalPath(), "GBK", sFilePath + sOutPutFileName, htMissedSystems);
				System.out.println("Length for current file:" + textFiles[i].length());
				System.out.println("Lines in total:" + index);
				System.out.println("------------------------------------------");
			}
		}
		//test();
		//insertIntoDBTMP();
		System.out.println(textFiles.length + "Files are merged");
		
	}
	public static void test(){
		String sTest = "001	000000000950011465	0000000003	0000000000	S0004199222	12.11.2013	16:52:51	0020079747	CPIC_CSR	12.11.2013	22:55:34	S0004199222	14.11.2013	11:02:19	0020079747	LIKEY_ISP	14.11.2013	10:43:04	0020079747	I309997	05.12.2017	10:39:17			X	0020259886	CNW	TEST			00.00.0000		00.00.0000	000	00000     0	CNW PI	I		00.00.0000		00.00.0000					00.00.0000		00.00.0000						   00000000            00000000	                  00000000            00000000                                OR	A	. .CLE				000																	01200615320800000721	01200615320900001504		";
		String[] aTest = sTest.split("	");
		
		String b = "2";
				
				
	}
	public static void FileReaderAll(String FileName, String charset, String outPutFileName,Set sysNrSet) throws IOException {
		File fOutPut = new File(outPutFileName);
		String sLast = "";
		if (fOutPut.exists() == false) {
			fOutPut.createNewFile();
		}
		BufferedWriter bwOutPut = new BufferedWriter(new FileWriter(fOutPut, true));
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FileName), charset));
		String str = reader.readLine();
		String[] aColumns;
		while (str != null) // a line ends with a symbol like "\n"
		{
			try {
				//bwOutPut.write(str);
				aColumns = str.split("	");
				if(aColumns[0].equals( "001" )&& (! sysNrSet.contains(aColumns[1]) )/* && aColumns[1].contains("0311194207")*/){
					/*sLast = aColumns[1];
					int i = 36;
					
					for(; i < 71 ; i++){
						aColumns[i] = "";
					}
					i= 29;
					for(; i < 34 ; i++){
						aColumns[i] = "";
					}
					if(! aColumns[56].matches("[0-9][0-9].[0-9][0-9].[0-9][0-9][0-9][0-9]") ){
						aColumns[56] = "00.00.0000";
					}
					if(! aColumns[60].matches("[0-9][0-9][0-9]") ){
						aColumns[60] = "000";
					}
					if(str.contains("00001266084")){
						String a = "";
						a = a+1;
					}
					bwOutPut.write(join(aColumns, "	"));
					*/
					if(aColumns[1] != sLast){
					bwOutPut.write(aColumns[1] + " " + aColumns[24]);
					bwOutPut.newLine();
					//System.out.println(aColumns[60]);
					sLast = aColumns[1];
					sysNrSet.add(aColumns[1]);
					index++;
					}else{
						int a = 0;
						int b = a++;
					}
				}else{
					//sLast = "asd";
				}
				
				
			} catch (Exception e) {
				System.out.println("Exception:"+str);
			}
			str = reader.readLine();
		}
		reader.close();
		bwOutPut.flush();
		bwOutPut.close();
	}
	public static void SplitIntoTxts(String FileName, String charset, String outPutFilePath) throws IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FileName), charset));
		String str = reader.readLine();
		String[] aColumns;
		String sLast = "";
		while (str != null) // a line ends with a symbol like "\n"
		{
			try {
				//bwOutPut.write(str);
				aColumns = str.split("	");
				if(aColumns[0].equals( "001" )){
					//System.out.println((index/100));
					//File fOutPut = new File(outPutFilePath + "\\B0SDSYST_"+ ((index/10)*10 + 1) +"-"+ (index/10 + 1)*10 +".txt");
					File fOutPutFolder = new File(outPutFilePath+ "\\" + (index/10000 + 1) );
					if (fOutPutFolder.exists() == false){
						fOutPutFolder.mkdirs();
					}
					File fOutPut = new File(fOutPutFolder.getCanonicalPath() +  "\\B0SDSYST_"+ index +".txt");
					
					
					if (fOutPut.exists() == false) {
						fOutPut.createNewFile();
					}
					BufferedWriter bwOutPut = new BufferedWriter(new FileWriter(fOutPut, true));
					
					bwOutPut.write(str);
					bwOutPut.newLine();
					index++;
					sLast = aColumns[1];		
					bwOutPut.flush();
					bwOutPut.close();
				}else{
					sLast = str.split("	")[1];
				}
				
				
			} catch (Exception e) {
				//System.out.println("Exception:"+str);
			}
			str = reader.readLine();
		}
		reader.close();
	}
	public static void splitIntoSE(String FileName, String charset, String outPutFileNameCorrect, String outPutFileNameFail) throws IOException {

		File fOutPutCorrect = new File(outPutFileNameCorrect);
		File fOutPutFail = new File(outPutFileNameFail);
		String sLast = "";
		if (fOutPutCorrect.exists() == false) {
			fOutPutCorrect.createNewFile();
		}
		if (fOutPutFail.exists() == false) {
			fOutPutFail.createNewFile();
		}
		BufferedWriter bwOutPutCorrect = new BufferedWriter(new FileWriter(fOutPutCorrect, true));
		BufferedWriter bwOutPutFail = new BufferedWriter(new FileWriter(fOutPutFail, true));
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FileName), charset));
		String str = reader.readLine();
		String[] aColumns;
		while (str != null) // a line ends with a symbol like "\n"
		{
			try {
				//bwOutPutCorrect.write(str);
				aColumns = str.split("	");
				if(aColumns[0].equals( "001" )  && (aColumns[60].matches("00[0-9]") && aColumns[56].matches("[0-9][0-9].[0-9][0-9].[0-9][0-9][0-9][0-9]")/*|| aColumns[60].equals("")*/ ) ){
					sLast = str;
					bwOutPutCorrect.write(str);
					bwOutPutCorrect.newLine();
					System.out.println(aColumns[60] + ":" + aColumns[56]);
					index++;
				}else{
					sLast = str;
					bwOutPutFail.write(str);
					bwOutPutFail.newLine();
				}
				
				
			} catch (Exception e) {
				System.out.println("Exception:"+str);
			}
			str = reader.readLine();
		}
		reader.close();
		bwOutPutCorrect.flush();
		bwOutPutCorrect.close();
		bwOutPutFail.flush();
		bwOutPutFail.close();
	}
	public static Hashtable prepareHashTable() throws IOException {
		Hashtable htSystems = new Hashtable<>();
		// 驱动程序名
        String driver = "com.mysql.jdbc.Driver";

        // URL指向要访问的数据库名scutcs
        String url = "jdbc:mysql://127.0.0.1:3306/compareow12"/*jdbc:mysql://127.0.0.1:3306/b0sdsystems"*//*"jdbc:mysql://127.0.0.1:3306/cbpinitialload"*/;

        // MySQL配置时的用户名
        String user = "root"; 

        // MySQL配置时的密码
        String password = "Zzz123.,";

        try { 
         // 加载驱动程序
         Class.forName(driver);

         // 连续数据库
         Connection conn = DriverManager.getConnection(url, user, password);

         if(!conn.isClosed()) 
          System.out.println("Succeeded connecting to the Database!");

         // statement用来执行SQL语句
         Statement statement = conn.createStatement();

         // 要执行的SQL语句
         String sql = "select * from i71_i72_inst";

         // 结果集
         ResultSet rs = statement.executeQuery(sql);

         //String name = null;
         int index = 0;
         while(rs.next()) {
 
          // 选择sname这列数据
          //name = rs.getString("sname");
 
          // 首先使用ISO-8859-1字符集将name解码为字节序列并将结果存储新的字节数组中。
          // 然后使用GB2312字符集解码指定的字节数组
          //name = new String(name.getBytes("ISO-8859-1"),"GB2312");

          // 输出结果
          htSystems.put(rs.getString(1), true);
          //System.out.println(rs.getString(1) + "\t" );
          index++;
          //System.out.println(index);
         }

         rs.close();
         conn.close();

        } catch(ClassNotFoundException e) {


         System.out.println("Sorry,can`t find the Driver!"); 
         e.printStackTrace();


        } catch(SQLException e) {


         e.printStackTrace();


        } catch(Exception e) {


         e.printStackTrace();


        } 
		return htSystems;
	}
	public static void insertIntoDB(String FileName, String charset, String table) throws IOException {
		Hashtable htSystems = new Hashtable<>();
		// 驱动程序名
        String driver = "com.mysql.jdbc.Driver";

        // URL指向要访问的数据库名scutcs
        String url = "jdbc:mysql://127.0.0.1:3306/1206b0sdsyst"/*jdbc:mysql://127.0.0.1:3306/b0sdsystems"*//*"jdbc:mysql://127.0.0.1:3306/cbpinitialload"*/;

        // MySQL配置时的用户名
        String user = "root"; 

        // MySQL配置时的密码
        String password = "admin";

        try { 
         // 加载驱动程序
         Class.forName(driver);

         // 连续数据库
         Connection conn = DriverManager.getConnection(url, user, password);

         if(!conn.isClosed()) 
          System.out.println("Succeeded connecting to the Database!");
         Statement statement = conn.createStatement();
         
 		String sLast = "";
 		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FileName), charset));
 		String str = reader.readLine();
 		String[] aColumns;
 		while (str != null) // a line ends with a symbol like "\n"
 		{
 			try {
 				//bwOutPut.write(str);
 				aColumns = str.split("	");
 				if(aColumns[0].equals( "001" )/* && aColumns[1].contains("0311194207")*/){
 					sLast = aColumns[1];
 				// statement用来执行SQL语句
 			         
 					//System.out.println(index);
 			         // 要执行的SQL语句
 			         String sql = "INSERT INTO " + table  + " VALUES('" + aColumns[1] + "')";

 			         // 结果集
 			         int rows = statement.executeUpdate(sql);   
 					//System.out.println(aColumns[60]);
 					index++;
 				}else{
 					sLast = "asd";
 				}
 				
 				
 			} catch (Exception e) {
 				System.out.println("Exception:"+str);
 			}
 			str = reader.readLine();
 		}
 		reader.close();
 		
         

         //String name = null;
         int index = 0;
         conn.close();

        } catch(ClassNotFoundException e) {


         System.out.println("Sorry,can`t find the Driver!"); 
         e.printStackTrace();


        } catch(SQLException e) {


         e.printStackTrace();


        } catch(Exception e) {


         e.printStackTrace();


        } 
	}
	public static void insertIntoDBTMP() throws IOException {
		int index1 = 0;
		String table = "csn";
		String errorString = "000000000950028410'),('000000000950028411'),('000000000950028412'),('000000000950028413'),('000000000950028414'),('000000000950028415'),('000000000950028416'),('000000000950028417'),('000000000950028418'),('000000000950028419'),('000000000950028420'),('000000000950028421'),('000000000950028422'),('000000000950028423'),('000000000950028424'),('000000000950028425'),('000000000950028426'),('000000000950028427'),('000000000950028428'),('000000000950028429'),('000000000950028430'),('000000000950028431'),('000000000950028432'),('000000000950028433'),('000000000950028434'),('000000000950028435'),('000000000950028436'),('000000000950028437'),('000000000950028438'),('000000000950028439'),('000000000950028440'),('000000000950028441'),('000000000950028442'),('000000000950028443'),('000000000950028444'),('000000000950028445'),('000000000950028446'),('000000000950028447'),('000000000950028448'),('000000000950028449'),('000000000950028450'),('000000000950028451'),('000000000950028452'),('000000000950028453'),('000000000950028454'),('000000000950028455'),('000000000950028456'),('000000000950028457'),('000000000950028458'),('000000000950028459'),('000000000950028460'),('000000000950028461'),('000000000950028462'),('000000000950028463'),('000000000950028464'),('000000000950028465'),('000000000950028466'),('000000000950028467'),('000000000950028468'),('000000000950028469'),('000000000950028470'),('000000000950028471'),('000000000950028472'),('000000000950028473'),('000000000950028474'),('000000000950028475'),('000000000950028476'),('000000000950028477'),('000000000950028478'),('000000000950028479'),('000000000950028473'),('000000000950028474'),('000000000950028475'),('000000000950028476'),('000000000950028477'),('000000000950028478'),('000000000950028479'),('000000000950028480'),('000000000950028481'),('000000000950028482'),('000000000950028483'),('000000000950028484'),('000000000950028485'),('000000000950028486'),('000000000950028487'),('000000000950028488'),('000000000950028489'),('000000000950028490'),('000000000950028491'),('000000000950028492'),('000000000950028493'),('000000000950028494'),('000000000950028495'),('000000000950028496'),('000000000950028497'),('000000000950028498'),('000000000950028499'),('000000000950028500'),('000000000950028501'),('000000000950028502";
		String[] aSysnr = errorString.split("'\\),\\('");
		// 驱动程序名
        String driver = "com.mysql.jdbc.Driver";

        // URL指向要访问的数据库名scutcs
        String url = "jdbc:mysql://127.0.0.1:3306/1206b0sdsyst"/*jdbc:mysql://127.0.0.1:3306/b0sdsystems"*//*"jdbc:mysql://127.0.0.1:3306/cbpinitialload"*/;

        // MySQL配置时的用户名
        String user = "root"; 

        // MySQL配置时的密码
        String password = "admin";

        try { 
         // 加载驱动程序
         Class.forName(driver);

         // 连续数据库
         Connection conn = DriverManager.getConnection(url, user, password);

         if(!conn.isClosed()) 
          System.out.println("Succeeded connecting to the Database!");
         Statement statement = conn.createStatement();
         
 		String sLast = "";
 		for(int i = 0 ; i < aSysnr.length ; i++)
 		{
 			try {
 				//bwOutPut.write(str);
 					sLast = aSysnr[i];
 				// statement用来执行SQL语句
 			         
 					//System.out.println(index);
 			         // 要执行的SQL语句
 			         String sql = "INSERT INTO " + table  + " VALUES('" + aSysnr[i] + "')";

 			         // 结果集
 			         int rows = statement.executeUpdate(sql);   
 					//System.out.println(aColumns[60]);
 			        index1++;
 				
 				
 			} catch (Exception e) {
 				System.out.println("Exception:"+ aSysnr[i]);
 			}
 		}
 		System.out.println("成功了:"+ index1);
         

         //String name = null;
         conn.close();

        } catch(ClassNotFoundException e) {


         System.out.println("Sorry,can`t find the Driver!"); 
         e.printStackTrace();


        } catch(SQLException e) {


         e.printStackTrace();


        } catch(Exception e) {


         e.printStackTrace();


        } 
	}
	public static void findMissedSystems(String FileName, String charset, String outPutFileName, Hashtable ht) throws IOException{

		File fOutPut = new File(outPutFileName);
		String sLast = "";
		if (fOutPut.exists() == false) {
			fOutPut.createNewFile();
		}
		BufferedWriter bwOutPut = new BufferedWriter(new FileWriter(fOutPut, true));
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FileName), charset));
		String str = reader.readLine();
		String[] aColumns;
		while (str != null) // a line ends with a symbol like "\n"
		{
			try {
				//bwOutPut.write(str);
				aColumns = str.split("	");
				if(aColumns[0].equals( "001" ) && ht.containsKey(aColumns[1])){
					sLast = aColumns[1];
					bwOutPut.write(str);
					bwOutPut.newLine();
					index++;
				}else{
					sLast = "asd";
				}
				
				
			} catch (Exception e) {
				System.out.println("Exception:"+str);
			}
			str = reader.readLine();
		}
		reader.close();
		bwOutPut.flush();
		bwOutPut.close();
	}
	public static String join( Object[] o , String flag ){  
        StringBuffer str_buff = new StringBuffer();  
        
        for(int i=0 , len=o.length ; i<len ; i++){  
            str_buff.append( String.valueOf( o[i] ) );  
            if(i<len-1)str_buff.append( flag );  
        }  
       
        return str_buff.toString();   
    }  
}
