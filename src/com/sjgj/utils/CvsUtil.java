package com.sjgj.utils;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import org.junit.Test;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by javalittleman on 2016/8/18.
 */
public class CvsUtil {
	/**
	 * CSV导出
	 *
	 * @throws Exception
	 */
	@Test
	public void exportCsv() throws IOException {
//		String srcCSV = "F:/cnt_programa.csv";
		String srcCSV = "C:/Users/dell/Desktop/ceshi.csv";
//		String targetFile = "F:/test.csv";
		CsvReader reader = new CsvReader(srcCSV, ',', Charset.forName("GBK"));
//		CsvWriter write = new CsvWriter(targetFile, ',', Charset.forName("UTF-8"));
		// 各字段以引号标记
//		write.setForceQualifier(true);
		// 路过表头
//		 r.readHeaders();
		// 逐条读取记录，直至读完
		String[] header = {};
		int i=0;
		while (reader.readRecord()) {
			
			// 把头保存起来
			if (reader.getCurrentRecord() == 0) {
				header = reader.getValues();
			}
			// 获取当前记录位置
//			System.out.print(reader.getCurrentRecord() + ".");
			// 读取一条记录
//			if (i>0) {
////				System.out.println(reader.getRawRecord());
//			}
				
			
			
			String[] tmp = { reader.getValues()[0], reader.getValues()[1] };
			// 修改记录，并只写入第一个字段和第二字段
			if (!header[1].equals(tmp[1]) && ("".equals(tmp[1]) || tmp == null)) {
				tmp[1] = "空";
//				write.writeRecord(tmp);
//				System.out.println(tmp);
			} else {
				if (i>0) {
					
				
//				write.writeRecord(new String[] { reader.getValues()[0], reader.getValues()[1] });
//				System.out.println(reader.getValues()[0].toString());
				System.out.println(reader.getValues()[1].toString());
				System.out.println(reader.getValues()[13].toString());
				System.out.println(reader.getValues()[16].toString());
				System.out.println(reader.getValues()[17].toString());
				System.out.println(reader.getValues()[19].toString());
				System.out.println(reader.getValues()[26].toString());
				}
			
			}
			i=i+1;
		}
		System.out.println(i);
		reader.close();
//		write.close();
	}
}