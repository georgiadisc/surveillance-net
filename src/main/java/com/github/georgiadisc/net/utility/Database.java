package com.github.georgiadisc.net.utility;

import com.github.georgiadisc.net.models.Registry;
import com.github.georgiadisc.net.models.Suspect;

/**
 * Database giving access to the {@link Registry}, the currently searched
 * {@link Suspect}'s info and the previously searched {@link Suspect}'s info.
 */
public final class Database {

	private static Database database;
	private Registry registry;
	private Suspect suspect;
	private Suspect prevSuspect;

	private Database() {
	}

	/**
	 * Returns the database instance. If the database hasn't been instantiated yet,
	 * it creates a new one and the new instance gets returned.
	 *
	 * @return {@link Database} The database instance
	 */
	public static Database getInstance() {
		if (database == null) {
			database = new Database();
		}
		return database;
	}

	public Registry getRegistry() {
		return registry;
	}

	public void setRegistry(Registry registry) {
		this.registry = registry;
	}

	public Suspect getSuspect() {
		return suspect;
	}

	public void setSuspect(Suspect suspect) {
		this.prevSuspect = this.suspect;
		this.suspect = suspect;
	}

	public Suspect getPrevSuspect() {
		return prevSuspect;
	}

}
