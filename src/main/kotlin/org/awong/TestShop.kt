package org.awong

// products
val idea = Product("IntelliJ IDEA Ultimate", 199.0)

val reSharper = Product("ReSharper", 149.0)
val dotTrace = Product("DotTrace", 159.0)
val dotMemory = Product("DotMemory", 129.0)
val dotCover = Product("DotCover", 99.0)

val appCode = Product("AppCode", 99.0)
val phpStorm = Product("PhpStorm", 99.0)
val pyCharm = Product("PyCharm", 99.0)
val rubyMine = Product("RubyMine", 99.0)
val webStorm = Product("WebStorm", 49.0)

val teamCity = Product("TeamCity", 299.0)
val youTrack = Product("YouTrack", 500.0)

// customers
val lucas = "Lucas"
val cooper = "Cooper"
val nathan = "Nathan"
val reka = "Reka"
val bajram = "Bajram"
val asuka = "Asuka"
val riku = "Riku"

// cities
val canberra = City("Canberra")
val vancouver = City("Vancouver")
val budapest = City("Budapest")
val ankara = City("Ankara")
val tokyo = City("Tokyo")

// functions
fun customer(name: String, city: City, vararg orders: Order) = Customer(name, city, orders.toList())
fun order(vararg products: Product, isDelivered: Boolean = true) = Order(products.toList(), isDelivered)
fun shop(name: String, vararg customers: Customer) = Shop(name, customers.toList())

val shop = shop("jb test shop",
        customer(lucas, canberra,
                order(reSharper),
                order(reSharper, dotMemory, dotTrace)),
        customer(cooper, canberra),
        customer(nathan, vancouver,
                order(rubyMine, webStorm)),
        customer(reka, budapest,
                order(idea, isDelivered = false),
                order(idea, isDelivered = false),
                order(idea)),
        customer(bajram, ankara,
                order(reSharper)),
        customer(asuka, tokyo,
                order(idea)),
        customer(riku, tokyo,
                order(phpStorm, phpStorm),
                order(phpStorm))
        )

val customers: Map<String, Customer> = shop.customers.fold(hashMapOf<String,Customer>(), { map, customer ->
    map[customer.name] = customer
    map
})

val setOfOrderedProducts = setOf(idea, reSharper, dotTrace, dotMemory, phpStorm, rubyMine, webStorm)

val sortedCustomers = listOf(cooper, nathan, bajram, asuka, lucas, riku, reka)

val groupedCities = mapOf(
        canberra to listOf(lucas,cooper),
        vancouver to listOf(nathan),
        budapest to listOf(reka),
        tokyo to listOf(asuka, riku)
)