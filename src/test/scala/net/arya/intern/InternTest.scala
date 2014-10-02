package net.arya.intern

/**
 * Created by arya on 7/25/14.
 */
class InternTest extends org.scalatest.FunSuite {

  import syntax._

  test("two interned objects should be identical") {
    val a = List(1,2,3).intern
    val b = (1 :: 2 :: 3 :: Nil).intern
    assert(a eq b)
  }

  test("internDeep interns Lists' insides") {
    val a = List(1,2,3,4,5).internDeep
    val b = List(99,2,3,4,5).internDeep
    assert(a.tail eq b.tail)
  }

}
