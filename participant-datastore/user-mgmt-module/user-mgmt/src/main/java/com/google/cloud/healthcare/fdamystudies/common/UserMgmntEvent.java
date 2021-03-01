/*
 * Copyright 2020 Google LLC
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package com.google.cloud.healthcare.fdamystudies.common;

import static com.google.cloud.healthcare.fdamystudies.common.PlatformComponent.MOBILE_APPS;
import static com.google.cloud.healthcare.fdamystudies.common.PlatformComponent.NATIVE_PUSH_NOTIFICATION_SERVER;
import static com.google.cloud.healthcare.fdamystudies.common.PlatformComponent.PARTICIPANT_USER_DATASTORE;
import static com.google.cloud.healthcare.fdamystudies.common.PlatformComponent.RESPONSE_DATASTORE;
import static com.google.cloud.healthcare.fdamystudies.common.PlatformComponent.SCIM_AUTH_SERVER;
import static com.google.cloud.healthcare.fdamystudies.common.PlatformComponent.STUDY_BUILDER;

import java.util.Optional;
import lombok.Getter;

@Getter
public enum UserMgmntEvent implements AuditLogEvent {
  ACCOUNT_REGISTRATION_REQUEST_RECEIVED(
      MOBILE_APPS, PARTICIPANT_USER_DATASTORE, null, null, "ACCOUNT_REGISTRATION_REQUEST_RECEIVED"),

  REGISTRATION_SUCCEEDED(
      null,
      SCIM_AUTH_SERVER,
      PARTICIPANT_USER_DATASTORE,
      //"User ID created after successful registration on auth server.",
      "認証サーバーへの登録後にユーザーIDは作成されました。",
      "USER_CREATED"),

  REGISTRATION_FAILED(
      null,
      SCIM_AUTH_SERVER,
      PARTICIPANT_USER_DATASTORE,
       //"New user registration failed.",
      "ユーザーの新規登録に失敗しました。",
      "REGISTRATION_FAILED"),

  USER_REGISTRATION_ATTEMPT_FAILED(
      MOBILE_APPS,
      PARTICIPANT_USER_DATASTORE,
      null,
          //"Account registration request could not be processed.",
      "アカウント登録リクエストを処理できませんでした。",
      "USER_REGISTRATION_ATTEMPT_FAILED"),

  USER_REGISTRATION_ATTEMPT_FAILED_EXISTING_USERNAME(
      MOBILE_APPS,
      PARTICIPANT_USER_DATASTORE,
      null,
          //"Request for new account registration was denied as the username is already registered.",
      "ユーザー名がすでに登録されているため、新規アカウント登録のリクエストは失敗しました。",
      "USER_REGISTRATION_ATTEMPT_FAILED_EXISTING_USERNAME"),

  VERIFICATION_EMAIL_SENT(
      PARTICIPANT_USER_DATASTORE,
      PARTICIPANT_USER_DATASTORE,
      null,
          //"Verification email with code sent to user for account activation.",
      "認証コードを記載した確認メールを送信しました。",
      "VERIFICATION_EMAIL_SENT"),

  VERIFICATION_EMAIL_FAILED(
      PARTICIPANT_USER_DATASTORE,
      PARTICIPANT_USER_DATASTORE,
      null,
      //"Verification email (for account activation) could not be sent to user.",
      "認証コードを記載した確認メールの送信が失敗しました。",
      "VERIFICATION_EMAIL_FAILED"),

  USER_EMAIL_VERIFIED_FOR_ACCOUNT_ACTIVATION(
      MOBILE_APPS,
      PARTICIPANT_USER_DATASTORE,
      null,
      null,
      "USER_EMAIL_VERIFIED_FOR_ACCOUNT_ACTIVATION"),

  USER_ACCOUNT_ACTIVATED(
      null, SCIM_AUTH_SERVER, PARTICIPANT_USER_DATASTORE, null, "USER_ACCOUNT_ACTIVATED"),

  ACCOUNT_ACTIVATION_USER_EMAIL_VERIFICATION_FAILED(
      MOBILE_APPS,
      PARTICIPANT_USER_DATASTORE,
      null,
          //"Email verification step for account activation failed.",
      "アカウント本登録の確認メールのステップで失敗しました。",
      "ACCOUNT_ACTIVATION_USER_EMAIL_VERIFICATION_FAILED"),

  ACCOUNT_ACTIVATION_USER_EMAIL_VERIFICATION_FAILED_WRONG_CODE(
      MOBILE_APPS,
      PARTICIPANT_USER_DATASTORE,
      null,
          //"Email verification for account activation failed due to invalid code.",
      "ユーザー登録用認証コードの認証に失敗しました。",
      "ACCOUNT_ACTIVATION_USER_EMAIL_VERIFICATION_FAILED_WRONG_CODE"),

  ACCOUNT_ACTIVATION_USER_EMAIL_VERIFICATION_FAILED_EXPIRED_CODE(
      MOBILE_APPS,
      PARTICIPANT_USER_DATASTORE,
      null,
          //"Email verification for account activation failed due to expired code.",
      "ユーザー登録用認証コードの有効期限が切れています。",
      "ACCOUNT_ACTIVATION_USER_EMAIL_VERIFICATION_FAILED_EXPIRED_CODE"),

  ACCOUNT_ACTIVATION_FAILED(
      null, SCIM_AUTH_SERVER, PARTICIPANT_USER_DATASTORE, null, "ACCOUNT_ACTIVATION_FAILED"),

  VERIFICATION_EMAIL_RESEND_REQUEST_RECEIVED(
      MOBILE_APPS,
      PARTICIPANT_USER_DATASTORE,
      null,
          //"Request received for resend of verification email.",
      "認証メールの再送信リクエストを受け取りました。",
      "VERIFICATION_EMAIL_RESEND_REQUEST_RECEIVED"),

  USER_DELETED(
      MOBILE_APPS,
      SCIM_AUTH_SERVER,
      PARTICIPANT_USER_DATASTORE,
          //"User record deactivated on Participant Datastore and deleted from Auth Server.",
      "認証サーバーからユーザーのデータが削除されました。",
      "USER_DELETED"),

  USER_DELETION_FAILED(
      MOBILE_APPS,
      SCIM_AUTH_SERVER,
      PARTICIPANT_USER_DATASTORE,
          //"User record deactivation from Participant Datastore and deletion from Auth Server failed. ",
      "認証サーバーからユーザーのデータ削除に失敗しました。",
      "USER_DELETION_FAILED"),

  USER_PROFILE_UPDATED(
      MOBILE_APPS,
      PARTICIPANT_USER_DATASTORE,
      null,
          //"Profile/Preferences updated by user.",
      "ユーザープロファイルが更新されました。",
      "USER_PROFILE_UPDATED"),

  USER_PROFILE_UPDATE_FAILED(
      MOBILE_APPS,
      PARTICIPANT_USER_DATASTORE,
      null,
          //"Profile/Preferences update by app user failed.",
      "ユーザープロファイルの更新に失敗しました。",
      "USER_PROFILE_UPDATE_FAILED"),

  DATA_RETENTION_SETTING_CAPTURED_ON_WITHDRAWAL(
      MOBILE_APPS,
      PARTICIPANT_USER_DATASTORE,
      null,
          //"Based on participant choice/study setting, the data retention setting upon withdrawal from study is read as '${delete_or_retain}'.",
      "ユーザーの任意で治験退会時の登録データは次のように設定されています。　'${delete_or_retain}'.",
      "DATA_RETENTION_SETTING_CAPTURED_ON_WITHDRAWAL"),

  PARTICIPANT_DATA_DELETED(
      PARTICIPANT_USER_DATASTORE,
      PARTICIPANT_USER_DATASTORE,
      null,
          //"Participant's study related data was deleted or nullified.",
      "ユーザーの治験データは削除、または無効化されました。",
      "PARTICIPANT_DATA_DELETED"),

  PARTICIPANT_DATA_DELETION_FAILED(
      PARTICIPANT_USER_DATASTORE,
      PARTICIPANT_USER_DATASTORE,
      null,
          //"Participant's study related data could not be completely deleted or nullified.",
      "ユーザーの治験データは削除、または無効化に失敗しました。",
      "PARTICIPANT_DATA_DELETION_FAILED"),

  WITHDRAWAL_INTIMATED_TO_RESPONSE_DATASTORE(
      PARTICIPANT_USER_DATASTORE,
      RESPONSE_DATASTORE,
      null,
          //"Response Datastore informed about participant's study withdrawal.",
      "DBシステムは、ユーザーの治験離脱について通知しました。",
      "WITHDRAWAL_INTIMATED_TO_RESPONSE_DATASTORE"),

  WITHDRAWAL_INTIMATION_TO_RESPONSE_DATASTORE_FAILED(
      PARTICIPANT_USER_DATASTORE,
      RESPONSE_DATASTORE,
      null,
          //"Communication failed to Response Datastore about participant's study withdrawal information and corresponding Data Retention setting '${delete_or_retain}'.",
      "DBシステムは、ユーザーの治験離脱についての通知に失敗しました。",
      "WITHDRAWAL_INTIMATION_TO_RESPONSE_DATASTORE_FAILED"),

  STUDY_METADATA_RECEIVED(
      STUDY_BUILDER,
      PARTICIPANT_USER_DATASTORE,
      null,
          //"App/Study metadata received.",
      "App/Study のメタデータを受け取りました。.",
      "STUDY_METADATA_RECEIVED"),

  NOTIFICATION_METADATA_RECEIVED(
      STUDY_BUILDER,
      PARTICIPANT_USER_DATASTORE,
      null,
          //"App/Study notifications metadata received.",
      "お知らせ内容を受け取りました。",
      "NOTIFICATION_METADATA_RECEIVED"),

  PUSH_NOTIFICATION_SENT(
      PARTICIPANT_USER_DATASTORE,
      NATIVE_PUSH_NOTIFICATION_SERVER,
      null,
          //"Push Notifications successfully sent to native platforms' notifications cloud services for delivery to app users/participants.",
      "アプリユーザーに配信するためのプッシュ通知がネイティブプラットフォームの通知クラウドサービスに正常に送信されました。",
      "PUSH_NOTIFICATION_SENT"),

  PUSH_NOTIFICATION_FAILED(
      PARTICIPANT_USER_DATASTORE,
      NATIVE_PUSH_NOTIFICATION_SERVER,
      null,
          //"Push Notifications failed to be sent to native platforms' notifications cloud service for delivery to app users/participants.",
      "アプリユーザーに配信するためのプッシュ通知がネイティブプラットフォームの通知クラウドサービスへの送信が失敗しました。",
      "PUSH_NOTIFICATION_FAILED"),

  READ_OPERATION_SUCCEEDED_FOR_USER_PROFILE(
      MOBILE_APPS,
      PARTICIPANT_USER_DATASTORE,
      null,
          //"App user's profile information retrieved.",
      "ユーザーのプロファイル情報を受け取りました。",
      "READ_OPERATION_SUCCEEDED_FOR_USER_PROFILE"),

  READ_OPERATION_FAILED_FOR_USER_PROFILE(
      MOBILE_APPS,
      PARTICIPANT_USER_DATASTORE,
      null,
          //"Attempt to retrieve app user's profile information failed.",
      "ユーザーのプロファイル情報の取得に失敗しました。",
      "READ_OPERATION_FAILED_FOR_USER_PROFILE"),

  FEEDBACK_CONTENT_EMAILED(
      MOBILE_APPS,
      PARTICIPANT_USER_DATASTORE,
      null,
          //"Content submitted by app user via 'Feedback' form in mobile app, emailed to ${feedback_destination_email_address}.",
      "ユーザーのフィードバックは次のメールアドレスに送信されます。 ${feedback_destination_email_address}.",
      "FEEDBACK_CONTENT_EMAILED"),

  FEEDBACK_CONTENT_EMAIL_FAILED(
      MOBILE_APPS,
      PARTICIPANT_USER_DATASTORE,
      null,
          //"Content submitted by app user via 'Feedback' form in mobile app, could not be emailed to ${feedback_destination_email_address}.",
      "ユーザーのフィードバックは次のメール宛に送信することが出来ませんでした。 ${feedback_destination_email_address}.",
      "FEEDBACK_CONTENT_EMAIL_FAILED"),

  CONTACT_US_CONTENT_EMAILED(
      MOBILE_APPS,
      PARTICIPANT_USER_DATASTORE,
      null,
          //"Content submitted by app user via 'Contact Us' form in mobile app, emailed to ${contactus_destination_email_address}.",
      "お問い合わせフォームの内容は次のメール宛に送信されます。 ${contactus_destination_email_address}.",
      "CONTACT_US_CONTENT_EMAILED"),

  CONTACT_US_CONTENT_EMAIL_FAILED(
      MOBILE_APPS,
      PARTICIPANT_USER_DATASTORE,
      null,
          //"Content submitted by app user via 'Contact Us' form in mobile app, could not be emailed to ${contactus_destination_email_address}.",
      "お問い合わせフォームからの送信が失敗しました。宛先は次のメールアドレスです。 ${contactus_destination_email_address}.",
      "CONTACT_US_CONTENT_EMAIL_FAILED");

  private final Optional<PlatformComponent> source;
  private final PlatformComponent destination;
  private final Optional<PlatformComponent> resourceServer;
  private final String description;
  private final String eventCode;

  private UserMgmntEvent(
      PlatformComponent source,
      PlatformComponent destination,
      PlatformComponent resourceServer,
      String description,
      String eventCode) {
    this.source = Optional.ofNullable(source);
    this.destination = destination;
    this.resourceServer = Optional.ofNullable(resourceServer);
    this.description = description;
    this.eventCode = eventCode;
  }
}
