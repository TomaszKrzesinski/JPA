package com.capgemini.dao.impl;

import com.capgemini.dao.AgencyDao;
import com.capgemini.dao.RankDao;
import com.capgemini.domain.AgencyEntity;
import com.capgemini.domain.RankEntity;
import org.springframework.stereotype.Repository;

@Repository
public class RankDaoImpl extends AbstractDao<RankEntity, Long> implements RankDao {

}
