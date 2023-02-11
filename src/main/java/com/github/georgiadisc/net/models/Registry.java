package com.github.georgiadisc.net.models;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;

import java.util.*;
import java.util.stream.Stream;

public class Registry {

	private static final List<SMS> messages = new ArrayList<>();
	private static final List<PhoneCall> phoneCalls = new ArrayList<>();
	private final List<Suspect> suspects = new ArrayList<>();
	private final Graph<String, Integer> graph = new UndirectedSparseMultigraph<>();

	/**
	 * Adds a suspect to the suspect's list of possible associates. This happens
	 * after checking whether it is a suspect that is already on the list potential
	 * partners.
	 *
	 * @param suspect The suspect to be added
	 */
	public void addSuspect(Suspect suspect) {
		suspects.add(suspect);
	}

	/**
	 * Adds an {@link SMS} or a {@link PhoneCall} to the corresponding list. If the
	 * communication happened between the phone numbers Num1 and Num2, then the
	 * suspects who have the corresponding numbers, will have their list of partners
	 * updated.
	 *
	 * @param communication The message or call being added
	 */
	public void addCommunication(Communication communication) {
		if (communication instanceof SMS) {
			messages.add((SMS) communication);
		} else {
			phoneCalls.add((PhoneCall) communication);
		}
		Suspect suspect = null;
		String otherNumber = null;
		for (Suspect s : suspects) {
			if (s.getNumbers().contains(communication.getFirstNumber())) {
				otherNumber = communication.getSecondNumber();
				suspect = s;
				break;
			} else if (s.getNumbers().contains(communication.getSecondNumber())) {
				otherNumber = communication.getFirstNumber();
				suspect = s;
				break;
			}
		}
		if (suspect != null) {
			for (Suspect s : suspects) {
				if (s.getNumbers().contains(otherNumber)) {
					s.addSuspect(suspect);
					suspect.addSuspect(s);
				}
			}
		}
	}

	/**
	 * Returns the complete list of messages and calls.
	 */
	public static List<Communication> getAllCommunication() {
		return Stream.concat(phoneCalls.stream(), messages.stream()).toList();
	}

	/**
	 * It adds the suspects to the graph, as vertices, and the relationship between
	 * them, as edges.
	 */
	public void buildGraph() {
		int index = 0;
		for (int i = 0; i < suspects.size(); i++) {
			final Suspect s = suspects.get(i);
			for (int j = i; j < s.getPartners().size(); j++) {
				graph.addEdge(index, s.getCodeName(), s.getPartners().get(j).getCodeName());
				index++;
			}
		}
	}

	public Graph<String, Integer> getGraph() {
		return graph;
	}

	/**
	 * Returns the first {@link Suspect} whose name equals the specified name.
	 *
	 * @param name The suspect's name
	 */
	public Suspect getSuspectByName(String name) {
		for (Suspect s : suspects) {
			if (s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}

	/**
	 * Returns the messages exchanged between the specified numbers, which contain
	 * at least one of the words “Bomb”, “Attack”, “Explosives”, “Gun”.
	 *
	 * @param firstNumber  The first number
	 * @param secondNumber The second number
	 */
	public List<SMS> getMessagesBetween(String firstNumber, String secondNumber) {
		final List<SMS> messagesBetween = new ArrayList<>();
		for (SMS message : messages) {
			if (message.consistsOf(firstNumber, secondNumber) && message.isMalicious()) {
				messagesBetween.add(message);
			}
		}
		return messagesBetween;
	}

	/**
	 * Returns all suspects originating from one country.
	 *
	 * @param country The country's name
	 */
	public List<Suspect> getSuspectsFromCountry(String country) {
		final List<Suspect> commonOrigin = new ArrayList<>();
		for (Suspect s : suspects) {
			if (s.getCountry().equals(country)) {
				commonOrigin.add(s);
			}
		}
		return commonOrigin;
	}

	/**
	 * Returns all suspects originating from a city.
	 *
	 * @param city The city's name
	 */
	public List<Suspect> getSuspectsFromCity(String city) {
		final List<Suspect> commonOrigin = new ArrayList<>();
		for (Suspect s : suspects) {
			if (s.getCity().equals(city)) {
				commonOrigin.add(s);
			}
		}
		return commonOrigin;
	}

	/**
	 * Calculates and returns the diameter of the graph.
	 */
	public int getDiameter() {
		int diameter = -1;
		for (int i = 0; i < suspects.size(); i++) {
			int maxPath = getMaxPath(i);
			if (maxPath > diameter) {
				diameter = maxPath;
			}
		}
		return diameter;
	}

	/**
	 * Returns the greatest possible distance that one suspect has from another
	 * suspect using BFS (Breadth First Search) algorithm.
	 *
	 * @param suspect The suspect being searched
	 */
	private int getMaxPath(int suspect) {
		final Queue<Integer> queue = new ArrayDeque<>();
		final int n = suspects.size();
		final int[] minDistance = new int[n];
		for (int i = 0; i < n; i++) {
			minDistance[i] = Integer.MAX_VALUE;
		}
		minDistance[suspect] = 0;
		queue.add(suspect);
		int maxDistance = -1;
		while (!queue.isEmpty()) {
			int v = queue.remove();
			for (int i = 0; i < n; i++) {
				if (!areConnected(i, v)) {
					continue;
				}
				if (minDistance[i] > minDistance[v] + 1) {
					minDistance[i] = minDistance[v] + 1;
					maxDistance = minDistance[i];
					queue.add(i);
				}
			}
		}
		return maxDistance;
	}

	/**
	 * Checks if the specified suspects have communicated at least once, either
	 * through an {@link SMS} or a {@link PhoneCall}.
	 *
	 * @param a The first suspect
	 * @param b The second suspect
	 */
	private boolean areConnected(int a, int b) {
		return suspects.get(a).isConnectedTo(suspects.get(b));
	}

}
