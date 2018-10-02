package com.krishimandi.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.krishimandi.application.KrishiMandiException;
import com.krishimandi.application.LanguageConstants;

public class Sample {

	public void mapLocalizationContent1() throws KrishiMandiException {

		/*String localizationDirectoryPath = new File("resources").getAbsolutePath();
		File localizationDirectory = new File(localizationDirectoryPath);
		File[] languages = null;
		if(null != localizationDirectory && null != localizationDirectoryPath) {

			languages = localizationDirectory.listFiles();
		}else {

			throw new KrishiMandiException("Error while loading localization content");
		}
		for (int i = 0; i < languages.length; i++) {

			System.out.println(languages[i].getAbsolutePath());
		}*/

		StringBuilder result = new StringBuilder("");

		try {

			File file = new File(getClass().getClassLoader().getResource("localization/languages").getFile());
			try (Scanner scanner = new Scanner(file)) {

				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					System.out.println("l: " + line);
					result.append(line).append("\n");
				}

				scanner.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mapLocalizationContent() throws KrishiMandiException {

		ArrayList<String> languagesList = getLanguagesList();

		if(null == languagesList || languagesList.isEmpty()) {
			
			throw new KrishiMandiException("Error while loading localization content: languages not available");
		}else {
			
//			for (int i = 0; i < languagesList.size(); i++) {
//
//				switch(languagesList.get(i)) {
//
//				case LanguageConstants.LANGUAGE_ENGLISH: 
//
//					LanguageConstants.MAP_LANGUAGE_ENGLISH = new HashMap<Integer, String>();
//					mapSpecificLangContent(LanguageConstants.MAP_LANGUAGE_ENGLISH, LanguageConstants.LANGUAGE_ENGLISH);
//					break;
//				case LanguageConstants.LANGUAGE_KANNADA:
//					LanguageConstants.MAP_LANGUAGE_KANNADA = new HashMap<Integer, String>();
//					mapSpecificLangContent(LanguageConstants.MAP_LANGUAGE_KANNADA, LanguageConstants.LANGUAGE_KANNADA);
//					break;
//				case LanguageConstants.LANGUAGE_HINDI:
//					LanguageConstants.MAP_LANGUAGE_HINDI = new HashMap<Integer, String>();
//					mapSpecificLangContent(LanguageConstants.MAP_LANGUAGE_HINDI, LanguageConstants.LANGUAGE_HINDI);
//					break;
//				default:
//					
//					throw new KrishiMandiException("Error while loading localization content: Invalid languages");
//				}
//			}
		}
	}

	private ArrayList<String> getLanguagesList() throws KrishiMandiException {

		ArrayList<String> languagesList = null;
		File languagesFile = new File(getClass().getClassLoader().getResource("localization/languages").getFile());
		if(null == languagesFile || !languagesFile.exists()) {

			throw new KrishiMandiException("Error while loading localization content: localization/languages");
		}else {

			try (Scanner scanner = new Scanner(languagesFile)){

				languagesList = new ArrayList<String>();
				while (scanner.hasNextLine()) {

					String languageCode = scanner.nextLine();
					if(null != languageCode) {

						languagesList.add(languageCode);
					}
				}
			} catch (FileNotFoundException e) {

				throw new KrishiMandiException("Error while loading localization content: " + e.getMessage());
			}
		}
		return languagesList;
	}
	
	private void mapSpecificLangContent(HashMap<Integer, String> launguageMap, String languageCode) throws KrishiMandiException {

		File languagesFile = new File(getClass().getClassLoader().getResource("localization/" + languageCode).getFile());
		if(null == languagesFile || !languagesFile.exists()) {

			throw new KrishiMandiException("Error while loading localization content: localization/languages");
		}else {

			try (Scanner scanner = new Scanner(languagesFile)){

				
				while (scanner.hasNextLine()) {

					String message = scanner.nextLine();
					if(null != message) {

						String[] keyValueData = message.trim().split(LanguageConstants.DELIMITER_COLON);
						System.out.println(keyValueData[0]);
						System.out.println(keyValueData[1]);
//						launguageMap.put(key, value);
					}
				}
			} catch (FileNotFoundException e) {

				throw new KrishiMandiException("Error while loading localization content: " + e.getMessage());
			}
		}
	}
}

