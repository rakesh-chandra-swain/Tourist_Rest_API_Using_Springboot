package com.nt.service;

import java.util.List;

import com.nt.entity.Tourist;

public interface ITouristMgmtservice {
	public String registerTourist(Tourist tourist);
	public List<Tourist> showAlltraveller();
	public Tourist fetchTouristById(int tid);
	public String updateTourist(Tourist tourist);
	public String updateTouristAddress(int tid,String newAddrs);
	//public List<Tourist> showTouristByAge(int minAge);
}
