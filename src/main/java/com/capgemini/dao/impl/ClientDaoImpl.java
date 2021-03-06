package com.capgemini.dao.impl;

import com.capgemini.dao.ClientDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.ClientEntity;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDaoImpl extends AbstractDao<ClientEntity, Long> implements ClientDao {

    @Override
    public Integer getRentalsCount(Long clientID) {
        ClientEntity client = findOne(clientID);
        return client.getRentals().size();
    }
}
