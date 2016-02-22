package org.crimenetwork.dataextraction.nameDisambiguation.data;

import java.util.ArrayList;

import org.crimenetwork.dataextraction.utility.FileUtil;
import org.crimenetwork.oracle.entity.suspect.SuspectBaseInfo;
import org.crimenetwork.oracle.repository.SuspectBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service("suspectDataHelper")
public class SuspectDataHelper {
	
	private ArrayList<ArrayList<SuspectBaseInfo>> data;
	public int count;
	
	@Autowired
	@Qualifier("suspectBaseDao")
    private SuspectBaseDao suspectBaseDao;
	
	public ArrayList<SuspectBaseInfo> getDataByGroupIndex(int index){
		if(data==null) loadData();
		if(index>=count) return null;
		return data.get(index);
	}
	
	private void loadData() {
		FileUtil fileUtil=new FileUtil("C:\\Users\\Daisy\\Desktop\\suspectData.csv","in", false);
		String line;
		data =new ArrayList<ArrayList<SuspectBaseInfo>>();
		ArrayList<SuspectBaseInfo> tmp=new ArrayList<SuspectBaseInfo>();
		line=fileUtil.readLine();
		line=fileUtil.readLine();
		String lastLine="start";
		count=0;
		while((line=fileUtil.readLine())!=null){		
			if(line.startsWith("重复次数")||line.equals("")){
				lastLine=line;
				continue;
			}
			
			if((lastLine.startsWith("重复次数")||lastLine.equals(""))&&
					!line.equals("")&&line.charAt(0)>='0'&&line.charAt(0)<='9'){
				data.add(tmp);
				tmp=new ArrayList<SuspectBaseInfo>();
				++count;
				lastLine=line;
			}
			
			
			String[] strs=line.split(",");
			Long id=Long.parseLong(strs[0]);
			SuspectBaseInfo sbi=suspectBaseDao.findById(id);
			tmp.add(sbi);
			System.out.println(line);
			lastLine=line;	
		}
		data.add(tmp);
		tmp=new ArrayList<SuspectBaseInfo>();
		++count;	
	}
	public static void main(String[] args) {
		String[] springConfig  = 
			{	"classpath:dataextraction/applicationContext.xml", 
			};
		
		@SuppressWarnings("resource")
		ApplicationContext context = 
				new ClassPathXmlApplicationContext(springConfig);
			
		SuspectDataHelper suspectDataHelper = (SuspectDataHelper) context.getBean("suspectDataHelper");
		suspectDataHelper.loadData();
		
        System.out.println( "Hello World!" );
	}
	


}
