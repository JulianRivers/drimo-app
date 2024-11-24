import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import com.drimo_app.model.dreams.Dream

@RequiresApi(Build.VERSION_CODES.O)
fun formatDreamDate(dateString: String): String {
    val date = ZonedDateTime.parse(dateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("es"))
    val dayOfMonth = date.dayOfMonth
    return "$dayOfWeek $dayOfMonth"
}

@RequiresApi(Build.VERSION_CODES.O)
fun getMonthName(dateString: String): String {
    val date = ZonedDateTime.parse(dateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    return date.month.getDisplayName(TextStyle.FULL, Locale("es")).capitalize()
}

@RequiresApi(Build.VERSION_CODES.O)
fun groupDreamsByMonth(dreams: List<Dream>): Map<String, List<Dream>> {
    return dreams
        .groupBy { getMonthName(it.dateDream) }
        .mapValues { (_, dreamsInMonth) ->
            dreamsInMonth.sortedByDescending { dream ->
                ZonedDateTime.parse(dream.dateDream, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            }
        }
}
