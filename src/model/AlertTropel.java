package model;

public class AlertTropel {

	private String alertText;
	private String alertMoment;
	
	public AlertTropel(String alertText, String alertMoment) {
		this.alertText = alertText;
		this.alertMoment = alertMoment;
	}

	public String getAlertText() {
		return alertText;
	}

	public String getAlertMoment() {
		return alertMoment;
	}

	public String[] getAttributeList() {
		return new String[] {alertMoment, alertText};
	}

	@Override
	public String toString() {
		return "AlertTropel [alertText=" + alertText + ", alertMoment=" + alertMoment + "]";
	}
}