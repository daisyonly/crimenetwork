package org.crimenetwork.oracle.service;

import org.crimenetwork.oracle.entity.cases.CaseBaseInfo;
import org.crimenetwork.oracle.repository.CaseBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



@Service("caseService")
public class CaseService {

    @Autowired
    private CaseBaseDao caseBaseDao;

    public Page<CaseBaseInfo> findAll(int page)
    {
        return  caseBaseDao.findAll(new PageRequest(page-1, 10));
    }
}
