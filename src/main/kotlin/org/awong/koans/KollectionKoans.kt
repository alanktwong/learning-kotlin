package org.awong.koans

// https://play.kotlinlang.org/koans/Collections/Introduction/Task.kt

fun Shop.getSetOfCustomers(): Set<Customer> = customers.toSet()

// https://play.kotlinlang.org/koans/Collections/Filter%20map/Task.kt

// Return the set of cities the customers are from
fun Shop.getCitiesCustomersAreFrom(): Set<City> = getSetOfCustomers().map { customer -> customer.city }.toSet()

// Return a list of the customers who live in the given city
val isInCity: (Customer, City) -> Boolean = { customer, city -> city == customer.city }

fun Shop.getCustomersFrom(city: City): List<Customer> = getSetOfCustomers()
        .filter { customer -> isInCity(customer, city) }

// https://play.kotlinlang.org/koans/Collections/All%20Any%20and%20other%20predicates/Task.kt

// Return true if all customers are from the given city
fun Shop.checkAllCustomersAreFrom(city: City): Boolean = getSetOfCustomers()
        .all { customer -> isInCity(customer, city) }

// Return true if there is at least one customer from the given city
fun Shop.hasCustomerFrom(city: City): Boolean = getSetOfCustomers()
        .any { customer -> isInCity(customer, city) }

// Return the number of customers from the given city
fun Shop.countCustomersFrom(city: City): Int = getSetOfCustomers()
        .count { customer -> isInCity(customer, city) }

// Return a customer who lives in the given city, or null if there is none
fun Shop.findAnyCustomerFrom(city: City): Customer? = getSetOfCustomers()
        .firstOrNull { customer -> isInCity(customer, city) }

// https://play.kotlinlang.org/koans/Collections/FlatMap/Task.kt

// Return all products this customer has ordered
val Customer.orderedProducts: Set<Product> get() {
    return orders.flatMap { order -> order.products }.toSet()
}

// Return all products that were ordered by at least one customer
val Shop.allOrderedProducts: Set<Product> get() {
    return customers.flatMap { customer -> customer.orderedProducts }.toSet()
}

// https://play.kotlinlang.org/koans/Collections/Max%20min/Task.kt

// Return a customer whose order count is the highest among all customers
fun Shop.getCustomerWithMaximumNumberOfOrders(): Customer? = customers.maxBy { customer -> customer.orders.size }

// Return the most expensive product which has been ordered
fun Customer.getMostExpensiveOrderedProduct(): Product? = orders.flatMap { order -> order.products }
        .maxBy { product -> product.price }

// https://play.kotlinlang.org/koans/Collections/Sort/Task.kt

// Return a list of customers, sorted by the ascending number of orders they made
fun Shop.getCustomersSortedByNumberOfOrders(): List<Customer> = customers.sortedBy { customer -> customer.orders.size }

// https://play.kotlinlang.org/koans/Collections/Sum/Task.kt

// Return the sum of prices of all products that a customer has ordered.
// Note: the customer may order the same product for several times.
fun Customer.getTotalOrderPrice(): Double = orders.flatMap { order -> order.products }
        .sumByDouble { product -> product.price }

// https://play.kotlinlang.org/koans/Collections/GroupBy/Task.kt

// Return a map of the customers living in each city
fun Shop.groupCustomersByCity(): Map<City, List<Customer>> = customers.groupBy( { customer -> customer.city })

// https://play.kotlinlang.org/koans/Collections/Partition/Task.kt

// Return customers who have more undelivered orders than delivered
fun Customer.getNumberOfUndeliveredOrders(): Int = orders.count { !it.isDelivered }

fun Customer.getNumberOfDeliveredOrders(): Int = orders.count { it.isDelivered }

fun Shop.getCustomersWithMoreUndeliveredOrdersThanDelivered(): Set<Customer> = customers.partition { customer ->
    customer.getNumberOfUndeliveredOrders() > customer.getNumberOfDeliveredOrders()
}.first.toSet()

// https://play.kotlinlang.org/koans/Collections/Fold/Task.kt

// Return the set of products that were ordered by every customer
fun Shop.getSetOfProductsOrderedByEveryCustomer(): Set<Product> {
    val allProducts = customers.flatMap { it.orders.flatMap { it.products }}.toSet()
    return customers.fold(allProducts, {
        orderedByAll, customer ->
        orderedByAll.intersect(customer.orders.flatMap { it.products }.toSet())
    })
}

// https://play.kotlinlang.org/koans/Collections/Compound%20tasks/Task.kt

// Return the most expensive product among all delivered products
// (use the Order.isDelivered flag)
fun Customer.getMostExpensiveDeliveredProduct(): Product? {
    val deliveredProducts = orders.filter { it.isDelivered }.flatMap { it.products }
    return deliveredProducts.maxBy { it.price }
}

// Return how many times the given product was ordered.
// Note: a customer may order the same product for several times.
fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int = customers.flatMap { it.getOrderedProductsList() }.count { it == product }


fun Customer.getOrderedProductsList(): List<Product> {
    return orders.flatMap { it.products }
}
