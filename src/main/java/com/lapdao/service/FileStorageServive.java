package com.lapdao.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;

import com.lapdao.common.internal.exception.CommonException;

public interface FileStorageServive {
	void init() throws CommonException;

	  public String write(String file) throws CommonException;

	  byte[] read(String fileName);

	  boolean isExisted(String fileName);

	  Resource loadFile(String filename) throws CommonException;

	  Stream<Path> loadFiles() throws CommonException;

}
