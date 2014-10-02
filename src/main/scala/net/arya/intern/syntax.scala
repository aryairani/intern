package net.arya.intern

/**
 * Created by arya on 7/26/14.
 */
object syntax {
  implicit class InterningSyntax[A:Manifest](a: A) {
    def intern = Intern(a)
    def internDeep(implicit d: InternDeep[A]) = d.internDeep(a)
  }
}
