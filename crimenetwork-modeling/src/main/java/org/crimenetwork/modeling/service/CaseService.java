package org.crimenetwork.modeling.service;

import org.crimenetwork.modeling.convert.instance.CaseConverter;
import org.crimenetwork.neo4j.entity.CounterfeitMoney;
import org.crimenetwork.neo4j.entity.CrimeCase;
import org.crimenetwork.neo4j.repository.CounterfeitMoneyRepository;
import org.crimenetwork.neo4j.repository.CrimeCaseRepository;
import org.crimenetwork.oracle.entity.cases.CaseBaseInfo;
import org.crimenetwork.oracle.entity.currency.JiabiBaseInfo;
import org.crimenetwork.oracle.repository.CaseBaseDao;
import org.crimenetwork.oracle.repository.JiabiBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service("caseService")
public class CaseService {
	final Logger logger = LoggerFactory.getLogger(SuspectService.class);

	@Autowired
	private CrimeCaseRepository crimeCaseRepository;
	
	@Autowired
	private CounterfeitMoneyRepository counterfeitMoneyRepository;

	@Autowired
	@Qualifier("jiabiBaseDao")
	private JiabiBaseDao jiabiBaseDao;

	@Autowired
	@Qualifier("caseBaseDao")
	private CaseBaseDao caseBaseDao;

	@Autowired
	private CaseConverter caseConverter;

	public void moveALLData() {
		System.out.println("Process case data start.");
		int onepage = 1000;
		long count = caseBaseDao.count();

		for (int i = 0; i <= count / onepage; i++) {
			Page<CaseBaseInfo> readPage = caseBaseDao.findAll(new PageRequest(
					i, onepage));
			for (CaseBaseInfo cbi : readPage.getContent()) {
				CrimeCase mCaseBaseInfo = (CrimeCase) caseConverter
						.setAndConvert(cbi, true);

				try {
					// System.out.println(mCaseBaseInfo.getcId()+":"+mCaseBaseInfo.getRegisterDate());
					mCaseBaseInfo.deleteRepeatingData();
					crimeCaseRepository.save(mCaseBaseInfo);
				} catch (Exception e) {
					// TODO: handle exception
					logger.error(
							"crimecase id " + mCaseBaseInfo.getcId() + ":", e);
				}
			}
			System.out.println("The number of completed items: "
					+ (i * onepage + readPage.getContent().size()));
		}
		System.out.println("Process case data end.");
		System.out.println();
	}

	public void updateCounterfeitMoney()
    {
    	System.out.println("Process case data start.");
    	
    	
    	int onepage=1000;
    	long count = caseBaseDao.count();
    	
    	for(int i=0;i<=count/onepage;i++){
    		Page<CaseBaseInfo> readPage = caseBaseDao.findAll(new PageRequest(i, onepage));
    		for(CaseBaseInfo cbi:readPage.getContent()){

                CrimeCase mCaseBaseInfo=crimeCaseRepository.findByCId(cbi.getId());
    			if(mCaseBaseInfo==null) continue;
    			if(cbi.getSuspects().isEmpty()&&!cbi.getCounterfeitMoneys().isEmpty()){
    				for(JiabiBaseInfo jb : cbi.getCounterfeitMoneys()){
    					CounterfeitMoney mjb=counterfeitMoneyRepository.findByFmid(jb.getFmid());
    					if(mjb!=null) mCaseBaseInfo.getCounterfeitMoneys().add(mjb);
    				}
    				crimeCaseRepository.save(mCaseBaseInfo);				
    			}				
        	}
    		System.out.println("The number of completed items: "+(i*onepage+readPage.getContent().size()));
    	}
    	System.out.println("Process case data end.");
    	System.out.println();
    }

}
