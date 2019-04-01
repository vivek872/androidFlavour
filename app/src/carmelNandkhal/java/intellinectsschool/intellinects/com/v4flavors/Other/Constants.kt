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
    val SLIDER_IMAGES = "slider_images"
    val SCHOOL_ID = "115"

    var APP_NAME = "Carmel Nandkhal";
    //APP CONSTANTS
    val NOTIFY_TONE = "NOTIFY_TONE"
    val NOTIFY_TONE_URI = "NOTIFY_TONE_URI"
    val url = "https://apps.intellinects.com/masterapi/public/api/wpEvents"
   // var SCHOOL_ID = "187"

    var ATTENDANCE_CHECK = 0
    var mStringDate = "2016-01-01"
    internal val PUSH_APP_NO = "857140338781"
    var CURRENT_DATE = "1"
    var TERM_END_DATE = "0"
    var NAV_ITEMS_POST_LOGIN: Array<String>? = null
    var FIRST_RUN = true
    var NEW_SECURE_LOGIN_REQUEST_LIMITER = 5
    var YOUTUBE_API_KEY = "AIzaSyAxhh_CN63BKZ-Es1gwyBQ_e-4FaV2kzYk"
    var LAST_FCM_UPDATE_DATE = "22-01-2017"



    //New Login
    val LOGIN_ACCEPT = "application/json"

    //SHARED PREF CONSTANTS
    var SWIPE_NEWS = "swipe_news"
    val TERMS_ACCEPTED = "terms_accepted"
    val PREF_SCHOOL_ID = "key_of_school_id"
    val STATUS_TIMER = "status_timer"
    val KEY_LOGGED_IN = "key_logged_in"
    val KEY_OF_USERNAME = "key_of_username"
    val KEY_OF_USER_DISPLAY_NAME = "key_of_user_display_name"
    val KEY_OF_PASSWORD = "key_of_password"
    val KEY_IS_GUARDIAN = "key_of_guardian"
    val KEY_IS_STUDENT = "key_of_student"
    val KEY_OF_MOBILE_NUMBER = "key_of_mobile_number"
    val ROLE = "key_of_role"
    val KEY_OF_USER_ID = "key_of_uid"
    val USERNAME = "username"
    val PASSWORD = "password"
    val OTP_VALUE = "OtpValue"
    val NO_EMAIL_ADDRESS = "NO_EMAIL"
    val CLICKED_GET_STARTED = "Get_Started"
    val KEY_OF_DOWNLOADABLES = "downloadAbles"


    //URLS
     val IP_ADDRESS = "http://isirs.org/intellinect_dashboard"
     val BASE_URL = "/WS/index_main.php"
    val LOG_API = "http://isirs.org/intellinect_dashboard/WS/log_api.php"

    var FEEPOLICY_URL = "http://intelliadmissions.org/fee-policy"

    /*static final String YOUTUBE_URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UChl5wsAj6nrVk8W66_LP0Kw&maxResults=50&order=date&key="
            + Constants.YOUTUBE_API_KEY;*/

    //NATIVE WEBVIEW CONSTANTS
    val STATIC_TEXT_DISPLAY = "static_text_to_display"
    val STATIC_PAGE_HEADER = "static_page_header"
    val intellinectsid = "intellinectsid"
    val STATIC_QUIZ_HEADER = "static_quiz_header"
    val NATIV2E_WEB_VIEW_IMAGE_ICON = "native_web_view_image_icon"

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


    const val SCHOOLCODE = "ACICSE"

    val LEARNING_CENTER = "https://moodle.cjmkharghar.org/login/index.php?mob_mobileno=admin&mob_random_key=taraba_cjm&mob_emailid=ranjan@palinfocom.net&mob_password=1234"

    val LMS_URL = "https://ilms.intellinects.net/LMS_PARENT/pages/Subject.php"


    const val BASEURL = "https://intellischoolnew.test.intellischools.org/"
    const val MASTER_API_URL="https://apps.intellinects.com/masterapi/public/api/"
     val MORE_NEWS_URL = "https://carmelconventhighschool.org/news/"
    val FACILITIES_URL = "https://carmelconventhighschool.org/facilities/" //"https://cjmkharghar.org/facilities/"
    val CONTACT_US ="https://carmelconventhighschool.org/contact-us/"
     val SCHOOL_WEBSITE = "https://carmelconventhighschool.org/"  //"https://cjmkharghar.org/"
    val ABOUT_US = "https://carmelconventhighschool.org/about-us/school-history/"//"https://cjmkharghar.org/about-us/"
    val PRINCIPALS_MESSAGE ="https://carmelconventhighschool.org/about-us/principals-corner/" //"https://stmarysicse.com/about-us/principals-desk/"
    val TEACHERS_URL = "https://carmelconventhighschool.org/faculty-staff/"  //" https://school.intellischools.org/staff/"
    val ROLL_OF_HONOUR="https://carmelconventhighschool.org/academics/roll-honour/"
    val YOUTUBE_EDU_URL = "https://www.youtube.com/watch?v=489o9elu4AA"





}
