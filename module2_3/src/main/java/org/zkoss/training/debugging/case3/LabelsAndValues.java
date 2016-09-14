package org.zkoss.training.debugging.case3;

/**
 * Represents view labels and inputs
 */
public class LabelsAndValues {
	private static final String EMPTY_STRING = ""; //$NON-NLS-1$
	private static final int LOW_LEVEL_ITEM = 1;
	private static final int TOP_LEVEL_ITEM = 0;

	public LabelsAndValues() {
		for (int i = 0; i < combo.length; i++) {
			combo[i] = new ComboBoxModel();
		}
	}

	private ComboBoxModel[] combo = new ComboBoxModel[3];

	public ComboBoxModel[] getCombo() {
		return combo;
	}

	public ComboBoxModel getCombo(int index) {
		return combo[index];
	}

	public void setCombos(ComboBoxModel[] combos) {
		this.combo = combos;
	}

	private boolean isLvl2Filled() {
		return !getCombo(LOW_LEVEL_ITEM).isVisible() || (!(getCombo(LOW_LEVEL_ITEM).getSelectedValue() == null)
				&& !getCombo(LOW_LEVEL_ITEM).getSelectedValue().equals(EMPTY_STRING));
	}

	private boolean isLvl1Filled() {
		return getCombo(TOP_LEVEL_ITEM).isVisible() && (!(getCombo(TOP_LEVEL_ITEM).getSelectedValue() == null)
				&& !getCombo(TOP_LEVEL_ITEM).getSelectedValue().equals(EMPTY_STRING));
	}

	public boolean isFilled() {
		return isLvl1Filled() && isLvl2Filled();
	}

	public void changeComboVisibility(int comboIndex, boolean visible) {
		getCombo(comboIndex).setVisible(visible);
	}

	public void clearItems(int comboIndex) {
		getCombo(comboIndex).getItemsList().clear();
	}

	public boolean isComboEmptyAndVisible(int comboIndex) {
		return getCombo(comboIndex).getItemsList().isEmpty() && getCombo(comboIndex).isVisible();
	}

	public boolean isCorrectValueSelected(int comboIndex) {
		return getCombo(comboIndex).getItemsList().contains(getCombo(comboIndex).getSelectedValue());
	}

	public boolean noValueSelected(int comboIndex) {
		return getCombo(comboIndex).getSelectedValue() == null
				|| EMPTY_STRING.equals(getCombo(comboIndex).getSelectedValue());
	}
}
