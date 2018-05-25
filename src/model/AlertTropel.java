package model;

import java.util.Date;

public class AlertTropel {

	private String alertText;
	private Date alertMoment;
	
	public AlertTropel(String alertText, Date alertMoment) {
		this.alertText = alertText;
		this.alertMoment = alertMoment;
	}

	public String getAlertText() {
		return alertText;
	}

	public Date getAlertMoment() {
		return alertMoment;
	}

	@Override
	public String toString() {
		return "AlertTropel [alertText=" + alertText + ", alertMoment=" + alertMoment + "]";
	}
}