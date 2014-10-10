package net.arya.intern

import java.lang.ref.WeakReference
import java.util.WeakHashMap
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by arya on 7/25/14.
 */
object Intern {
  private val map = new ConcurrentHashMap[Manifest[_],Intern[Any]]()

  def apply[A](a: A)(implicit m: Manifest[A]): A = {
    map.putIfAbsent(m, new Intern[Any])
    map.get(m).asInstanceOf[Intern[A]](a)
  }

  import collection.JavaConversions._

  /** returns the population of the intern cache, by type */
  def info = map.mapValues(_.size)
}


class Intern[A] {
  private val map = new WeakHashMap[A,WeakReference[A]]
  def size = map.size

  def apply(a: A): A = {
    if (map.containsKey(a)) map.get(a).get()
    else { map.put(a, new WeakReference[A](a)); a }
  }
}

trait InternDeep[A] {
  def internDeep(a: A): A
}

object InternDeep {

  implicit def list[A:Manifest] = new InternDeep[List[A]] {
    def internDeep(l: List[A]): List[A] =
      l.foldRight(List.empty[A]) {
        case (e, l) => Intern(e :: l)
      }
  }

}

