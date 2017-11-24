package com.atsistemas.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atsistemas.entities.Comercial;
import com.atsistemas.repositories.ComercialRepository;
import com.atsistemas.services.ComercialService;
import com.google.common.collect.Lists;

@Service
@Transactional(readOnly=true)
public class ComercialServiceImpl implements ComercialService {

	@Autowired
	private ComercialRepository comercialRepository;
	
	
	@Transactional(readOnly=false)
	@Override
	public Comercial save(Comercial comercial) {
		return comercialRepository.save(comercial);
	}

	@Override
	public List<Comercial> findAll() {
		
		List<Comercial> comerciales = new ArrayList<>();
		comerciales = Lists.newArrayList(comercialRepository.findAll());
		return comerciales;
	}

	@Override
	public Page<Comercial> findAllPages(Pageable pageable) {
		
		List<Comercial> comerciales = new ArrayList<>();
		comerciales = Lists.newArrayList(comercialRepository.findAll());
		PageImpl<Comercial> comercialesPages = new PageImpl<>(comerciales, pageable, comercialRepository.count());
		return comercialesPages;
	}

	@Override
	public Comercial findById(Long id) {
		return comercialRepository.findOne(id);
	}

	@Transactional(readOnly=false)
	@Override
	public boolean delete(Long id) {
		comercialRepository.delete(id);
		return true;
	}

}
