package org.zkoss.training.debugging.case3;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents single combo box on UI. Encapsulates combo box label, visibility,
 * selected value and the list of values assigned
 */
public class ComboBoxModel {
	private String label;
	private boolean visible;
	private String selectedValue;
	private String filterValue;

	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}

	private List<String> itemsList = new ArrayList<String>();
	private List<String> originalItemsList = new ArrayList<String>();

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getSelectedValue() {
		return selectedValue;
	}

	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

	public List<String> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<String> itemsList) {
		this.itemsList = itemsList;
	}

	public List<String> getOriginalItemsList() {
		return originalItemsList;
	}

	public void setOriginalItemsList(List<String> originalItemsList) {
		this.originalItemsList = originalItemsList;
	}
}
