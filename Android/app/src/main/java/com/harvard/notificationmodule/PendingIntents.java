package com.harvard.notificationmodule;

import io.realm.RealmObject;

public class PendingIntents extends RealmObject {
  private int pendingIntentId;

  private String activityId;

  private String title;

  private String description;

  private String type;

  private int notificationId;

  private String studyId;

  public int getNotificationId() {
    return notificationId;
  }

  public void setNotificationId(int notificationId) {
    this.notificationId = notificationId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getActivityId() {
    return activityId;
  }

  public void setActivityId(String activityId) {
    this.activityId = activityId;
  }

  public String getStudyId() {
    return studyId;
  }

  public void setStudyId(String studyId) {
    this.studyId = studyId;
  }

  int getPendingIntentId() {
    return pendingIntentId;
  }

  void setPendingIntentId(int pendingIntentId) {
    this.pendingIntentId = pendingIntentId;
  }
}
