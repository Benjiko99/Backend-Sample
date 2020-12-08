package adapters

import com.google.gson.*
import java.lang.reflect.Type
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class ZonedDateTimeAdapter : JsonSerializer<ZonedDateTime>, JsonDeserializer<ZonedDateTime> {

    override fun serialize(value: ZonedDateTime?, type: Type, context: JsonSerializationContext): JsonElement {
        return context.serialize(value?.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))
    }

    override fun deserialize(json: JsonElement?, type: Type, context: JsonDeserializationContext): ZonedDateTime {
        return ZonedDateTime.parse(json?.asString)
    }

}