package org.file.processing.utility;

import java.io.File;
import org.log.logger.LoggerAPI;

public class FileUtility {

	public static final String DOC_TYPE = "DOC_TYPE";
	public static final String MUSIC_TYPE = "MUSIC_TYPE";
	public static final String VIDEO_TYPE = "VIDEO_TYPE";
	public static final String IMAGE_TYPE = "IMAGE_TYPE";
	public static final String DIRECTORY_TYPE = "DIRECTORY_TYPE";
	public static final String UNABLE_TO_DETERMINE = "UNABLE_TO_DETERMINE";
	public static final String COMPRESSED_FILE = "COMPRESSED_FILE";

	private static final String[] DOC_TYPE_LIST = new String[] { ".pdf", ".txt", ".log", ".ppt", ".doc", ".xml",
			".xslt" };
	private static final String[] VIDEO__TYPE_LIST = new String[] { ".mkv", ".mp4" };
	private static final String[] MUSIC_TYPE_LIST = new String[] { ".aac", ".mp3" };
	private static final String[] IMAGE_TYPE_LIST = new String[] { ".jpg", ".jpeg", ".png", ".gif" };
	private static final String[] COMPRESSED_TYPE_LIST = new String[] { ".jar", ".rar", ".zip", ".tar", ".tar.gz",
			".tar.bz2", ".bz2" };

	public static String getFileType(String fileName) {
		try {
			if (fileName != null && !fileName.isEmpty() && fileName.contains(".")) {
				String extension = fileName.split(".")[1];
				if (checkInImageType(extension)) {
					return IMAGE_TYPE;
				} else if (checkInMusicType(extension)) {
					return MUSIC_TYPE;
				} else if (checkInVideoType(extension)) {
					return VIDEO_TYPE;
				} else if (checkInDocType(extension)) {
					return DOC_TYPE;
				} else if (checkInCompressedType(extension)) {
					return COMPRESSED_FILE;
				}
			} else {
				return DIRECTORY_TYPE;
			}
		} catch (Exception ex) {
			LoggerAPI.logError(ex);
		}
		return UNABLE_TO_DETERMINE;
	}

	public static boolean checkInMusicType(String extension) {
		for (String extens : MUSIC_TYPE_LIST) {
			if (extens.equalsIgnoreCase(extension.trim())) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkInVideoType(String extension) {
		for (String extens : VIDEO__TYPE_LIST) {
			if (extens.equalsIgnoreCase(extension.trim())) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkInDocType(String extension) {
		for (String extens : DOC_TYPE_LIST) {
			if (extens.equalsIgnoreCase(extension.trim())) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkInCompressedType(String extension) {
		for (String extens : COMPRESSED_TYPE_LIST) {
			if (extens.equalsIgnoreCase(extension.trim())) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkInImageType(String extension) {
		for (String extens : IMAGE_TYPE_LIST) {
			if (extens.equalsIgnoreCase(extension.trim())) {
				return true;
			}
		}
		return false;
	}

	public static File getFolderForTheFileType(String extension, String directory) {
		File folder = null;
		try {
			if (checkInImageType(extension)) {
				return createFolder(directory, "Images");
			} else if (checkInMusicType(extension)) {
				return createFolder(directory, "Music");
			} else if (checkInVideoType(extension)) {
				return createFolder(directory, "Videos");
			} else if (checkInDocType(extension)) {
				return createFolder(directory, "Docs");
			} else if (checkInCompressedType(extension)) {
				return createFolder(directory, "Compress");
			} else {
				return createFolder(directory, "Others");
			}
		} catch (Exception ex) {
			LoggerAPI.logError(ex);
			folder = new File(directory, "others");
			try {
				if (!folder.exists()) {
					folder.mkdir();
				}
			} catch (Exception e) {
				LoggerAPI.logError(e);
			}
		}
		return folder;
	}

	public static File createFolder(String dir, String folderName) throws Exception {
		File folder = new File(dir, folderName);
		if (!folder.exists()) {
			folder.mkdir();
		}
		return folder;
	}

	public static String getFileExtension(String fileName) {
		try {
			int length = fileName.length() - 1;
			while (length > 0) {
				if (fileName.charAt(length) == '.') {
					return fileName.substring(length, fileName.length());
				}
				length--;
			}
		} catch (Exception ex) {
			LoggerAPI.logError(ex);
		}
		return "";
	}

}
