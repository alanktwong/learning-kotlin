package org.awong

import org.assertj.core.api.Assertions.assertThat
import org.awong.koans.allOrderedProducts
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertFailsWith

object MyTest : Spek({
    println("this is the root")
    group("some group") {
        println("some group")
        test("some test") {
            println("some test")
        }
    }

    group("another group") {
        println("another group")
        test("another test") {
            println("another test")
        }
    }
})

/**
 * Specification Style
 */
object ShopSpec : Spek({
    describe("A calculator") {
        val shop by memoized { org.awong.koans.shop }

        describe("addition") {
            it("returns the sum of its arguments") {
                assertThat(shop.allOrderedProducts.size).isEqualTo(7)
            }
        }
    }
})

/**
 * Gherkin Style
 */
object SetFeature : Spek({
    Feature("Set") {
        val set by memoized { mutableSetOf<String>() }

        Scenario("adding items") {
            When("adding foo") {
                set.add("foo")
            }

            Then("it should have a size of 1") {
                assertThat(set.size).isEqualTo(1)
            }

            Then("it should contain foo") {
                assertThat(set).contains("foo")
            }
        }

        Scenario("empty") {
            Then("should have a size of 0") {
                assertThat(set).isEmpty()
            }

            Then("should throw when first is invoked") {
                assertFailsWith(NoSuchElementException::class) {
                    set.first()
                }
            }
        }

        Scenario("getting the first item") {
            val item = "foo"
            Given("a non-empty set") {
                set.add(item)
            }

            lateinit var result: String

            When("getting the first item") {
                result = set.first()
            }

            Then("it should return the first item") {
                assertThat(item).isEqualTo(result)
            }
        }
    }
})