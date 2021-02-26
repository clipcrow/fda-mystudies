<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="col-sm-10 col-rc white-bg p-none">

  <!--  Start top tab section-->
  <div class="right-content-head">
    <div class="text-right">
      <div class="black-md-f text-uppercase dis-line pull-left line34">治験の公開アクション</div>
      <div class="dis-line form-group mb-none mr-sm"></div>
      <div class="dis-line form-group mb-none"></div>
    </div>
  </div>
  <!--  End  top tab section-->
  <!--  Start body tab section -->
  <div class="right-content-body">
    <div>
      <c:if test="${studyBo.studyPreActiveFlag eq false}">
        <div class="form-group mr-sm" style="white-space: normal;">
          <button type="button" class="btn btn-primary blue-btn-action"
                  id="publishId" onclick="validateStudyStatus(this);"
              <c:choose>
                <c:when test="${not empty permission}">
                  disabled
                </c:when>
                <c:when
                    test="${not empty studyBo.status && (studyBo.status eq 'Paused' || studyBo.status eq 'Pre-launch(Published)' || studyBo.status eq 'Active' || studyBo.status eq 'Deactivated')}">
                  disabled
                </c:when>
              </c:choose>
                  <c:if test="${not studyPermissionBO.viewPermission}">disabled</c:if>>お知らせとして事前公開する
          </button>
        </div>
      </c:if>
      <c:if test="${studyBo.studyPreActiveFlag eq true}">
        <div class="form-group mr-sm" style="white-space: normal;">
          <button type="button" class="btn btn-primary blue-btn-action"
                  id="unpublishId" onclick="validateStudyStatus(this);"
              <c:choose>
                <c:when test="${not empty permission}">
                  disabled
                </c:when>
                <c:when
                    test="${not empty studyBo.status && (studyBo.status eq 'Paused' || studyBo.status eq 'Active' || studyBo.status eq 'Deactivated')}">
                  disabled
                </c:when>
              </c:choose>
                  <c:if test="${not studyPermissionBO.viewPermission}">disabled</c:if>>公開の取り消し
          </button>
        </div>
      </c:if>
      <div class="form-group mr-sm" style="white-space: normal;">
        <button type="button" class="btn btn-default gray-btn-action "
                id="lunchId" onclick="validateStudyStatus(this);"
            <c:choose>
              <c:when test="${not empty permission}">
                disabled
              </c:when>
              <c:when
                  test="${not empty studyBo.status && (studyBo.status eq 'Active' || studyBo.status eq 'Paused' || studyBo.status eq 'Deactivated')}">
                disabled
              </c:when>
            </c:choose>
                <c:if test="${not studyPermissionBO.viewPermission}">disabled</c:if>>治験を公開する
        </button>
      </div>

      <div class="form-group mr-sm" style="white-space: normal;">
        <button type="button" class="btn btn-default gray-btn-action"
                id="updatesId" onclick="validateStudyStatus(this);"
            <c:choose>
              <c:when test="${not empty permission}">
                disabled
              </c:when>
              <c:when test="${not empty studyBo.status && empty liveStudyBo && (studyBo.hasStudyDraft==0  || studyBo.status eq 'Pre-launch' || studyBo.status eq 'Pre-launch(Published)' ||
					             studyBo.status eq 'Paused' || studyBo.status eq 'Deactivated')}">
                disabled
              </c:when>
              <c:when test="${not empty studyBo.status && not empty liveStudyBo && (studyBo.hasStudyDraft==0  || studyBo.status eq 'Pre-launch' || studyBo.status eq 'Pre-launch(Published)' ||
					             studyBo.status eq 'Paused' || studyBo.status eq 'Deactivated' || liveStudyBo.status eq 'Paused')}">
                disabled
              </c:when>
            </c:choose>
                <c:if test="${not studyPermissionBO.viewPermission}">disabled</c:if>>更新内容を反映する
        </button>
      </div>

      <div class="form-group mr-sm" style="white-space: normal;">
        <button type="button" class="btn btn-default gray-btn-action "
                id="pauseId" onclick="validateStudyStatus(this);"
            <c:choose>
              <c:when test="${not empty permission}">
                disabled
              </c:when>
              <c:when
                  test="${empty liveStudyBo && not empty studyBo.status && (studyBo.status eq 'Pre-launch' || studyBo.status eq 'Pre-launch(Published)' || studyBo.status eq 'Paused'  || studyBo.status eq 'Deactivated')}">
                disabled
              </c:when>
              <c:when
                  test="${not empty liveStudyBo && not empty liveStudyBo.status && (liveStudyBo.status eq 'Pre-launch' || liveStudyBo.status eq 'Pre-launch(Published)' || liveStudyBo.status eq 'Paused'  || liveStudyBo.status eq 'Deactivated')}">
                disabled
              </c:when>
            </c:choose>
                <c:if test="${not studyPermissionBO.viewPermission}">disabled</c:if>>治験を一時停止する
        </button>
      </div>

      <div class="form-group mr-sm" style="white-space: normal;">
        <button type="button" class="btn btn-default gray-btn-action "
                id="resumeId" onclick="validateStudyStatus(this);"
            <c:choose>
              <c:when test="${not empty permission}">
                disabled
              </c:when>
              <c:when
                  test="${empty liveStudyBo && not empty studyBo.status && (studyBo.status eq 'Pre-launch' || studyBo.status eq 'Pre-launch(Published)' || studyBo.status eq 'Active' || studyBo.status eq 'Deactivated')}">
                disabled
              </c:when>
              <c:when
                  test="${not empty liveStudyBo && not empty liveStudyBo.status && (liveStudyBo.status eq 'Pre-launch' || liveStudyBo.status eq 'Pre-launch(Published)' || liveStudyBo.status eq 'Active'  || liveStudyBo.status eq 'Deactivated')}">
                disabled
              </c:when>
            </c:choose>
                <c:if test="${not studyPermissionBO.viewPermission}">disabled</c:if>>一時停止から再開する
        </button>
      </div>

      <div class="form-group mr-sm" style="white-space: normal;">
        <button type="button" class="btn btn-default red-btn-action "
                id="deactivateId" onclick="validateStudyStatus(this);"
            <c:choose>
              <c:when test="${not empty permission}">
                disabled
              </c:when>
              <c:when
                  test="${not empty studyBo.status && (studyBo.status eq 'Pre-launch' || studyBo.status eq 'Pre-launch(Published)' || studyBo.status eq 'Deactivated')}">
                disabled
              </c:when>
            </c:choose>
                <c:if test="${not studyPermissionBO.viewPermission}">disabled</c:if>>治験を終了する
        </button>
      </div>
    </div>
  </div>
</div>
<form:form
    action="/studybuilder/adminStudies/updateStudyAction.do?_S=${param._S}"
    name="actionInfoForm" id="actionInfoForm" method="post">
  <input type="hidden" name="studyId" id="studyId" value="${studyBo.id}"/>
  <input type="hidden" name="buttonText" id="buttonText" value=""/>
</form:form>
<form:form
    action="/studybuilder/adminStudies/studyList.do?_S=${param._S}"
    name="studyListInfoForm" id="studyListInfoForm" method="post">
</form:form>
<script type="text/javascript">
  $(document).ready(function () {
    $(".menuNav li").removeClass('active');
    $(".tenth").addClass('active');
    $("#createStudyId").show();
    $('.tenth').removeClass('cursor-none');
  });

  function validateStudyStatus(obj) {
    var buttonText = obj.id;
    var messageText = "";
    if (buttonText) {
      if (buttonText == 'unpublishId' || buttonText == 'pauseId'
          || buttonText == 'deactivateId') {
        if (buttonText == 'unpublishId') {
          message: '未保存の編集があります。保存をしないと編集内容が削除されてしまいます。本当にこのページを離れますか？',
        } else if (buttonText == 'pauseId') {
          messageText = "You are attempting to Pause the study. Mobile app users can no longer participate in study activities until you resume the study again. However, they will still be able to view the study dashboard and study resources. Are you sure you wish to proceed?";
        } else if (buttonText == 'deactivateId') {
          messageText = "You are attempting to Deactivate a live study. Once deactivated, mobile app users will no longer be able to participate in the study. Also, deactivated studies can never be reactivated. Are you sure you wish to proceed?";
        }
        bootbox.confirm({
          closeButton: false,
          message: messageText,
          buttons: {
            'cancel': {
              label: 'Cancel',
            },
            'confirm': {
              label: 'OK',
            },
          },
          callback: function (result) {
            if (result) {
              updateStudyByAction(buttonText);
            }
          }
        });
      } else {
        $
            .ajax({
              url: "/studybuilder/adminStudies/validateStudyAction.do?_S=${param._S}",
              type: "POST",
              datatype: "json",
              data: {
                buttonText: buttonText,
                "${_csrf.parameterName}": "${_csrf.token}",
              },
              success: function emailValid(data, status) {
                var message = data.message;
                var checkListMessage = data.checkListMessage;
                var checkFailureMessage = data.checkFailureMessage;
                if (message == "SUCCESS") {
                	showBootBoxMessage(buttonText,
                            messageText);
                } else {
                  if (buttonText == 'publishId') {
                    messageText = "治験を今後公開する前に、基本情報、設定、概要、および同意のセクションに完了のマークを付ける必要があります。これらのセクションを完了して、再試行してください。";
                  } else if (buttonText == 'lunchId') {
                    messageText = "治験を公開するためには、全てのセクションが完了になっている必要があります。続行するにはこれらのセクションを全て完了にしてください。";
                  } else if (buttonText == 'updatesId') {
                    messageText = "治験を公開するためには、全てのセクションが完了になっている必要があります。続行するにはこれらのセクションを全て完了にしてください。";
                  }
                  bootbox.confirm(message, function (result) {
                    bootbox.alert(messageText);
                  })

                }
              },
              error: function status(data, status) {
                $("body").removeClass("loading");
              },
              complete: function () {
                $('.actBut').removeAttr('disabled');
              }
            });
      }
    }

  }

  function showErrMsg1(message) {
    $("#alertMsg").removeClass('s-box').addClass('e-box').text(message);
    $('#alertMsg').show('10000');
    setTimeout(hideDisplayMessage, 10000);
  }

  function showBootBoxMessage(buttonText, messageText) {
    if (buttonText == 'resumeId') {
      messageText = "治験を再開すると、アプリユーザーは参加を再開できます。続行してよろしいですか？";
    } else if (buttonText == 'publishId') {
      messageText = "調査を「事前公開」として公開すると、アプリユーザーは治験の概要と同意フォームを確認できますが、参加することはできません。続行してよろしいですか？";
    } else if (buttonText == 'lunchId') {
      messageText = "治験の公開を行ってよろしいですか？これによりアプリユーザーは治験内容を確認して参加することが可能になります";
    } else if (buttonText == 'updatesId') {
      messageText = "治験の更新を公開してもよろしいですか？なお、アプリユーザーは治験の変更を確認することが出来ます。";
    }
    bootbox.confirm({
      closeButton: false,
      message: messageText,
      buttons: {
        'cancel': {
          label: 'Cancel',
        },
        'confirm': {
          label: 'OK',
        },
      },
      callback: function (result) {
        if (result) {
          updateStudyByAction(buttonText);
        }
      }
    })
  }

  function updateStudyByAction(buttonText) {
    if (buttonText) {
      var studyId = "${studyBo.id}";
      $
          .ajax({
            url: "/studybuilder/adminStudies/updateStudyAction.do?_S=${param._S}",
            type: "POST",
            datatype: "json",
            data: {
              buttonText: buttonText,
              studyId: studyId,
              "${_csrf.parameterName}": "${_csrf.token}",
            },
            success: function updateAction(data, status) {
              var message = data.message;
              if (message == "SUCCESS") {
                if (buttonText == 'deactivateId'
                    || buttonText == 'lunchId'
                    || buttonText == 'updatesId') {
                  $('#studyListInfoForm').submit();
                } else {
                  document.studyListInfoForm.action = "/studybuilder/adminStudies/actionList.do?_S=${param._S}";
                  document.studyListInfoForm.submit();
                }
              } else {
                $('#studyListInfoForm').submit();
              }
            },
            error: function status(data, status) {
              $("body").removeClass("loading");
            },
            complete: function () {
              $('.actBut').removeAttr('disabled');
            }
          });
    }
  }
</script>