package com.webWeavers.weaveGlow.biz.address;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("addressService")
public class AddressServiceImpl implements AddressService {

	@Autowired
	private IAddressDAO addressDAO;

	@Override
	public List<AddressDTO> selectAll(AddressDTO addressDTO) {
		Map<String,String>map = new HashMap<String,String>();
		map.put("memberID", addressDTO.getMemberID());
		 return addressDAO.selectAll(map);
	}

	@Override
	public AddressDTO selectOne(AddressDTO addressDTO) {
		 Map<String, Integer> map = new HashMap<String,Integer>();
		 map.put("addressPK", addressDTO.getAddressPK());
		 return addressDAO.selectOne(map);
	}

	@Override
	public boolean insert(AddressDTO addressDTO) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("memberID", addressDTO.getMemberID());
		map.put("addressZonecode", addressDTO.getAddressZonecode());
		map.put("addressJibun", addressDTO.getAddressJibun());
		map.put("addressRoad", addressDTO.getAddressRoad());
		map.put("addressDetail", addressDTO.getAddressDetail());
		map.put("addressName", addressDTO.getAddressName());
		return addressDAO.insert(map);
	}

	@Override
	public boolean update(AddressDTO addressDTO) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("addressZonecode", addressDTO.getAddressZonecode());
		map.put("addressJibun", addressDTO.getAddressJibun());
		map.put("addressRoad", addressDTO.getAddressRoad());
		map.put("addressDetail", addressDTO.getAddressDetail());
		map.put("addressName", addressDTO.getAddressName());
		map.put("addressPK", addressDTO.getAddressPK());
		return addressDAO.update(map);
	}

	@Override
	public boolean delete(AddressDTO addressDTO) {
		Map<String, Integer> map = new HashMap<String,Integer>();
		 map.put("addressPK", addressDTO.getAddressPK());
		 return addressDAO.delete(map);
	}

}
