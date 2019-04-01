package intellinectsschool.intellinects.com.v4flavors.Other

import android.graphics.drawable.Drawable
import com.android.volley.toolbox.Volley
import intellinectsschool.intellinects.com.v4flavors.Fcm.IntellinectsApp


import java.util.ArrayList

/**
 * Created by intellinects on 3/4/18.
 */

object Constants {
    //FCM
    val FCM_TOKEN = "fcm_token"
   // val SLIDER_IMAGES = "slider_images"

     val SCHOOL_ID = 200
     val SCHOOL_ID_ACT = 55

    var APP_NAME = "Divine Child CBSE"
    //APP CONSTANTS
    val NOTIFY_TONE = "NOTIFY_TONE"
    val NOTIFY_TONE_URI = "NOTIFY_TONE_URI"
    val url = "https://apps.intellinects.com/masterapi/public/api/wpEvents"
   // var SCHOOL_ID = "187"

    var ATTENDANCE_CHECK = 0
    var mStringDate = "2016-01-01"
    internal val PUSH_APP_NO = "857140338781"


    //New Login
    val LOGIN_ACCEPT = "application/json"

    //SHARED PREF CONSTANTS
    var SWIPE_NEWS = "swipe_news"



    //URLS
     val IP_ADDRESS = "http://isirs.org/intellinect_dashboard"
     val BASE_URL = "/WS/index_main.php"
    val LOG_API = "http://isirs.org/intellinect_dashboard/WS/log_api.php"

    var FEEPOLICY_URL = "http://intelliadmissions.org/fee-policy"

    /*static final String YOUTUBE_URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UChl5wsAj6nrVk8W66_LP0Kw&maxResults=50&order=date&key="
            + Constants.YOUTUBE_API_KEY;*/

    //NATIVE WEBVIEW CONSTANTS


    //ICONS
    var EVENTS_CLOCK: Drawable? = null
    var NDA_IMAGE: Drawable? = null
    var MOBILE_ICON: Drawable? = null
    var USER_ICON: Drawable? = null
    var PASSWORD_ICON: Drawable? = null
    var COG_ICON: Drawable? = null
    var ATTACHMENT_ICON: Drawable? = null
    var DOWNLOAD_ICON: Drawable? = null
    var DESCRIPTION_ICON: Drawable? = null
    var CALENDER_ICON: Drawable? = null
    var SUBJECT_ICON: Drawable? = null
    var BACK_ARROW: Drawable? = null
    var LOGOUT_BUTTON: Drawable? = null
    var RIGHT_ARROW: Drawable? = null
    var VENUE_ICON: Drawable? = null
    var PHONE_ICON: Drawable? = null

    //support
    val OS_VERSION_NAME = "os_version_name"
    val DEVICE_MODEL_NAME = "device_model_name"
    val EMP_HOMEWORK_RESPONSE = "EMP_HOMEWORK_RESPONSE"
    val EMP_CIRCUKAR_RESPONSE = "EMP_CIRCUKAR_RESPONSE"
    val EMP_MESSAGE_RESPONSE = "EMP_MESSAGE_RESPONSE"
    val EMP_STAFF_LIST = "EMP_SATFF_LIST"


    //New varibale

    var REQUEST_QUEUE = Volley.newRequestQueue(IntellinectsApp.getAppContext())
    const val ERRORMSG = "Error"

    const val KEY_OF_LOGGIN_FLAG = "login_status"

    val PARENT_ROLE = "Parent"
    val EMPLOYEE_ROLE = "Teacher"

    //REGISTRATION RESPONSE
    val USER_ALREADY_REGISTER = "User with this mobile number is already exist"
    val USER_MOBILE_NOT_REGISTERED = "Your Mobile Number is not available with us"
    val SUCCESS = "SUCCESS"
    val USER_ALREADY_EXITS = "User with this mobile number is already exist"


    //Token Constants
    var KEY_OF_AUTH_TOKEN = "token"
    val AUTH_CLIENT_SECRET = "t4Dwy0QUEjjQqNnwGQ71PhH4cDhfQyIs7XTy9V35"
    val AUTH_GRANT_TYPE = "password"
    val REFRESH_GRANT_TYPE = "refresh_token"
    val AUTH_CLIENT_ID = "4"
    val AUTH_ACCEPT = "application/json"
    val AUTH_SCOPE = ""
 val BOARDID="1"
    //Login Token
    val INCORRECTLOGIN = "The user credentials were incorrect."

    //otp
    const val OTP_RESPONSE = "OtpResponse"
    const val INVALIDPARAM = "Invalid Parameters."

    val EXTENSIONS_SUPPORTED = arrayOf("pdf", "PDF", "jpg", "JPG", "jpeg", "JPEG", "png", "PNG","doc","docx","xls","xlsx","ppt","pptx","rtf","txt","numbers","pages","keynote")

    val FILE_EXTENSIONS_SUPPORTED = arrayOf("pdf", "PDF","doc","docx","xls","xlsx","ppt","pptx","rtf","txt","numbers","pages","keynote")
    val IMAGE_EXTENSIONS_SUPPORTED = arrayOf("jpg", "JPG", "jpeg", "JPEG", "png", "PNG")

    const val EMPLOYEE_ROLE_ID = "employee_role_id"
    const val SUBJECT_SETTINGS = "school_employee_class_setting_array"
    const val EMPLOYEE_ID = "employee_id"
    const val PARTION_YEAR = "  partition_year"
    const val TYPE = "type"
    const val MONTH = "month"
    const val YEAR = "year"
    const val FROM_INTENT = "from_intent"

    const val ROLE_TYPE = "role_type"

    const val FCM_KEY = "fcm_key"
    const val TONE_KEY = "tone_key"


    const val ACADEMIC_YEAR = "academic_year"
    const val CLASS_NAME = "class_name"
    const val CLASS_ID = "class_id"
    const val DIV_ID = "div_id"
    const val INTELLINECT_ID = "intellinect_id"


    const val MOBILE_NUMBER = "mobilenumber"
    const val MOBILE_NUMBER_KEY = "mobilenumberkey"
    const val SCHOOL_PROFILE = "schoolprofile"
    const val TEACHER_PROFILE = "teacherprofile"

    const val DIGITAL_ENGAGEMENT = "digitalengagement"
    const val ATTENDANCE_SUMMARY = "attendancesummary"

    //school_profile

    const val school_profile_logo = "schoolprofilelogo"
    const val school_phone = "schoolphone"
    const val school_add = "schooladd"

    //digital engagement
    const val list_count_header = "listcountheader"
    const val list_count_data = "listcountdata"

    //attendace summary
    const val class_group_wise = "classgroupwise"
    const val classwise = "classwise"

    //teacher_profile
    const val statusteacher = "statusteacher"
    const val msgteacher = "msgteacher"
    const val teachername = "teachername"
    const val teacheradd = "teacheradd"
    const val teacheremail = "teacheremail"
    const val teachermob = "teachermob"


    const val HOMEWORK_RESPONSE = "homeworkresponse"
    const val CIRCUKAR_RESPONSE = "circularresponse"
    const val MESSAGE_RESPONSE = "messageresponse"

    //support ticket
    const val FATHER_NAME="father_name"
    const val FATHER_EMAIL="father_email"
    const val FATHER_MOB="father_mob"
    const val MOTHER_NAME="mother_name"
    const val MOTHER_EMAIL="mother_email"
    const val MOTEHR_MOB="mother_mob"

    //student_profile
    const val STUDENTPROFILEATTENDANCE="STUDENTPROFILEATTENDANCE"

    //digital_summary
    const val DIGISUMM="DIGISUMM"
    const val TAB_HEADER_COUNT="TAB_HEADER_COUNT"
    const val TAB_HEADER_TITLE="TAB_HEADER_TITLE"
    const val TAB_HEADER_STATUS="TAB_HEADER_STATUS"



    const val SCHOOL_DB_SETTINGS = "school_db_settings_array"

    const val INBOX_COUNT="inbox_count"
    const val PLATFORM="Android"
    const val SCHOOLCODE = "ACICSE"
    const val SCHOOL_ID_CODE = "252"
    const val BASEURL = "https://intellischoolnew.test.intellischools.org/"
    //        const val BASEURL_TEST="https://test.intellischools.org/api/"
 const val TERMS_URL = "https://donbosconaigaon.org/terms-of-app-use-privacy-policy/";
 const val MORE_NEWS_URL = "https://donbosconaigaon.org/news/";

 const val FACILITIES_URL = "https://donbosconaigaon.org/academics/facilities/";
 const val PRINCIPALS_MESSAGE = "https://donbosconaigaon.org/about-us/principals-message/";
 const val  CONTACT_US = "https://donbosconaigaon.org/contact-us/";
 const val  ABOUT_US = "https://donbosconaigaon.org/about-us/";
 const val SCHOOL_WEBSITE = "https://donbosconaigaon.org/";

 //const val FACEBOOK_URL = null;
 const val TEACHERS_URL = "https://donbosconaigaon.org/faculty-and-staff/";


    val LEARNING_CENTER = "https://moodle.cjmkharghar.org/login/index.php?mob_mobileno=admin&mob_random_key=taraba_cjm&mob_emailid=ranjan@palinfocom.net&mob_password=1234"
    val LMS_URL = "https://ilms.intellinects.net/LMS_PARENT/pages/Subject.php"


    val NEWS_URL = "https://donbosconaigaon.org/news/"
    val EVENT_URL = "https://donbosconaigaon.org/calendar/"








}
