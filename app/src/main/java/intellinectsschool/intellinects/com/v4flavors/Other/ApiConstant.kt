package intellinectsschool.intellinects.com.v4flavors.Other




/**
 *Modified by vikas 13/3/2018
 */
class ApiConstant {
    companion object {
        const val CLASS_LIST_API = Constants.BASEURL + "classWithSubjectListAPI"
        const val REGSITER_API = Constants.BASEURL + "register"
        const val LOGIN_API = Constants.BASEURL + "login"
        const val UPADTE_REGSITER_API = Constants.BASEURL + "updateRegistrationDetails"
        const val UPLOAD_HOMEWORK = Constants.BASEURL + "HomeworkAddAPI"
        const val UPLOAD_MSG = Constants.BASEURL + "MessageAddAPI"

        const val EMP_HOMEWORK_LIST = Constants.BASEURL + "HomeworkShowAPI"
        const val EMP_CIRCULAR_LIST = Constants.BASEURL + "CircularShowAPI"
        const val UPLOAD_CIRCUAR = Constants.BASEURL + "CircularAddAPI"
        const val HMC_COUNT = Constants.BASEURL + "monthly_hmc_count"
        const val SLIDER_IMAGES = Constants.BASEURL + "getSliderImages"

        const val FCM_UPDATE= Constants.BASEURL + "updateFcmToken"

        const val STUDENT_LIST = Constants.BASEURL + "studentList"
        const val STUDENT_LIST_SEARCH = Constants.BASEURL + "studentSearch"
        const val EDIT_ATTENDANCE_LIST = Constants.BASEURL + "editAttendanceList"


        const val SCHOOLPROFILE_ATTENDANCE_COUNT= Constants.BASEURL + "attendanceDetailsAPI"
        const val SAVE_ATTENDANCE_STUDENT= Constants.BASEURL + "saveAttendanceList"
        const val DIGITAL_ENGAGEMENT= Constants.BASEURL + "digitalEngagement"

        const val SCHOOLPROFILE= Constants.BASEURL +"schoolInfo"
        const val DIGITAL_ENGAGEMENT_TEACHER_PROFILE= Constants.BASEURL +"teacherDigitalEngagement"
        const val FORCE_UPDATE= Constants.BASEURL +"forceUpdate"
        const val NOTIFICATION_STATUS= Constants.BASEURL +"notificationStatus"


        const val EMPLOYEE_LIST= Constants.BASEURL +"employeeList"
        const val DAILY_HMC= Constants.BASEURL +"get_HMCList"
        const val ATTSUMPARENT= Constants.BASEURL +"studentMonthAttendanceSummary"

        const val DIGISUMMARY= Constants.BASEURL + "digital_summary"



        const val JOURNAL_COUNT= Constants.BASEURL +"studentJournalCount"
        const val JOURNAL_COUNT_TEACHER= Constants.BASEURL +"employeeJournalCount"


        const val JOURNAL_LIST= Constants.BASEURL +"studentJournalList"
        const val EMPLOYEE_JOURNAL_LIST= Constants.BASEURL +"employeeJournalList"


        const val JOURNAL_CATEGORY= Constants.BASEURL +"studentJournalCategoryList"
        const val JOURNAL_SEND_NOTE= Constants.BASEURL +"studentJournalEntry"

        const val SMC_COUNT= Constants.BASEURL +"MonthlySMSCount"

        const val INBOX_PARENT= Constants.BASEURL+"parentSMSList"

        const val SEND_SMS= Constants.BASEURL+"send_sms"

        const val JOURNAL_CATEGORY_TEACHER= Constants.BASEURL +"employeeJournalCategoryList"

        const val JOUNAL_SEND_NOTE_TEACHER=Constants.BASEURL+"storeEmployeeJournalEntry"

        const val COUNT_INBOX_UNREAD=Constants.BASEURL+"unreadParentSMSCount"

        const val INBOX_READ_STATUS=Constants.BASEURL+"ChangeSMSReadStatus"


        //New News and event API


        //sms_send_teacherside
        const val SEND_SMS_EMP=Constants.BASEURL+"send_smsToEmployee"





    }
}