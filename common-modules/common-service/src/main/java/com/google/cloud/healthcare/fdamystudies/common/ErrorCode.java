/*
 * Copyright 2020 Google LLC
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package com.google.cloud.healthcare.fdamystudies.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.time.Instant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

@Getter
@ToString
@RequiredArgsConstructor
@JsonSerialize(using = ErrorCode.ErrorCodeSerializer.class)
public enum ErrorCode {
  ACCOUNT_LOCKED(
      400,
      "EC_0001",
      Constants.BAD_REQUEST,
      "Due to consecutive failed sign-in attempts with incorrect password, your account has been locked for a period of 15 minutes. Please check your registered email inbox for assistance to reset your password in this period or wait until the lock period is over to sign in again." ),

  TEMP_PASSWORD_EXPIRED(
      400,
      "EC_0002",
      HttpStatus.BAD_REQUEST.toString(),
      "入力した一時パスワードが無効であるか、有効期限が切れています。受信したメールをご確認ください。"),

  ACCOUNT_DEACTIVATED(
      403, "EC_0003", HttpStatus.FORBIDDEN.toString(), "あなたのアカウントは既に無効化されています。"),

  SITE_NOT_FOUND(404, "EC_0004", HttpStatus.NOT_FOUND.toString(), "サイトが見つかりませんでした"),

  INVALID_LOGIN_CREDENTIALS(
      400,
      "EC_0005",
      Constants.BAD_REQUEST,
      "メールアドレスかパスワードが間違っています。 入力内容をご確認下さい。"),

  PASSWORD_EXPIRED(
      400,
      "EC_0006",
      HttpStatus.BAD_REQUEST.toString(),
      "パスワードの有効期限が切れています。パスワードのヘルプを取得するには、[パスワードを忘れた場合]リンクを使用してください。"),

  EMAIL_EXISTS(
      409,
      "EC_0007",
      HttpStatus.CONFLICT.toString(),
      "This email has already been used. Please try with a different email address."),

  EMAIL_SEND_FAILED_EXCEPTION(
      500,
      "EC_0008",
      HttpStatus.INTERNAL_SERVER_ERROR.toString(),
      "システムエラーが発生してメール送信が失敗しました。しばらくしてからお試しください。"),

  APPLICATION_ERROR(
      500,
      "EC_0009",
      HttpStatus.INTERNAL_SERVER_ERROR.toString(),
      "システムエラーが発生してリクエストを処理出来ませんでした。しばらくしてからお試しください。"),

  CURRENT_PASSWORD_INVALID(
      400, "EC_0010", Constants.BAD_REQUEST, "パスワードが正しくありません"),

  ENFORCE_PASSWORD_HISTORY(
      400,
      "EC_0011",
      Constants.BAD_REQUEST,
      "新しいパスワードは、直前10回まで使用したパスワードと異なるものを使用してください。"),

  USER_NOT_ACTIVE(
      400, "EC_0012", Constants.BAD_REQUEST, "アクティブなアカウントではありません"),

  APP_NOT_FOUND(404, "EC_0013", HttpStatus.NOT_FOUND.toString(), "Appが見つかりませんでした"),

  STUDY_NOT_FOUND(404, "EC_0014", HttpStatus.NOT_FOUND.toString(), "Studyが見つかりませんでした"),

  LOCATION_NOT_FOUND(404, "EC_0015", HttpStatus.NOT_FOUND.toString(), "Locationが見つかりませんでした"),

  CANNOT_DECOMMISSION_SITE_FOR_ENROLLED_ACTIVE_STATUS(
      400,
      "EC_0016",
      Constants.BAD_REQUEST,
      "この場所は、1つ以上の研究で活性部位として使用されており、廃止することはできません。"),

  NOT_SUPER_ADMIN_ACCESS(
      403,
      "EC_0017",
      HttpStatus.FORBIDDEN.toString(),
      "この情報を参照する権限がありません"),

  BAD_REQUEST(
      400, "EC_0018", Constants.BAD_REQUEST, "リクエスト内容が不正です(BAD_REQUEST)"),

  UNAUTHORIZED(401, "EC_0019", HttpStatus.UNAUTHORIZED.toString(), "tokenが不正です"),

  INVALID_UPDATE_USER_REQUEST(
      400, "EC_0020", Constants.BAD_REQUEST, "リクエストに失敗しました。メールアドレスかステータスを確認してください"),

  SITE_PERMISSION_ACCESS_DENIED(
      403,
      "EC_0021",
      HttpStatus.FORBIDDEN.toString(),
      "このサイトにアクセスする権限がありません"),

  SITE_EXISTS(
      400,
      "EC_0022",
      Constants.BAD_REQUEST,
      "このLOCATION_IDとSTUDY_IDの組み合わせのサイトはすでに存在します"),

  LOCATION_ACCESS_DENIED(
      403,
      "EC_0023",
      HttpStatus.FORBIDDEN.toString(),
      "このロケーションに対する編集権限がありません"),

  USER_NOT_FOUND(404, "EC_0024", HttpStatus.NOT_FOUND.toString(), "ユーザーが見つかりませんでした"),

  CUSTOM_ID_EXISTS(400, "EC_0025", Constants.BAD_REQUEST, "このLOCATION_IDは既に使用されています"),

  USER_NOT_INVITED(
      400,
      "EC_0026",
      Constants.BAD_REQUEST,
      "このメールは存在しないか、まだ招待状が送信されていません。"),

  CANNOT_REACTIVATE(
      400, "EC_0027", Constants.BAD_REQUEST, "このロケーションは既にアクティブになっています"),

  DEFAULT_SITE_MODIFY_DENIED(400, "EC_0028", Constants.BAD_REQUEST, "このサイトは編集はおこなえません"),

  ALREADY_DECOMMISSIONED(
      400, "EC_0029", Constants.BAD_REQUEST, "このロケーションはすでに廃止されています"),

  LOCATION_UPDATE_DENIED(
      403,
      "EC_0030",
      HttpStatus.FORBIDDEN.toString(),
      "そのロケーションを編集する権限がありません"),

  OPEN_STUDY(
      403,
      "EC_0031",
      HttpStatus.FORBIDDEN.toString(),
      "参加者を公開治験のレジストリに追加することはできません"),

  PERMISSION_MISSING(
      400,
      "EC_0032",
      Constants.BAD_REQUEST,
      "ユーザーは、このリソースにアクセスするために、システムで少なくとも1つの権限を持っている必要があります。"),

  SECURITY_CODE_EXPIRED(
      410,
      "EC_0034",
      HttpStatus.GONE.toString(),
      "このリンクは使用できなくなりました。アカウントのサポートについてはシステム管理者に連絡するか、すでに登録されている場合はサインインしてください。"),

  PARTICIPANT_REGISTRY_SITE_NOT_FOUND(
      400, "EC_0035", Constants.BAD_REQUEST, "ユーザー詳細情報の取得処理にてエラーが発生しました"),

  DOCUMENT_NOT_IN_PRESCRIBED_FORMAT(
      400, "EC_0036", Constants.BAD_REQUEST, "アップロードしたファイル形式が正しくありません"),

  FAILED_TO_IMPORT_PARTICIPANTS(
      500,
      "EC_0037",
      HttpStatus.INTERNAL_SERVER_ERROR.toString(),
      "アップロードされたファイルが指定されたテンプレートに準拠していません"),

  CANNOT_UPDATE_ENROLLMENT_TARGET_FOR_CLOSE_STUDY(
      400,
      "EC_0038",
      Constants.BAD_REQUEST,
      "この治験はすでに終了しています"),

  CANNOT_UPDATE_ENROLLMENT_TARGET_FOR_DECOMMISSIONED_SITE(
      400,
      "EC_0039",
      Constants.BAD_REQUEST,
      "このサイトはすでに廃止されています"),

  CONSENT_DATA_NOT_AVAILABLE(
      400, "EC_0040", Constants.BAD_REQUEST, "同意情報を取得する際にエラーが発生しました"),

  INVALID_APPS_FIELDS_VALUES(
      400, "EC_0041", Constants.BAD_REQUEST, "「フィールド」に許可される値は、調査、サイトです"),

  ADMIN_NOT_FOUND(404, "EC_0042", HttpStatus.NOT_FOUND.toString(), "管理者ユーザーを見つけることができませんでした"),

  PENDING_CONFIRMATION(
      403,
      "EC_0043",
      HttpStatus.BAD_REQUEST.toString(),
      "アカウントはアクティブ化を保留しています。詳細についてはメールを確認し、サインインしてアクティベーションを完了してください。"),

  ACCOUNT_NOT_VERIFIED(
      403,
      "EC_0044",
      HttpStatus.BAD_REQUEST.toString(),
      "アカウントの本登録が未完了です。Eメールを確認してください。"),

  USER_ALREADY_EXISTS(
      409,
      "EC_0045",
      HttpStatus.CONFLICT.toString(),
      "このEメールは登録済みです。サインインをお願いします。"),

  USER_NOT_EXISTS(401, "EC_0046", HttpStatus.UNAUTHORIZED.toString(), "ユーザーは存在していません"),

  STUDY_PERMISSION_ACCESS_DENIED(
      403,
      "EC_0047",
      HttpStatus.FORBIDDEN.toString(),
      "あなたはこの治験を参照/編集する権限がありません"),

  MANAGE_SITE_PERMISSION_ACCESS_DENIED(
      403,
      "EC_0048",
      HttpStatus.FORBIDDEN.toString(),
      "あなたはこのサイトを権利する権限がありません"),

  SITE_NOT_EXIST_OR_INACTIVE(
      400, "EC_0049", Constants.BAD_REQUEST, "サイトが存在しないか、非アクティブです"),

  INVALID_ONBOARDING_STATUS(
      400, "EC_0050", HttpStatus.BAD_REQUEST.toString(), "登録できる値は次のとおりです: N, D, I , E"),

  CANNOT_DECOMMISSION_SITE_FOR_OPEN_STUDY(
      400,
      "EC_0051",
      Constants.BAD_REQUEST,
      "このサイトは公開されている治験に属しているため、廃止することは出来ません。"),

  INVALID_USER_STATUS(400, "EC-0052", Constants.BAD_REQUEST, "ユーザー情報の更新に失敗しました"),

  CANNOT_ADD_SITE_FOR_OPEN_STUDY(
      403, "EC_0053", HttpStatus.FORBIDDEN.toString(), "公開後の治験にサイトを追加することはできません"),

  USER_ID_REQUIRED(400, "EC_0054", Constants.BAD_REQUEST, "ユーザーIDは必須です"),

  LOCATION_ID_UNIQUE(
      400,
      "EC_0058",
      Constants.BAD_REQUEST,
      "LOCACTION＿IDはロケーションディレクトリ内で一意である必要があります"),

  EMAIL_ID_OR_PASSWORD_NULL(
      400, "EC_0128", Constants.BAD_REQUEST, "メールアドレスとパスワードを入力してください"),

  APPLICATION_ID_MISSING(
      400, "EC_0129", Constants.BAD_REQUEST, "リクエストヘッダーがありません"),

  INVALID_FILE_UPLOAD(400, "EC_0057", Constants.BAD_REQUEST, "EXCELファイル(.xls / .xlsx)をアップロードしてください"),

  INVALID_DATA_SHARING_STATUS(400, "EC-130", Constants.BAD_REQUEST, "無効なデータです"),

  INVALID_SOURCE_NAME(400, "EC_0121", Constants.BAD_REQUEST, "無効な 'source' です"),

  APP_PERMISSION_ACCESS_DENIED(
      403,
      "EC_0123",
      HttpStatus.FORBIDDEN.toString(), "このアプリにアクセスする権限がありません"),

  CANNOT_ADD_SITE_FOR_DECOMMISSIONED_LOCATION(
      400, "EC_0122", Constants.BAD_REQUEST, "無効化されたロケーションにサイトを追加することはできません"),

  CANNOT_ADD_SITE_FOR_DEACTIVATED_STUDY(
      403,
      "EC_0124",
      HttpStatus.FORBIDDEN.toString(),
      "この治験は終了しています。終了している治験にサイトを追加することはできません。"),

  LOCATION_DECOMMISSIONED(
      400,
      "EC_0069",
      Constants.BAD_REQUEST,
      "This site cannot be activated as the associated location is decommissioned"),

  CANNOT_ACTIVATE_SITE_FOR_DEACTIVATED_STUDY(
      403,
      "EC_0127",
      HttpStatus.FORBIDDEN.toString(),
      "この治験は終了しています。終了した治験のサイトは再アクティブにすることはできません"),

  CANNOT_ENABLE_PARTICIPANT(
      403,
      "EC_0125",
      HttpStatus.FORBIDDEN.toString(),
      "1つ以上の参加者レコードを有効にできませんでした。これは、同じ治験の別のサイトにメールが有効な状態で存在する場合に発生する可能性があります。"),

  CANNOT_DELETE_INVITATION(
      403,
      "EC_0065",
      HttpStatus.FORBIDDEN.toString(),
      "ユーザーのアカウントはすでにアクティブになっています。代わりに、ユーザーを非アクティブ化してみてください。"),

  TOKEN_EXPIRED(
      410,
      "EC_0066",
      HttpStatus.GONE.toString(),
      "入力したトークンは無効になります。サポートが必要な場合は、サイトコーディネーターにお問い合わせください。"),

  LOCATION_NAME_EXISTS(
      400, "EC_0068", Constants.BAD_REQUEST, "このロケーション名はすでに存在しています"),

  NO_SITES_FOUND(404, "EC_0070", HttpStatus.NOT_FOUND.toString(), "サイト情報が見つかりませんでした"),

  NO_STUDIES_FOUND(
      404,
      "EC_0071",
      HttpStatus.NOT_FOUND.toString(),
      "このビューは治験ごとに複数のサイトを登録している場合に表示されます"),

  NO_APPS_FOUND(
      404,
      "EC_0072",
      HttpStatus.NOT_FOUND.toString(),
      "このビューはアプリごとに複数の治験を登録している場合に表示されます"),

  USER_EMAIL_EXIST(400, "EC_0064", Constants.BAD_REQUEST, "このメールアドレスはすでに存在しています"),

  REGISTRATION_EMAIL_SEND_FAILED(
      500,
      "EC_0075",
      "Internal Server Error",
      "アカウントのアクティブ化を完了するために必要なメールを送信できませんでした。もう一度やり直してください"),

  UNSUPPORTED_SORTBY_VALUE(
      400, "EC_0076", HttpStatus.BAD_REQUEST.toString(), "ソートに失敗しました"),

  UNSUPPORTED_SORT_DIRECTION_VALUE(
      400, "EC_0077", HttpStatus.BAD_REQUEST.toString(), "ソートに失敗しました"),

  FEEDBACK_ERROR_MESSAGE(
      500,
      "EC_0073",
      "Internal Server Error",
      "フィードバックを送信することが出来ませんでした。もう一度やり直してください"),

  CONTACT_US_ERROR_MESSAGE(
      500,
      "EC_0074",
      "Internal Server Error",
      "お問い合わせを送信することが出来ませんでした。もう一度やり直してください"),

  TEMP_PASSWORD_INCORRECT(
      400, "EC_0078", Constants.BAD_REQUEST, "入力した仮パスワードが正しくありません。"),

  ACTIVE_STUDY_ENROLLED_PARTICIPANT(
      400,
      "EC_0079",
      Constants.BAD_REQUEST,
      "公開中の治験に１人以上のユーザーが参加しているため、このサイトは終了することはできません");

  private final int status;
  private final String code;
  private final String errorType;
  private final String description;

  public static ErrorCode fromCodeAndDescription(String code, String description) {
    for (ErrorCode e : ErrorCode.values()) {
      if (StringUtils.equalsIgnoreCase(e.code, code)
          && StringUtils.equalsIgnoreCase(e.description, description)) {
        return e;
      }
    }
    return null; // not found
  }

  private static class Constants {

    private static final String BAD_REQUEST = "Bad Request";
  }

  static class ErrorCodeSerializer extends StdSerializer<ErrorCode> {

    private static final long serialVersionUID = 1L;

    public ErrorCodeSerializer() {
      super(ErrorCode.class);
    }

    @Override
    public void serialize(
        ErrorCode error, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
        throws IOException {
      jsonGenerator.writeStartObject();
      jsonGenerator.writeNumberField("status", error.getStatus());
      jsonGenerator.writeStringField("error_type", error.getErrorType());
      jsonGenerator.writeStringField("error_code", error.getCode());
      jsonGenerator.writeStringField("error_description", error.getDescription());
      jsonGenerator.writeNumberField("timestamp", Instant.now().toEpochMilli());
      jsonGenerator.writeEndObject();
    }
  }
}
