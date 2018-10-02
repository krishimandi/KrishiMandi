package com.krishimandi.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class is used to configure the system.
 *
 */
public class ConfigureSystem {


	/**
	 * This method is user to create application parent folder. 
	 * This method is used to create request response folder where also the requests and 
	 * corresponding response will be logged.
	 * @throws KrishiMandiException
	 */
	public void prepareDumpingGround() throws KrishiMandiException {

		/* Create application folder if necessary */
		createApplicationFolder();

		/* Create request response logs folder if necessary */
		createRequestLogFolder();
	}

	/**
	 * This method creates an application folder if not already available.
	 * @throws KrishiMandiException
	 */
	private void createApplicationFolder() throws KrishiMandiException {

		File[] drives = File.listRoots();
		if (drives != null && drives.length > 0) {

			File file = new File(drives[0] + SystemConstants.SYSTEM_NAME);
			if(null != file && !file.exists()) {

				if(!file.mkdirs())  {

					throw new KrishiMandiException("Unable to create application parent folder");
				}
			}
		}
	}

	/**
	 * This method creates a Request Response folder if not already available.
	 * @throws KrishiMandiException
	 */
	private void createRequestLogFolder() throws KrishiMandiException {

		File[] drives = File.listRoots();
		if (drives != null && drives.length > 0) {

			File file = new File(drives[0] + SystemConstants.SYSTEM_NAME + "/" + SystemConstants.REQUEST_RESPONSE_LOG);
			if(null != file && !file.exists()) {

				if(!file.mkdirs())  {

					throw new KrishiMandiException("Unable to create request response folder");
				}
			}
		}
	}

	/**
	 * This method is used to load language content into different maps. 
	 * When the application is launched, each language data is mapped into different individual hashmap. 
	 * Depending on the selected language of the request, data from the suitable hashmap will be obtained 
	 * and sent to the user.   
	 * @throws KrishiMandiException
	 */
	public void mapLocalizationContent() throws KrishiMandiException {

		/** Below list will hold all of the language codes supported by the application.  */
		ArrayList<String> languagesList = getLanguagesList();

		if(null == languagesList || languagesList.isEmpty()) {

			throw new KrishiMandiException("Error while loading localization content: languages not available");
		}else {

			for (int i = 0; i < languagesList.size(); i++) {

				switch(languagesList.get(i)) {

				case LanguageConstants.LANGUAGE_ENGLISH: 

					LanguageConstants.MAP_LANGUAGE_ENGLISH = new HashMap<Integer, String>();
					mapSpecificLangContent(LanguageConstants.MAP_LANGUAGE_ENGLISH, LanguageConstants.LANGUAGE_ENGLISH);
					break;
				case LanguageConstants.LANGUAGE_KANNADA:
					LanguageConstants.MAP_LANGUAGE_KANNADA = new HashMap<Integer, String>();
					mapSpecificLangContent(LanguageConstants.MAP_LANGUAGE_KANNADA, LanguageConstants.LANGUAGE_KANNADA);
					break;
				case LanguageConstants.LANGUAGE_HINDI:
					LanguageConstants.MAP_LANGUAGE_HINDI = new HashMap<Integer, String>();
					mapSpecificLangContent(LanguageConstants.MAP_LANGUAGE_HINDI, LanguageConstants.LANGUAGE_HINDI);
					break;
				default:

					throw new KrishiMandiException("Error while loading localization content: Invalid language");
				}
			}
		}
	}

	/**
	 * This method retrieves the language codes supported by the application. 
	 * The languages supported by the application are listed under the file res/languages.
	 * This file is read and the languages are put into a list and returned back.  
	 * @return ArrayList<String> (String - Language codes)
	 * @throws KrishiMandiException
	 */
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

	/**
	 * This method loads language contents from a particular resource file into a specific map.
	 * @param launguageMap - This map is will be filled up with contents from the language file 
	 * based on the other argument 'languageCode. 
	 * @param languageCode
	 * @throws KrishiMandiException
	 */
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
						Integer key = Integer.parseInt(keyValueData[0].trim());
						String value = keyValueData[1].trim();
//						TODO: Handle null check
						launguageMap.put(key, value);
					}
				}
			} catch (FileNotFoundException e) {

				throw new KrishiMandiException("Error while loading localization content: " + e.getMessage());
			}
		}
	}
}