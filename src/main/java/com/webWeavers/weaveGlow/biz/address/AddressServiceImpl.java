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
		 return addressDAO.selectAll(addressDTO.getMemberID());
	}

	@Override
	public AddressDTO selectOne(AddressDTO addressDTO) {
		 return addressDAO.selectOne(addressDTO.getAddressPK());
	}

	@Override
	public boolean insert(AddressDTO addressDTO) {
		// return addressDAO.insert(addressDTO);
	}

	@Override
	public boolean update(AddressDTO addressDTO) {
		// return addressDAO.update(addressDTO);
	}

	@Override
	public boolean delete(AddressDTO addressDTO) {
		// return addressDAO.delete(addressDTO);
	}

}
