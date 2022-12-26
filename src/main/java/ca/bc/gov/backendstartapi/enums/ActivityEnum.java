package ca.bc.gov.backendstartapi.enums;

import lombok.Getter;

@Getter
public enum ActivityEnum {
  SEEDLOT_REGISTRATION("icon-name", "Seedlot registration", "Start a new registration or check on existing seedlots registrations", "http://test.com");

  private final String iconName;
  private final String title;
  private final String description;
  private final String link;

  private ActivityEnum(final String iconName, final String title, final String description
      , final String link) {
    this.iconName = iconName;
    this.title = title;
    this.description = description;
    this.link = link;
  }

  public static ActivityEnum getByTitle(String title) {
    for (ActivityEnum activityEnum : values()) {
      if (activityEnum.title.equals(title)) {
        return activityEnum;
      }
    }
    return null;
  }
}
