package org.zkoss.training.lifecycle;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Init;

public class TrainingViewModel {
	private Styles styles = new Styles();

	@Init
	public void init() {
		System.out.format("init ViewModel");
	}
	
	@AfterCompose
	public void afterCompose() {
		System.out.format("afterCompose ViewModel");
	}
	
	public Styles getStyles() {
		return styles;
	}
}
