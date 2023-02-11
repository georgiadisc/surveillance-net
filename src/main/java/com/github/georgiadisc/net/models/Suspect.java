package com.github.georgiadisc.net.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Suspect {

	private final String name;
	private final String codeName;
	private final String country;
	private final String city;
	private final List<String> numbers = new ArrayList<>();
	private final List<Suspect> partners = new ArrayList<>();

	public Suspect(String name, String codeName, String country, String city) {
		this.name = name;
		this.codeName = codeName;
		this.country = country;
		this.city = city;
	}

	/**
	 * Adds a number to the suspect's phone number list.
	 *
	 * @param number The number to be added
	 */
	public void addNumber(String number) {
		numbers.add(number);
	}

	/**
	 * Adds a suspect to the suspect's list of possible partners.
	 *
	 * @param suspect The suspect to be added
	 */
	public void addSuspect(Suspect suspect) {
		if (!partners.contains(suspect)) {
			partners.add(suspect);
		}
	}

	public String getName() {
		return name;
	}

	public String getCodeName() {
		return codeName;
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public List<String> getNumbers() {
		return numbers;
	}

	public List<Suspect> getPartners() {
		return partners;
	}

	/**
	 * Returns suggested potential partners.
	 *
	 * @return {@code List<Suspect>}
	 */
	public List<Suspect> getSuggestedPartners() {
		final Set<Suspect> suspects = new HashSet<>();
		for (Suspect p : partners) {
			suspects.addAll(p.getPartners());
		}
		suspects.remove(this);
		partners.forEach(suspects::remove);
		return new ArrayList<>(suspects);
	}

	/**
	 * Takes another suspect as a parameter and returns true only if they have
	 * communicated at least once with each other.
	 *
	 * @param suspect The other suspect
	 * @return boolean
	 */
	public boolean isConnectedTo(Suspect suspect) {
		for (Communication c : Registry.getAllCommunication()) {
			for (String number : numbers) {
				for (String otherNumber : suspect.getNumbers()) {
					if (c.consistsOf(number, otherNumber)) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
