package intellinectsschool.intellinects.com.v4flavors.Models

/**
 * Created by Indrajeet Vadgama on 01-04-2017
 */

class Event : Comparable<Event> {

    var id: Int = 0
    internal var postDate: Long = 0
    val postContent: String? = null
    val postTitle: String? = null
    val postStatus: String? = null
    internal var postType: String? = null
    val eventTimeline: EventTimeline? = null
    var leftColor: Int = 0
    var rightColor: Int = 0
    var icon: Int = 0

    override fun compareTo(other: Event): Int {
        if (this.eventTimeline!!.startDate < other.eventTimeline!!.startDate) {
            return -1
        } else if (this.eventTimeline.startDate > other.eventTimeline.startDate) {
            return +1
        }
        return 0
    }
}