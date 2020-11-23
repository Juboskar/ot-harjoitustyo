
package com.example.paastopaivakirja.domain;

import com.example.paastopaivakirja.dao.AccountRepository;
import com.example.paastopaivakirja.dao.FoodEmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oskari
 */
@Service
public class FoodService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    FoodEmissionRepository foodEmissionRepository;

}
