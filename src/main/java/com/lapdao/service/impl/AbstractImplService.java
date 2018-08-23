package com.lapdao.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.integration.annotation.MessageEndpoint;

@MessageEndpoint
public abstract class AbstractImplService<M, ID extends Serializable, R extends JpaRepository<M, ID>> {
	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	protected final Map<String, M> INVOKING_QUEUE = new ConcurrentHashMap();
	protected static final int SIZE = 20;
	
	@Autowired
	protected R repo;

	public Optional<M> load(ID id) {
		return (Optional<M>) Optional.ofNullable(this.repo.findById(id));
	}

	public Optional<M> save(M model) {
		try {
			return Optional.of(this.repo.save(model));
		} catch (Exception arg2) {
			this.LOGGER.error(arg2.getMessage(), arg2);
			return Optional.empty();
		}
	}

	public List<M> save(List<M> list) {
		try {
			return (List<M>) this.repo.saveAll(list);
		} catch (Exception arg2) {
			this.LOGGER.error(arg2.getMessage(), arg2);
			return new ArrayList(0);
		}
	}

	public List<M> getAll() {
		LinkedList all = new LinkedList();
		Object pageable = new PageRequest(0, 20);

		while (true) {
			Page page = this.repo.findAll((Pageable) pageable);
			if (!page.hasContent()) {
				return all;
			}

			all.addAll(page.getContent());
			pageable = ((Pageable) pageable).next();
		}
	}

	public void addToInvokingQueue(String key, M model) {
		this.INVOKING_QUEUE.put(key, model);
	}

	public Optional<M> getFromInvokingQueue(String key) {
		return Optional.ofNullable(this.INVOKING_QUEUE.get(key));
	}

	public boolean existInInvokingQueue(String key) {
		return this.INVOKING_QUEUE.containsKey(key);
	}

	public Optional<M> removeFromInvokingQueue(String key) {
		return Optional.ofNullable(this.INVOKING_QUEUE.remove(key));
	}
	
}
