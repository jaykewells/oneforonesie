package com.ofonesie.ofonesie.models.data;


import com.ofonesie.ofonesie.models.Listing;
import org.springframework.data.repository.CrudRepository;

public interface ListingDao extends CrudRepository<Listing, Integer> {
}
