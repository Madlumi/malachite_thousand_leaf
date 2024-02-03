package com.madanie.malachite_thousand_leaf.prospect;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/** Interface for managing Prospects */
@Repository
public interface ProspectRepository extends CrudRepository<Prospect, Long> {}
