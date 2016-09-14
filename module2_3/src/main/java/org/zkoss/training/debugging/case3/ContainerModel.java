package org.zkoss.training.debugging.case3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

@SuppressWarnings("nls")
public class ContainerModel {

	private static final String EMPTY_STRING = null;
	private LabelsAndValues srcLabelsAndValues = new LabelsAndValues();
	private Map<String, List<String>> map = new HashMap<String, List<String>>();
	private Boolean dbSelectorFocus = false;

	/**
	 * Solution
	 */
	@Command @NotifyChange({"dbSelectorFocus", "srcLabelsAndValues"})
	public void select(){
		dbSelectorFocus = true;
		String selected = srcLabelsAndValues.getCombo(0).getItemsList().get(0);
		srcLabelsAndValues.getCombo(0).setSelectedValue(selected);
	}
	
	
	public LabelsAndValues getSrcLabelsAndValues() {
		return srcLabelsAndValues;
	}

	/**
	 * Select the type of Stitching System
	 */
	@NotifyChange("*")
	@Command("windowCreate")
	public void windowCreate() {
		List<String> dbSystems = Arrays.asList(new String[] { "Oracle", "MySQL", "Db2", "Teradata", "HSQL" });
		map.put("Oracle", Arrays
				.asList(new String[] { "Oracle DB 1", "Oracle DB 2", "Oracle DB 3", "Oracle DB 4", "Oracle DB 5" }));
		map.put("MySQL",
				Arrays.asList(new String[] { "MySQL DB 1", "MySQL DB 2", "MySQL DB 3", "MySQL DB 4", "MySQL DB 5" }));
		map.put("Db2", Arrays.asList(new String[] { "Db2 DB 1", "Db2 DB 2", "Db2 DB 3", "Db2 DB 4", "Db2 DB 5" }));
		map.put("Teradata", Arrays.asList(
				new String[] { "Teradata DB 1", "Teradata DB 2", "Teradata DB 3", "Teradata DB 4", "Teradata DB 5" }));
		map.put("HSQL",
				Arrays.asList(new String[] { "HSQL DB 1", "HSQL DB 2", "HSQL DB 3", "HSQL DB 4", "HSQL DB 5" }));
		srcLabelsAndValues.getCombo(0).setItemsList(dbSystems);
		srcLabelsAndValues.getCombo(0).setOriginalItemsList(dbSystems);
	}

	public void setSrcLabelsAndValues(LabelsAndValues srcLabelsAndValues) {
		this.srcLabelsAndValues = srcLabelsAndValues;
	}

	/**
	 * Select the type of DB System
	 */
	@NotifyChange("*")
	@Command("srcDbSystemSelect")
	public void srcDbSystemSelect() {
		String selectedValue = srcLabelsAndValues.getCombo(0).getSelectedValue();
		System.out.println("DB System selected: " + selectedValue);
		srcLabelsAndValues.getCombo(1).setItemsList(map.get(selectedValue));
		srcLabelsAndValues.getCombo(1).setOriginalItemsList(map.get(selectedValue));
		srcLabelsAndValues.getCombo(2).setItemsList(map.get(selectedValue));
		srcLabelsAndValues.getCombo(2).setOriginalItemsList(map.get(selectedValue));
		srcLabelsAndValues.getCombo(0).setFilterValue(selectedValue);
	}

	/**
	 * Select the type of Database
	 */
	@NotifyChange("*")
	@Command("srcDbSelect")
	public void srcDbSelect() {
		String selectedValue = srcLabelsAndValues.getCombo(1).getSelectedValue();
		System.out.println("DB selected: " + selectedValue);
		srcLabelsAndValues.getCombo(1).setFilterValue(selectedValue);
	}

	/**
	 * Select the type of Database
	 */
	@NotifyChange("*")
	@Command("srcSchSelect")
	public void srcSchSelect() {
		String selectedValue = srcLabelsAndValues.getCombo(2).getSelectedValue();
		System.out.println("Schema selected: " + selectedValue);
		srcLabelsAndValues.getCombo(2).setFilterValue(selectedValue);
	}

	@Command("changeSrcFilter")
	@NotifyChange("*")
	public void changeSrcFilter(@BindingParam("level") int level) {
		List<String> list = srcLabelsAndValues.getCombo(level).getOriginalItemsList();
		String filterValue = srcLabelsAndValues.getCombo(level).getFilterValue();
		if (filterValue == null) {
			filterValue = EMPTY_STRING;
		}
		if (list.contains(filterValue)) {
			srcLabelsAndValues.getCombo(level).setSelectedValue(filterValue);
			switch (level) {
			case 0:
				srcDbSystemSelect();
				break;
			case 1:
				srcDbSelect();
				break;
			case 2:
				srcSchSelect();
				break;
			default:
				break;
			}
		} else {
			if (level < 1) {
				srcLabelsAndValues.getCombo(level + 1).setItemsList(new ArrayList<String>());
				srcLabelsAndValues.getCombo(level + 1).setSelectedValue(null);
			}
			srcLabelsAndValues.getCombo(level)
					.setItemsList(filterListIgnoringCase(list, Pattern.quote(filterValue) + "*"));
		}
	}

	public static List<String> filterListIgnoringCase(List<String> list, String regex) {
		String javaRegexp = regex.replaceAll("\\*", ".*").toUpperCase(); //$NON-NLS-1$//$NON-NLS-2$
		List<String> matches = new ArrayList<String>();
		Pattern p = Pattern.compile(javaRegexp);
		for (String s : list) {
			if (p.matcher(s.toUpperCase()).matches()) {
				matches.add(s);
			}
		}
		return matches;
	}
	

	public Boolean getDbSelectorFocus() {
		return dbSelectorFocus;
	}

	public void setDbSelectorFocus(Boolean dbSelectorFocus) {
		this.dbSelectorFocus = dbSelectorFocus;
	}
}
