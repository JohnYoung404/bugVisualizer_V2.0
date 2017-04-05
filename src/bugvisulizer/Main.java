package bugvisulizer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;
import com.google.common.io.Files;

import cn.edu.thu.tsmart.tool.bd.report.FaultResult;
import cn.edu.thu.tsmart.tool.bd.report.Report;
import cn.edu.thu.tsmart.tool.bd.report.section.AbstractSection;
import cn.edu.thu.tsmart.tool.bd.report.section.Location;
import cn.edu.thu.tsmart.tool.bd.report.section.TransferRelation;
import cn.edu.thu.tsmart.tool.bd.report.util.ResultUtil;

public class Main {
	public static String projectPath = "/home/joungyoung/Desktop";    
	public static String reportPath = "/home/joungyoung/Desktop/report/result.xml";
	public static String escape(String src) {                    //encode转义字符
        int i;  
        char j;  
        StringBuffer tmp = new StringBuffer();  
        tmp.ensureCapacity(src.length() * 6);  
        for (i = 0; i < src.length(); i++) {  
            j = src.charAt(i);  
            if (Character.isDigit(j) || Character.isLowerCase(j)  
                    || Character.isUpperCase(j))  
                tmp.append(j);  
            else if (j < 256) {  
                tmp.append("%");  
                if (j < 16)  
                    tmp.append("0");  
                tmp.append(Integer.toString(j, 16));  
            } else {  
                tmp.append("%u");  
                tmp.append(Integer.toString(j, 16));  
            }  
        }  
        return tmp.toString();  
    }
	public static void main(String[] args) throws Exception {
		final List<File> files = new ArrayList<>();
		if(!projectPath.endsWith("/")){
			projectPath = projectPath + "/";					//若路径结尾没有'/'则加上
		}

		String errorlist = new String();             //缺陷路径的列表
		String errorcontent = new String();             //每条缺陷路径的具体内容
		try {
			Report report = ResultUtil.readFromFile(reportPath);
			final List<String> strs = new ArrayList();
			final List<String> strs2 = new ArrayList();
			final List<String> strs3 = new ArrayList();
			for (FaultResult fault : report.getFaultResults()) {
				StringBuilder sb_temp = new StringBuilder();
				StringBuilder sb_temp2 = new StringBuilder();
				sb_temp.append("{id:");
				sb_temp.append(fault.getId());
				sb_temp.append(",severity:\"");
				sb_temp.append(fault.getSeverity());
				sb_temp.append("\",confidence:\"");
				sb_temp.append(fault.getConfidence());
				sb_temp.append("\",weakness:\"");
				sb_temp.append(fault.getWeakness());
				sb_temp.append("\"}");
				strs.add(sb_temp.toString());
				sb_temp2.append("\"");
				sb_temp2.append(fault.getId());
				sb_temp2.append("\":");
				
				List<AbstractSection> section =  fault.getWitness();
				for(AbstractSection s : section){
					if(s.getOrientation().getFile().equals("<none>")){
						continue;
					}
					if (s instanceof Location) {
						//Location loc = (Location) s;
						continue;
					} else if (s instanceof TransferRelation) {
						StringBuilder node = new StringBuilder();
						node.append("{startline:");
						TransferRelation transfer = (TransferRelation) s;
						node.append(transfer.getOrientation().getStartCoordinate().getLineNumber());
						node.append(", endline:");
						node.append(transfer.getOrientation().getEndCoordinate().getLineNumber());
						node.append(", filePath:\"");
						node.append(transfer.getOrientation().getFile());
						node.append("\", supplementation:\"");
						node.append(transfer.getSupplementation());
						node.append("\"}");
						strs2.add(node.toString());
					}
				}
				StringBuilder nodeArray = new StringBuilder();
				nodeArray.append("[");
				nodeArray.append(Joiner.on(" , ").join(strs2));
				nodeArray.append("]");
				sb_temp2.append(nodeArray.toString());
				strs3.add(sb_temp2.toString());
				
			}
			errorlist = Joiner.on(" , ").join(strs);
			errorcontent = Joiner.on(" , ").join(strs3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		StringBuilder sb_errorlist = new StringBuilder();
		sb_errorlist.append("[");
		sb_errorlist.append(errorlist);
		sb_errorlist.append("]");
		
		StringBuilder sb_errorcontent = new StringBuilder();
		sb_errorcontent.append("{");
		sb_errorcontent.append(errorcontent);
		sb_errorcontent.append("}");
		
		FileWriter writer = new FileWriter("data/template/data/data.js");
		writer.write("var projectPath = \"" + projectPath + "\"");
		writer.write(";\nvar _FAULTS_SET = " + sb_errorlist.toString());
		writer.write(";\nvar faultID_Path_Dic = " + sb_errorcontent.toString() + ";");
		writer.close();
		System.out.println("done");
	}
}
