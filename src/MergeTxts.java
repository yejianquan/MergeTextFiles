import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class MergeTxts {
	static int index = 0;

	public static void main(String[] args) throws Exception {
		String sFilePath = "C:\\w71";
		String sOutPutFileName = "\\systems.txt";
		File fileDir = new File(sFilePath);
		File[] textFiles = fileDir.listFiles();
		for (int i = 0; i < textFiles.length; i++) {
			if (textFiles[i].isFile() && textFiles[i].getName().endsWith(".txt")) {
				System.out.println("Absolute path:" + textFiles[i].getAbsolutePath());
				System.out.println("File name:" + textFiles[i].getName());
				FileReaderAll(textFiles[i].getCanonicalPath(), "GBK", sFilePath + sOutPutFileName);
				// System.out.println("内容"+content);
				System.out.println("Length for current file:" + textFiles[i].length());
				System.out.println("Lines in total:" + index);
				System.out.println("------------------------------------------");
			}
		}
		System.out.println(textFiles.length + "Files are merged");
	}
	public static void FileReaderAll(String FileName, String charset, String outPutFileName) throws IOException {
		File fOutPut = new File(outPutFileName);
		if (fOutPut.exists() == false) {
			fOutPut.createNewFile();
		}
		BufferedWriter bwOutPut = new BufferedWriter(new FileWriter(fOutPut, true));
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FileName), charset));
		String str = reader.readLine();
		// while ((reader.readLine()) != null) //a line end with a symbol like
		// "\n"
		while (str != null) // a line ends with a symbol like "\n"
		{
			// temp +=reader.readLine();
			// temp +=reader.readLine()+"\n";
			try {
				bwOutPut.write(str.split("	")[1]);
				bwOutPut.newLine();
			} catch (Exception e) {
				System.out.println("Exception:"+str);
			}
			// System.out.println(str.split(" ")[1]);
			index++;
			str = reader.readLine();
		}
		reader.close();
		bwOutPut.flush();
		bwOutPut.close();
	}
}
