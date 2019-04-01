package intellinectsschool.intellinects.com.v4flavors.Models

class EventTimeline (var id: Int = 0, var startDate: Long = 0, var endDate: Long = 0, var recurrenceDate: String? = null, var venue: String? = null,
    var contactName: String? = null, var contactPhone: String? = null, var isAllDay: Boolean = false)