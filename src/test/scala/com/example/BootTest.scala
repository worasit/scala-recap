package com.example

import org.scalatest.FunSuite

class BootTest extends FunSuite {
  test("Hello should start with H") {
    assert("hello".startsWith("h"))
  }
}
