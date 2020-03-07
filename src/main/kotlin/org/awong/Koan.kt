package org.awong


data class Person(val name: String, val age: Int)
data class Client (val personalInfo: PersonalInfo?)
data class PersonalInfo (val email: String?)

interface Mailer {
    fun sendMessage(email: String, message: String)
}

class Koan {
    fun joinOptions(options: Collection<String>) = options.joinToString(
            prefix = "[",
            postfix = "]")

    fun foo(name: String, number: Int = 42, toUpperCase: Boolean = false) =
            (if (toUpperCase) name.toUpperCase() else name) + number

    fun useFoo() = listOf(
            foo("a"),
            foo("b", number = 1),
            foo("c", toUpperCase = true),
            foo(name = "d", number = 2, toUpperCase = true)
    )

    fun containsEven(collection: Collection<Int>): Boolean = collection.any {
        each: Int -> each % 2 == 0
    }

    val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"

    fun getPattern(): String = """\d{2} ${month} \d{4}"""

    fun getPeople(): List<Person> {
        return listOf(Person("Alice", 29), Person("Bob", 31))
    }

    fun sendMessageToClient(client: Client?, message: String?, mailer: Mailer) {
        val personalInfo: PersonalInfo? = client?.personalInfo
        val email: String? = personalInfo?.email
        if (email != null && message != null) {
            mailer.sendMessage(email, message)
        }
    }
}