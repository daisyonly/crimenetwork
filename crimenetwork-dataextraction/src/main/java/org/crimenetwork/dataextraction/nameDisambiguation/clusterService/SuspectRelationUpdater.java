package org.crimenetwork.dataextraction.nameDisambiguation.clusterService;


import java.util.ArrayList;
import java.util.List;

import org.crimenetwork.dataextraction.nameDisambiguation.data.SuspectDataHelper;
import org.crimenetwork.neo4j.entity.SuspectInfo;
import org.crimenetwork.neo4j.repository.SuspectInfoRepository;
import org.crimenetwork.oracle.entity.suspect.SuspectBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("suspectRelationUpdater")
public class SuspectRelationUpdater {
	@Autowired
	private SuspectInfoRepository suspectInfoRepository;
	
	@Autowired
	@Qualifier("suspectDataHelper")
	private SuspectDataHelper suspectDataHelper;
	
	@Autowired
	@Qualifier("clusterProcessor")
	private ClusterProcessor clusterProcessor;
	
	
	
	public void update(){
		suspectDataHelper.init();
		int count=suspectDataHelper.count;
		
		for(int dataIndex=0;dataIndex<count;dataIndex++){
			//if(dataIndex<744) continue;
			ArrayList<ArrayList<SuspectBaseInfo>> clusters=clusterProcessor.process(dataIndex);
			for(ArrayList<SuspectBaseInfo> cluster:clusters){
				List<SuspectInfo> suspectsNeo4j=new ArrayList<SuspectInfo>();
				for(SuspectBaseInfo one:cluster){	
					SuspectInfo tmp= suspectInfoRepository.findBySId(one.getId());
					if(tmp!=null){
						suspectsNeo4j.add(suspectInfoRepository.findBySId(one.getId()));
					}
				}
				for(int i=0;i<suspectsNeo4j.size();i++){
					for(int j=0;j<suspectsNeo4j.size();j++){
						if(i==j) continue;
						suspectsNeo4j.get(i).getIdenticalSuspects().add(suspectsNeo4j.get(j));
					}
				}
				suspectInfoRepository.save(suspectsNeo4j);
			}
		}	
	}

}
