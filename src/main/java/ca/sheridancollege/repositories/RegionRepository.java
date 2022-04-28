package ca.sheridancollege.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import ca.sheridancollege.beans.Region;
import ca.sheridancollege.beans.RegionType;

public interface RegionRepository extends JpaRepository<Region,Long>{
	public Region findByRegionName(String regionName);
	public List<Region> findByRegionType(RegionType regionType);
}
