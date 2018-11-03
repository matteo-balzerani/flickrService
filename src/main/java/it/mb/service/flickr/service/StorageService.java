package it.mb.service.flickr.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.mb.service.flickr.configuration.StorageConfiguration;
import it.mb.service.flickr.service.util.FlickrServiceException;

@Service
public class StorageService {

	Logger log = LoggerFactory.getLogger(StorageService.class);

	@Autowired
	private StorageConfiguration config;

	/**
	 * save the image in local storage
	 * @param url
	 * @param filename
	 * @param format
	 * @return
	 * @throws FlickrServiceException
	 */
	public String downloadPhoto(String url, String filename, String format) throws FlickrServiceException {
		String path = Paths.get(config.getFolder(), filename + "." + format).toString();
		if (filename == null || filename.trim().isEmpty() || format == null || format.trim().isEmpty()) {
			log.error("error on filename or format");
			throw new FlickrServiceException("filename or format exception");
		}
		HttpsURLConnection httpsConnection = null;
		try {
			httpsConnection = (HttpsURLConnection) (new URL(url).openConnection());
		} catch (IOException e) {
			log.error(e.getMessage());
			throw new FlickrServiceException(e, "IOException on httpsConnection");
		}
		try (BufferedInputStream in = new BufferedInputStream((httpsConnection.getInputStream()));
				FileOutputStream fout = new FileOutputStream(path)) {
			final byte data[] = new byte[1024];
			int count;
			long downloadedFileSize = 0;
			while ((count = in.read(data, 0, 1024)) != -1) {
				fout.write(data, 0, count);
				downloadedFileSize = downloadedFileSize + count;
			}
			log.debug("Downloaded photo {}", filename + "." + format);
			new File(path);
		} catch (IOException e) {
			log.error(e.getMessage());
			throw new FlickrServiceException(e, "IOException on downloadAndSave Photo");
		}
		return path;
	}
}
