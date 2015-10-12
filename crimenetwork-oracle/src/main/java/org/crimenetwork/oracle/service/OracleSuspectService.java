package org.crimenetwork.oracle.service;

import org.crimenetwork.oracle.entity.suspect.SuspectBaseInfo;
import org.crimenetwork.oracle.repository.SuspectBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



@Service("oracleSuspectService")
public class OracleSuspectService {

    @Autowired
    private SuspectBaseDao suspectBaseDao;

    public Page<SuspectBaseInfo> findAll(int page)
    {
        return  suspectBaseDao.findAll(new PageRequest(page-1, 10));
    }
}
