package org.crimenetwork.core.nodesim.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.crimenetwork.core.entity.NetworkModel;
import org.crimenetwork.core.nodesim.SimRankFeatureGenerator;
import org.crimenetwork.core.service.SearchService;
import org.crimenetwork.core.utility.FileUtil;
import org.crimenetwork.neo4j.entity.CounterfeitMoney;
import org.crimenetwork.neo4j.entity.CrimeCase;
import org.crimenetwork.neo4j.entity.SuspectInfo;
import org.crimenetwork.neo4j.repository.CounterfeitMoneyRepository;
import org.crimenetwork.neo4j.repository.CrimeCaseRepository;
import org.crimenetwork.neo4j.repository.SuspectInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dataGenerator")
public class DataGenerator {
	
	@Autowired
	private SuspectInfoRepository suspectInfoRepository;
	
	@Autowired
	private CrimeCaseRepository crimeCaseRepository;
	
	@Autowired
	private CounterfeitMoneyRepository counterfeitMoneyRepository;
		
	@Autowired
	private SearchService searchService;
	
	private SimRankFeatureGenerator simRankFeatureGenerator=new SimRankFeatureGenerator();
	
	public void outputRankingSVMData(String inputFilePath,String outputFilePath){
		FileUtil inputFileUtil=new FileUtil(inputFilePath, "in", false);
		FileUtil outputFileUtil=new FileUtil(outputFilePath, "out", false);
		String line=null;
		int index=1;
		while((line=inputFileUtil.readLine())!=null){
			String[] tokens=line.split(",");
			String query=tokens[0];
			List<Long> candidates=new ArrayList<Long>();
			List<Long> lables=new ArrayList<Long>();
			for(int i=1;i<tokens.length;i++){
				String[] tmps=tokens[i].split(":");
				candidates.add(Long.parseLong(tmps[0]));
				lables.add(Long.parseLong(tmps[1]));
			}
			generateSVMdata(index,query, candidates,lables,outputFileUtil);
			++index;
		}
		inputFileUtil.close();
		outputFileUtil.close();
	}
	
	private void generateSVMdata(int index,String query,List<Long> candidatesId,
			List<Long> lables,FileUtil outputFileUtil){
		char flag=query.charAt(0);
		Long queryId = Long.parseLong(query.substring(1));
		if(flag=='J'){
			CounterfeitMoney queryObject= counterfeitMoneyRepository.findByFmid(queryId);
		    List<CounterfeitMoney> candidates=new ArrayList<CounterfeitMoney>();
		    for(Long id:candidatesId){
		    	candidates.add(counterfeitMoneyRepository.findByFmid(id));
		    }
		    List<List<Double>> data=new ArrayList<List<Double>>();
		    for(CounterfeitMoney cm:candidates){
		    	List<Double> features=simRankFeatureGenerator.generate(queryObject, cm);
		    	data.add(features);
		    }
		    outputSVMData(lables,data,index,outputFileUtil);
		}else if(flag=='C'){
			CrimeCase queryObject= crimeCaseRepository.findByCId(queryId);
		    List<CrimeCase> candidates=new ArrayList<CrimeCase>();
		    for(Long id:candidatesId){
		    	candidates.add(crimeCaseRepository.findByCId(id));
		    }
		    List<List<Double>> data=new ArrayList<List<Double>>();
		    for(CrimeCase cm:candidates){
		    	List<Double> features=simRankFeatureGenerator.generate(queryObject, cm);
		    	data.add(features);
		    }
		    outputSVMData(lables,data,index,outputFileUtil);
		}else{
			SuspectInfo queryObject= suspectInfoRepository.findBySId(queryId);
		    List<SuspectInfo> candidates=new ArrayList<SuspectInfo>();
		    for(Long id:candidatesId){
		    	candidates.add(suspectInfoRepository.findBySId(id));
		    }
		    List<List<Double>> data=new ArrayList<List<Double>>();
		    for(SuspectInfo cm:candidates){
		    	List<Double> features=simRankFeatureGenerator.generate(queryObject, cm);
		    	data.add(features);
		    }
		    outputSVMData(lables,data,index,outputFileUtil);
		}
		
	}
	
	public Map<Long,Map<String, Double>> generateRankData(String query,List<Long> candidatesId){
		char flag=query.charAt(0);
		Long queryId = Long.parseLong(query.substring(1));
	    Map<Long,Map<String, Double>> data=new HashMap<Long,Map<String, Double>>();
		if(flag=='J'){
			CounterfeitMoney queryObject= counterfeitMoneyRepository.findByFmid(queryId);
		    List<CounterfeitMoney> candidates=new ArrayList<CounterfeitMoney>();
		    for(Long id:candidatesId){
		    	candidates.add(counterfeitMoneyRepository.findByFmid(id));
		    }
		    for(CounterfeitMoney cm:candidates){
		    	Map<String, Double> features=simRankFeatureGenerator.getFeatureMap('J',simRankFeatureGenerator.generate(queryObject, cm));
		    	data.put(cm.getFmid(), features);
		    }
		    
		}else if(flag=='C'){
			CrimeCase queryObject= crimeCaseRepository.findByCId(queryId);
		    List<CrimeCase> candidates=new ArrayList<CrimeCase>();
		    for(Long id:candidatesId){
		    	candidates.add(crimeCaseRepository.findByCId(id));
		    }
		    for(CrimeCase cm:candidates){
		    	Map<String, Double> features=simRankFeatureGenerator.getFeatureMap('C',simRankFeatureGenerator.generate(queryObject, cm));
		    	data.put(cm.getcId(), features);
		    }
		}else{
			SuspectInfo queryObject= suspectInfoRepository.findBySId(queryId);
		    List<SuspectInfo> candidates=new ArrayList<SuspectInfo>();
		    for(Long id:candidatesId){
		    	candidates.add(suspectInfoRepository.findBySId(id));
		    }
		    for(SuspectInfo cm:candidates){
		    	Map<String, Double> features=simRankFeatureGenerator.getFeatureMap('S',simRankFeatureGenerator.generate(queryObject, cm));
		    	data.put(cm.getsId(), features);
		    }
		}
		return data;
		
	}
	
	 
     private void outputSVMData(List<Long> lables,List<List<Double>> data,int id ,FileUtil file){
    	 file.writeLine("# query "+id);
    	 for(int i=0;i<data.size();i++){
    		 StringBuilder tmp=new StringBuilder();
    		 tmp.append(lables.get(i)+" qid:").append(id);
    		 int index=1;
    		 for(double feature: data.get(i)){
    			 tmp.append(" "+index+":"+feature);
    			 ++index;
    		 }
    		 file.writeLine(tmp.toString());
    	 }  
     }
     
     
     public  void outputLabelData(String id,String outputFilePath){
    	 FileUtil outputFileUtil=new FileUtil(outputFilePath, "out", true);
    	 NetworkModel result= searchService.searchByObjectId(id, 10);
    	 char flag=id.charAt(0);
    	 List<Long> candidates=new ArrayList<Long>();
    	 for(Map.Entry<String, HashMap<String, String>> entry:result.nodes.entrySet()){
    		 char cur= entry.getKey().charAt(0);
    		 if(cur==flag){
    			 candidates.add(Long.parseLong(entry.getKey().substring(1)));
    		 }
    	 }
    	 generateLabeldata(id,candidates,outputFileUtil);
    	 outputFileUtil.close();
    	 
     }
     
     private void generateLabeldata(String query,List<Long> candidatesId,
 			FileUtil outputFileUtil){
 		char flag=query.charAt(0);
 		Long queryId = Long.parseLong(query.substring(1));
 		outputFileUtil.writeLine("query:"+query);
 		if(flag=='J'){
 			CounterfeitMoney queryObject= counterfeitMoneyRepository.findByFmid(queryId);
 		    List<CounterfeitMoney> candidates=new ArrayList<CounterfeitMoney>();
 		    for(Long id:candidatesId){
 		    	candidates.add(counterfeitMoneyRepository.findByFmid(id));
 		    }
 		   
 		    for(CounterfeitMoney cm:candidates){
 		    	List<Double> features=simRankFeatureGenerator.generate(queryObject, cm);
 		    	outputFileUtil.writeLine("id:"+cm.getFmid()+"\t"+simRankFeatureGenerator.outputFeature(flag, features));
 		    	
 		    }
 		}else if(flag=='C'){
 			CrimeCase queryObject= crimeCaseRepository.findByCId(queryId);
 		    List<CrimeCase> candidates=new ArrayList<CrimeCase>();
 		    for(Long id:candidatesId){
 		    	candidates.add(crimeCaseRepository.findByCId(id));
 		    }
 		   
 		    for(CrimeCase cm:candidates){
 		    	List<Double> features=simRankFeatureGenerator.generate(queryObject, cm);
 		    	outputFileUtil.writeLine("id:"+cm.getcId()+"\t"+simRankFeatureGenerator.outputFeature(flag, features));
 		    }
 		    
 		}else{
 			SuspectInfo queryObject= suspectInfoRepository.findBySId(queryId);
 		    List<SuspectInfo> candidates=new ArrayList<SuspectInfo>();
 		    for(Long id:candidatesId){
 		    	candidates.add(suspectInfoRepository.findBySId(id));
 		    }
 		    for(SuspectInfo cm:candidates){
 		    	List<Double> features=simRankFeatureGenerator.generate(queryObject, cm);		    	
 		    	outputFileUtil.writeLine("id:"+cm.getsId()+"\t"+simRankFeatureGenerator.outputFeature(flag, features));
 		    }
 		}
 		
 	}
}
