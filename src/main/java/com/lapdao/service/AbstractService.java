package com.lapdao.service;

import java.util.List;
import java.util.Optional;

public interface AbstractService<M,ID> {
	Optional<M> load(ID arg0);

	Optional<M> save(M arg0);

	List<M> save(List<M> arg0);

	List<M> getAll();

	void addToInvokingQueue(String arg0, M arg1);

	Optional<M> getFromInvokingQueue(String arg0);

	boolean existInInvokingQueue(String arg0);

	Optional<M> removeFromInvokingQueue(String arg0);
	
}
