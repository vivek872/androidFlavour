package intellinectsschool.intellinects.com.v4flavors.Other

import android.content.Context
import android.graphics.PorterDuff
import android.support.v4.content.res.ResourcesCompat

import intellinectsschool.intellinects.com.v4flavors.R


/**
 * Created by Indrajeet  on 25-05-2017
 */

class DrawablePainter(private val context: Context) {


    /* Following method colors ui icons used throughout the app, R.color.colorPrimaryDark is used to set colors
       for all icons.*/

    fun paintDrawables() {

        //OTHER ICONS
        val drawable = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_not_available, null)!!
        drawable.setColorFilter(ResourcesCompat.getColor(context.resources, R.color.colorPrimaryDark, null), PorterDuff.Mode.MULTIPLY)
        Constants.NDA_IMAGE = drawable

        val drawable1 = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_clock, null)!!
        drawable1.setColorFilter(ResourcesCompat.getColor(context.resources, R.color.colorPrimaryDark, null), PorterDuff.Mode.MULTIPLY)
        Constants.EVENTS_CLOCK = drawable1

        val drawable2 = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_mobile, null)!!
        drawable2.setColorFilter(ResourcesCompat.getColor(context.resources, R.color.colorPrimaryDark, null), PorterDuff.Mode.MULTIPLY)
        Constants.MOBILE_ICON = drawable2

        val drawable3 = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_user, null)!!
        drawable3.setColorFilter(ResourcesCompat.getColor(context.resources, R.color.colorPrimaryDark, null), PorterDuff.Mode.MULTIPLY)
        Constants.USER_ICON = drawable3

        val drawable4 = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_password, null)!!
        drawable4.setColorFilter(ResourcesCompat.getColor(context.resources, R.color.colorPrimaryDark, null), PorterDuff.Mode.MULTIPLY)
        Constants.PASSWORD_ICON = drawable4

        val drawable5 = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_cog, null)!!
        drawable5.setColorFilter(ResourcesCompat.getColor(context.resources, R.color.colorPrimaryDark, null), PorterDuff.Mode.MULTIPLY)
        Constants.COG_ICON = drawable5

        val drawable6 = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_attachments_color, null)!!
        drawable6.setColorFilter(ResourcesCompat.getColor(context.resources, R.color.colorPrimaryDark, null), PorterDuff.Mode.MULTIPLY)
        Constants.ATTACHMENT_ICON = drawable6

        val drawable7 = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_download, null)!!
        drawable7.setColorFilter(ResourcesCompat.getColor(context.resources, R.color.colorPrimaryDark, null), PorterDuff.Mode.MULTIPLY)
        Constants.DOWNLOAD_ICON = drawable7

        val drawable8 = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_calender, null)!!
        drawable8.setColorFilter(ResourcesCompat.getColor(context.resources, R.color.colorPrimaryDark, null), PorterDuff.Mode.MULTIPLY)
        Constants.CALENDER_ICON = drawable8

        val drawable9 = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_description, null)!!
        drawable9.setColorFilter(ResourcesCompat.getColor(context.resources, R.color.colorPrimaryDark, null), PorterDuff.Mode.MULTIPLY)
        Constants.DESCRIPTION_ICON = drawable9

        val drawable10 = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_subject, null)!!
        drawable10.setColorFilter(ResourcesCompat.getColor(context.resources, R.color.colorPrimaryDark, null), PorterDuff.Mode.MULTIPLY)
        Constants.SUBJECT_ICON = drawable10

        val drawable11 = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_back_arrow_color, null)!!
        drawable11.setColorFilter(ResourcesCompat.getColor(context.resources, R.color.colorPrimaryDark, null), PorterDuff.Mode.MULTIPLY)
        Constants.BACK_ARROW = drawable11

        val drawable12 = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_logout, null)!!
        drawable12.setColorFilter(ResourcesCompat.getColor(context.resources, R.color.colorPrimaryDark, null), PorterDuff.Mode.MULTIPLY)
        Constants.LOGOUT_BUTTON = drawable12

        val drawable13 = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_chevron_right, null)!!
        drawable13.setColorFilter(ResourcesCompat.getColor(context.resources, R.color.colorPrimaryDark, null), PorterDuff.Mode.MULTIPLY)
        Constants.RIGHT_ARROW = drawable13

        val drawable14 = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_phone, null)!!
        drawable14.setColorFilter(ResourcesCompat.getColor(context.resources, R.color.colorPrimaryDark, null), PorterDuff.Mode.MULTIPLY)
        Constants.PHONE_ICON = drawable14

        val drawable15 = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_venue, null)!!
        drawable15.setColorFilter(ResourcesCompat.getColor(context.resources, R.color.colorPrimaryDark, null), PorterDuff.Mode.MULTIPLY)
        Constants.VENUE_ICON = drawable15
    }
}
