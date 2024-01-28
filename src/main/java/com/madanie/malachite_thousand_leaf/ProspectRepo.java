package com.madanie.malachite_thousand_leaf;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface ProspectRepo extends CrudRepository<Prospect, Long> {
   ArrayList<Prospect> findAll();
   Prospect findById(long id);
}
