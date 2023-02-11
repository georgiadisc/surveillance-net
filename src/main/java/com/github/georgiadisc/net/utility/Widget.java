package com.github.georgiadisc.net.utility;

import javax.swing.*;
import java.awt.*;

/**
 * The parent class of every widget. Widgets extending this class get access to
 * the database and the mechanism for building and updating its content. The
 * default updating mechanism is set to always.
 */
public abstract class Widget {

	protected final Database db;
	private final JPanel container;
	private Frequency updateFrequency;

	protected Widget() {
		this.db = Database.getInstance();
		this.container = new JPanel();
		this.updateFrequency = Frequency.ALWAYS;
	}

	protected JPanel getContainer() {
		return container;
	}

	protected void setUpdateFrequency(Frequency updateFrequency) {
		this.updateFrequency = updateFrequency;
	}

	protected void setPreferences() {
		container.setMaximumSize(container.getPreferredSize());
	}

	protected void addBorder() {
		container.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	}

	protected void add(Widget widget) {
		container.add(widget.container);
	}

	protected void add(Component comp) {
		container.add(comp);
	}

	public boolean shouldUpdate() {
		return switch (updateFrequency) {
			case CHANGE_OF_STATE -> !db.getSuspect().equals(db.getPrevSuspect());
			case NEVER -> false;
			case ALWAYS -> true;
		};
	}

	/**
	 * Builds the content of the {@link Widget}.
	 */
	public abstract void build();

	/**
	 * Updates the content of the {@link Widget}.
	 */
	public abstract void update();

	protected enum Frequency {

		ALWAYS, CHANGE_OF_STATE, NEVER

	}

	protected enum Row {

		SM(1), MD(3);

		private final int size;

		Row(int size) {
			this.size = size;
		}

		public int getSize() {
			return size;
		}

	}

	protected enum Column {

		SM(10), MD(20);

		private final int size;

		Column(int size) {
			this.size = size;
		}

		public int getSize() {
			return size;
		}

	}

}
