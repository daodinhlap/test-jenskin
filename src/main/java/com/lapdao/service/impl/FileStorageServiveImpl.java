package com.lapdao.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lapdao.common.internal.exception.CommonException;
import com.lapdao.common.internal.exception.ErrorCode;

public class FileStorageServiveImpl {

	protected Logger logger;
	private final Path rootLocation;
	protected final String folder;

	public FileStorageServiveImpl(final String folder) throws CommonException {
		this.logger = LoggerFactory.getLogger(getClass());

		logger.info("\n Init file storage folder: {}", folder);

		this.folder = folder.charAt(folder.length() - 1) == File.separatorChar ? folder : folder + File.separatorChar;

		rootLocation = Paths.get(folder);

		init();
	}

	public void init() throws CommonException {
		if (Files.exists(rootLocation)) {
			logger.info("\n File storage folder existed");
			return;
		}
		try {
			logger.info("\n File storage folder not exist");
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new CommonException(ErrorCode.UNKNOWN, "Could not initialize storage", "storageFile");
		}
	}

}