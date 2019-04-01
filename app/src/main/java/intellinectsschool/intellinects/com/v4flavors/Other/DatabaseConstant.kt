package intellinectsschool.intellinects.com.v4flavors.Other

class DatabaseConstant {
    companion object {
        //class table creation
        const val CLASSLIST_TABLE = "tbl_class_list"
        const val COLUMN_ID_AUTO = "id"
        const val COLUMN_CLASS_ID = "classID"
        const val COLUMN_CLASS_NAME = "class"
        const val CLASS_TABLE_CREATE_SCRIPT = ("create table if not exists " + CLASSLIST_TABLE + " ( " + COLUMN_ID_AUTO + " integer primary key autoincrement, "
                + COLUMN_CLASS_ID + " INTEGER, " + COLUMN_CLASS_NAME + " Text ) ")


        //division table creation
        const val DIVLIST_TABLE = "tbl_div_list"
        const val DIV_ID_AUTO = "id"
        const val COLUMN_DIV_ID = "divID"
        const val COLUMN_DIV_NAME = "divname"
        const val DIV_TABLE_CREATE_SCRIPT = ("create table if not exists " + DIVLIST_TABLE + " ( " + DIV_ID_AUTO + " integer primary key autoincrement, "
                + COLUMN_DIV_ID + " INTEGER, " + COLUMN_CLASS_ID + " INTEGER, " + COLUMN_DIV_NAME + " Text ) ")


        //subject table creation
        const val SUBLIST_TABLE = "tbl_sub_list"
        const val SUB_ID_AUTO = "id"
        const val COLUMN_SUB_ID = "subID"
        const val COLUMN_SUB_NAME = "subName"
        const val COLUMN_SUB_SUBJECTS = "subSubject"


        //subject table creation classwise
        const val SUBLIST_CLASSWISE_TABLE = "tbl_sub_list_classwise"
        const val SUBJECT_TABLE_CLASS_WISE_SCRIPT = ("create table if not exists " + SUBLIST_CLASSWISE_TABLE + " ( " + SUB_ID_AUTO + " integer primary key autoincrement, "
                + COLUMN_SUB_ID + " INTEGER, " + COLUMN_CLASS_ID + " INTEGER, " + COLUMN_SUB_SUBJECTS + " Text, " + COLUMN_SUB_NAME + " Text ) ")


        const val SUBJECT_TABLE_CREATE_SCRIPT = ("create table if not exists " + SUBLIST_TABLE + " ( " + SUB_ID_AUTO + " integer primary key autoincrement, "
                + COLUMN_SUB_ID + " INTEGER, " + COLUMN_DIV_ID + " INTEGER, " + COLUMN_SUB_SUBJECTS + " Text, " + COLUMN_SUB_NAME + " Text ) ")

        val USER_DETAILS_DATABASE = "user_details_database"


        //USER DETAILS DATA SOURCE CONSTANTS
        const val STUDENT_DETAILS_TABLE = "student_details_table"
        const val STUDENT_PROFILE_TABLE = "student_profile_table"

        private const val STUDENT_DETAILS_AUTO_ID = "id"
        const val STUDENT_DETAILS_ID = "student_details_id"
        const val STUDENT_DETAILS_GUARD = "student_details_guard"
        const val STUDENT_DETAILS_FNAME = "student_details_fname"
        const val STUDENT_DETAILS_MNAME = "student_details_mname"
        const val STUDENT_DETAILS_LNAME = "student_details_lname"
        const val STUDENT_DETAILS_EMAIL_ADDRESS = "student_details_email_address"
        const val STUDENT_DETAILS_GENDER = "student_details_gender"
        const val STUDENT_DETAILS_MOBILE_NUMBER = "student_details_phone_number"
        const val STUDENT_DETAILS_INTELLINECTS_ID = "student_details_intellinects_id"
        const val STUDENT_DETAILS_PHOTO = "student_details_photo"
        private const val STUDENT_DETAILS_USER_NAME = "student_details_user_name"
        const val STUDENT_DETAILS_USER_PASSWORD = "student_details_user_password"
        const val STUDENT_DETAILS_BOARD_LABEL = "student_details_board_label"
        const val STUDENT_DETAILS_CLASS_LABEL = "student_details_class_label"
        const val STUDENT_DETAILS_DIVISION_LABEL = "student_details_division_label"
        const val STUDENT_DETAILS_ROLL_NO = "student_details_roll_no"
        const val STUDENT_DETAILS_CLASS_ID = "student_details_class_id"
        const val STUDENT_DETAILS_DIVISION_ID = "student_details_division_id"

        const val STUDENT_HOME_TELEPHONE_NUMBER = "student_home_telephone_number"
        const val STUDENT_PRIMARY_CONTACT = "student_primary_contact"
        const val STUDENT_GR_NUMBER = "student_gr_number"
        const val STUDENT_BLOOD_GROUP = "student_blood_group"
        const val STUDENT_ALLERGY = "student_allergy"
        const val STUDENT_BIRTH_DATE = "student_birth_date"
        const val STUDENT_ADHAR_NUMBER = "student_adhar_number"
        const val STUDENT_ADDRESS = "student_address"
        const val STUDENT_GENDER= "student_gender"

        const val PARENT_DESIGNATION = "parent_designation"
        const val PARENT_OFFICE_ADDRESS = "parent_office_address"
        const val PARENT_OCCUPATION = "parent_occupation"
        const val PARENT_DECEASED = "parent_deceased"
        const val PARENT_PAN_NUMBER = "parent_pan_number"
        const val ROLE_TYPE = "role_type"
        const val GENDER_STD="gender_std"


        const val STUDENT_DETAILS_TABLE_CREATE_SCRIPT = (
                "create table if not exists "
                        + STUDENT_DETAILS_TABLE + " ( " + STUDENT_DETAILS_AUTO_ID + " integer primary key autoincrement, "
                        + STUDENT_DETAILS_ID + " Text, " + " Text, " + STUDENT_DETAILS_GUARD + " Text , "
                        + STUDENT_DETAILS_FNAME + " Text, " + STUDENT_DETAILS_MNAME + " Text, "
                        + STUDENT_DETAILS_LNAME + " Text, " + STUDENT_DETAILS_GENDER + " Text, " + STUDENT_DETAILS_MOBILE_NUMBER + " Text, "
                        + STUDENT_DETAILS_EMAIL_ADDRESS + " Text, " + STUDENT_DETAILS_INTELLINECTS_ID + " Text, " + STUDENT_DETAILS_PHOTO + " Text, "
                        + STUDENT_DETAILS_USER_NAME + " Text, " + STUDENT_DETAILS_USER_PASSWORD + " Text, "
                        + STUDENT_DETAILS_BOARD_LABEL + " Text, " + STUDENT_DETAILS_CLASS_LABEL + " Text, "
                        + STUDENT_DETAILS_DIVISION_LABEL + " Text, " + STUDENT_DETAILS_ROLL_NO + " Text, "
                        + STUDENT_DETAILS_CLASS_ID + " Text, " + STUDENT_DETAILS_DIVISION_ID + " Text, "
                        + STUDENT_HOME_TELEPHONE_NUMBER + " Text, " + STUDENT_PRIMARY_CONTACT + " Text, "
                        + STUDENT_GR_NUMBER + " Text, " + STUDENT_BLOOD_GROUP + " Text, "
                        + STUDENT_ALLERGY + " Text, " + STUDENT_BIRTH_DATE + " Text, "
                        + STUDENT_ADHAR_NUMBER + " Text, " + STUDENT_ADDRESS + " Text, "
                        + PARENT_DESIGNATION + " Text, " + PARENT_OFFICE_ADDRESS + " Text, "
                        + PARENT_OCCUPATION + " Text, " + PARENT_DECEASED + " Text, "+ ROLE_TYPE + " Text,"+ STUDENT_GENDER + " Text, "
                        + PARENT_PAN_NUMBER + " Text);")

        const val STUDENT_PROFILE_TABLE_CREATE_SCRIPT = (
                "create table if not exists "
                        + STUDENT_PROFILE_TABLE + " ( " + STUDENT_DETAILS_AUTO_ID + " integer primary key autoincrement, "
                        + STUDENT_DETAILS_ID + " Text, " + " Text, " + STUDENT_DETAILS_GUARD + " Text , "
                        + STUDENT_DETAILS_FNAME + " Text, " + STUDENT_DETAILS_MNAME + " Text, "
                        + STUDENT_DETAILS_LNAME + " Text, " + STUDENT_DETAILS_GENDER + " Text, " + STUDENT_DETAILS_MOBILE_NUMBER + " Text, "
                        + STUDENT_DETAILS_EMAIL_ADDRESS + " Text, " + STUDENT_DETAILS_INTELLINECTS_ID + " Text, " + STUDENT_DETAILS_PHOTO + " Text, "
                        + STUDENT_DETAILS_USER_NAME + " Text, " + STUDENT_DETAILS_USER_PASSWORD + " Text, "
                        + STUDENT_DETAILS_BOARD_LABEL + " Text, " + STUDENT_DETAILS_CLASS_LABEL + " Text, "
                        + STUDENT_DETAILS_DIVISION_LABEL + " Text, " + STUDENT_DETAILS_ROLL_NO + " Text, "
                        + STUDENT_DETAILS_CLASS_ID + " Text, " + STUDENT_DETAILS_DIVISION_ID + " Text, "
                        + STUDENT_HOME_TELEPHONE_NUMBER + " Text, " + STUDENT_PRIMARY_CONTACT + " Text, "
                        + STUDENT_GR_NUMBER + " Text, " + STUDENT_BLOOD_GROUP + " Text, "
                        + STUDENT_ALLERGY + " Text, " + STUDENT_BIRTH_DATE + " Text, "
                        + STUDENT_ADHAR_NUMBER + " Text, " + STUDENT_ADDRESS + " Text, "
                        + PARENT_DESIGNATION + " Text, " + PARENT_OFFICE_ADDRESS + " Text, "
                        + PARENT_OCCUPATION + " Text, " + PARENT_DECEASED + " Text, "+ ROLE_TYPE + " Text, "+ STUDENT_GENDER + " Text, "
                        + PARENT_PAN_NUMBER + " Text);")

        //EMPLOYEE DETAILS DATA SOURCE CONSTANTS
        val EMPLOYEE_DETAILS_TABLE = "employee_details_table"
        private val EMPLOYEE_DETAILS_TABLE_ID = "employee_details_id"
        val EMPLOYEE_INTELLINECTS_ID = "employee_details_intellinects_id"
        val EMPLOYEE_PHOTO = "employee_photo"
        val EMPLOYEE_FNAME = "employee_details_fname"
        val EMPLOYEE_LNAME = "employee_details_lname"
        val EMPLOYEE_USER_NAME = "employee_details_user_name"
        val EMPLOYEE_ROLL_ID = "employee_details_roll_id"
        val EMPLOYEE_GENDER = "employee_details_gender"
        val EMPLOYEE_ROLE_DETAILS_TABLE_ID = "employee_role_details_table_id"
        val EMPLOYEE_ROLE = "employee_role"
        val EMPLOYEE_RANK_LEVEL = "empolyee_rank_level"



        val EMPLOYEE_DETAILS_TABLE_CREATE_SCRIPT = (
                "create table if not exists "
                        + EMPLOYEE_DETAILS_TABLE + " ( " + EMPLOYEE_DETAILS_TABLE_ID + " integer, "
                        + EMPLOYEE_FNAME + " Text, " + EMPLOYEE_LNAME + " Text, "
                        + EMPLOYEE_INTELLINECTS_ID + " Text, " + EMPLOYEE_PHOTO + " Text, "
                        + EMPLOYEE_USER_NAME + " Text, " + EMPLOYEE_ROLL_ID + " Text, "
                        + EMPLOYEE_GENDER + " Text, " + EMPLOYEE_ROLE_DETAILS_TABLE_ID + " Text, "
                        + ROLE_TYPE + " Text, "
                        + EMPLOYEE_ROLE + " Text, " + EMPLOYEE_RANK_LEVEL + " Text);")


        //FACILITY CONSTANTS
        const val FACILITY_TABLE = "facility_table"
        const val FACILITY_ID_KEY = "facility_id"
        const val FACILITY_GUARD = "facility_guard"
        const val FACILITY_NAME = "facility_name"
        const val FACILITY_ACTIVE = "facility_active"
        const val FACILITY_LINK = "facility_link"
        const val FACILITY_DETAILS = "facility_details"
        const val FACILITY_CLASS_DETAILS = "facility_class_details"
        const val FACILITY_ADD_ACTIVE = "facility_add_active"
        const val FACILITY_DELETE_ACTIVE = "facility_delete_active"
        const val FACILITY_VIEW_ACTIVE = "facility_view_active"
        const val FACILITY_EDIT_ACTIVE = "facility_edit_active"

        const val FACILITY_TABLE_CREATE_SCRIPT = ("create table if not exists "
                + FACILITY_TABLE + " ( " + FACILITY_ID_KEY + " integer primary key autoincrement, "
                + FACILITY_NAME + " Text, " + FACILITY_GUARD + " Text, "
                + FACILITY_ACTIVE + " Text, " + FACILITY_LINK + " Text, "
                + FACILITY_DETAILS + " Text, " + FACILITY_ADD_ACTIVE + " Text, "
                + FACILITY_DELETE_ACTIVE + " Text, " + FACILITY_VIEW_ACTIVE + " Text, "
                + FACILITY_EDIT_ACTIVE + " Text, " + FACILITY_CLASS_DETAILS + " Text );")

        const val ATTENDANCE_TABLE = "tbl_attendance_table"
        private const val ATTENDANCE_COLUMN_ID_AUTO = "id"
        const val ATTENDANCE_COLUMN_INTELLINECTS_ID = "intellinects_id"
        const val ATTENDANCE_COLUMN_MONTH = "month"
        const val ATTENDANCE_COLUMN_YEAR = "year"
        const val ATTENDANCE_COLUMN_DAY = "day"
        const val ATTENDANCE_COLUMN_ATTENDANCE = "attendance"
        const val ATTENDANCE_TABLE_CREATE_SCRIPT = ("create table if not exists " + ATTENDANCE_TABLE + " ( " + ATTENDANCE_COLUMN_ID_AUTO + " integer primary key autoincrement, "
                + ATTENDANCE_COLUMN_INTELLINECTS_ID + " Text, " + ATTENDANCE_COLUMN_MONTH + " Text, " + ATTENDANCE_COLUMN_YEAR + " Text, " + ATTENDANCE_COLUMN_ATTENDANCE + " Text, " + ATTENDANCE_COLUMN_DAY + " Text ) ")


        //NEWS DATA SOURCE CONSTANTS
        const val NEWS_TABLE = "news_table"
        const val NEWS_EXTRA_KEY_DATE = "newsDate"
        const val NEWS_EXTRA_KEY_TITLE = "newsTitle"
        const val NEWS_EXTRA_KEY_DETAILS = "newsDetails"
        private const val NEWS_ID_KEY = "news_table_id"
        const val NEWS_DATE = "news_table_date"
        const val NEWS_DESCRIPTION = "news_table_description"
        const val NEWS_TITLE = "news_title"
        const val NEWS_PID_KEY = "news_table_pid"
        const val NEWS_POST_STATUS = "news_table_status"
        const val NEWS_TABLE_CREATE_SCRIPT = ("create table if not exists "
                + NEWS_TABLE + " ( " + NEWS_ID_KEY
                + " integer primary key autoincrement, " + NEWS_DATE + " Text, "
                + NEWS_DESCRIPTION + " Text, " + NEWS_TITLE + " Text, "
                + NEWS_PID_KEY + " Text," + NEWS_POST_STATUS + " Text );")

        //SECURE NEWS DATA SOURCE CONSTANTS
        const val SECURE_NEWS_TABLE = "secure_news_table"
        const val SECURE_NEWS_EXTRA_KEY_DATE = "secure_newsDate"
        const val SECURE_NEWS_EXTRA_KEY_TITLE = "secure_newsTitle"
        const val SECURE_NEWS_EXTRA_KEY_DETAILS = "secure_newsDetails"
        private const val SECURE_NEWS_ID_KEY = "secure_news_table_id"
        const val SECURE_NEWS_DATE = "secure_news_table_date"
        const val SECURE_NEWS_DESCRIPTION = "secure_news_table_description"
        const val SECURE_NEWS_TITLE = "secure_news_title"
        const val SECURE_NEWS_PID_KEY = "secure_news_table_pid"
        const val SECURE_NEWS_POST_STATUS = "secure_news_table_status"
        const val SECURE_NEWS_TABLE_CREATE_SCRIPT = ("create table if not exists "
                + SECURE_NEWS_TABLE + " ( " + SECURE_NEWS_ID_KEY
                + " integer primary key autoincrement, " + SECURE_NEWS_DATE + " Text, "
                + SECURE_NEWS_DESCRIPTION + " Text, " + SECURE_NEWS_TITLE + " Text, "
                + SECURE_NEWS_PID_KEY + " Text," + SECURE_NEWS_POST_STATUS + " Text );")


        //EVENTS DATA SOURCE CONSTANTS
        const val EVENT_EXTRA_KEY_TITLE = "eventTitle"
        const val EVENT_EXTRA_KEY_EVENTDATE = "eventDate"
        const val EVENT_EXTRA_KEY_START_DATE = "eventStartDate"
        const val EVENT_EXTRA_KEY_END_DATE = "eventEndDate"
        const val EVENT_EXTRA_KEY_END_ISALL_DAY = "eventIsAllDay"
        const val EVENT_EXTRA_KEY_DESCRIPTION = "eventDescription"
        const val EVENT_EXTRA_KEY_VENUE = "eventVenue"
        const val EVENT_EXTRA_KEY_CONTACT_NAME = "eventContactName"
        const val EVENT_EXTRA_KEY_CONTACT_PHONE = "eventContactPhone"
        const val EVENT_TABLE = "event_table"

        private const val EVENT_ID = "event_id"
        const val EVENT_ID_KEY = "event_id"
        const val EVENT_JSON = "event_json"
        const val EVENT_TABLE_CREATE_SCRIPT = ("create table if not exists "
                + EVENT_TABLE + " ( " + EVENT_ID + " integer, " + EVENT_JSON + " Text );")

        //SECURE EVENTS DATA SOURCE CONSTANTS
        const val SECURE_EVENT_TABLE = "secure_event_table"
        private const val SECURE_EVENT_ID = "secure_event_id"
        const val SECURE_EVENT_ID_KEY = "secure_event_id"
        const val SECURE_EVENT_JSON = "secure_event_json"
        const val SECURE_EVENT_TABLE_CREATE_SCRIPT = ("create table if not exists "
                + SECURE_EVENT_TABLE + " ( " + SECURE_EVENT_ID + " integer, " + SECURE_EVENT_JSON + " Text );")



        //MESSAGE CONSTANTS
        val MESSAGE_TABLE = "tbl_message"
        private val MESSAGE_TABLE_AUTO_ID = "ID"
        val MESSAGE_TABLE_ID = "message_id"
        val MESSAGE_TABLE_CONTENT = "content"
        val MESSAGE_TABLE_DATE = "date"
        val MESSAGE_TABLE_TEACHER_PHOTO="teacher_photo"
        val MESSAGE_TABLE_TITLE = "title"
        val MESSAGE_TABLE_STANDARD_ID = "standard_id"
        val MESSAGE_TABLE_DIVISION_ID = "division_id"
        val MESSAGE_TABLE_TEACHER = "teacher"
        val MESSAGE_TABLE_ATTACHMENT = "attachment"
        val MESSAGE_TABLE_CREATE_SCRIPT = ("create table if not exists "
                + MESSAGE_TABLE + " ( " + MESSAGE_TABLE_AUTO_ID + " integer primary key autoincrement, "
                + MESSAGE_TABLE_ID + " Text , " + MESSAGE_TABLE_CONTENT + " Text , "
                + MESSAGE_TABLE_TITLE + " Text, " + MESSAGE_TABLE_STANDARD_ID
                + " Text, " + MESSAGE_TABLE_DIVISION_ID + " Text, "
                + MESSAGE_TABLE_TEACHER + " Text, " + MESSAGE_TABLE_DATE + " Text,"
                + MESSAGE_TABLE_TEACHER_PHOTO + " Text,"
                + MESSAGE_TABLE_ATTACHMENT + " Text)")

        //HOMEWORK CONSTANTS
        val HOMEWORK_TABLE = "tbl_homework"
        val HOMEWORK_TABLE_ID = "homework_id"
        private val HOMEWORK_TABLE_AUTO_ID = "ID"
        val HOMEWORK_TEACHER_PHOTO="teacher_photo"
        val HOMEWORK_TABLE_CONTENT = "content"
        val HOMEWORK_TABLE_DATE = "date"
        val HOMEWORK_TABLE_TITLE = "title"
        val HOMEWORK_TABLE_SUBJECT = "subject"
        val HOMEWORK_TABLE_TEACHER = "teacher"
        val HOMEWORK_TABLE_STANDARD_ID = "standard_id"
        val HOMEWORK_TABLE_DIVISION_ID = "division_id"
        val HOMEWORK_TABLE_ATTACHMENT = "image"
        val HOMEWORK_TABLE_CREATE_SCRIPT = ("create table if not exists "
                + HOMEWORK_TABLE + " ( " + HOMEWORK_TABLE_AUTO_ID + " integer primary key autoincrement, "
                + HOMEWORK_TABLE_ID + " Text, " + HOMEWORK_TABLE_CONTENT + " Text , "
                + HOMEWORK_TABLE_SUBJECT + " Text, " + HOMEWORK_TABLE_TEACHER + " Text, " + HOMEWORK_TABLE_STANDARD_ID + " Text, "
                + HOMEWORK_TABLE_TITLE + " Text, " + HOMEWORK_TABLE_DATE + " Text, "
                + HOMEWORK_TABLE_ATTACHMENT + " Text, " + MESSAGE_TABLE_TEACHER_PHOTO + " Text,"+ HOMEWORK_TABLE_DIVISION_ID + " Text)")

        //CIRCULARS CONSTANTS
        val CIRCULAR_TABLE = "tbl_circular"
        val CIRCULAR_TABLE_ID = "circular_id"
        private val CIRCULAR_TABLE_AUTO_ID = "ID"
        val CIRCULAR_TABLE_CONTENT = "content"
        val CIRCULAR_TEACHER_PHOTO="teacher_photo"
        val CIRCULAR_TABLE_DATE = "date"
        val CIRCULAR_TABLE_ATTACHMENT = "attachment"
        val CIRCULAR_TABLE_TITLE = "title"
        val CIRCULAR_TABLE_STANDARD_ID = "standard_id"
        val CIRCULAR_TABLE_CREATE_SCRIPT = ("create table if not exists "
                + CIRCULAR_TABLE + " ( " + CIRCULAR_TABLE_AUTO_ID + " integer primary key autoincrement, "
                + CIRCULAR_TABLE_ID + " Text , " + CIRCULAR_TABLE_CONTENT + " Text , "
                + CIRCULAR_TABLE_ATTACHMENT + " Text, " + CIRCULAR_TABLE_TITLE + " Text, "
                + CIRCULAR_TABLE_STANDARD_ID + " Text, " + CIRCULAR_TEACHER_PHOTO + " Text, "+ CIRCULAR_TABLE_DATE + " Text)")

        //CLASS TIMETABLE CONSTANTS
        val CLASS_TIMETABLE_TABLE = "tbl_timetable"
        private val CLASS_TIMETABLE_TABLE_ID_AUTO = "ID"
        val CLASS_TIMETABLE_TABLE_CLASS_ID = "class_id"
        val CLASS_TIMETABLE_TABLE_DIVISION_ID = "division_id"
        val CLASS_TIMETABLE_TIME = "class_timetable_time"
        val CLASS_TIMETABLE_MON = "class_timetable_mon"
        val CLASS_TIMETABLE_TUE = "class_timetable_tue"
        val CLASS_TIMETABLE_WED = "class_timetable_wen"
        val CLASS_TIMETABLE_THU = "class_timetable_thu"
        val CLASS_TIMETABLE_FRI = "class_timetable_fri"
        val CLASS_TIMETABLE_SAT = "class_timetable_sat"
        val CLASS_TIMETABLE_TABLE_CREATE_SCRIPT = ("create table if not exists "
                + CLASS_TIMETABLE_TABLE + " ( " + CLASS_TIMETABLE_TABLE_ID_AUTO + " integer primary key autoincrement, "
                + CLASS_TIMETABLE_TABLE_CLASS_ID + " Text, " + CLASS_TIMETABLE_TABLE_DIVISION_ID + " Text, "
                + CLASS_TIMETABLE_TIME + " Text, " + CLASS_TIMETABLE_MON + " Text, "
                + CLASS_TIMETABLE_TUE + " Text, " + CLASS_TIMETABLE_WED + " Text, "
                + CLASS_TIMETABLE_THU + " Text, " + CLASS_TIMETABLE_FRI + " Text, "
                + CLASS_TIMETABLE_SAT + " Text);")

        //EXAM TIMETABLE CONSTANTS
        val EXAM_TIME_TABLE = "tbl_examtimetable"
        val EXAM_TIME_TABLE_ID = "ett_id"
        private val EXAM_TIME_TABLE_ID_AUTO = "ID"
        val EXAM_TIME_TABLE_EXAM_NAME = "exam_name"
        val EXAM_TIME_TABLE_STANDARD = "standard"
        val EXAM_TIME_TABLE_EXAM_DETAILS = "exam_details"
        val EXAM_TIME_TABLE_CREATE_SCRIPT = ("create table if not exists "
                + EXAM_TIME_TABLE + " ( " + EXAM_TIME_TABLE_ID_AUTO + " integer primary key autoincrement, " + EXAM_TIME_TABLE_ID + " Text, " + EXAM_TIME_TABLE_EXAM_NAME + " Text, "
                + EXAM_TIME_TABLE_STANDARD + " Text, " + EXAM_TIME_TABLE_EXAM_DETAILS
                + " Text )")

    }

}