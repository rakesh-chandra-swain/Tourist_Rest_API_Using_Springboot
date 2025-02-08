package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Tourist;
import com.nt.repository.ITouristRepository;
@Service("touristService")
public class ITouristMgmtServiceImpl implements ITouristMgmtservice {
	
	@Autowired
	private ITouristRepository touristRepo;

	@Override
	public String registerTourist(Tourist tourist) {
		int idVal=touristRepo.save(tourist).getTid();
		return "Tourist is Registred having the id Value::"+idVal;
	}

	@Override
	public List<Tourist> showAlltraveller() {
		
		return touristRepo.findAll().stream().sorted((t1,t2)->t1.getName().compareTo(t2.getName())).toList();
	}

	@Override
	public Tourist fetchTouristById(int tid) {
	
		return touristRepo.findById(tid)
                .orElseThrow(()->new IllegalArgumentException(  "Tourist not found::"+tid));

	}

	@Override
	public String updateTourist(Tourist tourist) {
		//load object
		Optional<Tourist> opt=touristRepo.findById(tourist.getTid());
		//update the object
		if(opt.isPresent()) {
			touristRepo.save(tourist);
			return tourist.getTid()+"Tourist is updated";
		}
		return  tourist.getTid()+"Tourist is not updated";
	}

	@Override
	public String updateTouristAddress(int tid, String newAddrs) {
		
		//load object
		Optional<Tourist> opt=touristRepo.findById(tid);
	
		if(opt.isPresent()) {
			//update the object
		Tourist tourist=opt.get();
		 tourist.setCity(newAddrs);
		 touristRepo.save(tourist);
			return tourist.getTid()+"Tourist Address is updated";
		}
		return  tid+"Tourist is not updated";
	}

}
