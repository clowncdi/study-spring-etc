package filesplit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class FileSplit {
	public static void main(String[] args) throws Exception {

		try {
			String fileName = "D:\\ctd\\CTD_genes_diseases.csv";
			long splitLine = 100000;
			boolean firstHeadLine = false;
			String tmpStr = "";
			String firstLineStr = "";

			System.out.println("원본 파일명 : " + fileName);
			System.out.println("파일당 줄수 : " + splitLine);
			System.out.println("머리글 여부 : " + firstHeadLine);

			if (firstHeadLine)
				splitLine++;

			File file = new File(fileName);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			int n = 1;
			long i = 0;
			File wrFile = new File("D:\\ctd\\temp\\data_"+n+".csv");
			FileWriter fw = new FileWriter(wrFile);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			while ((tmpStr=br.readLine()) != null) {
				if (i == 0)
					firstLineStr = tmpStr;
				if (tmpStr.substring(0, 1).equals("#"))
					continue;
				pw.println(tmpStr);
				i++;
				if (i >= splitLine) {
					pw.close();
					pw = null;
					bw.close();
					bw = null;
					fw.close();
					fw = null;
					wrFile = null;
					n++;
					wrFile = new File("D:\\ctd\\temp\\data_"+n+".csv");
					fw = new FileWriter(wrFile);
					bw = new BufferedWriter(fw);
					pw = new PrintWriter(bw);
					i = 0;
					if (firstHeadLine) {
						pw.println(firstLineStr);
						i++;
					}
				}
			}
			pw.close();
			pw = null;
			bw.close();
			bw = null;
			fw.close();
			fw = null;
			wrFile = null;

			br.close();
			fr.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
