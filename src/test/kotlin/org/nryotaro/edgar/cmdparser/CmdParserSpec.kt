package org.nryotaro.edgar.cmdparser

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.api.dsl.*
//import kotlin.test.assertEquals
//import kotlin.test.todo

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
//@SpringBootTest
class DemoApplicationTests {

    @Test
    fun contextLoads() {
        println("foobar")
    }

}

/*
//@RunWith(JUnitPlatform::class)
class CmdParserSpec: Spek({
    describe("CmdParser") {

        on("takes only a date") {
            val d = CmdParser.parse("-d" , "2016-12-12", "-o", "~/")
            val sum = 2+4

            it("should return the result of adding the first number to the second number") {
                assertEquals(6, sum)
            }
        }
    }
})
*/