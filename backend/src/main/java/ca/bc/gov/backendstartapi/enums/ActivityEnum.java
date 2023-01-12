package ca.bc.gov.backendstartapi.enums;

import java.util.Optional;
import lombok.Getter;

/**
 * This enumeration contains all possible activities.
 */
@Getter
public enum ActivityEnum {
  SEEDLOT_REGISTRATION(
      "SoilMoistureField",
      "Seedlot registration",
      "Start a new registration or check on existing seedlots registrations",
      "/seedlot-registration"),
  PARENT_TREE_ORCHARD(
      "Tree",
      "Parent tree orchard",
      "Manage the parent trees inside your orchard and look for their "
          + "latest reports and the latest data.",
      "/parent-tree-orchard"),
  SEEDLING_REQUEST(
      "CropGrowth",
      "Seedling request",
      "Open a new seedling request for your reforestation needs.",
      "/seedling-request");

  private final String iconName;
  private final String title;
  private final String description;
  private final String page;

  private ActivityEnum(final String iconName, final String title, final String description,
      final String page) {
    this.iconName = iconName;
    this.title = title;
    this.description = description;
    this.page = page;
  }

  /**
   * Finds an ActivityEnum by the page title.
   *
   * @param title the title of the page
   * @return an ActivityEnum instance if found, null otherwise
   */
  public static Optional<ActivityEnum> findByTitle(String title) {
    for (ActivityEnum activityEnum : values()) {
      if (activityEnum.title.equals(title)) {
        return Optional.of(activityEnum);
      }
    }
    return Optional.empty();
  }
}
