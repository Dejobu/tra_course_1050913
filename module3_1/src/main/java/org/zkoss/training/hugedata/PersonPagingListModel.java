/**
 * @author Ian Tsai
 *
 */
package org.zkoss.training.hugedata;

import java.util.List;

import org.zkoss.zul.AbstractListModel;

import bean.Person;
import bean.PersonDAO;

public class PersonPagingListModel extends AbstractListModel<Person> {

	private static final long serialVersionUID = 1015524612541054426L;

	private final int pageSize;
	private final PageDataModel innerModel;

	public PersonPagingListModel(int pageSize) {
		this.pageSize = pageSize;
		if (pageSize <= 0)
			throw new IllegalArgumentException("page size should be positive integer");
		innerModel = new PageDataModel();
	}

	public Person getElementAt(int index) {
		int residual = index % pageSize;
		System.out.println(" getElementAt:" + index);
		return innerModel.toPage(index / pageSize).get(residual);
	}

	public int getSize() {
		return innerModel.getTotalSize();
	}

	/**
	 * 
	 * @author ian
	 *
	 */
	private class PageDataModel {
		private int currentPageNumber;
		private List<Person> temporaryPage;

		private int totalSize;

		PageDataModel() {
			PersonDAO dao = new PersonDAO();
			totalSize = dao.countAll();
			temporaryPage = dao.getPersons(pageSize, 0);
		}

		// TODO: EJB should provide such method to get the total size.
		public int getTotalSize() {
			return totalSize;
		}

		public List<Person> toPage(int pageIdx) {
			if (temporaryPage != null && currentPageNumber == pageIdx)
				return temporaryPage;

			System.out.println(">>>>>> page fault, fetching from DB pageIdx:" + pageIdx);
			PersonDAO dao = new PersonDAO();

			temporaryPage = dao.getPersons(pageSize, pageIdx);
			currentPageNumber = pageIdx;
			return temporaryPage;
		}
	}
}
