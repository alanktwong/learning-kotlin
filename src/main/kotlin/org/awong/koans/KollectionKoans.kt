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
fun Shop.getCustomerWithMaximumNumberOfOrders(): Customer? = TODO()

// Return the most expensive product which has been ordered
fun Customer.getMostExpensiveOrderedProduct(): Product? = TODO()